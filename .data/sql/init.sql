CREATE TABLE CAR (
  id             SERIAL NOT NULL PRIMARY KEY, -- 고유 식별 아이디
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
  image_url      VARCHAR(255) NOT NULL, -- 차량 사진
  brand          VARCHAR(50) NOT NULL, -- 브랜드명
  vin            VARCHAR(50) NOT NULL, -- 차대 번호
  lat            double precision NOT NULL, -- 위도
  lon            double precision NOT NULL, -- 경도
  posting_date   TIMESTAMP NOT NULL, -- 게시 일자
  region         VARCHAR(50) NOT NULL -- 판매 지역
);