# app/views.py

from flask import Blueprint, render_template, request, redirect, url_for, session, flash
from db.connector import get_db_connection  # 예시로 사용하는 경우, 실제 프로젝트에 맞게 수정 필요

login_bp = Blueprint('login', __name__)
app = Blueprint('app', __name__, template_folder='templates')

# 로그인 라우트
@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        user_id = request.form['user_id']
        user_pw = request.form['user_pw']

        # 예시로 데이터베이스 연결 함수 사용
        conn = get_db_connection()

        if conn:
            cursor = conn.cursor()

            # 사용자 정보 조회 쿼리 실행 (예시)
            query = "SELECT user_id FROM login WHERE user_id = %s AND user_pw = %s"
            cursor.execute(query, (user_id, user_pw))
            result = cursor.fetchone()

            cursor.close()
            conn.close()

            if result:
                # 로그인 성공
                session['user_id'] = user_id  # 세션에 사용자 ID 저장
                flash('로그인 성공', 'success')
                return redirect(url_for('app.main'))
            else:
                # 로그인 실패
                flash('아이디 또는 비밀번호를 다시 입력해 주세요', 'error')
                return redirect(url_for('app.index'))

        else:
            flash('데이터베이스 연결 실패', 'error')
            return redirect(url_for('app.index'))

    # GET 요청인 경우
    return render_template('index.html')

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/main')
def main():
    if 'user_id' in session:
        return render_template('main.html')
    else:
        flash('로그인이 필요합니다', 'error')
        return redirect(url_for('app.index'))

@app.route('/logout')
def logout():
    session.pop('user_id', None)
    flash('로그아웃 되었습니다', 'info')
    return redirect(url_for('app.index'))
