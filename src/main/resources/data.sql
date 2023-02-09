/*아이디:admin01, 비밀번호: adminadmin*/
insert into member (member_id, member_password, name, phone, term, role)
values ('admin01', '$2a$10$LxQGnpIA2VLANZNTfSwnwuuVyzvIzEmT0.3OrH.NM15khYIUYxX..', '운영자', '01012345678', 1, 'ADMIN');
--
/*아이디:chan0729 비밀번호: chan0729*/
insert into member (member_id, member_password, name, phone, term, role)
values ('chan0729', '$2a$10$QNgQVbxlHVim/zIbkoPcOe7EYvHB2BfHEufLHYwnzUCZgPMuuG/4W', '찬', '01012345678', 1, 'USER');

-- 테스트 계정
    insert into member (member_id, member_password, name, phone, term, role) VALUES
    ('rko','{noop}123456', '이재철', '01011113333', 1, 'USER')
;
    insert into member (member_id, member_password, name, phone, term, role) VALUES
    ('rko2','{noop}111111','이재훈', '01011112222', 1, 'USER')
;

-- 게시글
    insert into board (member_id, board_type, title, content, registered_date, modified_date) values
    ('rko2','FORUM','test title','test test test','2023-01-01 09:14:25', '2023-01-01 09:20:25'),
    ('rko','FORUM','test2 title','test2 test2 test2', '2023-01-02 09:14:25','2023-01-02 09:24:25');

-- 댓글
    insert into board_comment (member_id, board_id, content, registered_date) values
    ('rko', 1,'lololol','2023-01-01 10:14:25'),
    ('rko2', 2,'wowowow','2023-01-02 10:14:25');