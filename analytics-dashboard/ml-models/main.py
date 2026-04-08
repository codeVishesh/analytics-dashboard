
from fastapi import FastAPI
import random

app = FastAPI()

@app.post("/predict")
def predict():
    return {"prediction": random.uniform(0,100)}
