# app.py

from flask import Flask, render_template
from app.views.login import app as app_blueprint
from app.views.posts import posts_bp

app = Flask(__name__)
app.secret_key = b'_5#y2L"F4Q8z\n\xec]/'  # 임의의 시크릿 키 설정

# Blueprint 등록
app.register_blueprint(app_blueprint)
app.register_blueprint(posts_bp)

posts = [{'title': 'Post 1', 'content': 'Content 1'},
         {'title': 'Post 2', 'content': 'Content 2'}]

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/signup')
def signup():
    return render_template('signup.html')

@app.route('/posts')
def posts():
    return render_template('posts.html')

@app.route('/posts/create_post')
def create_post():
    # 실제 데이터 로직에 맞게 데이터를 처리한 후 posts 변수에 할당
    # 이후 해당 데이터를 템플릿에 전달
    return render_template('posts.html', posts=posts)


if __name__ == '__main__':
    app.run(debug=True)
