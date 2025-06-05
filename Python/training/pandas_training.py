#Pandas-Bibliothek: verwendet C-Erweiterungen (meist in Cyphon geschrieben), um bestimmte Operationen zu beschleunigen
# Dokumentation aller Funktionen: https://pandas.pydata.org/docs/reference/

import os
import pandas as pd

pandas_version = pd.__version__             #Versions-Check bevor neue Funktionen benutzt werden sollte
print(pandas_version)

'''
---- Read .xlsx ----
'''
path_xlsx = os.path.join(os.getcwd(), "data", "customer.xlsx")
df_xlsx = pd.read_excel(path_xlsx)
print(df_xlsx)



'''
---- Read .xml ----
'''
path = os.path.join(os.getcwd(), "data", "books.xml")
df_xml = pd.read_xml(path, parser="etree")
print(df_xml)



'''
---- Wichtige Funktionen und Möglichkeiten ----
'''
#merged_df = pd.merge(df_xlsx, df_xml, on=["Author","Name"], how="outer")
