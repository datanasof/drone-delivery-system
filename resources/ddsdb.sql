DROP DATABASE IF EXISTS ddsdb;
CREATE DATABASE ddsdb;
USE ddsdb;

CREATE TABLE Products (id int NOT NULL AUTO_INCREMENT, name varchar(255), weight double, quantity int, PRIMARY KEY (ID));

INSERT INTO Products (name, weight, quantity) VALUES ('Keyboard','0.5', '5');
INSERT INTO Products (name, weight, quantity) VALUES ('Speakers','1', '5');
INSERT INTO Products (name, weight, quantity) VALUES ('Mouse','0.3', '5');
INSERT INTO Products (name, weight, quantity) VALUES ('Display','1.5', '5');
INSERT INTO Products (name, weight, quantity) VALUES ('Power cable','0.3', '5');

CREATE TABLE Drones (id int NOT NULL AUTO_INCREMENT, battery int, capacity float, chargerate int, available timestamp, PRIMARY KEY (ID));

INSERT INTO Drones (battery, capacity, chargerate, available) VALUES ('2000','0.5', '5', '2017-01-19 20:00:01');
INSERT INTO Drones (battery, capacity, chargerate, available) VALUES ('2000','0.5', '5', '2017-01-19 20:00:01');
INSERT INTO Drones (battery, capacity, chargerate, available) VALUES ('2000','0.5', '5', '2017-01-19 20:00:01');
INSERT INTO Drones (battery, capacity, chargerate, available) VALUES ('2000','0.5', '5', '2017-01-19 20:00:01');
INSERT INTO Drones (battery, capacity, chargerate, available) VALUES ('2000','0.5', '5', '2017-01-19 20:00:01');


