# db/connector.py

# db/connector.py

import mysql.connector

def get_db_connection():
    config = {
        'user': 'root',
        'password': '1234',
        'host': 'localhost',
        'database': 'minr_pro1',
        'port': '3306',
        'raise_on_warnings': True
    }

    try:
        conn = mysql.connector.connect(**config)
        return conn

    except mysql.connector.Error as e:
        print(f'Error connecting to MySQL database: {e}')
        return None
