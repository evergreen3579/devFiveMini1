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

    # enrollments, students, courses 테이블을 JOIN하여 데이터 조회
    query = """
        SELECT e.enrollment_id, s.name as student_name, c.title as course_title
        FROM enrollments e
        INNER JOIN students s ON e.student_id = s.student_id
        INNER JOIN courses c ON e.course_id = c.course_id
    """
    cursor.execute(query)
    rows = cursor.fetchall()

    # 결과 출력
    for row in rows:
        enrollment_id, student_name, course_title = row
        print(f"번호: {enrollment_id}\n 이름: {student_name}, 내용: {course_title}")

except mysql.connector.Error as error:
    print(f"Failed to select data from MySQL tables: {error}")

finally:
    # 리소스 정리
    if connection.is_connected():
        cursor.close()
        connection.close()
        print("MySQL connection is closed.")
