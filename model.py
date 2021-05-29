import json
import requests
import tensorflow as tf
import os

SIZE = 128
MODEL_URI = 'http://localhost:8501/v1/models/pets:predict'

model_path = "./EXT_Model_v1"
ext_model = tf.keras.models.load_model(model_path)

def predict(data):
    prediction = ext_model.predict(data)
   # prediction = ext_model.predict(data.get("instances"))
    prediction_string = [str(pred) for pred in prediction]
    response_json = {
        "data" : data,
        #"data" : data.get("instances"),
        "prediction": list(prediction_string)
    }

    return json.dumps(response_json)
