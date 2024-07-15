# db/connector.py

from . import get_db_connection

# MySQL 데이터베이스 연결 객체 가져오기
conn = get_db_connection()

# 데이터베이스 쿼리 실행 함수
def execute_query(query, params=None):
    cursor = conn.cursor()
    try:
        if params:
            cursor.execute(query, params)
        else:
            cursor.execute(query)
        
        conn.commit()  # 쿼리 실행 후 커밋
        return cursor.fetchall()  # 쿼리 결과 반환
    
    except mysql.connector.Error as e:
        print(f'Error executing query: {e}')
        return None
    
    finally:
        cursor.close()
