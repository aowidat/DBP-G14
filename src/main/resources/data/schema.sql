create table if not exists category
(
    id   integer not null
        primary key,
    name varchar(255)
);

create table if not exists parent_of_category
(
    parent_id integer not null
        constraint fkqnro3m6pqh3yilfiq13sjt3jn
            references category,
    child_id  integer not null
        constraint fkrgjbc28o6k2bhvpvsaqs4mfpm
            references category
);

create table if not exists person
(
    id   integer not null
        primary key,
    name varchar(255)
);

create table if not exists product
(
    id         varchar(255)     not null
        primary key,
    image      text,
    price      double precision not null,
    rating     integer          not null,
    sales_rank integer          not null,
    status     varchar(255),
    title      varchar(255)
);

create table if not exists book
(
    binding     varchar(255),
    edition     varchar(255),
    height      integer      not null,
    isbn        varchar(255),
    length      integer      not null,
    page        integer      not null,
    publication date,
    weight      integer      not null,
    id          varchar(255) not null
        primary key
        constraint fk8cjf4cjanicu58p2l5t8d9xvu
            references product
);

create table if not exists author
(
    book_id   varchar(255) not null
        constraint fkqi5nll4mal57ohohlv5g0qlv2
            references book,
    person_id integer      not null
        constraint fkd8cm5fy8qunfotg4xpdi1wad7
            references person
);

create table if not exists book_publisher
(
    book_id   varchar(255) not null
        constraint fk8ywuvxfycghsfmxvu363jllpq
            references book,
    publisher varchar(255)
);

create table if not exists cd
(
    binding varchar(255),
    date    date,
    disc_nr integer      not null,
    format  varchar(255),
    id      varchar(255) not null
        primary key
        constraint fkg450nkyhi3a0t7kn2cosol0xf
            references product
);

create table if not exists artist
(
    cd_id     varchar(255) not null
        constraint fk3xd8j8p11jo7rrcphikx4i0u
            references cd,
    person_id integer      not null
        constraint fk42u4bruy3o7qi1jlqvreu49ak
            references person
);

create table if not exists cd_labels
(
    cd_id  varchar(255) not null
        constraint fkjqlfqvxce9dygyvl1dkh8xe24
            references cd,
    labels varchar(255)
);

create table if not exists cd_tracks
(
    cd_id  varchar(255) not null
        constraint fk66a5xwpfjq2uu32hndlfsl71m
            references cd,
    tracks varchar(255)
);

create table if not exists dvd
(
    aspectratio     varchar(255),
    format          varchar(255),
    regioncode      integer      not null,
    release_date    date,
    running_time    integer      not null,
    theater_release integer      not null,
    id              varchar(255) not null
        primary key
        constraint fk8767xtav39bmxqpras2ivshb9
            references product
);

create table if not exists actor
(
    dvd_id    varchar(255) not null
        constraint fk865vtt4l7wh5qwua14meyqvkc
            references dvd,
    person_id integer      not null
        constraint fktk6djt6y6ni04hiton4o64s1f
            references person,
    primary key (dvd_id, person_id)
);

create table if not exists creator
(
    dvd_id    varchar(255) not null
        constraint fks0xroutb574db5dj4g7cdfiw9
            references dvd,
    person_id integer      not null
        constraint fklfiq9ovibww39lq6jflx3fhx9
            references person,
    primary key (dvd_id, person_id)
);

create table if not exists dirctor
(
    dvd_id    varchar(255) not null
        constraint fkg3wnmxurjw9q99lxddcwmxmhp
            references dvd,
    person_id integer      not null
        constraint fk2rn03idcmv63c8icldfkyq4i6
            references person,
    primary key (dvd_id, person_id)
);

create table if not exists dvd_studio
(
    dvd_id varchar(255) not null
        constraint fk1krgw1lu8fcuuo7mf1whobg1u
            references dvd,
    studio varchar(255)
);

create table if not exists product_category
(
    product_id  varchar(255) not null
        constraint fk2k3smhbruedlcrvu6clued06x
            references product,
    category_id integer      not null
        constraint fkkud35ls1d40wpjb5htpp14q4e
            references category
);

create table if not exists product_listmania
(
    product_id varchar(255) not null
        constraint fkdjct2smg0s2pc44hmdletggva
            references product,
    listmania  varchar(255)
);

create table if not exists product_similar
(
    product_id varchar(255) not null
        constraint fkqknn5a6cl56rrvn9dvlclx9av
            references product,
    similar_id varchar(255) not null
        constraint fk7f5hbigt1xka3n186q3hefghk
            references product
);

create table if not exists review
(
    id         integer not null
        primary key,
    content    text,
    date       date,
    helpful    integer not null,
    rating     integer not null,
    summery    varchar(255),
    person     integer
        constraint fkdo60vxlownqykeceb1l8uqm2v
            references person,
    product_id varchar(255)
        constraint fkiyof1sindb9qiqr9o8npj8klt
            references product
);

create table if not exists store
(
    id     integer not null
        primary key,
    name   varchar(255),
    street varchar(255),
    zip    varchar(255)
);

create table if not exists product_in_store
(
    product_id varchar(255) not null
        constraint fk8oit7v8029dtcxlgcx8rsdwn3
            references product,
    store_id   integer      not null
        constraint fkov0l5dmnkncljexe0liqgeq8
            references store
);

create table if not exists product_store
(
    product_id varchar(255) not null
        constraint fk5ixdrvrn9b8v6gwrws3s3brsf
            references product,
    store_id   integer      not null
        constraint fk6ofo19q4q8aqicf2tifxv91et
            references store
);