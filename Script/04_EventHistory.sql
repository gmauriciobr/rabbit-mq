USE globodb;

CREATE TABLE eventhistory (
    id integer NOT NULL auto_increment,
    type varchar(100) NOT NULL,
    subscription_id varchar(100) NOT NULL,
    created_at datetime NOT NULL,
    PRIMARY KEY(id)
);

ALTER TABLE eventhistory ADD CONSTRAINT FK_EVEN_SUBS FOREIGN KEY (subscription_id)
REFERENCES subscription (id);