import os, tkinter as tk
from tkinter import filedialog, messagebox
from types import SimpleNamespace
from PIL import Image, ImageFilter, ImageEnhance, ImageOps, ImageDraw, ImageFont, ImageTk

# --- Kernfunktionen aus der CLI-Version ---
def parse_size(s):
    try:
        w,h = s.lower().split("x")
        return int(w), int(h)
    except Exception:
        raise ValueError("Größe als BreitexHöhe, z.B. 1280x720")

def place_text(img, text, pos="br"):
    img = img.convert("RGBA"); draw = ImageDraw.Draw(img)
    font = ImageFont.load_default()
    tw, th = draw.textbbox((0,0), text, font=font)[2:]
    m=10; posmap={"br":(img.width-tw-m,img.height-th-m),"bl":(m,img.height-th-m),
                  "tr":(img.width-tw-m,m),"tl":(m,m),"center":((img.width-tw)//2,(img.height-th)//2)}
    x,y = posmap.get(pos,posmap["br"])
    bg = Image.new("RGBA",(tw+8, th+6),(0,0,0,120)); img.alpha_composite(bg, dest=(x-4,y-3))
    draw.text((x,y), text, font=font, fill=(255,255,255,230))
    return img.convert("RGB")

def apply_ops(img, a):
    if a.grayscale: img = ImageOps.grayscale(img).convert("RGB")
    if a.rotate: img = img.rotate(-a.rotate, expand=True)
    if a.resize:
        try: img = img.resize(parse_size(a.resize), Image.LANCZOS)
        except Exception as e: raise ValueError(str(e))
    if a.blur>0: img = img.filter(ImageFilter.GaussianBlur(a.blur))
    if a.contrast!=1.0: img = ImageEnhance.Contrast(img).enhance(a.contrast)
    if a.brightness!=1.0: img = ImageEnhance.Brightness(img).enhance(a.brightness)
    if a.edges: img = img.filter(ImageFilter.FIND_EDGES)
    if a.equalize: img = ImageOps.equalize(img.convert("RGB"))
    if a.watermark: img = place_text(img, a.watermark, a.wmpos)
    return img

# --- GUI ---
class App(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("imgtool – einfache Bildbearbeitung"); self.geometry("920x560")
        self.orig = None; self.edited = None; self.path = None
        # Controls
        left = tk.Frame(self); left.pack(side="left", fill="y", padx=8, pady=8)
        tk.Button(left, text="Bild öffnen…", command=self.open_img).pack(fill="x")
        tk.Button(left, text="Speichern unter…", command=self.save_img).pack(fill="x", pady=(4,10))

        self.var_gray = tk.BooleanVar(); self.var_edges = tk.BooleanVar(); self.var_equal = tk.BooleanVar()
        tk.Checkbutton(left, text="Graustufen", variable=self.var_gray, command=self.render).pack(anchor="w")
        tk.Checkbutton(left, text="Kanten", variable=self.var_edges, command=self.render).pack(anchor="w")
        tk.Checkbutton(left, text="Equalize", variable=self.var_equal, command=self.render).pack(anchor="w")

        self.s_rotate = tk.Scale(left, from_=-180, to=180, orient="horizontal", label="Drehen (°)", command=lambda e:self.render()); self.s_rotate.pack(fill="x")
        self.s_blur = tk.Scale(left, from_=0, to=10, resolution=0.5, orient="horizontal", label="Blur", command=lambda e:self.render()); self.s_blur.pack(fill="x")
        self.s_contrast = tk.Scale(left, from_=0.1, to=2.0, resolution=0.05, orient="horizontal", label="Kontrast", command=lambda e:self.render()); self.s_contrast.set(1.0); self.s_contrast.pack(fill="x")
        self.s_bright = tk.Scale(left, from_=0.1, to=2.0, resolution=0.05, orient="horizontal", label="Helligkeit", command=lambda e:self.render()); self.s_bright.set(1.0); self.s_bright.pack(fill="x")

        r = tk.Frame(left); r.pack(fill="x", pady=(6,0))
        tk.Label(r, text="Resize (BxH):").pack(side="left"); self.e_resize = tk.Entry(r, width=12); self.e_resize.pack(side="left", padx=4)
        tk.Button(r, text="Anwenden", command=self.render).pack(side="left")

        w = tk.Frame(left); w.pack(fill="x", pady=(6,0))
        tk.Label(w, text="Wasserzeichen:").pack(anchor="w")
        self.e_wm = tk.Entry(w); self.e_wm.pack(fill="x")
        posf = tk.Frame(w); posf.pack(fill="x")
        tk.Label(posf, text="Pos:").pack(side="left")
        self.var_pos = tk.StringVar(value="br")
        tk.OptionMenu(posf, self.var_pos, "br","bl","tr","tl","center", command=lambda e:self.render()).pack(side="left")

        # Preview
        right = tk.Frame(self, bg="#222"); right.pack(side="left", fill="both", expand=True)
        self.preview = tk.Label(right, bg="#222"); self.preview.pack(fill="both", expand=True, padx=8, pady=8)

    def open_img(self):
        p = filedialog.askopenfilename(title="Bild wählen", filetypes=[("Bilder","*.jpg;*.jpeg;*.png;*.bmp;*.webp;*.tif")])
        if not p: return
        try:
            self.orig = Image.open(p); self.path = p
            self.render()
        except Exception as e:
            messagebox.showerror("Fehler", f"Bild konnte nicht geladen werden:\n{e}")

    def collect_args(self):
        return SimpleNamespace(
            grayscale=self.var_gray.get(),
            rotate=float(self.s_rotate.get()),
            resize=self.e_resize.get().strip() or None,
            blur=float(self.s_blur.get()),
            contrast=float(self.s_contrast.get()),
            brightness=float(self.s_bright.get()),
            edges=self.var_edges.get(),
            equalize=self.var_equal.get(),
            watermark=self.e_wm.get().strip() or None,
            wmpos=self.var_pos.get()
        )

    def render(self):
        if not self.orig: return
        try:
            self.edited = apply_ops(self.orig.copy(), self.collect_args())
            disp = self.edited.copy(); disp.thumbnail((800, 520))
            self.tkimg = ImageTk.PhotoImage(disp)
            self.preview.config(image=self.tkimg)
        except Exception as e:
            messagebox.showwarning("Eingabe prüfen", str(e))

    def save_img(self):
        if not self.edited:
            messagebox.showinfo("Hinweis", "Bitte zuerst ein Bild öffnen/ändern."); return
        base, ext = os.path.splitext(self.path or "output.jpg")
        p = filedialog.asksaveasfilename(defaultextension=ext or ".jpg",
            initialfile=os.path.basename(base)+"_edit"+(ext or ".jpg"),
            filetypes=[("JPEG","*.jpg;*.jpeg"),("PNG","*.png"),("Alle","*.*")])
        if not p: return
        try:
            self.edited.save(p, quality=95); messagebox.showinfo("Gespeichert", p)
        except Exception as e:
            messagebox.showerror("Fehler beim Speichern", str(e))

if __name__ == "__main__":
    App().mainloop()
