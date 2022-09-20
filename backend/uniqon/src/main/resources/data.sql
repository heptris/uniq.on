insert into member (created_date, last_modified_date, email, member_type, name, nickname, password, wallet_address)
values (now(), now(), "aa@naver.com", "ADMIN", "AAA", "aaa", "$2a$10$Fq/c/lgxWYldTv/t.aQEjOcpiOqbZcqAUJnvCQx78wjbh6E3oUXpG", "0XA"),
       (now(), now(), "bb@naver.com", "USER", "BBB", "bbb", "$2a$10$Fq/c/lgxWYldTv/t.aQEjOcpiOqbZcqAUJnvCQx78wjbh6E3oUXpG", "0XB"),
       (now(), now(), "cc@naver.com", "USER", "CCC", "ccc", "$2a$10$Fq/c/lgxWYldTv/t.aQEjOcpiOqbZcqAUJnvCQx78wjbh6E3oUXpG", "0XC"),
       (now(), now(), "dd@naver.com", "USER", "DDD", "ddd", "$2a$10$Fq/c/lgxWYldTv/t.aQEjOcpiOqbZcqAUJnvCQx78wjbh6E3oUXpG", "0XD"),
       (now(), now(), "ee@naver.com", "USER", "EEE", "eee", "$2a$10$Fq/c/lgxWYldTv/t.aQEjOcpiOqbZcqAUJnvCQx78wjbh6E3oUXpG", "0XE"),
       (now(), now(), "test@naver.com", "USER", "test", "test", "$2a$10$BlTLOFbU79iOq9rm34.vVub.bULEw/9iPf1wkKxJ1qu3DhA1eMka.", "0XF");

insert into startup (created_date, last_modified_date, description, business_plan, cur_total_price
                    , discord_url, end_date, enroll_status, goal_price, image_nft, invest_count, is_finished, is_goal, nft_count
                    , price_per_nft, road_map, reject_reason, startup_name, title, member_id)
values (now(), now(), "startup D입니다", null, 0, null,
        now() + 5, null, 10, null, 0, false, false, 0, 0, null, null, null, "startup D", 4),
       (now(), now(), "startup E입니다", null, 0, null,
        now() + 5, null, 10, null, 0, false, false, 0, 0, null, null, null, "startup E", 5);

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

insert into startup_community (created_date, last_modified_date, content, title, member_id, startup_id)
values (now(), now(), "Content Test", "Title Test", 1, 1);

insert into community_comment (created_date, last_modified_date, content, member_id, parent_id, startup_community_id)
values (now(), now(), "Content Test", 1, null, 1),
       (now(), now(), "Content Test", 2, 1, 1),
       (now(), now(), "Content Test", 3, 1, 1);

insert into invest_history (created_date, last_modified_date, invest_status, member_id, startup_id)
values (now(), now(), "INVESTING", 1, 1),
       (now(), now(), "INVESTING", 2, 1),
       (now(), now(), "INVESTING", 3, 1),
       (now(), now(), "INVESTING", 1, 2),
       (now(), now(), "INVESTING", 2, 2);