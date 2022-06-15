1. Wieviele Produkte jeden Typs (Buch, Musik-CD, DVD) sind in der Datenbank erfasst? Hinweis: Geben Sie das Ergebnis in einer 3-spaltigen Relation aus.
```sql
SELECT (select count(*)
   FROM cd) as CD_Number,
   (select count(*)
   from book) as Book_Number,
   (select count(*)
   from dvd) as DVD_Number
```
2. Nennen Sie die 5 besten Produkte jedes Typs (Buch, Musik-CD, DVD) sortiert nach dem durchschnittlichem Rating. Hinweis: Geben Sie das Ergebnis in einer einzigen Relation mit den Attributen Typ, ProduktNr, Rating aus.
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

````
4. SQL Trigger
```sql
CREATE OR REPLACE FUNCTION averageUpdate()
    RETURNS TRIGGER
AS '
    BEGIN
        UPDATE product p
        SET rating = (
            SELECT AVG(r.rating)
            FROM review r
            WHERE p.id = r.product_id
        )
        WHERE p.id = new.product_id;
        return new;
    END;
' language plpgsql;

DROP TRIGGER IF EXISTS averageUpdateTrigger on review;
CREATE TRIGGER averageUpdateTrigger
    AFTER INSERT
    ON review
    FOR EACH ROW
EXECUTE PROCEDURE averageUpdate();
```