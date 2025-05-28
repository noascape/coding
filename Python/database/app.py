# app.py
from flask import Flask, render_template, request, session, redirect, url_for
from werkzeug.security import generate_password_hash, check_password_hash
from connection import get_connection

app = Flask(__name__)
app.secret_key = "secret"  # in Produktion sicher anders handhaben!

# Hilfsfunktion: Liste aller User
def show_users():
    conn = get_connection()
    cursor = conn.cursor(dictionary=True) if hasattr(conn, "cursor") else conn.cursor()
    cursor.execute("SELECT username AS Benutzername, email AS Email FROM users")
    users = cursor.fetchall()
    cursor.close()
    conn.close()
    return users

@app.route("/")
def index():
    return render_template("login.html")

@app.route("/login", methods=["POST"])
def login():
    username = request.form["username"]
    password = request.form["password"]

    conn = get_connection()
    cursor = conn.cursor(dictionary=True) if hasattr(conn, "cursor") else conn.cursor()
    cursor.execute("SELECT * FROM users WHERE username = %s", (username,))
    user = cursor.fetchone()
    cursor.close()
    conn.close()

    if user and check_password_hash(user["password"], password):
        session["username"] = user["username"]
        return redirect(url_for("homepage"))
    else:
        return "Login fehlgeschlagen – Benutzername oder Passwort falsch", 401

@app.route("/register", methods=["GET", "POST"])
def register():
    if request.method == "POST":
        username = request.form["username"]
        email    = request.form["email"]
        pw_hash  = generate_password_hash(request.form["password"])

        conn = get_connection()
        cursor = conn.cursor()
        # prüfen, ob schon vorhanden
        cursor.execute("SELECT 1 FROM users WHERE username=%s OR email=%s", (username, email))
        exists = cursor.fetchone()
        if not exists:
            cursor.execute(
                "INSERT INTO users (username, email, password) VALUES (%s, %s, %s)",
                (username, email, pw_hash)
            )
            conn.commit()
            cursor.close()
            conn.close()
            return redirect(url_for("index"))
        else:
            cursor.close()
            conn.close()
            return "Benutzername oder E-Mail bereits vergeben", 400

    return render_template("register.html")

@app.route("/homepage")
def homepage():
    if "username" not in session:
        return redirect(url_for("index"))

    users = show_users()
    return render_template("homepage.html",
                           current_user=session["username"],
                           users=users)

@app.route("/logout", methods=["POST"])
def logout():
    session.clear()
    return redirect(url_for("index"))

if __name__ == "__main__":
    app.run(debug=True)
