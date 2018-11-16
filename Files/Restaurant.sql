DROP SCHEMA IF EXISTS Restaurant;

CREATE SCHEMA Restaurant;

USE Restaurant;

DROP TABLE IF EXISTS commande;

DROP TABLE IF EXISTS commande_tmp;

DROP TABLE IF EXISTS stats_pizza;

DROP TABLE IF EXISTS stats_sales;

DROP TABLE IF EXISTS stat_waiter;

DROP TABLE IF EXISTS menu;

DROP TABLE IF EXISTS log;


CREATE TABLE commande

(
        nb_order int NOT NULL,
        day_date date NOT NULL,
        margherita int NULL,
        veggie int NULL,
        meat int NULL,
        best int NULL,
        chicken int NULL,
        cheese int NULL,
        tiramisu int NULL,
        ice_cream int NULL,
        apple_pie int NULL,
        coca int NULL,
        fanta int NULL,
        ice_tea int NULL,
        CONSTRAINT PRIMARY KEY (nb_order, day_date)

)

;

CREATE TABLE commande_tmp

(
        nb_order int NOT NULL,
        day_date date NOT NULL,
        margherita int NULL,
        veggie int NULL,
        meat int NULL,
        best int NULL,
        chicken int NULL,
        cheese int NULL,
        tiramisu int NULL,
        ice_cream int NULL,
        apple_pie int NULL,
        coca int NULL,
        fanta int NULL,
        ice_tea int NULL,
        CONSTRAINT PRIMARY KEY (nb_order, day_date)

)

;

CREATE TABLE stats_pizza

(
        day_date date NOT NULL,
        margherita int NOT NULL,
        veggie int NOT NULL,
        meat int NOT NULL,
        best int NOT NULL,
        chicken int NOT NULL,
        cheese int NOT NULL,

        CONSTRAINT PRIMARY KEY (day_date)

)

;


CREATE TABLE stats_sales

(

        day_date date NOT NULL,
        ca_day int NOT NULL,
        nmb_person int NOT NULL,
        constraint primary key(day_date)
)

;


CREATE TABLE menu
(
    plate varchar(10) NOT NULL,
    price int NOT NULL,
    constraint primary key(plate)
)
;
CREATE TABLE log

(
        id varchar(9) NOT NULL,
        password varchar(9) NOT NULL,
        CONSTRAINT PRIMARY KEY (id)
)
;


CREATE TABLE stat_waiter

(
        day_date date NOT NULL,
        waiter_1 int NOT NULL,
        waiter_2 int NOT NULL,
        waiter_3 int NOT NULL,
        waiter_4 int NOT NULL,
        CONSTRAINT PRIMARY KEY (day_date)
)
;

