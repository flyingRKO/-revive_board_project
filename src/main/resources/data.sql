-- 테스트 계정
    insert into user_account(user_id, password, phone, user_name, role, term) VALUES
    ('rko','123456','01011112222','이재훈','GENERAL',true)
;

-- 게시글
    insert into board (user_account_id, board_type, title, content, registered_date, modified_date) values
    (1,'FORUM','test title','test test test','2023-01-01 09:14:25', '2023-01-01 09:20:25'),
    (1,'FORUM','test2 title','test2 test2 test2', '2023-01-02 09:14:25','2023-01-02 09:24:25');

-- 댓글
    insert into board_comment (user_account_id, board_id, content, registered_date) values
    (1, 1,'lololol','2023-01-01 10:14:25'),
    (1, 2,'wowowow','2023-01-02 10:14:25')