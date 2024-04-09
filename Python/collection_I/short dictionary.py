transactions = [
    {"date": "14.01.2024", "purchase": "10", "income": "0"},
    {"date": "14.01.2024", "purchase": "10", "income": "0"}
]

def find_all(my_key, my_value):
    matching_transactions = [t for t in transactions if t[my_key] == my_value]
    return matching_transactions

selected_transactions = find_all('date', '14.01.2024')
print("Selected Transactions:", selected_transactions)
print("Number of Transactions:", len(selected_transactions))
