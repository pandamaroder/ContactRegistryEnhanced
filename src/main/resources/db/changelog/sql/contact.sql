--liquibase formatted sql


create sequence if not exists contact_seq;


create table if not exists contact
(
    id          bigint primary key default nextval('demo.contact_seq'),
    first_name  varchar(255) not null,
    last_name   varchar(255) not null,
    middle_name varchar(255),
    phone       varchar(20)  not null,
    email       varchar(50)  not null

);


