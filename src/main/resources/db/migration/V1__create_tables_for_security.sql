CREATE SCHEMA app;

CREATE TABLE app.roles (
    id bigint generated always as identity primary key,
    name text not null
);

CREATE TABLE app.users (
    id bigint generated always as identity primary key,
    first_name text not null,
    last_name text not null,
    username text not null,
    password text not null
);

CREATE TABLE app.users_roles (
    id_user bigint not null references app.users(id),
    id_role bigint not null references app.roles(id),
    primary key (id_user, id_role),
    constraint fk_users_roles_1 foreign key (id_user) references app.users (id),
    constraint fk_users_roles_2 foreign key (id_role) references app.roles (id)
);