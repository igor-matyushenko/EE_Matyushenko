CREATE sequence USERS_SEQ;


create table USERS
(
	ID BIGINT auto_increment
		primary key,
	LOGIN VARCHAR(20) not null
		unique,
	PASSWORD VARCHAR(100) not null,
	ROLE VARCHAR(5) default ('USER')
		check ((ROLE IN ('ADMIN', 'USER'))),
    FIRST_NAME   VARCHAR(40)   not null,
    SECOND_NAME  VARCHAR(40)   not null,
    PHONE_NUMBER NUMBER    ,
    EMAIL    VARCHAR(100) ,
    ADDRESS  VARCHAR(255)    ,
    BALANCE     DECIMAL(10,2) not null,
    DISCOUNT    NUMERIC(3)     not null,
);




--------------------------------------------------------
--  Insert data
--------------------------------------------------------
INSERT  INTO USERS (ID,LOGIN,PASSWORD,ROLE,FIRST_NAME,SECOND_NAME,PHONE_NUMBER,EMAIL,ADDRESS,BALANCE,DISCOUNT)
VALUES ( USERS_SEQ.NEXTVAL, 'ADMIN', 'ADMIN123', 'ADMIN','fAdminName','sAdminName',79788758745, 'admin@mail.ru','Russia', 0, 0),
       (USERS_SEQ.NEXTVAL, 'USER', 'USER123', 'USER','fUserName','sUserName',79788758777, 'User@mail.ru','Russia', 2000, 3);


COMMIT;