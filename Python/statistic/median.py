import csv

with open('Python/statistic/state.csv', newline='') as csvfile: 
    spamreader = csv.reader(csvfile, delimiter=' ', quotechar='|')
    for row in spamreader:
        print(', '.join(row))
