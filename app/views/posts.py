from flask import Blueprint, request, jsonify, render_template
from datetime import datetime
from db.connector import get_db_connection

posts_bp = Blueprint('posts', __name__, url_prefix='/posts')

# 글 생성 API
@posts_bp.route('/create_post', methods=['GET', 'POST'])
def create_post():
    if request.method == 'POST':
        title = request.form.get('title')
        content = request.form.get('content')
        if not title or not content:
            return jsonify({'error': 'Title and content are required'}), 400
        
        conn = get_db_connection()
        if conn:
            cursor = conn.cursor()
            query = "INSERT INTO posts (title, content, created_at) VALUES (%s, %s, %s)"
            cursor.execute(query, (title, content, datetime.utcnow().strftime('%Y-%m-%d %H:%M:%S')))
            conn.commit()
            cursor.close()
            conn.close()
            return jsonify({'message': 'Post created successfully'}), 201
        else:
            return jsonify({'error': 'Database connection failed'}), 500
    
    return render_template('create_post.html')


# 글 삭제 API
@posts_bp.route('/delete_post/<int:post_id>', methods=['DELETE'])
def delete_post(post_id):
    conn = get_db_connection()
    if conn:
        cursor = conn.cursor()
        query = "DELETE FROM posts WHERE id = %s"
        cursor.execute(query, (post_id,))
        conn.commit()
        cursor.close()
        conn.close()
        if cursor.rowcount > 0:
            return jsonify({'message': 'Post deleted successfully'}), 200
        else:
            return jsonify({'error': 'Post not found'}), 404
    else:
        return jsonify({'error': 'Database connection failed'}), 500


# 모든 글 조회 API
@posts_bp.route('/posts', methods=['GET'])
def get_posts():
    conn = get_db_connection()
    if conn:
        cursor = conn.cursor(dictionary=True)
        query = "SELECT * FROM posts"
        cursor.execute(query)
        posts = cursor.fetchall()
        cursor.close()
        conn.close()
        return jsonify(posts), 200
    else:
        return jsonify({'error': 'Database connection failed'}), 500


@posts_bp.route('/posts/<int:post_id>')
def post_detail(post_id):
    conn = get_db_connection()
    if conn:
        cursor = conn.cursor(dictionary=True)
        query = "SELECT * FROM posts WHERE id = %s"
        cursor.execute(query, (post_id,))
        post = cursor.fetchone()
        cursor.close()
        conn.close()
        if post:
            return render_template('post_detail.html', post=post)
        else:
            return jsonify({'error': 'Post not found'}), 404
    else:
        return jsonify({'error': 'Database connection failed'}), 500


@posts_bp.route('/')
def posts():
    return render_template('posts.html')
