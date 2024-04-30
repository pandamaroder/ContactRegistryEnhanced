--liquibase formatted sql

--changeset olga.kukushkina:2024.05.01:contact.sequence
create sequence if not exists demo.contact_seq;

--changeset olga.kukushkina:2024.05.01:buyer.table
create table if not exists demo.contact
(
    id          bigint primary key default nextval('demo.contact_seq'),
    first_name  varchar(255) not null,
    last_name   varchar(255) not null,
    middle_name varchar(255),
    phone       varchar(20)  not null,
    email       varchar(50)  not null

);

--changeset olga.kukushkina:2024.05.01:contact.indexes
create index if not exists i_contact_first_name on demo.contact (first_name);
create index if not exists i_contact_last_name on demo.contact (last_name);
create index if not exists i_contact_middle_name on demo.contact (middle_name);
create index if not exists i_contact_names on demo.contact (first_name, last_name, middle_name);

--changeset olga.kukushkina:2024.07.10:populate.data
insert into demo.contact (first_name, last_name, phone, email)
values ('John', 'Smith', '89201213456', 'john@example.com'),
    ('Mary', 'Smith', '89201213457', 'hello@example.com'),
    ('Anna', 'Smith', '89201213458', 'hello@example.com');

--changeset olga.kukushkina:2024.07.10:buyer.comments.on.table.and.columns
comment on table demo.contact is 'Information about the contact';
comment on column demo.contact.id is 'Unique identifier of the record in the current table';
comment on column demo.contact.first_name is 'contact''s given name';
comment on column demo.contact.last_name is 'contact''s last name';
comment on column demo.contact.middle_name is 'Patronymic of the contact';
comment on column demo.contact.phone is 'contact''s phone number';
comment on column demo.contact.email is 'contact''s email address';

