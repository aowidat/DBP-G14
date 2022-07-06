CREATE OR REPLACE FUNCTION public.averageUpdate()
    RETURNS TRIGGER
AS
'
    BEGIN
        UPDATE public.product p
        SET rating = (SELECT AVG(r.rating)
                      FROM public.review r
                      WHERE p.id = r.product_id)
        WHERE p.id = new.product_id;
        return new;
    END;
' language plpgsql;

DROP TRIGGER IF EXISTS averageUpdateTrigger on public.review;
CREATE TRIGGER averageUpdateTriggerOnInsert
    AFTER INSERT
    ON public.review
    FOR EACH ROW
EXECUTE PROCEDURE public.averageUpdate();

CREATE index parent_id on category (parnet_id);
create index prodduct_id on product_category (product_id);
create index category_id on category (id);