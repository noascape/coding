#ifndef CIRCLE_H
#define CIRCLE_H

class Circle
{
public:
    Circle( double nradius = 0.0 );
    // Erzeugt einen Kreis mit Radius nradius

    double Area() const;
    // Berechnet die Fläche des Kreises

    double Perimeter() const;
    // Liefert den Umfang des Kreises

    double Diameter() const;
    // Liefert den Durchmesser des Kreises

    static constexpr double PI = 3.14159265358979323846;

protected:
    double radius;
};

#endif
