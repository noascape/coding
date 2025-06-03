import re



# Slicing-Syntax:  seq[start:stop:step]  start = Index, wo Slice beginnt | stop = Index, wo Slice endet | step = Schrittweise, überspringt jeweils step-1 Elemente | - = von hinten
value = "HalloWelt"
print(value[:5]) # "Hallo"
value1 = "0000ff453"
print(value1[4:]) # "ff453"
value2 = "abcdefg"
print(value2[0:7:2]) # "aceg"
value3 = "145 mm"
print(value3[:-3]) # "145"

# String extrahieren:
text = "Das ist ein Beispiel (extrahierter Inhalt) Ende"
start = text.find("(")
end = text.find(")", start)   #sucht ab dem "("-Index nach ")" - Index
inhalt = text[start+1 : end]
print(inhalt)


"""
 ----- Regex (import re) -----
"""
#1. Grundlagen
#.          --> Beliebiges Zeichen außer Zeilenumbruch (\n)
#^          --> Anfang des Strings (bei MULTILINE auch nach \n)
#$          --> Ende des Strings (bei MULTILINE auch vor \n)
#[...]      --> Zeichenklasse, z.B. [abc] oder [0-9]
#[^...]     --> Negierte Zeichenklasse, z.B. [^0-9]  = alles außer 0-9
#\d         --> Digit (entspricht [0-9]
#\D         --> Nicht-Digit ([^0-9])
#\w         --> Wortzeichen (Buchstabe, Zahl (digit) oder underscore, [A-Za-z0-9_]) = \w (ASCII)
#\W         --> Kein Wortzeichen ([^A-Za-z0-9_])
#\s         --> Whitespace (Leerzeichen, Tab, Zeilenumbruch)
#\S         --> Kein Whitespace
#\b         --> Wortgrenze (zwischen \w und \W)
#\B         --> Keine Wortgrenze
#\\         --> Literal Backslash

#2. Wichtige re-Funktionen
#re.search(pattern, string)     --> Erster Treffer irgendwo (Match-Objekt oder None)
#re.match(pattern, string)      --> Nur am String-Anfang (Macht-Objekt oder None)
#re.fullmatch(pattern, string)  --> Gesamter String muss passen (Match-Objekt oder None)
#re.findall(pattern, string)    --> Lister aller Nicht-überlappenden Treffer
#re.finditer(pattern, string)   --> Iterator über Match-Objekte
#re.split(pattern, string)      --> List[str], trennt dort, wo Pattern matcht
#re.sub(pattern, repl, string)  --> Ersetzt Vorkommen durch repl (String oder Funktion)
#re.compile (pattern)           --> Kompiliertes Pattern-Objekt für wiederholte Nutzung

#3. Quantifizierung
#*          --> 0 oder mehr des vorangehenden Elements
#+          --> 1 oder mehr
#?          --> 0 oder 1
#{n}        --> Genau n Wiederholungen
#{n,}       --> Mindestens n Wiederholungen
#{n,m}      --> Zwischen n und m Wiederholungen (inklusive)
text = "aaaab"
pattern_greedy = r"a+"
print(f"Gierig r'{pattern_greedy}':  {re.findall(pattern_greedy, text)}")
print(re.findall(r"a+", text))

#4. Gruppierung
#(ABC)          --> Capturing-Gruppe, speichert den Match
#(?:ABC)        --> Non-capturing-Gruppe, speichert nicht
#(?P<name>...)  --> Benannte Gruppe
#\1, \2, ...    --> Referenzen auf Capturing-Gruppen
date = "Geboren: 24.12.1980"
pattern_named = r"(?P<day>\d{2})\.(?P<month>\d{2})\.(?P<year>\d{4})"
m2 = re.search(pattern_named, date)
if m2:    #!r -> ruft intern repr(object) auf, !s -> ruft intern str(object) auf [DEFAULT] und !a -> ruft intern ascii(object) auf [non-ASCII-Zeichen werden escaped]
    print(f" Datum in {date!r} -> Day: {m2.group('day')}, Month: {m2.group('month')}, Year: {m2.group('year')}")