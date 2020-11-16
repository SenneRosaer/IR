import pandas as pd
import re
import nltk
nltk.download('stopwords')
from nltk.corpus import stopwords

if __name__ == '__main__':
    data = pd.read_csv("news_dataset.csv")
    print(data.content.head())
    banned =[".", ",", ";", ':', '?', '!']
    banned += list(stopwords.words('english'))
    for item in banned:
        data.content = data.content.str.replace(' '+item +' ', ' ')
    print(data["content"])
    #data.content.str()