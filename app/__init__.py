# app/__init__.py

from flask import Flask
from app.views.posts import posts_bp
from app.views.login import login_bp

app = Flask(__name__)
app.register_blueprint(posts_bp, url_prefix='/posts')
app.register_blueprint(login_bp, url_prefix='/login')

if __name__ == '__main__':
    app.run(debug=True)