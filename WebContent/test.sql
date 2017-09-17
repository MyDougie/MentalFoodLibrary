insert into BOOK(BOOK_NO, TITLE, DESCRIPTION, WRITER, WRITE_DATE, PUBLISHER,AVAILABLE, BOOK_IMG,CATEGORY_NO ) values(BOOK_NO.nextVal,'abc','abc','abc','1234','abc','0','abc','1');
select * from BOOK

insert into BOOK(BOOK_NO, TITLE, DESCRIPTION, WRITER, WRITE_DATE, PUBLISHER,AVAILABLE, BOOK_IMG,CATEGORY_NO ) values(BOOK_NO.nextVal,?,?,?,?,?,?,?,?)

commit
select * from book where book_no like 3;

insert into BOOK_CATEGORY(CATEGORY_NO, CATEGORY_NAME) values ('0', '철학')
insert into BOOK_CATEGORY(CATEGORY_NO, CATEGORY_NAME) values ('1', '과학')
insert into BOOK_CATEGORY(CATEGORY_NO, CATEGORY_NAME) values ('2', '문학')
insert into BOOK_CATEGORY(CATEGORY_NO, CATEGORY_NAME) values ('3', '수학')
insert into BOOK_CATEGORY(CATEGORY_NO, CATEGORY_NAME) values ('4', '생활정보')
insert into BOOK_CATEGORY(CATEGORY_NO, CATEGORY_NAME) values ('5', '자격증')
insert into BOOK_CATEGORY(CATEGORY_NO, CATEGORY_NAME) values ('6', '외국어')
