# Java Dynamic Query

```sql
-- Risk
CREATE TABLE risk
(
    name                        VARCHAR(250) NOT NULL,
    description                 VARCHAR(250) NOT NULL,
    responsible                 VARCHAR(250) NOT NULL,
    responsibleAux              VARCHAR(250) NOT NULL,
    critic                      BOOLEAN NOT NULL,
    level                       SMALLINT NOT NULL,
    CONSTRAINT risk_pk PRIMARY KEY (name)
);

INSERT INTO risk
("name", description, responsible, responsibleaux, critic, "level")
VALUES('RiskA', 'Description A', 'AAA', 'BBB', true, 2);


INSERT INTO risk
("name", description, responsible, responsibleaux, critic, "level")
VALUES('RiskB', 'Description B', 'YYY', 'ZZZ', false, 5);

-- Process
CREATE TABLE process
(
    name                        VARCHAR(250) NOT NULL,
    description                 VARCHAR(250) NOT NULL,
    responsible                 VARCHAR(250) NOT NULL,
    responsibleAux              VARCHAR(250) NOT NULL,
    status                      VARCHAR(50) NOT NULL,
    CONSTRAINT process_pk PRIMARY KEY (name)
);

INSERT INTO process
    ("name", description, responsible, responsibleaux, status)
VALUES('ProcessM', 'Process M', 'JJJ', 'AAA', 'OK');

INSERT INTO process
    ("name", description, responsible, responsibleaux, status)
VALUES('ProcessQ', 'Process Q', 'NNN', 'LLL', 'OK');
```