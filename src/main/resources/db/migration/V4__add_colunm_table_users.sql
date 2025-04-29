ALTER TABLE app.users ADD COLUMN account_non_expired boolean default null;
ALTER TABLE app.users ADD COLUMN account_non_locked boolean default null;
ALTER TABLE app.users ADD COLUMN credentials_non_expired boolean default null;
ALTER TABLE app.users ADD COLUMN enabled boolean default null;