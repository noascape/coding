#include <iostream>
using namespace std;

// Eine einfache LED-Klasse
class Led
{
public:
    Led();
    void turnOn();
    void turnOff();
    void toggle();
    bool isOn() const;
private:
    bool ison;
};

Led::Led()
// Erzeugt eine neue LED, die anfangs aus ist
{
    turnOff();
}

void Led::turnOn()
// Schaltet die LED an
{
    ison = true;
}

void Led::turnOff()
// Schaltet die LED aus
{
    ison = false;
}

void Led::toggle()
// Wechselt den Zustand der LED
{
    ison = !ison;
}

bool Led::isOn() const
// Liefert true, wenn die LED an ist
{
    return ison;
}


int main()
{
    Led led1;

    led1.turnOn();

    if( led1.isOn() )
    {
        cout << "Die LED ist an." << endl;
    }
    else
    {
        cout << "Die LED ist aus." << endl;
    }

    return 0;
}
