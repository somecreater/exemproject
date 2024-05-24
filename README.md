# exemproject
(주)엑셈에서 제시한 백엔드 개발자 과제입니다

## 개발 환경
-- 'eclipse 2023-09 (4.29.0)', 'sqldeveloper 22.2.1.234.1810', 'Oracle Database 18c', 'java 17'

## 사용된 파일

csv 파일


## 데이터베이스 테이블 생성 스크립트

create sequence seq_dust_code;
ALTER sequence seq_dust_code NOCACHE;
create sequence seq_dust_alert_code;
ALTER sequence seq_dust_alert_code NOCACHE;
create sequence seq_inspection;
ALTER sequence seq_inspection NOCACHE;

create table station_inspection(
code number(37) not null primary key,
measuring_station_name varchar2(20),
measuring_station_code varchar2(20),
alert_time date
)

create table dust_data(
code number(37) not null primary key,
measuring_station_name varchar2(20),
measuring_station_code varchar2(20),
alert_10_level number(1),
alert_25_level number(1),
alert_time date
);

create table dust_alert(
dust_alert_code number(37) not null primary key,
dust_code number(37) not null,
measuring_station_name varchar2(20),
alert_level number(1),
alert_time date,
constraint fk_dust_code foreign key(dust_code) references dust_data(code) ON DELETE SET NULL
);
