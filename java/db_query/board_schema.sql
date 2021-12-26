# board DB 생성
CREATE DATABASE board;

# board DB 사용
USE board;

# article table 생성
CREATE TABLE article (
    idx INT(5) PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    `body` TEXT NOT NULL,
    nickname VARCHAR(50),
    regDate DATETIME # 게시물 등록일
);

DESC article;

INSERT INTO article SET title = 'aaa', `body` = 'bbb', nickname = 'ccc', regDate = '20211121103300';

SELECT *
FROM article;

# 게시물 수정 query
UPDATE article
SET title = 'kkk',
`body` = 'jjj'
WHERE idx = 5;

# 특정 게시물 조회 query
SELECT *
FROM article
WHERE idx = 9;

# 특정 게시물 삭제 query
DELETE
FROM article
WHERE idx = 9;

# 특정 키워드를 포함하는 게시물 검색 query
SELECT *
FROM article
WHERE title LIKE '%이%';

# 회원 테이블 생성
CREATE TABLE `member` (
    idx INT(5) PRIMARY KEY AUTO_INCREMENT,
    loginId VARCHAR(30) NOT NULL,
    loginPw VARCHAR(30) NOT NULL,
    nickname VARCHAR(50) NOT NULL,
    regDate DATETIME NOT NULL
);

DESC `member`;

# 회원 데이터 생성 query
INSERT INTO `member`
SET loginId = 'hong123',
loginPw = 'h1234',
nickname = '홍길동',
regDate = NOW();

SELECT *
FROM `member`;