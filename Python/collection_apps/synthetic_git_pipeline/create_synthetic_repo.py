import json
import os
import shutil
import subprocess
from datetime import datetime, timedelta
from pathlib import Path


def run_command(command: list[str], cwd: Path, env: dict | None = None) -> None:
    subprocess.run(
        command,
        cwd=cwd,
        env=env,
        check=True,
        text=True
    )


def load_manifest(path: str) -> dict:
    manifest_path = Path(path)

    if not manifest_path.exists():
        raise FileNotFoundError(f"Manifest nicht gefunden: {manifest_path}")

    with manifest_path.open("r", encoding="utf-8") as file:
        return json.load(file)


def validate_manifest(manifest: dict) -> None:
    required_fields = [
        "repository_name",
        "author_name",
        "author_email",
        "start_time",
        "commits"
    ]

    for field in required_fields:
        if field not in manifest:
            raise ValueError(f"Pflichtfeld fehlt im Manifest: {field}")

    if not manifest["commits"]:
        raise ValueError("Das Manifest enthält keine Commits.")

    for index, commit in enumerate(manifest["commits"], start=1):
        if "message" not in commit:
            raise ValueError(f"Commit {index}: Feld 'message' fehlt.")

        if "offset_hours" not in commit:
            raise ValueError(f"Commit {index}: Feld 'offset_hours' fehlt.")

        if "files" not in commit:
            raise ValueError(f"Commit {index}: Feld 'files' fehlt.")

        if not isinstance(commit["files"], dict):
            raise ValueError(f"Commit {index}: 'files' muss ein Objekt sein.")


def write_files(repo_path: Path, files: dict[str, str]) -> None:
    for relative_path, content in files.items():
        file_path = repo_path / relative_path
        file_path.parent.mkdir(parents=True, exist_ok=True)
        file_path.write_text(content, encoding="utf-8")


def create_synthetic_repo(manifest_path: str, output_dir: str = ".") -> Path:
    manifest = load_manifest(manifest_path)
    validate_manifest(manifest)

    output_path = Path(output_dir).resolve()
    repo_path = output_path / manifest["repository_name"]

    if repo_path.exists():
        raise FileExistsError(
            f"Zielordner existiert bereits: {repo_path}\n"
            "Bitte löschen, umbenennen oder einen anderen Repository-Namen verwenden."
        )

    repo_path.mkdir(parents=True)

    start_time = datetime.fromisoformat(manifest["start_time"])

    run_command(["git", "init"], cwd=repo_path)
    run_command(["git", "config", "user.name", manifest["author_name"]], cwd=repo_path)
    run_command(["git", "config", "user.email", manifest["author_email"]], cwd=repo_path)

    env = os.environ.copy()
    first_timestamp = start_time.isoformat()
    env["GIT_AUTHOR_DATE"] = first_timestamp
    env["GIT_COMMITTER_DATE"] = first_timestamp

    for commit in manifest["commits"]:
        commit_time = start_time + timedelta(minutes=commit["offset_minutes"])
        timestamp = commit_time.isoformat()

        write_files(repo_path, commit["files"])

        run_command(["git", "add", "."], cwd=repo_path)

        env = os.environ.copy()
        env["GIT_AUTHOR_DATE"] = timestamp
        env["GIT_COMMITTER_DATE"] = timestamp

        commit_message = f"{commit['message']}"

        run_command(
            ["git", "commit", "-m", commit_message],
            cwd=repo_path,
            env=env
        )

    return repo_path


if __name__ == "__main__":
    created_repo = create_synthetic_repo(
        manifest_path="commits_manifest.json",
        output_dir="."
    )

    print(f"Repository wurde erstellt unter:")
    print(created_repo)
    print()