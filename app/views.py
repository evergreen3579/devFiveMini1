# app/views.py

from flask import Blueprint, render_template, request, redirect, url_for
from db.connector import get_db_connection

app = Blueprint('app', __name__, template_folder='../templates')

# 로그인 라우트
@app.route('/login', methods=['POST'])
def login():
    user_id = request.form['user_id']
    user_pw = request.form['user_pw']

    # MySQL 데이터베이스 연결 가져오기
    conn = get_db_connection()

    if conn:
        cursor = conn.cursor()

        # 사용자 정보 조회 쿼리 실행
        query = "SELECT user_id FROM login WHERE user_id = %s AND user_pw = %s"
        cursor.execute(query, (user_id, user_pw))
        result = cursor.fetchone()  # 쿼리 결과 가져오기

        cursor.close()
        conn.close()

        if result:
            return '로그인 성공'
        else:
            return '로그인 실패'

    else:
        return '데이터베이스 연결 실패'

@app.route('/')
def index():
    return render_template('index.html')
