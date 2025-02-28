#include <iostream>
#include <string>

using namespace std;

string buildModbusMsg( string addr,
                       string data,
                       string checksum )
// Erzeugt eine Modbus-Nachricht aus Adresse addr,
// Daten data und Prüfsumme checksum
{
    if( addr.size() != 2 ||
        data.empty() ||
        checksum.size() != 2  )
    {
        return "";
    }

    string msg = ":" + addr + data + checksum + "\r\n";

    return msg;
}

bool isModbusMsg( string msg )
// Liefert true, wenn Start- und Endezeichen von msg
// korrekt sind und die Nachricht mindestens
// 7 Zeichen lang ist, sonst false
{
    return ( msg.size() > 7 &&
             msg.front() == ':' &&
             msg.substr( msg.size()-2, 2 ) == "\r\n" );
}

string addressFromModbusMsg( string msg )
// Liefert die Adresse, an die die Nachricht msg
// geschickt wird oder einen leeren String,
// wenn das Nachrichtenformat falsch ist
{
    // Abbruch bei falschem Format
    if( !isModbusMsg( msg ) )
    {
        return "";
    }

    // Extrahiere die beiden Adresszeichen
    return ( msg.substr( 1, 2 ) );
}

int main()
{
    // Beispielnachricht erzeugen
    string modbusmsg = buildModbusMsg( "01", "0300010002", "FB" );

    cout << "Nachricht: " << modbusmsg << endl;

    // Bei inkorrektem Format abbrechen
    if( !isModbusMsg( modbusmsg ) )
    {
        cout << "Nachrichtenformat ist nicht korrekt." << endl;
        return 0;
    }

    // Adresse anzeigen
    string address = addressFromModbusMsg( modbusmsg );

    if( address == "00" )
    {
        cout << "Broadcast an alle" << endl;
    }
    else
    {
        cout << "Adresse:   " << address << endl;
    }

    return 0;
}
