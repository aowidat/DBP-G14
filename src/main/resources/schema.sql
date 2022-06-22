drop schema if exists public cascade;
create schema if not exists public;
create sequence public.hibernate_sequence;
alter sequence public.hibernate_sequence owner to postgres;

create table if not exists public.category
(
    id        integer      not null
        constraint category_pkey
            primary key,
    name      varchar(255) not null,
    parnet_id integer
        constraint fkjuhi5c41octuei0syqcc1akv9
            references public.category on delete cascade on update CASCADE
);

alter table public.category
    owner to postgres;

create table if not exists public.person
(
    id              integer      not null
        constraint person_pkey
            primary key,
    street          varchar(255),
    birthdate       date,
    building_number integer check ( building_number > 0 ),
    city            varchar(255),
    email           varchar(255),
    phone           varchar(255),
    zip             varchar(255),
    name            varchar(255) not null
);

alter table public.person
    owner to postgres;

create table if not exists public.orders
(
    id        integer          not null
        constraint orders_pkey
            primary key,
    date      date,
    total     double precision not null check ( total > 0 ),
    person_id integer
        constraint fk1b0m4muwx1t377w9if3w6wwqn
            references public.person on delete set null on update cascade
);

alter table public.orders
    owner to postgres;

create table if not exists public.product
(
    id         varchar(255)     not null
        constraint product_pkey
            primary key,
    image      text,
    rating     double precision not null,
    sales_rank integer          not null,
    title      varchar(255)     not null
);

alter table public.product
    owner to postgres;

create table if not exists public.book
(
    binding     varchar(255),
    edition     varchar(255),
    height      integer,
    isbn        varchar(255),
    length      integer,
    page        integer,
    publication date,
    weight      integer,
    id          varchar(255) not null
        constraint book_pkey
            primary key
        constraint fk8cjf4cjanicu58p2l5t8d9xvu
            references public.product on delete cascade on update cascade
);

alter table public.book
    owner to postgres;

create table if not exists public.author
(
    book_id   varchar(255) not null
        constraint fkqi5nll4mal57ohohlv5g0qlv2
            references public.book on delete cascade on update cascade,
    person_id integer      not null
        constraint fkd8cm5fy8qunfotg4xpdi1wad7
            references public.person on delete cascade on update cascade
);

alter table public.author
    add constraint unique_author unique (person_id, book_id),
    owner to postgres;

create table if not exists public.book_publisher
(
    book_id   varchar(255) not null
        constraint fk8ywuvxfycghsfmxvu363jllpq
            references public.book on delete cascade on update cascade,
    publisher varchar(255)
);

alter table public.book_publisher
    owner to postgres;

create table if not exists public.cd
(
    binding varchar(255),
    date    date,
    disc_nr integer,
    format  varchar(255),
    id      varchar(255) not null
        constraint cd_pkey
            primary key
        constraint fkg450nkyhi3a0t7kn2cosol0xf
            references public.product on delete cascade on update cascade
);

alter table public.cd
    owner to postgres;

create table if not exists public.artist
(
    cd_id     varchar(255) not null
        constraint fk3xd8j8p11jo7rrcphikx4i0u
            references public.cd on delete cascade on update cascade,
    person_id integer      not null
        constraint fk42u4bruy3o7qi1jlqvreu49ak
            references public.person on delete cascade on update cascade
);

alter table public.artist
    add constraint unique_atrist unique (person_id, cd_id),
    owner to postgres;

create table if not exists public.cd_labels
(
    cd_id  varchar(255) not null
        constraint fkjqlfqvxce9dygyvl1dkh8xe24
            references public.cd on delete cascade on update cascade,
    labels varchar(255)
);

alter table public.cd_labels
    owner to postgres;

create table if not exists public.cd_tracks
(
    cd_id  varchar(255) not null
        constraint fk66a5xwpfjq2uu32hndlfsl71m
            references public.cd on delete cascade on update cascade,
    tracks varchar(255)
);

alter table public.cd_tracks
    owner to postgres;

create table if not exists public.dvd
(
    aspectratio     varchar(255),
    format          varchar(255),
    regioncode      integer,
    release_date    date,
    running_time    integer,
    theater_release integer,
    id              varchar(255) not null
        constraint dvd_pkey
            primary key
        constraint fk8767xtav39bmxqpras2ivshb9
            references public.product on delete cascade on update cascade
);

alter table public.dvd
    owner to postgres;

create table if not exists public.actor
(
    dvd_id    varchar(255) not null
        constraint fk865vtt4l7wh5qwua14meyqvkc
            references public.dvd on delete cascade on update cascade,
    person_id integer      not null
        constraint fktk6djt6y6ni04hiton4o64s1f
            references public.person on delete cascade on update cascade,
    constraint actor_pkey
        primary key (dvd_id, person_id)
);

alter table public.actor
    add constraint unique_actor unique (person_id, dvd_id),
    owner to postgres;

create table if not exists public.creator
(
    dvd_id    varchar(255) not null
        constraint fks0xroutb574db5dj4g7cdfiw9
            references public.dvd on delete cascade on update cascade,
    person_id integer      not null
        constraint fklfiq9ovibww39lq6jflx3fhx9
            references public.person on delete cascade on update cascade,
    constraint creator_pkey
        primary key (dvd_id, person_id)
);

alter table public.creator
    add constraint unique_creator unique (person_id, dvd_id),
    owner to postgres;

create table if not exists public.dirctor
(
    dvd_id    varchar(255) not null
        constraint fkg3wnmxurjw9q99lxddcwmxmhp
            references public.dvd on delete cascade on update cascade,
    person_id integer      not null
        constraint fk2rn03idcmv63c8icldfkyq4i6
            references public.person on delete cascade on update cascade,
    constraint dirctor_pkey
        primary key (dvd_id, person_id)
);

alter table public.dirctor
    add constraint unique_director unique (person_id, dvd_id),
    owner to postgres;

create table if not exists public.dvd_studio
(
    dvd_id varchar(255) not null
        constraint fk1krgw1lu8fcuuo7mf1whobg1u
            references public.dvd on delete cascade on update cascade,
    studio varchar(255)
);

alter table public.dvd_studio
    owner to postgres;

create table if not exists public.product_category
(
    product_id  varchar(255) not null
        constraint fk2k3smhbruedlcrvu6clued06x
            references public.product on delete cascade on update cascade,
    category_id integer      not null
        constraint fkkud35ls1d40wpjb5htpp14q4e
            references public.category on delete cascade on update cascade
);

alter table public.product_category
    add constraint unique_cat_pro unique (product_id, category_id),
    owner to postgres;

create table if not exists public.product_listmania
(
    product_id varchar(255) not null
        constraint fkdjct2smg0s2pc44hmdletggva
            references public.product on delete cascade on update cascade,
    listmania  varchar(255)
);

alter table public.product_listmania
    owner to postgres;

create table if not exists public.product_order
(
    order_id   integer      not null
        constraint fkjwsik4uvq2sdqtb7x6h1o5f0v
            references public.orders on delete cascade on update cascade,
    product_id varchar(255) not null
        constraint fkh73acsd9s5wp6l0e55td6jr1m
            references public.product on delete cascade on update cascade
);

alter table public.product_order
    add constraint unique_order_pro unique (product_id, order_id),
    owner to postgres;

create table if not exists public.product_similar
(
    product_id varchar(255) not null
        constraint fkqknn5a6cl56rrvn9dvlclx9av
            references public.product on delete cascade on update cascade,
    similar_id varchar(255) not null
        constraint fk7f5hbigt1xka3n186q3hefghk
            references public.product on delete cascade on update cascade
);

alter table public.product_similar
    add constraint unique_sim_pro unique (product_id, similar_id),
    owner to postgres;

create table if not exists public.review
(
    id         integer not null
        constraint review_pkey
            primary key,
    content    text,
    date       date,
    helpful    integer not null CHECK ( helpful >= 0 ),
    rating     integer not null CHECK ( rating <= 6 AND rating > 0),
    summery    varchar(255),
    person     integer
        constraint fkdo60vxlownqykeceb1l8uqm2v
            references public.person on delete set null on update cascade,
    product_id varchar(255)
        constraint fkiyof1sindb9qiqr9o8npj8klt
            references public.product on delete cascade on update cascade
);

alter table public.review
    ADD CONSTRAINT unique_review unique (person, product_id),
    owner to postgres;

create table if not exists public.store
(
    id     integer      not null
        constraint store_pkey
            primary key,
    name   varchar(255) not null,
    street varchar(255) not null,
    zip    varchar(255) not null
);

alter table public.store
    add constraint UNIQUE_Store unique (street, zip),
    owner to postgres;

create table if not exists public.offer
(
    id         integer          not null
        constraint offer_pkey
            primary key,
    price      double precision not null,
    status     varchar(255),
    product_id varchar(255)
        constraint fk3cow2cmfxb0nrt43hxm7yu1q3
            references public.product on delete cascade on update cascade,
    store_id   integer
        constraint fk71eegvrtlg3qtybhd39ynsiqu
            references public.store on delete cascade on update cascade
);

alter table public.offer
    add CONSTRAINT unique_offer unique (product_id, store_id, status, price),
    owner to postgres;

create table if not exists public.product_store
(
    product_id varchar(255) not null
        constraint fk5ixdrvrn9b8v6gwrws3s3brsf
            references public.product on delete cascade on update cascade,
    store_id   integer      not null
        constraint fk6ofo19q4q8aqicf2tifxv91et
            references public.store on delete cascade on update cascade
);

alter table public.product_store
    add CONSTRAINT unique_pro_store unique (product_id, store_id),
    owner to postgres;

create table if not exists public.store_order
(
    order_id integer not null
        constraint fkes9e61iw01g19cd55wx50djn0
            references public.orders on delete cascade on update cascade,
    store_id integer not null
        constraint fkrejb4m86jrrpbmstii2m7te0q
            references public.store on delete cascade on update cascade
);

alter table public.store_order
    add CONSTRAINT unique_order_store unique (order_id, store_id),
    owner to postgres;

CREATE OR REPLACE FUNCTION averageUpdate()
    RETURNS TRIGGER
AS
'
    BEGIN
        UPDATE product p
        SET rating = (SELECT AVG(r.rating)
                      FROM review r
                      WHERE p.id = r.product_id)
        WHERE p.id = new.product_id;
        return new;
    END;
' language plpgsql;

DROP TRIGGER IF EXISTS averageUpdateTrigger on review;
CREATE TRIGGER averageUpdateTriggerOnInsert
    AFTER INSERT
    ON review
    FOR EACH ROW
EXECUTE PROCEDURE averageUpdate();

CREATE index parent_id on category (parnet_id);
create index prodduct_id on product_category (product_id);
create index category_id on category (id);