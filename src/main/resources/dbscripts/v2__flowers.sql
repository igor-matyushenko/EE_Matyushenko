CREATE sequence FLOWERS_SEQ;


create table FLOWERS
(
	ID_FLOWER INT auto_increment primary key,
	TITLE_FLOWER VARCHAR(20) not null 		unique,
	QUANTITY_FLOWER INT ,
    PRICE_FLOWER     DECIMAL(10,2) not null,
 );




--------------------------------------------------------
--  Insert data
--------------------------------------------------------
Insert into FLOWERS (ID_FLOWER, TITLE_FLOWER, PRICE_FLOWER, QUANTITY_FLOWER)
values (FLOWERS_SEQ.NEXTVAL, 'rose RED', 180.00, 100),
       (FLOWERS_SEQ.NEXTVAL, 'rose YELLOW', 160.00, 360),
       (FLOWERS_SEQ.NEXTVAL, 'rose BLACK', 175.00, 560),
       (FLOWERS_SEQ.NEXTVAL, 'chamomile', 72.25, 1800),
       (FLOWERS_SEQ.NEXTVAL, 'Tulip YELLOW', 80.50, 360),
       (FLOWERS_SEQ.NEXTVAL, 'Tulip RED', 75.00, 560);


COMMIT;
