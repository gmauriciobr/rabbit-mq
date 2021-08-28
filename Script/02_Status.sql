use globodb;

CREATE TABLE status (
  id INT NOT NULL,
  name varchar(100) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO status (id, name) values (1, 'Ativo');
INSERT INTO status (id, name) values (2, 'Inativo');
