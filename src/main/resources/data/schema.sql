create table if not exists public.category
(
    id        integer not null,
    name      varchar(255),
    parnet_id integer,
    primary key (id),
    constraint fkjuhi5c41octuei0syqcc1akv9
        foreign key (parnet_id) references public.category
);

create table if not exists public.person
(
    id   integer not null,
    name varchar(255),
    primary key (id)
);

create table if not exists public.product
(
    id         varchar(255) not null,
    image      text,
    rating     integer      not null,
    sales_rank integer      not null,
    title      varchar(255),
    primary key (id)
);

create table if not exists public.book
(
    binding     varchar(255),
    edition     varchar(255),
    height      integer      not null,
    isbn        varchar(255),
    length      integer      not null,
    page        integer      not null,
    publication date,
    weight      integer      not null,
    id          varchar(255) not null,
    primary key (id),
    constraint fk8cjf4cjanicu58p2l5t8d9xvu
        foreign key (id) references public.product
);

create table if not exists public.author
(
    book_id   varchar(255) not null,
    person_id integer      not null,
    constraint fkd8cm5fy8qunfotg4xpdi1wad7
        foreign key (person_id) references public.person,
    constraint fkqi5nll4mal57ohohlv5g0qlv2
        foreign key (book_id) references public.book
);

create table if not exists public.book_publisher
(
    book_id   varchar(255) not null,
    publisher varchar(255),
    constraint fk8ywuvxfycghsfmxvu363jllpq
        foreign key (book_id) references public.book
);

create table if not exists public.cd
(
    binding varchar(255),
    date    date,
    disc_nr integer      not null,
    format  varchar(255),
    id      varchar(255) not null,
    primary key (id),
    constraint fkg450nkyhi3a0t7kn2cosol0xf
        foreign key (id) references public.product
);

create table if not exists public.artist
(
    cd_id     varchar(255) not null,
    person_id integer      not null,
    constraint fk42u4bruy3o7qi1jlqvreu49ak
        foreign key (person_id) references public.person,
    constraint fk3xd8j8p11jo7rrcphikx4i0u
        foreign key (cd_id) references public.cd
);

create table if not exists public.cd_labels
(
    cd_id  varchar(255) not null,
    labels varchar(255),
    constraint fkjqlfqvxce9dygyvl1dkh8xe24
        foreign key (cd_id) references public.cd
);

create table if not exists public.cd_tracks
(
    cd_id  varchar(255) not null,
    tracks varchar(255),
    constraint fk66a5xwpfjq2uu32hndlfsl71m
        foreign key (cd_id) references public.cd
);

create table if not exists public.dvd
(
    aspectratio     varchar(255),
    format          varchar(255),
    regioncode      integer      not null,
    release_date    date,
    running_time    integer      not null,
    theater_release integer      not null,
    id              varchar(255) not null,
    primary key (id),
    constraint fk8767xtav39bmxqpras2ivshb9
        foreign key (id) references public.product
);

create table if not exists public.actor
(
    dvd_id    varchar(255) not null,
    person_id integer      not null,
    primary key (dvd_id, person_id),
    constraint fktk6djt6y6ni04hiton4o64s1f
        foreign key (person_id) references public.person,
    constraint fk865vtt4l7wh5qwua14meyqvkc
        foreign key (dvd_id) references public.dvd
);

create table if not exists public.creator
(
    dvd_id    varchar(255) not null,
    person_id integer      not null,
    primary key (dvd_id, person_id),
    constraint fklfiq9ovibww39lq6jflx3fhx9
        foreign key (person_id) references public.person,
    constraint fks0xroutb574db5dj4g7cdfiw9
        foreign key (dvd_id) references public.dvd
);

create table if not exists public.dirctor
(
    dvd_id    varchar(255) not null,
    person_id integer      not null,
    primary key (dvd_id, person_id),
    constraint fk2rn03idcmv63c8icldfkyq4i6
        foreign key (person_id) references public.person,
    constraint fkg3wnmxurjw9q99lxddcwmxmhp
        foreign key (dvd_id) references public.dvd
);

create table if not exists public.dvd_studio
(
    dvd_id varchar(255) not null,
    studio varchar(255),
    constraint fk1krgw1lu8fcuuo7mf1whobg1u
        foreign key (dvd_id) references public.dvd
);

create table if not exists public.product_category
(
    product_id  varchar(255) not null,
    category_id integer      not null,
    constraint fkkud35ls1d40wpjb5htpp14q4e
        foreign key (category_id) references public.category,
    constraint fk2k3smhbruedlcrvu6clued06x
        foreign key (product_id) references public.product
);

create table if not exists public.product_listmania
(
    product_id varchar(255) not null,
    listmania  varchar(255),
    constraint fkdjct2smg0s2pc44hmdletggva
        foreign key (product_id) references public.product
);

create table if not exists public.product_similar
(
    product_id varchar(255) not null,
    similar_id varchar(255) not null,
    constraint fk7f5hbigt1xka3n186q3hefghk
        foreign key (similar_id) references public.product,
    constraint fkqknn5a6cl56rrvn9dvlclx9av
        foreign key (product_id) references public.product
);

create table if not exists public.review
(
    id         integer not null,
    content    text,
    date       date,
    helpful    integer not null,
    rating     integer not null,
    summery    varchar(255),
    person     integer,
    product_id varchar(255),
    primary key (id),
    constraint fkdo60vxlownqykeceb1l8uqm2v
        foreign key (person) references public.person,
    constraint fkiyof1sindb9qiqr9o8npj8klt
        foreign key (product_id) references public.product
);

create table if not exists public.store
(
    id     integer not null,
    name   varchar(255),
    street varchar(255),
    zip    varchar(255),
    primary key (id)
);

create table if not exists public.offer
(
    id         integer          not null,
    price      double precision not null,
    status     varchar(255),
    product_id varchar(255),
    store_id   integer,
    primary key (id),
    constraint fk3cow2cmfxb0nrt43hxm7yu1q3
        foreign key (product_id) references public.product,
    constraint fk71eegvrtlg3qtybhd39ynsiqu
        foreign key (store_id) references public.store
);

create table if not exists public.product_store
(
    product_id varchar(255) not null,
    store_id   integer      not null,
    constraint fk6ofo19q4q8aqicf2tifxv91et
        foreign key (store_id) references public.store,
    constraint fk5ixdrvrn9b8v6gwrws3s3brsf
        foreign key (product_id) references public.product
);


