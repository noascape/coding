import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
from sklearn.metrics import r2_score

# 1. Datensatz laden
df = pd.read_csv("C:/Users/nheim/Documents/GitHub/coding/Python/statistic/house_sales.csv", delimiter="\t")
print("Erste Zeilen des Datensatzes:")
print(df.head())

# 2. Überblick über den Datensatz
print("\nStatistische Übersicht:")
print(df.describe())

print("\nAnzahl fehlender Werte pro Spalte:")
print(df.isnull().sum())

# Konvertiere DocumentDate in ein datetime-Objekt und extrahiere das Jahr
df["DocumentDate"] = pd.to_datetime(df["DocumentDate"], errors="coerce")
df["Year"] = df["DocumentDate"].dt.year

# Entfernen Sie die ursprüngliche DocumentDate-Spalte, wenn sie nicht mehr benötigt wird
df = df.drop(columns=["DocumentDate"])

# Berechnung der Korrelation nur für numerische Spalten
numeric_df = df.select_dtypes(include=[np.number])
corr_matrix = numeric_df.corr()
print("\nKorrelationsmatrix (nach Konvertierung):")
print(corr_matrix)

# 4. Feature Selection und Vorverarbeitung
# Falls es eine Spalte mit dem Immobilientyp gibt, kodieren wir diese:
if "PropertyType" in df.columns:
    df = pd.get_dummies(df, columns=["PropertyType"], drop_first=True)

# Entfernen Sie Spalten, die für die Vorhersage nicht geeignet sind (z.B. IDs oder Datums-Spalten)
cols_to_drop = []
for col in ["PropertyID", "ym", "date"]:  # passen Sie diese Liste ggf. an
    if col in df.columns:
        cols_to_drop.append(col)
df = df.drop(columns=cols_to_drop)

# 5. Aufteilen in Features (X) und Zielvariable (y)
# Zielvariable heißt hier "SalePrice"
X = df.drop(columns=["SalePrice"])
y = df["SalePrice"]

# Verwenden Sie nur numerische Variablen für X
X = X.select_dtypes(include=[np.number])

# 6. Aufteilen in Trainings- und Testdatensatz (80/20-Split)
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# 7. Lineares Regressionsmodell trainieren
model = LinearRegression()
model.fit(X_train, y_train)

# 8. Modell auf dem Testdatensatz evaluieren
y_pred = model.predict(X_test)
r2 = r2_score(y_test, y_pred)
print("\nBestimmtheitsmaß R2 auf dem Testdatensatz:", r2)
