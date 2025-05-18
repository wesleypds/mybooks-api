ALTER TABLE app.books DROP COLUMN cover;
ALTER TABLE app.books DROP COLUMN book;
ALTER TABLE app.books ADD COLUMN path_file text not null;