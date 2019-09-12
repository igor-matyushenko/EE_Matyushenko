CREATE sequence PRODUCT_SEQ;


create table PRODUCT
(
    ID     BIGINT auto_increment
        primary key,
    TITLE  VARCHAR(100)   not null,
    PRICE  NUMERIC(10, 2) not null,
    QUANTITY INT
);


Insert into PRODUCT (ID, TITLE, PRICE, QUANTITY)
values (PRODUCT_SEQ.NEXTVAL, 'rose RED', 80.00, 10),
       (PRODUCT_SEQ.NEXTVAL, 'rose YELLOW', 60.00, 36),
       (PRODUCT_SEQ.NEXTVAL, 'rose BLACK', 75.00, 56),
       (PRODUCT_SEQ.NEXTVAL, 'chamomile', 12.25, 18),
       (PRODUCT_SEQ.NEXTVAL, 'Tulip YELLOW', 20.50, 36),
       (PRODUCT_SEQ.NEXTVAL, 'Tulip RED', 25.00, 56);


COMMIT;