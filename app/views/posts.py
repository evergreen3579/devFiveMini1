# app/views/posts.py

from flask import Blueprint, request, jsonify
from datetime import datetime

posts_bp = Blueprint('posts', __name__)

# 모델 대신 간단한 데이터 구조를 사용합니다.
posts = []

# 글 생성 API
@posts_bp.route('/create_post', methods=['POST'])
def create_post():
    data = request.get_json()

    title = data.get('title')
    content = data.get('content')

    if not title or not content:
        return jsonify({'error': 'Title and content are required'}), 400

    new_post = {
        'id': len(posts) + 1,
        'title': title,
        'content': content,
        'created_at': datetime.utcnow().strftime('%Y-%m-%d %H:%M:%S')
    }

    posts.append(new_post)
    return jsonify(new_post), 201

# 글 삭제 API
@posts_bp.route('/delete_post/<int:post_id>', methods=['DELETE'])
def delete_post(post_id):
    post_to_delete = None
    for post in posts:
        if post['id'] == post_id:
            post_to_delete = post
            break

    if post_to_delete:
        posts.remove(post_to_delete)
        return jsonify({'message': 'Post deleted successfully'}), 200
    else:
        return jsonify({'error': 'Post not found'}), 404

# 모든 글 조회 API
@posts_bp.route('/posts', methods=['GET'])
def get_posts():
    return jsonify(posts), 200
