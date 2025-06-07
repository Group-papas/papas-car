CREATE TABLE USERS (
   user_id SERIAL NOT NULl PRIMARY KEY, -- 고유 식별 아이디
   nickname VARCHAR(50) NOT NULL, -- 사용자 이름
   birthday DATE NOT NULL, -- 생년월일
   gender VARCHAR(1) NOT NULL, -- 성별
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL -- 가입일
);

CREATE TABLE follow (
  follow_id SERIAL, -- 고유 식별 아이디
  from_user BIGINT NOT NULL,     -- 팔로우 하는 사람
  to_user BIGINT NOT NULL,    -- 팔로우 당하는 사람
  followed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL  -- 팔로우 시점
);

CREATE TABLE CAR (
  car_id             SERIAL NOT NULL PRIMARY KEY, -- 고유 식별 아이디
  price          INTEGER NOT NULL, -- 판매 가격
  year           INTEGER NOT NULL, -- 연식
  manufacturer   VARCHAR(50) NOT NULL, -- 브랜드명
  model          VARCHAR(50) NOT NULL, -- 모델명
  condition      VARCHAR(50) NOT NULL, -- 상태
  engine         INTEGER NOT NULL, -- 엔진 실린더 개수
  odometer       BIGINT NOT NULL, -- 주행거리
  title_status   VARCHAR(10) NOT NULL, -- 차량 상태
  transmission   VARCHAR(10) NOT NULL, -- 수동/자동
  drive          VARCHAR(10) NOT NULL, -- 전륜/후륜/4륜
  size           VARCHAR(10) NOT NULL, -- 차량 크기
  type           VARCHAR(10) NOT NULL, -- 차종
  color          VARCHAR(10) NOT NULL, -- 색상
  brand          VARCHAR(50) NOT NULL, -- 브랜드명
  vin            VARCHAR(50) NOT NULL, -- 차대 번호
  lat            double precision NOT NULL, -- 위도
  lon            double precision NOT NULL, -- 경도
  posting_date   TIMESTAMP NOT NULL, -- 게시 일자
  region         VARCHAR(50) NOT NULL, -- 판매 지역
  user_id        INTEGER NOT NULL -- 판매자 참조 아이디
);

CREATE TABLE CAR_IMAGE (
  img_id SERIAL NOT NULL PRIMARY KEY, -- 고유 식별 아이디
  origin_name VARCHAR(255) NOT NULL, -- 고유 이름
  hash_name VARCHAR(64) NOT NULL, -- 해쉬 네임
  file_path VARCHAR(255) NOT NULL, -- 저장 경로
  extension VARCHAR(10) NOT NULL, -- 확장자
  is_thumbnail boolean NOT NULL, -- 썸네일 여부
  car_id INTEGER, -- 차 참조 아이디
  created_at TIMESTAMP NOT NULL -- 이미지 생성 날짜
);

CREATE TABLE HASHTAG (
  hashtag_id SERIAL NOT NULL PRIMARY KEY, -- 해쉬태그 고유 식별 아이디
  name VARCHAR(100) NOT NULL, -- 해쉬태그 이름
  created_at TIMESTAMP NOT NULL -- 해쉬태그 생성 일시
);

CREATE TABLE CAR_HASHTAG_MAPPING (
  car_hashtag_id SERIAL NOT NULL PRIMARY KEY, -- 차-해쉬태그 매핑 고유 식별 아이디
  car_id INTEGER, -- 차 참조 아이디
  hashtag_id INTEGER, -- 해쉬태그 참조 아이디
  created_at TIMESTAMP NOT NULL -- 매핑 생성 일시
);