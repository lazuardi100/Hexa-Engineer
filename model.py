import json
import requests

SIZE = 128
MODEL_URI = 'http://localhost:8501/v1/models/pets:predict'

def predict(value):
    data = json.dumps({
        'instances': [input]
    })

    response = requests.post(MODEL_URI, data=data.encode('utf-8'))
    result = json.loads(response.text)

    return result