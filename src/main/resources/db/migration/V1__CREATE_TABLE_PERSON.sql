create table IF NOT EXISTS persons
(
    id  uuid not null
        primary key
        default uuid_generate_v4(),
    first_name varchar(80),
    last_name  varchar(80),
    address    varchar(100),
    gender     varchar(10)
);

alter table persons
    owner to dpgis;

create unique index IF NOT EXISTS person_pkey
    on persons (id);