CREATE TABLE messages
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 ),
    sender integer,
    receiver integer,
    content text,
    send_date text,
    PRIMARY KEY (id)
);