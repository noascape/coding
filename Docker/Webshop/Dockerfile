# 1) Node.js-Image verwenden, z.B. schlankes Alpine
FROM node:16-alpine

# 2) Arbeitsverzeichnis definieren
WORKDIR /app

# 3) Zusätzliche Pakete für native Addons (z.B. sqlite3)
RUN apk add --no-cache python3 make g++

# 4) package.json / package-lock.json kopieren und Abhängigkeiten installieren
COPY package*.json ./
RUN npm install

# 5) Restlichen Code kopieren
COPY . .

# 6) App läuft auf Port 3000
EXPOSE 3000

# 7) Startbefehl
CMD ["node", "index.js"]
