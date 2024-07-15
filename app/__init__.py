from flask import Flask, render_template

app = Flask(__name__)

# 첫 화면을 index.html로 렌더링하는 예제
@app.route('/')
def index():
    return render_template('index.html')

if __name__ == '__main__':
    app.run(debug=True)
