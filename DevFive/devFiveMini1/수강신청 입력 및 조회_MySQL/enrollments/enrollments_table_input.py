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
    # 사용자로부터 입력 받기
    student_id = int(input("학생 번호: "))
    course_id = int(input("작성글 번호: "))

    # 커서 생성
    cursor = connection.cursor()

    # enrollments 테이블에 데이터 입력
    insert_query = "INSERT INTO enrollments (student_id, course_id) VALUES (%s, %s)"
    cursor.execute(insert_query, (student_id, course_id))
    connection.commit()

    print("Enrollment record inserted successfully.")

except ValueError:
    print("Please enter valid integers for student ID and course ID.")

except mysql.connector.Error as error:
    print(f"Failed to insert record into MySQL table: {error}")

finally:
    # 리소스 정리
    if connection.is_connected():
        cursor.close()
        connection.close()
        print("MySQL connection is closed.")
