1. Wieviele Produkte jeden Typs (Buch, Musik-CD, DVD) sind in der Datenbank erfasst? Hinweis: Geben Sie das Ergebnis in
   einer 3-spaltigen Relation aus.

```sql
SELECT (select count(*)
        FROM cd)   as CD_Number,
       (select count(*)
        from book) as Book_Number,
       (select count(*)
        from dvd)  as DVD_Number
```

2. Nennen Sie die 5 besten Produkte jedes Typs (Buch, Musik-CD, DVD) sortiert nach dem durchschnittlichem Rating.
   Hinweis: Geben Sie das Ergebnis in einer einzigen Relation mit den Attributen Typ, ProduktNr, Rating aus.

* Buch

```sql
select 'Book' as typ, x.rating, x.id
from (SELECT b.id, rating
      FROM (SELECT p.id, p.rating
            FROM product p) ratings
               RIGHT JOIN book b ON b.id = ratings.id
      ORDER BY rating DESC
      LIMIT 5) as x
```

* DVD

```sql
select 'DVD' as typ, x.rating, x.id
from (SELECT b.id, rating
      FROM (SELECT p.id, p.rating
            FROM product p) ratings
               RIGHT JOIN dvd b ON b.id = ratings.id
      ORDER BY rating DESC
      LIMIT 5) as x
```

* CD

```sql
select 'CD' as typ, x.rating, x.id
from (SELECT b.id, rating
      FROM (SELECT p.id, p.rating
            FROM product p) ratings
               RIGHT JOIN cd b ON b.id = ratings.id
      ORDER BY rating DESC
      LIMIT 5) as x
```

3. Für welche Produkte gibt es im Moment kein Angebot?

````sql
select id
from product
except
SELECT DISTINCT p.product_id
FROM offer p
         LEFT JOIN product_store ps
                   ON p.product_id = ps.product_id
````

4. Für welche Produkte ist das teuerste Angebot mehr als doppelt so teuer wie das preiswerteste?

````sql
SELECT t1.product_id, t1.price as price1, t2.price as price2
FROM offer t1,
     offer t2
WHERE t1.product_id = t2.product_id
  AND t1.store_id != t2.store_id
  AND (
    t1.price > 2 * t2.price
    )
ORDER BY 2 desc
````

5. Welche Produkte haben sowohl mindestens eine sehr schlechte (Punktzahl: 1) als auch mindestens eine sehr gute (
   Punktzahl: 5) Bewertung?

````sql
select distinct t1.product_id
from (select t1.product_id, t1.rating
      From review as t1
      Where t1.rating = 5) t1
         inner join (select t2.product_id, t2.rating
                     From review as t2
                     where t2.rating = 1) t2
                    ON t1.product_id = t2.product_id
````

6. Für wieviele Produkte gibt es gar keine Rezension?

````sql
SELECT COUNT(*)
FROM product p
         LEFT JOIN review r
                   ON p.id = r.product_id
WHERE r is null
````

7. Nennen Sie alle Rezensenten, die mindestens 10 Rezensionen geschrieben haben.

````sql
select p.name
from (select count(*) as number_review, t1.person
      from review as t1
      group by t1.person) t1
         right join person p on p.id = t1.person
where number_review >= 10
````

8. Geben Sie eine duplikatfreie und alphabetisch sortierte Liste der Namen aller Buchautoren an, die auch an DVDs oder
   Musik-CDs beteiligt sind.

````sql
select x.name
from person x
         join(select b.person_id
              from author b
                       join actor b2 on b.person_id = b2.person_id
              union
              select b.person_id
              from author b
                       join dirctor b2 on b.person_id = b2.person_id
              union
              select b.person_id
              from author b
                       join creator b2 on b.person_id = b2.person_id
              union
              select b.person_id
              from author b
                       join artist b2 on b.person_id = b2.person_id
              group by b.person_id) as ir on x.id = ir.person_id
order by x.name asc
````

9. Wie hoch ist die durchschnittliche Anzahl von Liedern einer Musik-CD?

````sql
SELECT ROUND(avg(t1.CountRes))
FROM (SELECT COUNT(*) AS CountRes
      FROM cd_tracks as t1
      GROUP BY t1.cd_id) as t1
````

10. Für welche Produkte gibt es ähnliche Produkte in einer anderen Hauptkategorie? Hinweis: Eine Hauptkategorie ist eine
    Produktkategorie ohne Oberkategorie. Erstellen Sie eine rekursive Anfrage, die zu jedem Produkt dessen
    Hauptkategorie bestimmt.

````sql
CREATE OR REPLACE FUNCTION getParent(child_id integer) RETURNS integer AS
$$
BEGIN
    RETURN (SELECT parnet_id FROM category cat WHERE cat.id = child_id);
END;
$$
    LANGUAGE 'plpgsql';

-- getMainCategory(int)
CREATE OR REPLACE FUNCTION getMainCategory(category_id integer) RETURNS integer AS
$$
BEGIN
    IF category_id = 0 THEN
        RETURN 0;
    ELSEIF category_id IN (SELECT id FROM category WHERE parnet_id IS NULL) THEN
        RETURN category_id;
    ELSEIF category_id is not null THEN
        RETURN getMainCategory(getParent(category_id));
    END IF;
    RETURN -1;
END
$$
    LANGUAGE 'plpgsql';

-- getCategory(String)
CREATE OR REPLACE FUNCTION getCategory(similar_id varchar(255)) RETURNS integer AS
$$
BEGIN
    RETURN (SELECT category_id FROM product_category WHERE product_id = similar_id LIMIT 1);
END
$$
    LANGUAGE 'plpgsql';
-- Die eigentliche Query:
SELECT DISTINCT product
FROM (SELECT cp.product_id                               product,
             getMainCategory(cp.category_id)             mc1,
             *,
             getMainCategory(getCategory(ps.similar_id)) mc2
      FROM product_category cp
               natural JOIN
           product_similar ps) tab1
WHERE tab1.mc1 != tab1.mc2;
````

11. Welche Produkte werden in allen Filialen angeboten? Hinweis: Ihre Query muss so formuliert werden, dass sie für eine
    beliebige Anzahl von Filialen funktioniert. Hinweis: Beachten Sie, dass ein Produkt mehrfach von einer Filiale
    angeboten werden kann (z.B. neu und gebraucht).

````sql
select t4.product_id
from (select count(count), count.product_id
      from (select t1.product_id, t1.store_id
            from offer as t1
            where price != 0
            group by t1.product_id, t1.store_id) as count

      group by count.product_id) as t4

where t4.count = (select count(*)
                  from store)
````

12. In wieviel Prozent der Fälle der Frage 11 gibt es in Leipzig das preiswerteste Angebot?

````sql
SELECT 100 *
       (SELECT COUNT(*)
        FROM (SELECT leipziger.product_id
              FROM (SELECT tab1.product_id, ps.price, store.name
                    FROM (select t4.product_id, t4.count
                          from (select count(count), count.product_id
                                from (select t1.product_id, t1.store_id
                                      from offer as t1
                                      where price != 0
                                      group by t1.product_id, t1.store_id) as count
                                group by count.product_id) as t4
                          where t4.count = (select count(*) from store)) tab1
                             LEFT JOIN offer ps ON tab1.product_id = ps.product_id
                             LEFT JOIN store store ON ps.store_id = store.id
                    WHERE name = 'Leipzig') leipziger
                       LEFT JOIN
                   (SELECT tab1.product_id, ps.price, store.name
                    FROM (select t4.product_id, t4.count
                          from (select count(count), count.product_id
                                from (select t1.product_id, t1.store_id
                                      from offer as t1
                                      where price != 0
                                      group by t1.product_id, t1.store_id) as count
                                group by count.product_id) as t4
                          where t4.count = (select count(*) from store)) tab1
                             LEFT JOIN offer ps ON tab1.product_id = ps.product_id
                             LEFT JOIN store store ON ps.store_id = store.id
                    WHERE store.name != 'Leipzig') andere
                   ON leipziger.product_id = andere.product_id
              WHERE leipziger.price < andere.price
              ORDER BY 1) count1)::float
           /
       (SELECT COUNT(*)
        FROM ((select t4.product_id, t4.count
               from (select count(count), count.product_id
                     from (select t1.product_id, t1.store_id
                           from offer as t1
                           where price != 0
                           group by t1.product_id, t1.store_id) as count
                     group by count.product_id) as t4
               where t4.count = (select count(*) from store))) alle)::float count2
````