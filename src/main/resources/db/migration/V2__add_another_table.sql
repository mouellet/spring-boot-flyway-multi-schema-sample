CREATE TABLE event
(
    id   uuid    not null primary key,
    type integer not null,
    data varchar(255)
);
