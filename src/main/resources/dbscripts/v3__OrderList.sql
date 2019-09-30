CREATE sequence ORDER_SEQ;


create table ORDERS_LIST
(
	ID_ORDER INT auto_increment primary key,
	LOGIN_USER VARCHAR(20) ,
	ID_USER INT,
	TOTAL_PRICE DECIMAL(10,2) not null,
	STATUS_ORDER VARCHAR(10) default ('CREATED')  check (status_order IN ('PAID', 'CREATED',  'CLOSED')),
	DATE_CREATE DATE ,
    DATE_CLOSE  DATE,

 );

COMMIT;
