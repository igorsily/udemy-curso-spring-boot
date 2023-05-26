create table IF NOT EXISTS permissions (
    id  uuid not null
        primary key
        default uuid_generate_v4(),
    description varchar(255)
);

alter table permissions
    owner to dpgis;

create unique index IF NOT EXISTS permission_pkey
    on permissions (id);