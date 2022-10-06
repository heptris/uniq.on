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
       (now(), now(), "cc@naver.com", "USER", "모바비", "0XC", "", "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/ac3f047b-c782-4fe7-b2de-0d22e5892179uniqonlogo1.png"),
       (now(), now(), "dd@naver.com", "USER", "엠에스자원", "0XD", "", "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/8c3861d0-d657-4b1e-ba04-2c99e5743fbduniqonlogo2.png"),
       (now(), now(), "ee@naver.com", "USER", "eee", "0XE", "", "https://picsum.photos/200"),
       (now(), now(), "test@naver.com", "USER", "퓨로닉스", "0XF", "", "https://picsum.photos/200"),
       (now(), now(), "ahj@naver.com", "USER", "더하이", "0xba919124c708c11fd15a59b150ead9cb75616a56", "", "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/0795acba-98a0-4cfc-bc4c-86dd02c61d24uniqonlogo3.png"),
       (now(), now(), "ejshin0213@naver.com", "USER", "신라면", "0x892bef9c95ad82bdf6fb1207eebd3959be7c1c42", "", "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/95101c8f-0719-4e15-8a2f-15461fb4b877uniqonlogo4.png");


insert into startup (created_date, last_modified_date, description, plan_paper, plan_paper_img
                    , discord_url, enroll_status, due_date, nft_image, nft_reserve_count, is_finished, is_goal, nft_target_count
                    , nft_price, road_map, reject_reason, startup_name, title, member_id, nft_description, token_uri, metadata)
values (now(), now(), "이번 여름은 에어컨 없이 건강하게!!", "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/e4057d27-775e-4887-bd31-1c3ff4368eb3uniqonplan_paper1.pdf",
        "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/8fc6c17f-f915-48e0-a5c9-83d0e782e2ccuniqonuniqonplan_paper1.jpg", null, null,
        DATE_ADD(now(), interval 1 day), "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/4ccfac1e-c598-4d91-9394-bcf5e9cee9d6uniqonnft_image1.jpg", 7, false, false, 10, 8, "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/84f1d435-e2ee-4987-a843-bafdf63932b1uniqonroad_map1.jpg", null, "모바비", "소재가 다른 냉감패드. 한번 써보시고 결정하세요", 3, "우주의 별동별", "https://ipfs.io/ipfs/bafyreienvkbfakzzpot56ae7mcipysyuuooygtwha4kqsduek2mxrczki4/metadata.json"
        , "{\"name\":\"ahj\",\"description\":\"1::132\",\"image\":\"ipfs://bafybeictbl4grabi7oaqawuuzxxa7is47hl737vkcbhns7lbkvi27bxjx4/street-gfaefddf00_1920.jpg\"}"),
       (now(), now(), "금리 10%의 수익과 환경 보전을 동시에!", "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/3388d19e-5af7-4079-8bdd-5d8ee5827c61uniqonplan_paper2.pdf",
        "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/099e3ee6-4232-4507-874a-21e31b55e620uniqonuniqonplan_paper2.jpg",null, null,
        DATE_ADD(now(), interval 2 day), "https://ipfs.io/ipfs/bafybeihqoym3kapp5y5omzreooit2d5ngk6w26vin226xvyl644xbwu674/2.png", 11, false, false, 15, 5, "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/5afdce61-e9e5-497f-b00f-0d662378bbbduniqonroad_map2.png", null, "엠에스자원", "골치 아픈 산업폐기물 엠에스를 통해 자원이 되다!", 4, "가면쓴 곰돌이", "https://ipfs.io/ipfs/bafyreienvkbfakzzpot56ae7mcipysyuuooygtwha4kqsduek2mxrczki4/metadata.json", null),
       (now(), now(), "유기농 청보리로 만든 핸드메이드 유기농 마스크팩", "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/6e233c79-fa14-4cc6-9271-d14697e1a52buniqonplan_paper3.pdf",
        "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/47182607-620a-44a0-89bb-5f8a3528b284uniqonuniqonplan_paper3.jpg",null, null,
        DATE_ADD(now(), interval 50 minute), "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/e7359507-b813-4d58-bbc2-69573277ab2auniqonnft_image3.jpg", 21, false, false, 30, 3, "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/f7c78b26-4fd9-483e-8933-24497602381buniqonroad_map3.png", null, "퓨로닉스", "유기농함량 66.5%! 식품의약품안전처 인증 유기농 마스크팩!", 6, "고뇌하는 사람", "https://ipfs.io/ipfs/bafyreienvkbfakzzpot56ae7mcipysyuuooygtwha4kqsduek2mxrczki4/metadata.json", null),
       (now(), now(), "맛과 영양을 고려한 우리아이 첫 유기농 쌀과자", "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/4f9cd9cf-cac7-4b75-881e-cc6c9adf3859uniqonplan_paper4.pdf",
        "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/f19a8541-3d13-487d-a2c2-311f94e3c17duniqonuniqonplan_paper4.jpg",null, null,
        DATE_ADD(now(), interval 3 day), "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/33d5f406-2e75-46f0-adcc-b1f04aa04f78uniqonnft_image4.jpg", 3, false, false, 5, 15, "https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/9ed7f493-acd0-4523-9436-2b61423b929duniqonroad_map4.png", null, "더하이", "우리아이 첫 간식 베베당", 7, "미소녀 검사", "https://ipfs.io/ipfs/bafyreienvkbfakzzpot56ae7mcipysyuuooygtwha4kqsduek2mxrczki4/metadata.json", null);

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



