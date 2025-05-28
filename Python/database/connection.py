import os
from dotenv import load_dotenv

load_dotenv()

DB_DRIVER = os.getenv("DB_DRIVER", "mysql") #oder "postgresql" bzw. "sqlite"

if DB_DRIVER == "mysql":
    import mysql.connector
    def get_connection():
        return mysql.connector.connect(
            host=os.getenv("DB_HOST", "localhost"),  #jeweil mit Fallback-Wert
            port=(os.getenv("DB_PORT", "3306")),
            user=os.getenv("DB_USER", "root"),
            password=os.getenv("DB_PASS", ""),
            database=os.getenv("DB_NAME", "userdb")
        )

elif DB_DRIVER == "postgresql":
    import psycopg2
    def get_connection():
        return psycopg2.connect(
            host=os.getenv("DB_HOST", "localhost"),
            port=os.getenv("DB_PORT", "5432"),
            user=os.getenv("DB_USER", "postgres"),
            password=os.getenv("DB_PASS", ""),
            dbname=os.getenv("DB_NAME", "testdb")
        )

elif DB_DRIVER == "sqlite":
    import sqlite3
    def get_connection():
        #Pfad zur Datei-DB
        db_path = os.getenv("DB_PATH", "/path/to/database.db")
        return sqlite3.connect(db_path)

else:
    raise RuntimeError(f"Unknown DB_DRIVER: {DB_DRIVER}")