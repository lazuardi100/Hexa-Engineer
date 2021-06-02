import json
import os
import time

from flask import Flask, request
from flask_mysqldb import MySQL
from db import initialize as init_db

from utility import to_JSON
from model import predict as prediction
import numpy as np

app = Flask(__name__)

init_db(app)

mysql = MySQL(app)


@app.route("/")
def index():
    return "Hello world"


@app.route('/fetch/<value>', methods=['POST'])
def fetch(value):
    return value


@app.route('/api/get/user', methods=['GET'])
def get_user():
    cur = mysql.connection.cursor()
    cur.execute('SELECT * FROM USER_TABLE')
    column = cur.description
    res = to_JSON(cur.fetchall(), column)
    cur.close()
    return json.dumps(res)


@app.route('/api/get/user/<int:id>', methods=['GET'])
def get_detail_user(id):
    cur = mysql.connection.cursor()
    cur.execute('SELECT * FROM DETAIL_USER WHERE ID_USER =' + str(id))
    column = cur.description
    res = to_JSON(cur.fetchall(), column)
    cur.close()
    return json.dumps(res)


@app.route('/api/predict/<int:id>', methods=['POST'])
def predict(id):
    words = request.args.get("words")

    np_words = np.array([words])

    test_data = {
        'instances': np_words.tolist()
    }
    data = json.dumps(test_data)
    response_json = prediction(np_words.tolist())

    return response_json


@app.route('/api/predict/test', methods=['GET'])
def predict_test():
    words = 'bangun tidur siang tengah aneh pindah texas milik konsentrasi halhal kerja rumah kelas 10 cepat jam ' \
            'dentang 4 henti mudah laku pindah kerja rumah tantang kerja sibuk putus habis berjamjam laku bayar ' \
            'perhati kelas barangbarang benarbenar keras tinggal lacak tahun malas jenius hei lambat baik benarbenar ' \
            'fokus tinggal kampus konsentrasi mudah sayang tinggal rumah awas ketat tua omel adik omel omel omel ' \
            'titik repot pergi jalan sekolah pergi pustaka ajar pindah memberitahu salah pindah pergi lindung milik ' \
            'khawatir dunia satusatunya jaga kamar bersih bantu bisnis uang ut hidup asrama apartemen semester pikir ' \
            'ambil untung off topik pergi jalan enam malam milik ledak cinta austin tinggal va pergi dc waktu milik ' \
            'ledak siswa lari malam bersenangsenang tanggung bersenangsenang prioritas lurus tinggal rumah kau harap ' \
            'laku tanggung adik kacau pergi gila pindah guru tinggi kacau karir guru tinggi pesta alas utama pergi ' \
            'bersenangsenang biar pergi jajah dunia india budaya india nilainilai india lawan bersenangsenang maksud ' \
            'temu orangorang pacar pesta bersenangsenang sekolah sulit pikir milik bebas tempat tekan buat sekolah ' \
            'tua harap senang tulis pergi tulis bantu pikir urut harap bersenangsenang baca untung ta '
    input = np.array([words])

    test_data = {
        'instances': input.tolist()
    }
    data = json.dumps(test_data)
    response_json = prediction(input.tolist())

    return response_json


if __name__ == '__main__':
    # app.run()
    start_time = time.time()
    app.run(host="0.0.0.0", port=int(os.environ.get("PORT", 8080)))
    print("Time consumed to start server: {} second".format(str(time.time()-start_time)))
