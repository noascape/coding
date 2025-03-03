import pandas as pd;

csv = pd.read_csv('C:/Users/nheim/Documents/GitHub/coding/Python/statistic/titanic.csv', delimiter=',')
print(csv.head())

# Beispiel
y_true = [0, 1, 0, 1, 0, 1, 0, 0, 1, 1]
y_pred = [0, 1, 0, 0, 0, 1, 0, 1, 1, 0]

# Korrektklassifikationsrate
from sklearn.metrics import accuracy_score
accuracy = accuracy_score(y_true, y_pred)

# Konfusionsmatrix
from sklearn.metrics import confusion_matrix
cm = confusion_matrix(y_true, y_pred)

# Prävalenz --> Anteil der tatsächlich positiven Fälle
import numpy as np
prevalence = np.sum(y_true) / len(y_true)

# Relevanz (Precision)   --> Anteil der richtig positiv vorhergesagten Fälle
from sklearn.metrics import precision_score
precision = precision_score(y_true, y_pred)

# Sensitivität (Recall)  --> Anteil der korrekt erkannten positiven Fällen an allen positiven Fällen
from sklearn.metrics import recall_score
recall = recall_score(y_true, y_pred)

# Spezifität  --> Anteil der korrekt erkannten negativen Fällen an allen negativen Fällen
tn, fp, fn, tp = cm.ravel()  # Nur bei binärer Klassifikation!
specificity = tn / (tn + fp)

# F1-Score  --> Harmonisches Mittel aus Precision und Recall
from sklearn.metrics import f1_score
f1 = f1_score(y_true, y_pred)

# Beispiel
cm = confusion_matrix(y_true, y_pred)
print("Konfusionsmatrix:\n", cm)

precision = precision_score(y_true, y_pred)
print("Precision:", precision)

#...
