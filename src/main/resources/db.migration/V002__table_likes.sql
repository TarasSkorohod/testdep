CREATE TABLE likes
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 ),
    user_from integer,
    user_to integer,
    status boolean,
    PRIMARY KEY (id)
);