# app.py

from flask import Flask
from app.views import app as app_blueprint

app = Flask(__name__)
app.secret_key = 'your_secret_key'  # 필요한 경우 플라스크 애플리케이션에 시크릿 키 설정

# 블루프린트 등록
app.register_blueprint(app_blueprint)

if __name__ == '__main__':
    app.run(debug=True)
