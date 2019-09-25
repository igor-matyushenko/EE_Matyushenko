CREATE sequence ORDER_SEQ;


create table OrderList
(
	ID_ORDER INT auto_increment primary key,
	id_user INT ,
	total_price DECIMAL(10,2) not null,
	status_order VARCHAR(10) default ('CREATED')  check (status_order IN ('PAID', 'CREATED',  'CLOSED')),
	date_create DATE ,
    date_close  DATE,

 );





COMMIT;
