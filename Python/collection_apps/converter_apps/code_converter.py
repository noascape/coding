import os
import sys
from pathlib import Path
from typing import Iterable, List, Set

import tkinter as tk
from tkinter import filedialog, messagebox, scrolledtext

# Optional drag & drop support
try:
    from tkinterdnd2 import DND_FILES, TkinterDnD  # type: ignore
    DND_AVAILABLE = True
except Exception:
    DND_AVAILABLE = False
    TkinterDnD = None
    DND_FILES = None


DEFAULT_EXTENSIONS: Set[str] = {
    ".py", ".pyw", ".pyi", ".ipynb",
    ".java", ".kt", ".kts", ".groovy", ".scala",
    ".gradle", ".properties",
    ".js", ".mjs", ".cjs",
    ".ts", ".tsx", ".jsx",
    ".vue", ".svelte", ".astro",
    ".html", ".htm", ".css", ".scss", ".sass", ".less",
    ".dart",
    ".c", ".h", ".cpp", ".cc", ".cxx", ".hpp", ".hh", ".hxx",
    ".cs",
    ".php", ".rb", ".go", ".rs", ".swift",
    ".lua", ".pl", ".pm", ".r", ".m", ".mm",
    ".vb", ".fs", ".fsx", ".clj", ".cljs",
    ".erl", ".ex", ".exs", ".hs", ".lhs",
    ".nim", ".zig",
    ".sh", ".bash", ".zsh", ".fish",
    ".bat", ".cmd", ".ps1", ".psm1",
    ".json", ".jsonc", ".json5",
    ".xml", ".yaml", ".yml",
    ".toml", ".ini", ".cfg", ".conf", ".config",
    ".env", ".properties",
    ".csv", ".tsv",
    ".sql", ".dbml",
    ".md", ".markdown", ".txt", ".rst", ".tex",
    ".adoc", ".asciidoc",
    ".pom", ".lock",
    ".dockerfile", ".containerfile",
    ".gitignore", ".gitattributes", ".editorconfig",
    ".npmrc", ".yarnrc", ".prettierrc", ".eslintrc",
    ".babelrc", ".browserslistrc",
    ".tf", ".tfvars",
    ".hcl",
    ".puml", ".plantuml", ".drawio", ".mermaid", ".mmd",
    ".log", ".diff", ".patch",
    ".proto", ".graphql", ".gql",
    ".sol", ".wasm",
}

DEFAULT_FILENAMES: Set[str] = {
    "dockerfile", "containerfile",
    "makefile", "rakefile", "gemfile", "podfile",
    "package-lock.json", "yarn.lock", "pnpm-lock.yaml",
    "requirements.txt", "pyproject.toml", "poetry.lock",
    "pom.xml", "build.gradle", "build.gradle.kts",
    ".gitignore", ".gitattributes", ".editorconfig",
    ".env", ".env.example", ".env.local",
}

IGNORED_DIRS = {
    ".git", "__pycache__", ".idea", ".vscode", "node_modules", "dist", "build",
    ".venv", "venv", "target", "out", ".next", ".nuxt", ".gradle"
}


def parse_dnd_files(raw_data: str) -> List[str]:
    paths = []
    current = ""
    in_braces = False

    for char in raw_data:
        if char == "{":
            in_braces = True
            if current:
                current = ""
            continue
        if char == "}":
            in_braces = False
            if current:
                paths.append(current)
                current = ""
            continue

        if char == " " and not in_braces:
            if current:
                paths.append(current)
                current = ""
            continue

        current += char

    if current:
        paths.append(current)

    return [p.strip() for p in paths if p.strip()]


class CodeConverterApp:
    def __init__(self, root: tk.Tk):
        self.root = root
        self.root.title("Code-Datei-Converter")
        self.root.geometry("1100x780")

        self.selected_paths: List[Path] = []
        self.extensions = set(DEFAULT_EXTENSIONS)

        self._build_ui()

    def _build_ui(self) -> None:
        top_info = (
            "Dateien oder Ordner hier hineinziehen. Unterstützte Code-/Textdateien werden gesammelt "
            "und als Fließtext im Format 'Dateiname:\\nInhalt' ausgegeben."
        )
        tk.Label(self.root, text=top_info, wraplength=1000, justify="left", anchor="w").pack(
            fill="x", padx=12, pady=(12, 6)
        )

        self.drop_frame = tk.Label(
            self.root,
            text=(
                "Dateien / Ordner hier hineinziehen\n\n"
                "oder unten über die Buttons auswählen"
                + ("" if DND_AVAILABLE else "\n\nHinweis: Drag & Drop benötigt 'tkinterdnd2'.")
            ),
            relief="groove",
            bd=2,
            padx=20,
            pady=30,
            font=("Segoe UI", 12)
        )
        self.drop_frame.pack(fill="x", padx=12, pady=6)

        if DND_AVAILABLE:
            self.drop_frame.drop_target_register(DND_FILES)
            self.drop_frame.dnd_bind("<<Drop>>", self.on_drop)

        controls = tk.Frame(self.root)
        controls.pack(fill="x", padx=12, pady=6)

        tk.Button(controls, text="Dateien auswählen", command=self.add_files).pack(side="left", padx=(0, 8))
        tk.Button(controls, text="Ordner auswählen", command=self.add_folder).pack(side="left", padx=(0, 8))
        tk.Button(controls, text="Liste leeren", command=self.clear_paths).pack(side="left", padx=(0, 8))
        tk.Button(controls, text="Konvertieren", command=self.convert, bg="#4CAF50", fg="white").pack(side="left", padx=(0, 8))
        tk.Button(controls, text="In Zwischenablage kopieren", command=self.copy_output).pack(side="left")

        ext_frame = tk.LabelFrame(self.root, text="Erlaubte Dateiendungen (kommagetrennt)")
        ext_frame.pack(fill="x", padx=12, pady=6)

        self.ext_var = tk.StringVar(value=", ".join(sorted(self.extensions)))
        tk.Entry(ext_frame, textvariable=self.ext_var).pack(fill="x", padx=8, pady=8)

        list_frame = tk.LabelFrame(self.root, text="Ausgewählte Pfade")
        list_frame.pack(fill="both", expand=False, padx=12, pady=6)

        self.path_list = tk.Listbox(list_frame, height=8)
        self.path_list.pack(fill="both", expand=True, padx=8, pady=8)

        output_frame = tk.LabelFrame(self.root, text="Ausgabe")
        output_frame.pack(fill="both", expand=True, padx=12, pady=(6, 12))

        self.output_text = scrolledtext.ScrolledText(output_frame, wrap="word")
        self.output_text.pack(fill="both", expand=True, padx=8, pady=8)

    def sync_extensions(self) -> None:
        raw = self.ext_var.get().strip()
        if not raw:
            self.extensions = set(DEFAULT_EXTENSIONS)
            return

        cleaned = set()
        for part in raw.split(","):
            ext = part.strip().lower()
            if not ext:
                continue
            if not ext.startswith("."):
                ext = "." + ext
            cleaned.add(ext)

        self.extensions = cleaned or set(DEFAULT_EXTENSIONS)

    def is_allowed_file(self, file_path: Path) -> bool:
        name = file_path.name.lower()
        suffix = file_path.suffix.lower()
        return suffix in self.extensions or name in DEFAULT_FILENAMES

    def refresh_list(self) -> None:
        self.path_list.delete(0, tk.END)
        for path in self.selected_paths:
            self.path_list.insert(tk.END, str(path))

    def add_unique_paths(self, paths: Iterable[Path]) -> None:
        existing = {p.resolve() for p in self.selected_paths if p.exists()}
        for path in paths:
            resolved = path.resolve()
            if resolved not in existing:
                self.selected_paths.append(path)
                existing.add(resolved)
        self.refresh_list()

    def add_files(self) -> None:
        file_paths = filedialog.askopenfilenames(title="Dateien auswählen")
        if file_paths:
            self.add_unique_paths(Path(p) for p in file_paths)

    def add_folder(self) -> None:
        folder = filedialog.askdirectory(title="Ordner auswählen")
        if folder:
            self.add_unique_paths([Path(folder)])

    def clear_paths(self) -> None:
        self.selected_paths.clear()
        self.refresh_list()
        self.output_text.delete("1.0", tk.END)

    def on_drop(self, event) -> None:
        dropped = parse_dnd_files(event.data)
        self.add_unique_paths(Path(p) for p in dropped)

    def collect_files(self) -> List[Path]:
        self.sync_extensions()
        collected: List[Path] = []

        for source in self.selected_paths:
            if not source.exists():
                continue

            if source.is_file():
                if self.is_allowed_file(source):
                    collected.append(source)
                continue

            for root, dirs, files in os.walk(source):
                dirs[:] = [d for d in dirs if d not in IGNORED_DIRS]
                for file_name in files:
                    file_path = Path(root) / file_name
                    if self.is_allowed_file(file_path):
                        collected.append(file_path)

        return sorted({p.resolve() for p in collected}, key=lambda p: str(p).lower())

    def build_output(self, files: List[Path]) -> str:
        blocks = []

        for file_path in files:
            try:
                content = file_path.read_text(encoding="utf-8")
            except Exception:
                content = "[Datei konnte nicht gelesen werden]"

            blocks.append(f"{file_path}:\n{content}")

        return "\n\n\n".join(blocks)

    def convert(self) -> None:
        files = self.collect_files()
        if not files:
            messagebox.showinfo("Keine Dateien", "Keine passenden Dateien gefunden.")
            return

        result = self.build_output(files)
        self.output_text.delete("1.0", tk.END)
        self.output_text.insert("1.0", result)

    def copy_output(self) -> None:
        text = self.output_text.get("1.0", tk.END).strip()
        if text:
            self.root.clipboard_clear()
            self.root.clipboard_append(text)
            self.root.update()
            messagebox.showinfo("Kopiert", "In Zwischenablage kopiert.")


def main() -> None:
    root_class = TkinterDnD.Tk if DND_AVAILABLE else tk.Tk
    root = root_class()
    app = CodeConverterApp(root)
    root.mainloop()


if __name__ == "__main__":
    main()