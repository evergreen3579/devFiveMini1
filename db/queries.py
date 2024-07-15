# db/queries.py

from . import conn

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
