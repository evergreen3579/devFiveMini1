import mysql.connector

# MySQL 서버 연결 정보
host = 'localhost'
database = 'python'
username = 'root'
password = '1234'

# MySQL 데이터베이스에 연결
connection = mysql.connector.connect(
  host=host,
  database=database,
  user=username,
  password=password
)

try:
    # 커서 생성
    cursor = connection.cursor()

    # students 테이블에서 데이터 조회 예시
    cursor.execute("SELECT * FROM courses")
    rows = cursor.fetchall()

    # 결과 출력
    for row in rows:
        print(row)

finally:
    # 리소스 정리
    cursor.close()
    connection.close()
