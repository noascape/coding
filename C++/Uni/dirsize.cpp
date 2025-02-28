#include <iostream>
#include <filesystem>

using namespace std;

int main( int argc, char* argv[] )
{
    // Zu untersuchenden Pfad als Kommandozeilenparameter lesen,
    // aktuellen Ordner verwenden, falls kein Parameter übergeben
    filesystem::path folder( argc > 1 ? filesystem::absolute( argv[ 1 ] )
                                      : filesystem::current_path() );

    // Nur Ordner untersuchen
    if( !filesystem::is_directory( folder ) )
    {
        cerr << folder.string() << " ist kein Ordner." << endl;
        return -1;
    }

    // Gesamtgröße der Dateien
    uintmax_t totalsize = 0;

    // Anzahl der Dateien
    uintmax_t nrfiles   = 0;

    // Anzahl der Ordner
    uintmax_t nrdirs = 0;

    // Iteriere durch alle Dateien und Unterordner
    for( const auto& entry :
        filesystem::recursive_directory_iterator( folder,
                                                  filesystem::directory_options::skip_permission_denied ) )
    {
        if( entry.is_directory() )
        {
            nrdirs++;
        }

        if( entry.is_regular_file() )
        {
            nrfiles++;
            totalsize += entry.file_size();
        }
    }

    // Ergebnis anzeigen
    cout << "Pfad:        " << folder.string() << endl;
    cout << "Ordner:      " << nrdirs << endl;
    cout << "Dateien:     " << nrfiles << endl;
    cout << "Gesamtgröße: " << totalsize << " B" << endl;

    return 0;
}
