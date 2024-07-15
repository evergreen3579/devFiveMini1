# db/__init__.py

from flask import Flask
import mysql.connector

app = Flask(__name__)
app.secret_key = 'your_secret_key'  # 필요한 경우 플라스크 애플리케이션에 시크릿 키 설정

# MySQL 데이터베이스 연결 객체 초기화
def get_db_connection():
    config = {
        'user': 'root',
        'password': '1234',
        'host': 'localhost',
        'database': 'minr_pro1',
        'port': '3306',  # MySQL 포트 번호 (기본값은 3306)
        'raise_on_warnings': True
    }
    
    try:
        conn = mysql.connector.connect(**config)
        return conn
    
    except mysql.connector.Error as e:
        print(f'Error connecting to MySQL database: {e}')
        return None
