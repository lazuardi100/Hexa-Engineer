import json
import requests
import tensorflow as tf
import os
from Sastrawi.StopWordRemover.StopWordRemoverFactory import StopWordRemoverFactory
from Sastrawi.Stemmer.StemmerFactory import StemmerFactory
import string

SIZE = 128
MODEL_URI = 'http://localhost:8501/v1/models/pets:predict'

model_path = "./EXT_Model_v1"
ext_model = tf.keras.models.load_model(model_path)

def preprocess_text(text):
  #lowercase all character in the text
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
    data = preprocess_text(data)
    prediction = ext_model.predict(data)
   # prediction = ext_model.predict(data.get("instances"))
    prediction_string = [str(pred) for pred in prediction]
    response_json = {
        "data" : data,
        #"data" : data.get("instances"),
        "prediction": list(prediction_string)
    }

    return json.dumps(response_json)
