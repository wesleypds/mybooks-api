CREATE TABLE app.people (
    id bigint generated always as identity primary key,
    first_name text not null,
    last_name text not null,
    id_user bigint not null references app.users(id),
    constraint fk_users_roles_1 foreign key (id_user) references app.users (id)
);

CREATE TABLE app.publishers (
    id bigint generated always as identity primary key,
    name text not null
);

CREATE TABLE app.authors (
    id bigint generated always as identity primary key,
    first_name text not null,
    last_name text not null
);

CREATE TABLE app.reading_datas (
    id bigint generated always as identity primary key,
    open boolean default null,
    read boolean default null,
    opening_date timestamp default null,
    end_date_reading timestamp default null
);

CREATE TABLE app.books (
    id bigint generated always as identity primary key,
    title text not null,
    edition text,
    cover bytea,
    pages bigint not null,
    book bytea,
    shipping_date timestamp default null,
    id_publisher bigint not null references app.publishers(id),
    id_reading_data bigint not null references app.reading_datas(id),
    constraint fk_books_1 foreign key (id_publisher) references app.publishers (id),
    constraint fk_books_2 foreign key (id_reading_data) references app.reading_datas (id)
);

CREATE TABLE app.books_authors (
    id_book bigint not null references app.books(id),
    id_author bigint not null references app.authors(id),
    primary key (id_book, id_author),
    constraint fk_books_authors_1 foreign key (id_book) references app.books (id),
    constraint fk_books_authors_2 foreign key (id_author) references app.authors (id)
);