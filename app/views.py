# app/views.py

from flask import Blueprint, render_template
from app import app  # 플라스크 애플리케이션 객체 가져오기
from db.queries import execute_query

# 블루프린트 생성
bp = Blueprint('main', __name__)

# 라우트 정의
@bp.route('/')
def index():
    # 예시: 데이터베이스에서 데이터 가져오기
    result = execute_query('SELECT * FROM example_table')
    
    return render_template('index.html', data=result)

# 플라스크 애플리케이션에 블루프린트 등록
app.register_blueprint(bp)
