import pandas as pd
from nltk.corpus import stopwords
import unidecode

if __name__ == "__main__":
    df = pd.read_csv("news_dataset.csv")
    print(df.content.head())


    # remove accents
    # https://stackoverflow.com/questions/37926248/how-to-remove-accents-from-values-in-columns
    df.content = df.content.str.normalize('NFKD').str.encode('ascii', errors='ignore').str.decode('utf-8').str.lower()
    print(df.content.head())

    # remove stop words
    # https://stackoverflow.com/questions/29523254/python-remove-stop-words-from-pandas-dataframe
    stopwords = stopwords.words('english')
    pat = r'\b(?:{})\b'.format('|'.join(stopwords))
    df['content'] = df['content'].str.replace(pat, '')


    # remove multiple spaces in a row
    df['content'] = df['content'].str.replace(r'\s+', ' ')

    print(df.content.head())
