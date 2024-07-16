#공지 페이지

[Flask 다운로드 코드]
pip install flask

[Flask mysql-connector-python 다운도드 코드]
pip install mysql-connector-python

[가상환경 구축 코드]
가상환경생성:python -m venv 가상환경이름
python -m venv venv

가상환경시작:가상환경 이름\Scripts\activate
venv\Scripts\activate

가상환경종료:deactivate

[db 생성 코드]
CREATE DATABASE minr_pro1;
USE minr_pro1;

CREATE TABLE login (
    login_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(50) NOT NULL,
    user_pw VARCHAR(100) NOT NULL,
    user_name VARCHAR(100) NOT NULL
);



