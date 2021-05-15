from flask import Flask
from flask_mysqldb import MySQL

app = Flask(__name__)

app.config['MYSQL_HOST'] = '34.101.238.80'
app.config['MYSQL_USER'] = 'user'
app.config['MYSQL_PASSWORD'] = 'Rahasia'
app.config['MYSQL_DB'] = 'BANGKIT'
mysql = MySQL(app)

@app.route("/")
def index():
    return "Hello world"