import os
import pandas as pd
import tkinter as tk
from tkinter import filedialog
from openpyxl import load_workbook
from openpyxl.utils import get_column_letter

#Terminal: pyhton3.13 -m pip install pandas openpyxl Jinja2
#Exe bauen: python3.13 -m pip install pyinstaller;  cd /pfad/zum/Projektcode;   python3.13 -m PyInstaller --onefile --noconsole --name MergerApp app.py

desired_columns= ["Nr", "Beschreibung", "Beschreibung2", "Produktgruppencode", "Inventurgruppencode", "Lagerort", "Bestand Datum_23", "Bestand_Datum_24", "Bestandsveränderung", "Basiseinheit", "Nettogewicht kg/ Einheit_23", "Nettogewicht kg/ Einheit_24", "Nettogewicht kg_23", "Nettogewicht kg_24", "Bewertungspreis € / Einheit_23", "Bewertungspreis € / Einheit_24", "Relative Bewertungspreisveränderung", "Bewertungspreis €_23", "Bewertungspreis €_24", "Absolute Bewertungspreisveränderung"]

def select_excel_file(prompt: str) -> str:
    root = tk.Tk()
    root.withdraw() #versteckt das Hauptfenster
    path = filedialog.askopenfilename(
        title=prompt,
        filetypes=[("Excel-Dateien", "*.xlsx *.xls")],
    )
    root.destroy()
    if not path:
        raise FileNotFoundError("Keine Datei ausgewählt.")
    return path

def merge_excels(file1: str, file2:str, output_path:str):
    # Einlesen und zusammenführen
    cols1 = ["Nr", "Beschreibung_23", "Beschreibung2_23", "Produktgruppencode_23", "Inventurgruppencode_23", "Lagerort_23", "Bestand Datum_23", "Basiseinheit_23", "Nettogewicht kg/ Einheit_23", "Nettogewicht kg_23", "Bewertungspreis € / Einheit_23", "Bewertungspreis €_23"]
    cols2 = ["Nr", "Beschreibung_24", "Beschreibung2_24", "Produktgruppencode_24", "Inventurgruppencode_24", "Lagerort_24", "Bestand Datum_24", "Basiseinheit_24", "Nettogewicht kg/ Einheit_24", "Nettogewicht kg_24", "Bewertungspreis € / Einheit_24", "Bewertungspreis €_24"]
    ds1 = pd.read_excel(file1, sheet_name=0, skiprows=4, header=None, names=cols1)
    ds2 = pd.read_excel(file2, sheet_name=0, skiprows=3, header=None, names=cols2)
    df = pd.merge(ds1, ds2, on="Nr", how="outer")

    df["Beschreibung"] = df["Beschreibung_23"].combine_first(df["Beschreibung_24"])
    df = df.drop(columns=["Beschreibung_23", "Beschreibung_24"])
    df["Beschreibung2"] = df["Beschreibung2_23"].combine_first(df["Beschreibung2_24"])
    df = df.drop(columns=["Beschreibung2_23", "Beschreibung2_24"])
    df["Produktgruppencode"] = df["Produktgruppencode_23"].combine_first(df["Produktgruppencode_24"])
    df = df.drop(columns=["Produktgruppencode_23", "Produktgruppencode_24"])
    df["Inventurgruppencode"] = df["Inventurgruppencode_23"].combine_first(df["Inventurgruppencode_24"])
    df = df.drop(columns=["Inventurgruppencode_23", "Inventurgruppencode_24"])
    df["Lagerort"] = df["Lagerort_23"].combine_first(df["Lagerort_24"])
    df = df.drop(columns=["Lagerort_23", "Lagerort_24"])

    # Neue Custom-Spalten anlegen
    df["Relative Bewertungspreisveränderung"]= df["Bewertungspreis € / Einheit_24"] - df["Bewertungspreis € / Einheit_23"]
    df["Bestandveränderung"]= df["Bestand Datum_24"] - df["Bestand Datum_23"]
    df["Absolute Bewertungspreisveränderung"]= df["Bewertungspreis €_24"]- df["Bewertungspreis €_23"]

    # Sortieren und Ordnen
    df = df.sort_values("Absolute Bewertungspreisveränderung", ascending=False)
    df = df[desired_columns]

    # Färbung
    def highlight_row(row):
        # Rot, wenn Preisveränderung > 50; Gelb, wenn < 50; sonst keine Färbung
         pct = (row["Bewertungspreis € / Einheit_24"] - row["Bewertungspreis € / Einheit_23"]) / row["Bewertungspreis € / Einheit_23"]
         if pct > 0.10:
            return ["background-color: red"] * len(row)
         elif pct < -0.10:
            return ["background-color: yellow"] * len(row)
         else:
            return [""] * len(row)

    style = df.style.apply(highlight_row, axis=1)

    # Excel mit Styling schreiben
    with pd.ExcelWriter(output_path, engine="openpyxl") as writer:
        style.to_excel(writer, index=False, sheet_name="Zusammengeführt")   #startrow=1, um erst ab Zeile 2 zu beginnen

    #Spaltenbreite anhand der Kopfzeile anpassen ("Autofit")
    wb = load_workbook(output_path)
    ws = wb["Zusammengeführt"]
    for cell in ws[1]:   #oder mit startrow=1, dann 2
        col = cell.column_letter
        ws.column_dimensions[col].width = len(str(cell.value)) + 2
    wb.save(output_path)

    return df

if __name__ == "__main__":
    try:
        file1 = select_excel_file("Wähle den ersten Excel-Datensatz (z.B. von 2023)")
        file2 = select_excel_file("Wähle den zweiten Excel-Datensatz (z.B. von 2024)")

        default_out = os.path.join(os.getcwd(), "Zusammengeführt.xlsx")
        output = filedialog.asksaveasfilename(
            title="Speichere den zusammengeführten Datensatz",
            defaultextension=".xlsx",
            initialfile="Zusammengeführt.xlsx",
            filetypes=[("Excel-Dateien", "*.xlsx")]
        ) or default_out

        df = merge_excels(file1, file2, output)
        print(f"Vorgang erfolgreich! Datei gespeichert unter:\n{output}")
        print(df)
    except Exception as e:
        print(f"Abbruch: {e}")