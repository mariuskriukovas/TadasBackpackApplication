CREATE TABLE traveler
(
    id   IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

ALTER TABLE traveler
    ADD UNIQUE (name);

CREATE TABLE item
(
    id                 IDENTITY PRIMARY KEY,
    name               VARCHAR(100) NOT NULL,
    is_mandatory       BOOLEAN      NULL,
    weight             DOUBLE       NOT NULL,
    provide_kilometers DOUBLE       NULL
);

ALTER TABLE item
    ADD UNIQUE (name, weight);

CREATE TABLE travel
(
    id                  IDENTITY PRIMARY KEY,
    name                VARCHAR(100) NOT NULL,
    distance_kilometers DOUBLE       NOT NULL,
    traveler_id         INT
);

ALTER TABLE travel
    ADD UNIQUE (name, traveler_id, distance_kilometers);

ALTER TABLE travel
    ADD FOREIGN KEY (traveler_id)
        REFERENCES traveler (id);

CREATE TABLE bag_item
(
    id        IDENTITY PRIMARY KEY,
    travel_id INT,
    item_id   INT
);

ALTER TABLE bag_item
    ADD UNIQUE (travel_id, item_id);

ALTER TABLE bag_item
    ADD FOREIGN KEY (travel_id)
        REFERENCES travel (id);

ALTER TABLE bag_item
    ADD FOREIGN KEY (item_id)
        REFERENCES item (id);