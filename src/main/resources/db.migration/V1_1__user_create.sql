DROP TABLE client IF EXISTS;

CREATE TABLE client
(
                 id bigserial not null,
                 first_name varchar(30),
                 last_name varchar(30),
                 date varchar(30) ,
                 PRIMARY KEY (id)
) ;
