import csv

with open('Python/statistic/state.csv', newline='') as csvfile: 
    spamreader = csv.reader(csvfile, delimiter=',', quotechar='|')        #delimiter = Trennzeichen von Spalten  | quotechar gibt an, welches Zeichen verwendet wird um Inhalte einzuschließen
    for row in spamreader:
        print(', '.join(row))     #.join verbindet Elemente eines iterierbaren Objekts zu einem String
