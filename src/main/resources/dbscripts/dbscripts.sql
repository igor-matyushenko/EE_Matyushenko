CREATE sequence ADDRESS_SEQ;
CREATE sequence USERS_SEQ;
CREATE sequence BUYER_SEQ;
CREATE sequence PRODUCT_SEQ;
CREATE sequence ORDER_CREATED_SEQ;
CREATE sequence ORDER_CONTAINS_SEQ;

create table USERS
(
    ID        NUMBER(10) auto_increment
        primary key,
    LOGIN     VARCHAR(20) not null
        unique,
    PASSWORDS VARCHAR(20) not null,
    ROLE      VARCHAR(5) default ('USER')
        check (ROLE IN ('ADMIN', 'USER')),
    ID_BUYER number(10) not null

);


create table BUYER
(
    ID          number(10) auto_increment
        primary key,
    FIRSTNAME   varchar(40)   not null,
    SECONDNAME  varchar(40)   not null,
    PHONENUMBER number(11)    ,
    ID_ADDRESS  number(10)    ,
    BALANCE     number(10, 2) not null,
    DISCOUNT    number(3)     not null

);

create table PRODUCT
(
    ID     number(10) auto_increment
        primary key,
    TITLE  varchar(50)   not null,
    PRICE  number(10, 2) not null,
    VOLUME number(10)    not null
);
create table ADDRESS
(
    ID      number(10) auto_increment
        primary key,
    COUNTRY VARCHAR(30) not null,
    CITY    VARCHAR(30) ,
    STREET  VARCHAR(60) ,
    HOME    VARCHAR(5)
);

create table ORDER_CREATED
(
    ID         number(10) auto_increment
        primary key,
    ID_BUYER   VARCHAR(30) not null,
    All_SUM    double(10)  not null,
    STATUS     VARCHAR(10) default ('BUY')
        check (STATUS IN ('BUY', 'CLOSED')) ,
    DATACREATED    date,
    DATACLOSED date
);

create table ORDER_CONTAINS
(
    ID               number(10) auto_increment
        primary key,
    ID_PRODUCT       number(10) not null,
    ID_ORDER_CREATED number(10) not null,
    VOLUME           int        not null,
    SUM              double(10) not null
);
--------------------------------------------------------
--  Insert FOREIGN KEY
--------------------------------------------------------

ALTER TABLE USERS  ADD FOREIGN KEY (ID_BUYER) REFERENCES BUYER(ID);
ALTER TABLE BUYER  ADD FOREIGN KEY (ID_ADDRESS) REFERENCES ADDRESS(ID);
ALTER TABLE ORDER_CREATED  ADD FOREIGN KEY (ID_BUYER) REFERENCES BUYER(ID);
ALTER TABLE ORDER_CONTAINS  ADD FOREIGN KEY (ID_PRODUCT) REFERENCES PRODUCT(ID);
ALTER TABLE ORDER_CONTAINS  ADD FOREIGN KEY (ID_ORDER_CREATED) REFERENCES ORDER_CREATED(ID);


--------------------------------------------------------
--  Insert data
--------------------------------------------------------
Insert into ADDRESS (ID, COUNTRY, CITY, STREET, HOME)values (ADDRESS_SEQ.NEXTVAL, 'RUSSIA', 'MOSCOW', 'Невского', '25');
Insert into ADDRESS (ID, COUNTRY, CITY, STREET, HOME)values (ADDRESS_SEQ.NEXTVAL, 'RUSSIA', 'Simpherorpol', 'Ялтинская', '55');
Insert into ADDRESS (ID, COUNTRY, CITY, STREET, HOME)values (ADDRESS_SEQ.NEXTVAL, 'RUSSIA', 'Saint-Peterburg', 'Набережная', '6');
Insert into ADDRESS (ID, COUNTRY, CITY, STREET, HOME)values (ADDRESS_SEQ.NEXTVAL, 'RUSSIA', 'Tver', 'Набережная', '78');
Insert into ADDRESS (ID, COUNTRY, CITY, STREET, HOME)values (ADDRESS_SEQ.NEXTVAL, 'RUSSIA', 'Krasnoperekopsk', 'Lenina', '45');
Insert into ADDRESS (ID, COUNTRY, CITY, STREET, HOME)values (ADDRESS_SEQ.NEXTVAL, 'RUSSIA', 'Tver', 'Тверская', '72');
Insert into ADDRESS (ID, COUNTRY, CITY, STREET, HOME)values (ADDRESS_SEQ.NEXTVAL, 'RUSSIA', 'Krasnoperekopsk', 'Калинина', '41');

Insert into BUYER (ID, FIRSTNAME, SECONDNAME, PHONENUMBER, ID_ADDRESS, BALANCE, DISCOUNT)
values (BUYER_SEQ.NEXTVAL, 'ADMIN', 'ADMIN', 79788758745, 1, 0, 0);

Insert into BUYER (ID, FIRSTNAME, SECONDNAME, PHONENUMBER, ID_ADDRESS, BALANCE, DISCOUNT)
values (BUYER_SEQ.NEXTVAL, 'Вася', 'Doe', 79788758746, 2, 2000, 0);
Insert into BUYER (ID, FIRSTNAME, SECONDNAME, PHONENUMBER, ID_ADDRESS, BALANCE, DISCOUNT)
values (BUYER_SEQ.NEXTVAL, 'Коля', 'Doe',79788738746, 3, 2000, 15);
Insert into BUYER (ID, FIRSTNAME, SECONDNAME, PHONENUMBER, ID_ADDRESS, BALANCE, DISCOUNT)
values (BUYER_SEQ.NEXTVAL, 'Максим', 'Doe', 79781758746, 4, 2000, 23);
Insert into BUYER (ID, FIRSTNAME, SECONDNAME, PHONENUMBER, ID_ADDRESS, BALANCE, DISCOUNT)
values (BUYER_SEQ.NEXTVAL, 'Саша', 'Doe',79788758766, 5, 2000, 7);
Insert into BUYER (ID, FIRSTNAME, SECONDNAME, PHONENUMBER, ID_ADDRESS, BALANCE, DISCOUNT)
values (BUYER_SEQ.NEXTVAL, 'Дима', 'Doe',47978758746, 6, 2000, 5);

Insert into USERS (ID, LOGIN, PASSWORDS, ROLE,ID_BUYER)values (USERS_SEQ.NEXTVAL, 'admin', 'admin123', 'ADMIN',1);
Insert into USERS (ID, LOGIN, PASSWORDS, ROLE,ID_BUYER)values (USERS_SEQ.NEXTVAL, 'user1', 'user123', 'USER',2);
Insert into USERS (ID, LOGIN, PASSWORDS, ROLE,ID_BUYER)values (USERS_SEQ.NEXTVAL, 'user2', 'user123', 'USER',3);
Insert into USERS (ID, LOGIN, PASSWORDS, ROLE,ID_BUYER)values (USERS_SEQ.NEXTVAL, 'user3', 'user123', 'USER',4);
Insert into USERS (ID, LOGIN, PASSWORDS, ROLE,ID_BUYER)values (USERS_SEQ.NEXTVAL, 'user4', 'user123', 'USER',5);
Insert into USERS (ID, LOGIN, PASSWORDS, ROLE,ID_BUYER)values (USERS_SEQ.NEXTVAL, 'user5', 'user123', 'USER',6);





Insert into PRODUCT (ID, TITLE, PRICE, VOLUME) values (PRODUCT_SEQ.NEXTVAL, 'rose RED', 10.25, 10);
Insert into PRODUCT (ID, TITLE, PRICE, VOLUME)values (PRODUCT_SEQ.NEXTVAL, 'rose YELLOW', 11.50, 36);
Insert into PRODUCT (ID, TITLE, PRICE, VOLUME)values (PRODUCT_SEQ.NEXTVAL, 'rose BLACK', 15.00, 56);


COMMIT;