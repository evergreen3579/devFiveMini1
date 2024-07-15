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

    # 사용자 입력을 받아 데이터 입력
    student_name = input("이름 입력: ")

    # 데이터 삽입
    sql = "INSERT INTO students (name) VALUES (%s)"
    cursor.execute(sql, (student_name,))

    # 변경 사항을 커밋
    connection.commit()

    print(f"{cursor.rowcount} record inserted with id: {cursor.lastrowid}")

except mysql.connector.Error as error:
    print(f"Failed to insert data into MySQL table: {error}")

finally:
    # 리소스 정리
    if connection.is_connected():
        cursor.close()
        connection.close()
        print("MySQL connection is closed.")
