# db/__init__.py

from .connector import get_db_connection

# MySQL 데이터베이스 연결 객체 초기화
conn = get_db_connection()
