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
    enrollment_id = int(input("번호 입력: "))

    # 커서 생성
    cursor = connection.cursor()

    # enrollments 테이블에서 입력받은 enrollment_id로 데이터 조회
    query = """
        SELECT e.enrollment_id, s.student_id, s.name as student_name, c.course_id, c.title as course_title
        FROM enrollments e
        INNER JOIN students s ON e.student_id = s.student_id
        INNER JOIN courses c ON e.course_id = c.course_id
        WHERE e.enrollment_id = %s
    """
    cursor.execute(query, (enrollment_id,))
    row = cursor.fetchone()

    # 결과 출력
    if row:
        enrollment_id, student_id, student_name, course_id, course_title = row
        print(f"번호: {enrollment_id}")
        print(f"이름: {student_name}")
        print(f"내용: {course_title}")
    else:
        print(f"No enrollment found with ID {enrollment_id}")

except ValueError:
    print("Please enter a valid integer for enrollment ID.")

except mysql.connector.Error as error:
    print(f"Failed to select data from MySQL table: {error}")

finally:
    # 리소스 정리
    if connection.is_connected():
        cursor.close()
        connection.close()
        print("MySQL connection is closed.")

