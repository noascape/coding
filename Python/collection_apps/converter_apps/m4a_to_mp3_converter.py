import os
import subprocess
import tkinter as tk
from tkinter import messagebox
from tkinterdnd2 import DND_FILES, TkinterDnD


FFMPEG_PATH = r"C:\Users\Noah\AppData\Local\Microsoft\WinGet\Links\ffmpeg.exe"

def clean_drop_path(path: str) -> str:
    """
    Entfernt geschweifte Klammern, die Windows bei Drag & Drop
    manchmal um Pfade mit Leerzeichen setzt.
    """
    path = path.strip()

    if path.startswith("{") and path.endswith("}"):
        path = path[1:-1]

    return path


def convert_m4a_to_mp3(input_path: str):
    input_path = clean_drop_path(input_path)

    if not input_path.lower().endswith(".m4a"):
        messagebox.showerror("Fehler", "Bitte nur eine .m4a-Datei hineinziehen.")
        return

    if not os.path.exists(input_path):
        messagebox.showerror("Fehler", f"Datei wurde nicht gefunden:\n\n{input_path}")
        return

    output_path = os.path.splitext(input_path)[0] + ".mp3"

    status_label.config(text="Konvertierung läuft...")
    root.update_idletasks()

    try:
        command = [
            FFMPEG_PATH,
            "-y",                 # vorhandene MP3 überschreiben
            "-i", input_path,      # Eingabedatei
            "-vn",                # kein Video / Cover-Art als Video übernehmen
            "-codec:a", "libmp3lame",
            "-b:a", "192k",
            output_path
        ]

        result = subprocess.run(
            command,
            stdout=subprocess.PIPE,
            stderr=subprocess.PIPE,
            text=True
        )

        if result.returncode != 0:
            status_label.config(text="Fehler bei der Konvertierung.")
            messagebox.showerror(
                "FFmpeg-Fehler",
                result.stderr
            )
            return

        status_label.config(text="Fertig!")
        messagebox.showinfo(
            "Erfolg",
            f"Datei wurde erfolgreich konvertiert:\n\n{output_path}"
        )

    except FileNotFoundError:
        status_label.config(text="FFmpeg wurde nicht gefunden.")
        messagebox.showerror(
            "FFmpeg fehlt",
            "FFmpeg wurde nicht gefunden.\n\n"
            "Installiere FFmpeg und füge es zum Windows-PATH hinzu."
        )

    except Exception as e:
        status_label.config(text="Unbekannter Fehler.")
        messagebox.showerror("Fehler", str(e))


def on_drop(event):
    dropped_data = event.data

    # Bei einer einzelnen Datei reicht das meistens.
    # Falls Windows geschweifte Klammern nutzt, wird es später bereinigt.
    convert_m4a_to_mp3(dropped_data)


root = TkinterDnD.Tk()
root.title("M4A zu MP3 Converter")
root.geometry("480x260")
root.resizable(False, False)

title_label = tk.Label(
    root,
    text="M4A zu MP3 Converter",
    font=("Arial", 18, "bold")
)
title_label.pack(pady=20)

drop_label = tk.Label(
    root,
    text="Ziehe eine .m4a-Datei hier hinein",
    font=("Arial", 13),
    relief="ridge",
    borderwidth=2,
    width=42,
    height=6
)
drop_label.pack(pady=10)

drop_label.drop_target_register(DND_FILES)
drop_label.dnd_bind("<<Drop>>", on_drop)

status_label = tk.Label(
    root,
    text="Warte auf Datei...",
    font=("Arial", 11)
)
status_label.pack(pady=10)

root.mainloop()