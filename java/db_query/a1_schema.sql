# 개념/개체 -> table / 엔터티
# 속성 -> 컬럼
# 튜플, 레코드 -> 로우

CREATE DATABASE a1;

USE a1;

SHOW TABLES;

CREATE TABLE t_addr (
    idx INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(30) NOT NULL,   
    address VARCHAR(100) NOT NULL,
    phone VARCHAR(15) NOT NULL
);

DESC t_addr;

INSERT INTO t_addr SET `name` = '홍길동', address = '대전', phone = '010-1111-2222';

SELECT *
FROM t_addr;

UPDATE t_addr SET `name` = '홍길순', address = '서울', phone = '010-3333-4444' WHERE idx = 1;

DELETE FROM t_addr WHERE idx = 3;


# 특정 문자가 포함된 결과 찾기 = LIKE
# %로 특정 문자의 위치를 정할 수 있음
# %keyword - keyword로 끝나는 결과
# keyword% - keyword로 시작하는 결과
# %keyword% - keyword가 포함된 결과


USE a1;

SELECT *
FROM t_addr
WHERE `name` LIKE '%순%'; 

