insert into member (created_date, last_modified_date, email, member_type, nickname, password, wallet_address)
values ('2022-09-07 16:00:48.700000000', '2022-09-07 16:00:48.700000000', "aa@naver.com", "INVESTOR", "aaa", "1234", "0XA"),
       ('2022-09-07 16:00:48.700000000', '2022-09-07 16:00:48.700000000', "bb@naver.com", "INVESTOR", "bbb", "1234", "0XB"),
       ('2022-09-07 16:00:48.700000000', '2022-09-07 16:00:48.700000000', "cc@naver.com", "INVESTOR", "ccc", "1234", "0XC"),
       ('2022-09-07 16:00:48.700000000', '2022-09-07 16:00:48.700000000', "dd@naver.com", "STARTUP", "ddd", "1234", "0XD"),
       ('2022-09-07 16:00:48.700000000', '2022-09-07 16:00:48.700000000', "ee@naver.com", "STARTUP", "eee", "1234", "0XE");

insert into startup (created_date, last_modified_date, description, business_plan, cur_total_price
, discord_url, end_date, enroll_status, goal_price, image_nft, invest_count, is_finished, is_goal, nft_count
, price_per_nft, project_pdf, reject_reason, startup_name, title, member_id)
values ('2022-09-07 16:00:48.700000000', '2022-09-07 16:00:48.700000000', "startup D입니다", null, 0, null,
        null, null, 0, null, 0, false, false, 0, 0, null, null, null, "startup D", 4),
       ('2022-09-07 16:00:48.700000000', '2022-09-07 16:00:48.700000000', "startup E입니다", null, 0, null,
        null, null, 0, null, 0, false, false, 0, 0, null, null, null, "startup E", 5);

insert into startup_question (created_date, last_modified_date, question, member_id, startup_id)
values ('2022-09-07 16:00:48.700000000', '2022-09-07 16:00:48.700000000', "질문1", 1, 1),
       ('2022-09-07 16:00:48.700000000', '2022-09-07 16:00:48.700000000', "질문2", 2, 1),
       ('2022-09-07 16:00:48.700000000', '2022-09-07 16:00:48.700000000', "질문3", 3, 1),
       ('2022-09-07 16:00:48.700000000', '2022-09-07 16:00:48.700000000', "질문4", 1, 2),
       ('2022-09-07 16:00:48.700000000', '2022-09-07 16:00:48.700000000', "질문5", 2, 2),
       ('2022-09-07 16:00:48.700000000', '2022-09-07 16:00:48.700000000', "질문6", 3, 2);

insert into startup_answer (created_date, last_modified_date, member_id, answer, parent_id, startup_question_id)
values ('2022-09-07 21:15:53.559000000', '2022-09-07 21:15:53.559000000',4,  "답변1", null, 1),
       ('2022-09-07 21:15:55.559000000', '2022-09-07 21:15:55.559000000',1,  "답변2", 1, 1),
       ('2022-09-07 21:15:56.559000000', '2022-09-07 21:15:56.559000000',4,  "답변3", 2, 1),
       ('2022-09-07 21:15:57.559000000', '2022-09-07 21:15:57.559000000',4,  "답변4", null, 2),
       ('2022-09-07 21:15:58.559000000', '2022-09-07 21:15:58.559000000', 4, "답변5", null, 3),
       ('2022-09-07 21:15:59.559000000', '2022-09-07 21:15:59.559000000', 3, "답변6", 5, 3),
       ('2022-09-07 21:15:53.559000000', '2022-09-07 21:15:53.559000000', 4, "답변7", 6, 3);

insert into startup_community (created_date, last_modified_date, content, title, member_id, startup_id)
values (now(), now(), "Content Test", "Title Test", 1, 1);

insert into community_comment (created_date, last_modified_date, content, member_id, parent_id, startup_community_id)
values (now(), now(), "Content Test", 1, null, 1),
       (now(), now(), "Content Test", 2, 1, 1),
       (now(), now(), "Content Test", 3, 1, 1);