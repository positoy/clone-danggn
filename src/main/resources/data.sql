-- DROP TABLE IF EXISTS users;

-- CREATE TABLE users (
--     id BIGINT PRIMARY KEY,
--     nickname VARCHAR(20) default NULL,
--     profile_image VARCHAR(100) default NULL,
--     name VARCHAR(20) default NULL,
--     access_token VARCHAR(100)  default NULL,
--     refresh_token VARCHAR(100) default NULL,
--     token_type VARCHAR(100) default NULL,
--     expires_in VARCHAR(100) default NULL,
--     area VARCHAR(20) default NULL,
--     manner INT default 368
-- );

INSERT INTO users (id, nickname, profile_image, name, access_token, refresh_token, token_type, expires_in, area, manner)
VALUES
    (-1, 'uknown', '/img/user/0.png', 'known', '', '', '', '', '달나라', 368),
    (0, '앤디', '/img/user/0.png', '앤', '', '', '', '', '성남시 분당구 운중동', 368),
    (1, '브리트니', '/img/user/1.jpg', '브리', '', '', '', '', '성남시 분당구 정자동', 368),
    (2, '디미트리', '/img/user/2.jpg', '디미', '', '', '', '', '수원시 영통구 광교동', 368);

-- DROP TABLE IF EXISTS goods;

-- CREATE TABLE goods (
--     id VARCHAR(30) PRIMARY KEY,
--     title VARCHAR(50) default NULL,
--     area VARCHAR(20) default NULL,
--     timestamp VARCHAR(20) default 0,
--     price INT default 0,
--     chat INT default 0,
--     liked INT default 0,
--     img VARCHAR(100) default NULL,
--     category VARCHAR(30) default NULL,
--     body VARCHAR(300) default NULL,
--     userid BIGINT default 0
-- );

INSERT INTO goods (id, title, area, timestamp, price, chat, liked, img, category, body, userid)
VALUES
    ('0', '라오스 다이어리','성남시 분당구 운중동','1594645127',2000,0,5, '/img/goods/001_1.jpg', '디지털/가전', '라오스에서 구입한 다이어리입니다.', 0),
    ('1', '자이글','성남시 분당구 정자동','1594645127',20000,0,0, '/img/goods/002_1.jpg', '디지털/가전', '2번 밖에 안 쓴 자이글 팝니다', 1),
    ('2', '삼성 파워건 VS80M8090KC 싸게 팔아요','성남시 분당구 운중동','1594645127',270000,1,3, '/img/goods/003_1.jpg', '디지털/가전', '미개봉품입니다.', 0),
    ('3', '삼성 파워건 무선청소기 새 배터리!','성남시 분당구 정자동','594645127',150000,0,3, '/img/goods/004_1.jpg', '디지털/가전', '한번도 사용하지 않은 배터리입니다.', 1),
    ('4', '바질 분양합니다','성남시 분당구 정자동','594645127',150000,0,3, '/img/goods/000_empty.png', '디지털/가전', '집에서 키우는 바질 분양합니다.', 1);
    