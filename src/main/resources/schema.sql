DROP TABLE IF EXISTS SUPERHERO;

CREATE TABLE SUPERHERO (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(128) NOT NULL,
    FULLNAME VARCHAR(128),
    PLACE_OF_BIRTH VARCHAR(128),
    PRIMARY KEY (id)
);