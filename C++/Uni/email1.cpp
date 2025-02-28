#include <iostream>
#include <regex>
#include <string>

using namespace std;

bool isValidEmail(const string& email)
// Prüft, ob die übergebene E-Mail-Adresse korrekt aufgebaut ist.
// Ob die Adresse tatsächlich funktioniert, wird nicht geprüft.
{
    // RegEx für den den Aufbau <User>@<Domain>.<TLD>
    // (z.B. anna.mueller@company.com)
    const regex emailRegex(
        R"((^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$))"
    );

    return regex_match(email, emailRegex);
}

int main()
{
    // Zu prüfende E-Mail-Adresse einlesen
    string email;
    cout << "Geben Sie eine E-Mail-Adresse ein: ";
    getline( cin, email );

    // E-Mail-Adresse prüfen
    if (isValidEmail(email))
    {
        cout << "Die E-Mail-Adresse ist gültig.\n";
    } else
    {
        cout << "Die E-Mail-Adresse ist ungültig.\n";
    }

    return 0;
}
