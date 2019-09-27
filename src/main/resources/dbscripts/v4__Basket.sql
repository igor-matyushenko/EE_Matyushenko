CREATE sequence BASKET_SEQ;


create table BASKET
(
	id_basket INT auto_increment,
	id_order INT,
	id_flower INT,
	flower_name VARCHAR(255),
	id_user INT ,
    quantity  INT ,
    TOTAL_PRICE DECIMAL(15,2) ,
    primary key (id_basket),

 );




--------------------------------------------------------
--  Insert data
--------------------------------------------------------



COMMIT;
