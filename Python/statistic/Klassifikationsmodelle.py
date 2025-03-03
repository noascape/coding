import pandas as pd;

csv = pd.read_csv('titanic.csv', delimiter=';')
print(csv.head())