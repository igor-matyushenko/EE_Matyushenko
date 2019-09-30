CREATE sequence ORDER_POSITION_SEQ;


create table ORDER_POSITION
(
	ID_ORDER_POSITION INT auto_increment,
	id_order INT,
	id_flower INT,
	flower_name VARCHAR(255),
	id_user INT ,
    quantity  INT ,
    TOTAL_PRICE DECIMAL(15,2) ,
    primary key (ID_ORDER_POSITION),

 );




--------------------------------------------------------
--  Insert data
--------------------------------------------------------



COMMIT;
