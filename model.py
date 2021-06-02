import json
import requests
import numpy
import matplotlib
import string, time
import os
from Sastrawi.StopWordRemover.StopWordRemoverFactory import StopWordRemoverFactory
from Sastrawi.Stemmer.StemmerFactory import StemmerFactory

import tensorflow as tf
import tensorflow_hub as hub

import numpy as np

SIZE = 128
MODEL_URI = 'http://localhost:8501/v1/models/pets:predict'

custom_objects = {'KerasLayer':hub.KerasLayer,'tf': tf}
ext_model = tf.keras.models.load_model('./ext_model.h5',custom_objects=custom_objects)
# neu_model = tf.keras.models.load_model('./neu_model.h5',custom_objects=custom_objects)
# agr_model = tf.keras.models.load_model('./agr_model.h5',custom_objects=custom_objects)
# con_model = tf.keras.models.load_model('./con_model.h5',custom_objects=custom_objects)
# opn_model = tf.keras.models.load_model('./opn_model.h5',custom_objects=custom_objects)

def preprocess_text(input):
  #lowercase all character in the text
  text = input[0]
  text = text.lower()
  #remove punctuation
  text = text.translate(str.maketrans("","",string.punctuation))
  #remove leading and trailing whitespace
  text = text.strip()
  #remove StopWord
  stopword = StopWordRemoverFactory().create_stop_word_remover()
  text = stopword.remove(text)
  #stemming
  stemmer = StemmerFactory().create_stemmer()
  text = stemmer.stem(text)
  return text

def predict(data):
    start_time = time.time()

    data = preprocess_text(data)
    ext_prediction = ext_model.predict(data)
    # neu_prediction = neu_model.predict(data)
    # agr_prediction = agr_model.predict(data)
    # con_prediction = con_model.predict(data)
    # opn_prediction = opn_model.predict(data)

   # prediction = ext_model.predict(data.get("instances"))
    ext_prediction_string = [str(pred) for pred in ext_prediction]
    # neu_prediction_string = [str(pred) for pred in neu_prediction]
    # agr_prediction_string = [str(pred) for pred in agr_prediction]
    # con_prediction_string = [str(pred) for pred in con_prediction]
    # opn_prediction_string = [str(pred) for pred in opn_prediction]
    response_json = {
        "data" : data,
        #"data" : data.get("instances"),
        "ext_prediction": list(ext_prediction_string),
        # "neu_prediction": list(neu_prediction_string),
        # "agr_prediction": list(agr_prediction_string),
        # "con_prediction": list(con_prediction_string),
        # "opn_prediction": list(opn_prediction_string),
        "time_consumed": str(time.time()-start_time)
    }

    return json.dumps(response_json)


# if __name__ == '__main__':
#     words = 'bangun tidur siang tengah aneh pindah texas milik konsentrasi halhal kerja rumah kelas 10 cepat jam ' \
#             'dentang 4 henti mudah laku pindah kerja rumah tantang kerja sibuk putus habis berjamjam laku bayar ' \
#             'perhati kelas barangbarang benarbenar keras tinggal lacak tahun malas jenius hei lambat baik benarbenar ' \
#             'fokus tinggal kampus konsentrasi mudah sayang tinggal rumah awas ketat tua omel adik omel omel omel ' \
#             'titik repot pergi jalan sekolah pergi pustaka ajar pindah memberitahu salah pindah pergi lindung milik ' \
#             'khawatir dunia satusatunya jaga kamar bersih bantu bisnis uang ut hidup asrama apartemen semester pikir ' \
#             'ambil untung off topik pergi jalan enam malam milik ledak cinta austin tinggal va pergi dc waktu milik ' \
#             'ledak siswa lari malam bersenangsenang tanggung bersenangsenang prioritas lurus tinggal rumah kau harap ' \
#             'laku tanggung adik kacau pergi gila pindah guru tinggi kacau karir guru tinggi pesta alas utama pergi ' \
#             'bersenangsenang biar pergi jajah dunia india budaya india nilainilai india lawan bersenangsenang maksud ' \
#             'temu orangorang pacar pesta bersenangsenang sekolah sulit pikir milik bebas tempat tekan buat sekolah ' \
#             'tua harap senang tulis pergi tulis bantu pikir urut harap bersenangsenang baca untung ta '
#     input = np.array([words])
#
#     test_data = {
#         'instances': input.tolist()
#     }
#     data = json.dumps(test_data)
#     response_json = predict(input.tolist())