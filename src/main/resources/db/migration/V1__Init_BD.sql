/*

create table order_item
(
    id         int8 auto_increment,
    amount     int8,
    quantity   int8,
    perfume_id int8,
    primary key (id)
);

create table orders
(
    id           int8 auto_increment,
    address      varchar(255),
    city         varchar(255),
    date         date,
    email        varchar(255),
    first_name   varchar(255),
    last_name    varchar(255),
    phone_number varchar(255),
    post_index   int4,
    total_price  float8,
    primary key (id)
);

create table orders_order_items
(
    order_id       int8 not null,
    order_items_id int8 not null
);

create table perfume
(
    id                     int8 not null auto_increment,
    country                varchar(255),
    description            varchar(255),
    filename               varchar(255),
    fragrance_base_notes   varchar(255),
    fragrance_middle_notes varchar(255),
    fragrance_top_notes    varchar(255),
    perfume_gender         varchar(255),
    perfume_title          varchar(255),
    perfumer               varchar(255),
    price                  int4,
    type                   varchar(255),
    volume                 varchar(255),
    year                   int4,
    perfume_rating         float8,
    primary key (id)
);

create table perfume_reviews
(
    perfume_id int8 not null,
    reviews_id int8 not null
);

create table review
(
    id      int8 auto_increment,
    author  varchar(255),
    date    date,
    message varchar(255),
    rating  int4,
    primary key (id)
);

create table user_role
(
    user_id int8 not null,
    roles   varchar(255)
);

create table users
(
    id                  int8    not null,
    activation_code     varchar(255),
    active              boolean not null,
    address             varchar(255),
    city                varchar(255),
    email               varchar(255),
    first_name          varchar(255),
    last_name           varchar(255),
    password            varchar(255),
    password_reset_code varchar(255),
    phone_number        varchar(255),
    post_index          varchar(255),
    provider            varchar(255),
    primary key (id)
);*/
