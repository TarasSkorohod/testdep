CREATE TABLE users
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 ),
    email character varying(255),
    password character varying(255),
    firstname character varying(255),
    lastname character varying(255),
    age integer,
    url text,
    PRIMARY KEY (id)
);