insert into member (created_date, last_modified_date, email, member_type, nickname, password, wallet_address)
values (now(), now(), "aa@naver.com", "INVESTOR", "aaa", "1234", "0XA"),
       (now(), now(), "bb@naver.com", "INVESTOR", "bbb", "1234", "0XB"),
       (now(), now(), "cc@naver.com", "INVESTOR", "ccc", "1234", "0XC"),
       (now(), now(), "dd@naver.com", "STARTUP", "ddd", "1234", "0XD"),
       (now(), now(), "ee@naver.com", "STARTUP", "eee", "1234", "0XE");

insert into startup (created_date, last_modified_date, description, business_plan, cur_total_price
                    , discord_url, end_date, enroll_status, goal_price, image_nft, invest_count, is_finished, is_goal, nft_count
                    , price_per_nft, project_pdf, reject_reason, startup_name, title, member_id)
values (now(), now(), "startup D입니다", null, 0, null,
        null, null, 0, null, 0, false, false, 0, 0, null, null, null, "startup D", 4),
       (now(), now(), "startup E입니다", null, 0, null,
        null, null, 0, null, 0, false, false, 0, 0, null, null, null, "startup E", 5);

insert into startup_question (created_date, last_modified_date, question, member_id, startup_id)
values (now(), now(), "질문1", 1, 1),
       (now(), now(), "질문2", 2, 1),
       (now(), now(), "질문3", 3, 1),
       (now(), now(), "질문4", 1, 2),
       (now(), now(), "질문5", 2, 2),
       (now(), now(), "질문6", 3, 2);

insert into startup_answer (created_date, last_modified_date, member_id, answer, parent_id, startup_question_id)
values (now(), now(),4,  "답변1", null, 1),
       (now(), now(),1,  "답변2", 1, 1),
       (now(), now(),4,  "답변3", 1, 1),
       (now(), now(),4,  "답변4", null, 2),
       (now(), now(), 4, "답변5", null, 3),
       (now(), now(), 3, "답변6", 5, 3),
       (now(), now(), 4, "답변7", 5, 3);
