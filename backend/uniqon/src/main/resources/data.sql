-- insert into member (created_date, last_modified_date, email, member_type, name, nickname, password, wallet_address)
-- values (now(), now(), "aa@naver.com", "ADMIN", "AAA", "aaa", "$2a$10$Fq/c/lgxWYldTv/t.aQEjOcpiOqbZcqAUJnvCQx78wjbh6E3oUXpG", "0XA"),
--        (now(), now(), "bb@naver.com", "USER", "BBB", "bbb", "$2a$10$Fq/c/lgxWYldTv/t.aQEjOcpiOqbZcqAUJnvCQx78wjbh6E3oUXpG", "0XB"),
--        (now(), now(), "cc@naver.com", "USER", "CCC", "ccc", "$2a$10$Fq/c/lgxWYldTv/t.aQEjOcpiOqbZcqAUJnvCQx78wjbh6E3oUXpG", "0XC"),
--        (now(), now(), "dd@naver.com", "USER", "DDD", "ddd", "$2a$10$Fq/c/lgxWYldTv/t.aQEjOcpiOqbZcqAUJnvCQx78wjbh6E3oUXpG", "0XD"),
--        (now(), now(), "ee@naver.com", "USER", "EEE", "eee", "$2a$10$Fq/c/lgxWYldTv/t.aQEjOcpiOqbZcqAUJnvCQx78wjbh6E3oUXpG", "0XE"),
--        (now(), now(), "test@naver.com", "USER", "test", "test", "$2a$10$BlTLOFbU79iOq9rm34.vVub.bULEw/9iPf1wkKxJ1qu3DhA1eMka.", "0XF");

insert into member (created_date, last_modified_date, email, member_type, nickname, wallet_address, password, profile_image)
values (now(), now(), "aa@naver.com", "USER", "aaa", "0XA", "", "https://picsum.photos/200"),
       (now(), now(), "bb@naver.com", "USER", "bbb", "0XB", "", "https://picsum.photos/200"),
       (now(), now(), "cc@naver.com", "USER", "ccc", "0XC", "", "https://picsum.photos/200"),
       (now(), now(), "dd@naver.com", "USER", "ddd", "0XD", "", "https://picsum.photos/200"),
       (now(), now(), "ee@naver.com", "USER", "eee", "0XE", "", "https://picsum.photos/200"),
       (now(), now(), "test@naver.com", "USER", "test", "0XF", "", "https://picsum.photos/200"),
       (now(), now(), "ahj@naver.com", "USER", "ahj", "0xba919124c708c11fd15a59b150ead9cb75616a56", "", "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/member/default/3.jpg");


insert into startup (created_date, last_modified_date, description, plan_paper, plan_paper_img
                    , discord_url, enroll_status, due_date, nft_image, nft_reserve_count, is_finished, is_goal, nft_target_count
                    , nft_price, road_map, reject_reason, startup_name, title, member_id, nft_description)
values (now(), now(), "startup description", "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/13e35725-8b22-4065-975e-fe15a840a1cduniqon%EB%B8%94%EB%A1%9D%EC%B2%B4%EC%9D%B8_%EC%A1%B0%EC%98%81%EB%8F%84%EB%A9%98%ED%86%A0%EB%8B%98.pdf",
        "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/fb45f1bc-0130-4932-a908-7e9f6b4b5985uniqonuniqon%EB%B8%94%EB%A1%9D%EC%B2%B4%EC%9D%B8_%EC%A1%B0%EC%98%81%EB%8F%84%EB%A9%98%ED%86%A0%EB%8B%98.jpg", null, null,
        DATE_ADD(now(), interval 1 day), "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/22f3d64a-ddfd-49f8-900d-be43a60affe7uniqon5dc267300fefd2738de6.jpg", 0, false, false, 10, 5, null, null, "ccc", "title", 3, "nft description"),
       (now(), now(), "startup description2", "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/13e35725-8b22-4065-975e-fe15a840a1cduniqon%EB%B8%94%EB%A1%9D%EC%B2%B4%EC%9D%B8_%EC%A1%B0%EC%98%81%EB%8F%84%EB%A9%98%ED%86%A0%EB%8B%98.pdf",
        "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/fb45f1bc-0130-4932-a908-7e9f6b4b5985uniqonuniqon%EB%B8%94%EB%A1%9D%EC%B2%B4%EC%9D%B8_%EC%A1%B0%EC%98%81%EB%8F%84%EB%A9%98%ED%86%A0%EB%8B%98.jpg",null, null,
        DATE_ADD(now(), interval 1 day), "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/22f3d64a-ddfd-49f8-900d-be43a60affe7uniqon5dc267300fefd2738de6.jpg", 0, false, false, 10, 5, null, null, "ddd", "title2", 4, "nft description2"),
       (now(), now(), "startup description3", "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/13e35725-8b22-4065-975e-fe15a840a1cduniqon%EB%B8%94%EB%A1%9D%EC%B2%B4%EC%9D%B8_%EC%A1%B0%EC%98%81%EB%8F%84%EB%A9%98%ED%86%A0%EB%8B%98.pdf",
        "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/fb45f1bc-0130-4932-a908-7e9f6b4b5985uniqonuniqon%EB%B8%94%EB%A1%9D%EC%B2%B4%EC%9D%B8_%EC%A1%B0%EC%98%81%EB%8F%84%EB%A9%98%ED%86%A0%EB%8B%98.jpg",null, null,
        DATE_ADD(now(), interval 1 day), "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/22f3d64a-ddfd-49f8-900d-be43a60affe7uniqon5dc267300fefd2738de6.jpg", 0, false, false, 10, 5, null, null, "test", "title3", 6, "nft description3"),
       (now(), now(), "startup description4", "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/13e35725-8b22-4065-975e-fe15a840a1cduniqon%EB%B8%94%EB%A1%9D%EC%B2%B4%EC%9D%B8_%EC%A1%B0%EC%98%81%EB%8F%84%EB%A9%98%ED%86%A0%EB%8B%98.pdf",
        "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/fb45f1bc-0130-4932-a908-7e9f6b4b5985uniqonuniqon%EB%B8%94%EB%A1%9D%EC%B2%B4%EC%9D%B8_%EC%A1%B0%EC%98%81%EB%8F%84%EB%A9%98%ED%86%A0%EB%8B%98.jpg",null, null,
        DATE_ADD(now(), interval 1 day), "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/22f3d64a-ddfd-49f8-900d-be43a60affe7uniqon5dc267300fefd2738de6.jpg", 0, false, false, 10, 5, null, null, "test", "title3", 7, "nft description4");

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
       (now(), now(), "INVESTING", 2, 2),
       (now(), now(), "INVESTED", 6, 1),
       (now(), now(), "INVESTING", 6, 2),
       (now(), now(), "INVESTED", 7, 1),
       (now(), now(), "INVESTING", 7, 2),
       (now(), now(), "CANCELED", 7, 3);
--        (now(), now(), "INVESTING", 6, 3);

insert into alarm  (created_date, last_modified_date, content, is_read, member_id)
values (now(), now(), "예약한 펀딩이 성공했습니다 !!", false, 1),
       (now(), now(), "예약한 펀딩이 실패했습니다 !!", false, 1),
       (now(), now(), "예약한 펀딩이 성공했습니다 !!", false, 2),
       (now(), now(), "예약한 펀딩이 실패했습니다 !!", true, 2),
       (now(), now(), "예약한 펀딩이 실패했습니다 !!", false, 3),
       (now(), now(), "예약한 펀딩이 성공했습니다 !!", false, 4),
       (now(), now(), "SSF를 입금하시겠습니까 ?", false, 4),
       (now(), now(), "예약한 펀딩이 성공했습니다 !!", true, 5),
       (now(), now(), "예약한 펀딩이 실패했습니다 !!", false, 5),
       (now(), now(), "예약한 펀딩이 성공했습니다 !!", false, 6),
       (now(), now(), "SSF를 입금하시겠습니까 ?", true, 6),
       (now(), now(), "입금이 완료되었습니다 !!", false, 6),
       (now(), now(), "예약한 펀딩이 실패했습니다 !!", false, 7),
       (now(), now(), "예약한 펀딩이 성공했습니다 !!", false, 7),
       (now(), now(), "SSF를 입금하시겠습니까 ?", true, 7),
       (now(), now(), "입금이 완료되었습니다 !!", false, 7);

insert into startup_favorite (created_date, last_modified_date, is_fav, member_id, startup_id)
values (now(), now(), true, 1, 1),
       (now(), now(), false, 1, 2),
       (now(), now(), false, 6, 1),
       (now(), now(), true, 6, 2),
       (now(), now(), false, 7, 1),
       (now(), now(), true, 7, 2),
       (now(), now(), true, 7, 3);


