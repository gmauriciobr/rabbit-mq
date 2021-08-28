use globodb;

CREATE TABLE subscription (
  id varchar(100) NOT NULL,
  status_id INT NOT NULL,
  created_at datetime NOT NULL,
  updated_at datetime NOT NULL,
  PRIMARY KEY (id)
);

ALTER TABLE subscription ADD CONSTRAINT FK_SUBS_STATUS FOREIGN KEY (status_id)
REFERENCES status (id);
