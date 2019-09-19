CREATE sequence USERS_SEQ;


create table USERS
(
	id_user INT auto_increment primary key,
	LOGIN VARCHAR(20) not null 		unique,
	PASSWORD VARCHAR(100) not null,
	ROLE VARCHAR(5) default ('USER')  check ((ROLE IN ('ADMIN', 'USER'))),
    FIRST_NAME   VARCHAR(40)   ,
    SECOND_NAME  VARCHAR(40)   ,
    PHONE_NUMBER VARCHAR(40)   ,
    EMAIL    VARCHAR(100) ,
    ADDRESS  VARCHAR(255)    ,
    BALANCE     DECIMAL(10,2) not null,
    DISCOUNT    INT     not null,
);




--------------------------------------------------------
--  Insert data
--------------------------------------------------------
insert  into USERS (id_user,LOGIN,PASSWORD,ROLE,FIRST_NAME,SECOND_NAME,PHONE_NUMBER,EMAIL,ADDRESS,BALANCE,DISCOUNT)
values ( USERS_SEQ.nextval, 'admin', 'admin123', 'ADMIN','fAdminName','sAdminName','79788758745', 'admin@mail.ru','Russia', 0, 0),
       (USERS_SEQ.NEXTVAL, 'user', 'user123', 'USER','fUserName','sUserName','79788758777', 'User@mail.ru','Russia', 2000, 3);


commit;