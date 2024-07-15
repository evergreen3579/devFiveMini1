# app.py

from flask import Flask
from app.views import app as app_blueprint

app = Flask(__name__)
app.register_blueprint(app_blueprint)

if __name__ == '__main__':
    app.run(debug=True)
