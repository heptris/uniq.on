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
       (now(), now(), "ahj@naver.com", "USER", "ahj", "0xba919124c708c11fd15a59b150ead9cb75616a56", "", "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/member/default/3.jpg"),
       (now(), now(), "ejshin0213@naver.com", "USER", "신라면", "0x892bef9c95ad82bdf6fb1207eebd3959be7c1c42", "", "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/member/default/3.jpg");


insert into startup (created_date, last_modified_date, description, plan_paper, plan_paper_img
                    , discord_url, enroll_status, due_date, nft_image, nft_reserve_count, is_finished, is_goal, nft_target_count
                    , nft_price, road_map, reject_reason, startup_name, title, member_id, nft_description)
values (now(), now(), "startup description", "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/491de458-5c1d-41d8-942f-71b625f17cdcuniqonUniq.on.pdf",
        "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/23814390-aadd-4606-9c7b-406c1859de94uniqonuniqonUniq.on.jpg", null, null,
        DATE_ADD(now(), interval 1 day), "https://ipfs.io/ipfs/bafybeihqoym3kapp5y5omzreooit2d5ngk6w26vin226xvyl644xbwu674/2.png", 0, false, false, 10, 5, "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/e945a496-2f1a-4e21-9479-5f0c5b50114buniqon%EC%A0%9C%EB%AA%A9%EC%9D%84-%EC%9E%85%EB%A0%A5%ED%95%B4%EC%A3%BC%EC%84%B8%EC%9A%94_-001.jpg", null, "ccc", "title", 3, "nft description"),
       (now(), now(), "startup description2", "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/491de458-5c1d-41d8-942f-71b625f17cdcuniqonUniq.on.pdf",
        "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/23814390-aadd-4606-9c7b-406c1859de94uniqonuniqonUniq.on.jpg",null, null,
        DATE_ADD(now(), interval 1 day), "https://ipfs.io/ipfs/bafybeihqoym3kapp5y5omzreooit2d5ngk6w26vin226xvyl644xbwu674/2.png", 0, false, false, 10, 5, "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/e945a496-2f1a-4e21-9479-5f0c5b50114buniqon%EC%A0%9C%EB%AA%A9%EC%9D%84-%EC%9E%85%EB%A0%A5%ED%95%B4%EC%A3%BC%EC%84%B8%EC%9A%94_-001.jpg", null, "ddd", "title2", 4, "nft description2"),
       (now(), now(), "startup description3", "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/491de458-5c1d-41d8-942f-71b625f17cdcuniqonUniq.on.pdf",
        "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/23814390-aadd-4606-9c7b-406c1859de94uniqonuniqonUniq.on.jpg",null, null,
        DATE_ADD(now(), interval 1 day), "https://ipfs.io/ipfs/bafybeihqoym3kapp5y5omzreooit2d5ngk6w26vin226xvyl644xbwu674/2.png", 0, false, false, 10, 5, "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/e945a496-2f1a-4e21-9479-5f0c5b50114buniqon%EC%A0%9C%EB%AA%A9%EC%9D%84-%EC%9E%85%EB%A0%A5%ED%95%B4%EC%A3%BC%EC%84%B8%EC%9A%94_-001.jpg", null, "test", "title3", 6, "nft description3"),
       (now(), now(), "startup description4", "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/491de458-5c1d-41d8-942f-71b625f17cdcuniqonUniq.on.pdf",
        "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/23814390-aadd-4606-9c7b-406c1859de94uniqonuniqonUniq.on.jpg",null, null,
        DATE_ADD(now(), interval 1 day), "https://ipfs.io/ipfs/bafybeihqoym3kapp5y5omzreooit2d5ngk6w26vin226xvyl644xbwu674/2.png", 0, false, false, 10, 5, "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/e945a496-2f1a-4e21-9479-5f0c5b50114buniqon%EC%A0%9C%EB%AA%A9%EC%9D%84-%EC%9E%85%EB%A0%A5%ED%95%B4%EC%A3%BC%EC%84%B8%EC%9A%94_-001.jpg", null, "test", "title3", 7, "nft description4");

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

insert into startup_community (created_date, last_modified_date, content, title, hit, member_id, startup_id)
values (now(), now(), "Content Test", "Title Test", 0, 1, 1);

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
       (now(), now(), "CANCELED", 7, 3),
       (now(), now(), "INVESTED", 8, 1),
       (now(), now(), "INVESTING", 8, 2),
       (now(), now(), "CANCELED", 8, 3);
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
       (now(), now(), "입금이 완료되었습니다 !!", false, 7),
       (now(), now(), "예약한 펀딩이 실패했습니다 !!", false, 8),
       (now(), now(), "예약한 펀딩이 성공했습니다 !!", false, 8),
       (now(), now(), "SSF를 입금하시겠습니까 ?", true, 8),
       (now(), now(), "입금이 완료되었습니다 !!", false, 8);

insert into startup_favorite (created_date, last_modified_date, is_fav, member_id, startup_id)
values (now(), now(), true, 1, 1),
       (now(), now(), false, 1, 2),
       (now(), now(), false, 6, 1),
       (now(), now(), true, 6, 2),
       (now(), now(), false, 7, 1),
       (now(), now(), true, 7, 2),
       (now(), now(), true, 7, 3),
       (now(), now(), false, 8, 1),
       (now(), now(), true, 8, 2),
       (now(), now(), true, 8, 3);



