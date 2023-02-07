-- 테스트 계정
    insert into user_account(user_id, password, phone, user_name, role, term) VALUES
    ('rko','{noop}123456','01011113333','이재철','GENERAL',true)
;
    insert into user_account(user_id, password, phone, user_name, role, term) VALUES
    ('rko2','{noop}111111','01011112222','이재훈','GENERAL',true)
;

-- 게시글
    insert into board (user_id, board_type, title, content, registered_date, modified_date) values
    ('rko2','FORUM','test title','test test test','2023-01-01 09:14:25', '2023-01-01 09:20:25'),
    ('rko','FORUM','test2 title','test2 test2 test2', '2023-01-02 09:14:25','2023-01-02 09:24:25');

-- 댓글
    insert into board_comment (user_id, board_id, content, registered_date) values
    ('rko', 1,'lololol','2023-01-01 10:14:25'),
    ('rko2', 2,'wowowow','2023-01-02 10:14:25');