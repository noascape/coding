import os
import pandas as pd
import tkinter as tk
from tkinter import filedialog
from openpyxl import load_workbook
from openpyxl.utils import get_column_letter

#Terminal: pyhton3.13 -m pip install pandas openpyxl Jinja2
#Exe bauen: python3.13 -m pip install pyinstaller;  cd /pfad/zum/Projektcode;   python3.13 -m PyInstaller --onefile --noconsole --name MergerApp app.py

desired_columns= ["Material", "ID", "Stückpreis_23", "Stückpreis_24", "Preisveränderung", "Stückzahl_23", "Stückzahl_24", "Stückzahlveränderung", "Gesamtkosten_23", "Gesamtkosten_24", "Kostendifferenz"]

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
    cols1 = ["Material_23", "ID", "Stückpreis_23", "Stückzahl_23", "Gesamtkosten_23"]
    cols2 = ["Material_24", "ID", "Stückpreis_24", "Stückzahl_24", "Gesamtkosten_24"]
    ds1 = pd.read_excel(file1, skiprows=2, header=None, names=cols1)
    ds2 = pd.read_excel(file2, skiprows=2, header=None, names=cols2)
    df = pd.merge(ds1, ds2, on="ID", how="outer")
    df["Material"] = df["Material_23"].combine_first(df["Material_24"])
    df = df.drop(columns=["Material_23", "Material_24"])

    # Neue Custom-Spalten anlegen
    df["Preisveränderung"]      = df["Stückpreis_24"]       - df["Stückpreis_23"]
    df["Stückzahlveränderung"]  = df["Stückzahl_24"]        - df["Stückzahl_23"]
    df["Kostendifferenz"]       = df["Gesamtkosten_24"]     - df["Gesamtkosten_23"]

    # Sortieren und Ordnen
    df = df.sort_values("Kostendifferenz", ascending=False)
    df = df[desired_columns]

    # Färbung
    def highlight_row(row):
        # Rot, wenn Preisveränderung > 50; Gelb, wenn < 50; sonst keine Färbung
         pct = (row["Stückpreis_24"] - row["Stückpreis_23"]) / row["Stückpreis_23"]
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