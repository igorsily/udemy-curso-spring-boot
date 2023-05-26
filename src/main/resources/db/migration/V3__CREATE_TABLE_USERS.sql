create table if not exists users
(
    id uuid not null
        primary key
        default uuid_generate_v4(),
        user_name varchar(40) not null unique,
        full_name varchar(255) not null,
        password varchar(255) not null,
        email varchar(80) not null unique,
        account_non_expired boolean not null default true,
        account_non_locked boolean not null default true,
        credentials_non_expired boolean not null default true,
        enabled boolean not null default true
);

alter table users
    owner to dpgis;

create unique index IF NOT EXISTS users_pkey
    on users (id);
