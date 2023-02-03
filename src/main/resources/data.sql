insert into board (board_type, title, content, registered_date, modified_date)
values ('FORUM', 'test title', 'test test test', '2023-01-01 09:14:25', '2023-01-01 09:20:25'),
       ('FORUM', 'test2 title', 'test2 test2 test2', '2023-01-02 09:14:25', '2023-01-02 09:24:25');

-- 댓글
insert into board_comment (board_id, content, registered_date)
values (1, 'lololol', '2023-01-01 10:14:25'),
       (2, 'wowowow', '2023-01-02 10:14:25')