#include <iostream>
#include <algorithm>

using namespace std;

// Repräsentiert einen Motor
class Motor
{
public:
    // Maximalgeschwindigkeit
    static constexpr int maxSpeed = 100;

    Motor( int value = 0 );
    void setSpeed( int value );
    int getSpeed() const;

private:
    // Aktuelle Geschwindigkeit
    int speed;
};

Motor::Motor( int value )
// Erzeugt einen Motor mit der Anfangsgeschwindigkeit value
{
    setSpeed( value );
}

void Motor::setSpeed( int value )
// Ändert die Geschwindigkeit auf value,
// ein negativer Wert bedeutet rückwärts
{
    speed = clamp( value, -maxSpeed, maxSpeed );
}

int Motor::getSpeed() const
// Liefert die aktuelle Geschwindigkeit
{
    return speed;
}

// Repräsentiert einen Antrieb mit zwei Motoren
class Drive
{
public:
    Drive();
    void start( int speed );
    void stop();
    void turnLeft( int speed );
    void turnRight( int speed );

private:
    // Linker Motor
    Motor motor_left;
    // Rechter Motor
    Motor motor_right;
};

Drive::Drive()
// Erzeugt den Antrieb im Stillstand
    : motor_left( 0 ), motor_right( 0 )
{
}

void Drive::start( int speed )
// Fährt geradeaus mit Geschwindigkeit speed
// oder rückwärts mit negativer Geschwindigkeit
{
    motor_left.setSpeed( speed );
    motor_right.setSpeed( speed );
}

void Drive::stop()
// Stoppt das Fahrzeug
{
    motor_left.setSpeed( 0 );
    motor_right.setSpeed( 0 );
}

void Drive::turnLeft( int speed )
// Dreht das Fahrzeug nach links mit Geschwindigkeit speed
{
    motor_left.setSpeed( -speed );
    motor_right.setSpeed( speed );
}

void Drive::turnRight( int speed )
// Dreht das Fahrzeug nach rechts mit Geschwindigkeit speed
{
    motor_left.setSpeed( speed );
    motor_right.setSpeed( -speed );
}


int main()
{
    Drive maindrive;

    maindrive.start( 50 );
    maindrive.turnLeft( 10 );
    maindrive.stop();

    return 0;
}
