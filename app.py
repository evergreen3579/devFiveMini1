# app.py

from flask import Flask, render_template
from app.views import app as app_blueprint

app = Flask(__name__)
app.secret_key = b'_5#y2L"F4Q8z\n\xec]/'  # 임의의 시크릿 키 설정

# Blueprint 등록
app.register_blueprint(app_blueprint)

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/signup')
def signup():
    return render_template('signup.html')

if __name__ == '__main__':
    app.run(debug=True)
