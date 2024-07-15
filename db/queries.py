# db/queries.py

from . import get_db_connection

# 데이터베이스 연결 객체 가져오기
conn = get_db_connection()

# 사용자 로그인 확인 함수
def check_login(user_id, user_pw):
    cursor = conn.cursor()
    query = "SELECT * FROM login WHERE user_id = %s AND user_pw = %s"
    cursor.execute(query, (user_id, user_pw))
    user = cursor.fetchone()
    cursor.close()
    return user
