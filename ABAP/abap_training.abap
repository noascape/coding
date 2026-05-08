REPORT z_abap_grundlagen_codesammlung.

*-----------------------------------------------------------------------
* ABAP-Grundlagen-Codesammlung
* Zweck:
*   Kurze, praktische Beispiele zu wichtigen ABAP-Grundlagen.
*   Die Datei kann als .abap gespeichert und als Lern-/Nachschlagecode
*   verwendet werden.
*-----------------------------------------------------------------------

*-----------------------------------------------------------------------
* 1) Einfache Variablen und Typen
*-----------------------------------------------------------------------
DATA lv_name   TYPE string VALUE 'Noah'.
DATA lv_age    TYPE i      VALUE 21.
DATA lv_price  TYPE p LENGTH 8 DECIMALS 2 VALUE '19.99'.
DATA lv_active TYPE abap_bool VALUE abap_true.

WRITE: / 'Name:', lv_name,
       / 'Alter:', lv_age,
       / 'Preis:', lv_price.

IF lv_active = abap_true.
  WRITE: / 'Status: aktiv'.
ENDIF.


*-----------------------------------------------------------------------
* 2) Konstanten
*-----------------------------------------------------------------------
CONSTANTS c_currency TYPE string VALUE 'EUR'.

WRITE: / 'Währung:', c_currency.


*-----------------------------------------------------------------------
* 3) Struktur wie ein einzelner Datensatz
*-----------------------------------------------------------------------
TYPES: BEGIN OF ty_person,
         id   TYPE i,
         name TYPE string,
         city TYPE string,
       END OF ty_person.

DATA ls_person TYPE ty_person.

ls_person-id = 1.
ls_person-name = 'Anna'.
ls_person-city = 'Stuttgart'.

WRITE: / 'Person:', ls_person-id, ls_person-name, ls_person-city.


*-----------------------------------------------------------------------
* 4) Interne Tabelle wie eine Liste von Datensätzen
*-----------------------------------------------------------------------
DATA lt_people TYPE STANDARD TABLE OF ty_person WITH EMPTY KEY.
DATA ls_new_person TYPE ty_person.

ls_new_person-id = 2.
ls_new_person-name = 'Beate'.
ls_new_person-city = 'München'.
APPEND ls_new_person TO lt_people.

APPEND VALUE #( id = 3 name = 'Horst' city = 'Berlin' ) TO lt_people.
APPEND VALUE #( id = 4 name = 'Kevin' city = 'Köln' ) TO lt_people.

LOOP AT lt_people INTO DATA(ls_loop_person).
  WRITE: / ls_loop_person-id, ls_loop_person-name, ls_loop_person-city.
ENDLOOP.


*-----------------------------------------------------------------------
* 5) Lesen, Ändern und Löschen in internen Tabellen
*-----------------------------------------------------------------------

" Einzelnen Eintrag suchen
READ TABLE lt_people INTO DATA(ls_found) WITH KEY name = 'Beate'.

IF sy-subrc = 0.
  WRITE: / 'Gefunden:', ls_found-name, ls_found-city.
ELSE.
  WRITE: / 'Nicht gefunden'.
ENDIF.

" Eintrag per Index ändern
READ TABLE lt_people INTO DATA(ls_change) INDEX 1.
IF sy-subrc = 0.
  ls_change-city = 'Hamburg'.
  MODIFY lt_people FROM ls_change INDEX 1.
ENDIF.

" Eintrag löschen
DELETE lt_people WHERE name = 'Kevin'.


*-----------------------------------------------------------------------
* 6) Sortieren und Duplikate entfernen
*-----------------------------------------------------------------------
SORT lt_people BY name ASCENDING.

" Duplikate entfernen funktioniert sinnvoll nach vorherigem SORT
DELETE ADJACENT DUPLICATES FROM lt_people COMPARING name.


*-----------------------------------------------------------------------
* 7) Bedingungen
*-----------------------------------------------------------------------
DATA lv_score TYPE i VALUE 75.

IF lv_score >= 90.
  WRITE: / 'Sehr gut'.
ELSEIF lv_score >= 50.
  WRITE: / 'Bestanden'.
ELSE.
  WRITE: / 'Nicht bestanden'.
ENDIF.

CASE lv_score.
  WHEN 100.
    WRITE: / 'Maximale Punktzahl'.
  WHEN 75.
    WRITE: / 'Gute Punktzahl'.
  WHEN OTHERS.
    WRITE: / 'Andere Punktzahl'.
ENDCASE.


*-----------------------------------------------------------------------
* 8) Schleifen
*-----------------------------------------------------------------------

" Zählschleife
DO 5 TIMES.
  WRITE: / 'Durchlauf:', sy-index.
ENDDO.

" WHILE-Schleife
DATA lv_counter TYPE i VALUE 1.

WHILE lv_counter <= 3.
  WRITE: / 'Counter:', lv_counter.
  lv_counter = lv_counter + 1.
ENDWHILE.

" LOOP über interne Tabelle
LOOP AT lt_people INTO DATA(ls_person_loop).
  WRITE: / 'Name aus Tabelle:', ls_person_loop-name.
ENDLOOP.


*-----------------------------------------------------------------------
* 9) Zeichenketten / Strings
*-----------------------------------------------------------------------
DATA lv_firstname TYPE string VALUE 'Max'.
DATA lv_lastname  TYPE string VALUE 'Mustermann'.
DATA lv_fullname  TYPE string.

" String Template
lv_fullname = |{ lv_firstname } { lv_lastname }|.

WRITE: / 'Vollständiger Name:', lv_fullname.

" Enthält-Prüfung
IF lv_fullname CS 'Max'.
  WRITE: / 'Der Name enthält Max'.
ENDIF.

" Länge ermitteln
DATA lv_length TYPE i.
lv_length = strlen( lv_fullname ).

WRITE: / 'Länge:', lv_length.


*-----------------------------------------------------------------------
* 10) Zahlen und Berechnungen
*-----------------------------------------------------------------------
DATA lv_a TYPE i VALUE 10.
DATA lv_b TYPE i VALUE 3.

DATA lv_sum TYPE i.
DATA lv_div TYPE p LENGTH 8 DECIMALS 2.

lv_sum = lv_a + lv_b.
lv_div = lv_a / lv_b.

WRITE: / 'Summe:', lv_sum,
       / 'Division:', lv_div.


*-----------------------------------------------------------------------
* 11) Form-Routine als einfache Funktion
*-----------------------------------------------------------------------
FORM print_person USING ps_person TYPE ty_person.
  WRITE: / 'FORM-Ausgabe:', ps_person-id, ps_person-name, ps_person-city.
ENDFORM.

PERFORM print_person USING ls_person.


*-----------------------------------------------------------------------
* 12) Methode in lokaler Klasse
*-----------------------------------------------------------------------
CLASS lcl_calculator DEFINITION.
  PUBLIC SECTION.
    METHODS add
      IMPORTING
        iv_a TYPE i
        iv_b TYPE i
      RETURNING
        VALUE(rv_result) TYPE i.
ENDCLASS.

CLASS lcl_calculator IMPLEMENTATION.
  METHOD add.
    rv_result = iv_a + iv_b.
  ENDMETHOD.
ENDCLASS.

DATA lo_calculator TYPE REF TO lcl_calculator.
CREATE OBJECT lo_calculator.

DATA lv_result TYPE i.
lv_result = lo_calculator->add( iv_a = 5 iv_b = 7 ).

WRITE: / 'Methoden-Ergebnis:', lv_result.


*-----------------------------------------------------------------------
* 13) Fehlerbehandlung mit TRY / CATCH
*-----------------------------------------------------------------------
TRY.
    DATA lv_number_text TYPE string VALUE '123'.
    DATA lv_number TYPE i.

    lv_number = lv_number_text.

    WRITE: / 'Konvertierte Zahl:', lv_number.

  CATCH cx_sy_conversion_no_number INTO DATA(lx_error).
    WRITE: / 'Fehler bei Konvertierung:', lx_error->get_text( ).
ENDTRY.


*-----------------------------------------------------------------------
* 14) Optionaler Wert / Initial-Wert prüfen
*-----------------------------------------------------------------------
DATA lv_optional TYPE string.

IF lv_optional IS INITIAL.
  WRITE: / 'Der Wert ist noch leer/initial.'.
ENDIF.


*-----------------------------------------------------------------------
* 15) Tabellen moderner mit VALUE füllen
*-----------------------------------------------------------------------
DATA lt_cities TYPE STANDARD TABLE OF string WITH EMPTY KEY.

lt_cities = VALUE #(
  ( 'Stuttgart' )
  ( 'München' )
  ( 'Berlin' )
).

LOOP AT lt_cities INTO DATA(lv_city).
  WRITE: / 'Stadt:', lv_city.
ENDLOOP.


*-----------------------------------------------------------------------
* 16) Interne Tabelle filtern
*-----------------------------------------------------------------------
DATA lt_filtered TYPE STANDARD TABLE OF ty_person WITH EMPTY KEY.

LOOP AT lt_people INTO DATA(ls_filter_person)
     WHERE city = 'München'.
  APPEND ls_filter_person TO lt_filtered.
ENDLOOP.


*-----------------------------------------------------------------------
* 17) Beispiel: Key-Value-ähnliche Struktur
*-----------------------------------------------------------------------
TYPES: BEGIN OF ty_setting,
         key   TYPE string,
         value TYPE string,
       END OF ty_setting.

DATA lt_settings TYPE STANDARD TABLE OF ty_setting WITH EMPTY KEY.

APPEND VALUE #( key = 'theme' value = 'dark' ) TO lt_settings.
APPEND VALUE #( key = 'language' value = 'de' ) TO lt_settings.

READ TABLE lt_settings INTO DATA(ls_setting) WITH KEY key = 'language'.

IF sy-subrc = 0.
  WRITE: / 'Sprache:', ls_setting-value.
ENDIF.


*-----------------------------------------------------------------------
* 18) Beispiel: Einfaches Mapping von Rohdaten in Zieldaten
*-----------------------------------------------------------------------
TYPES: BEGIN OF ty_raw_user,
         user_id TYPE i,
         fullname TYPE string,
       END OF ty_raw_user.

TYPES: BEGIN OF ty_target_user,
         id   TYPE i,
         name TYPE string,
       END OF ty_target_user.

DATA lt_raw_users TYPE STANDARD TABLE OF ty_raw_user WITH EMPTY KEY.
DATA lt_target_users TYPE STANDARD TABLE OF ty_target_user WITH EMPTY KEY.

lt_raw_users = VALUE #(
  ( user_id = 1 fullname = 'Anna Müller' )
  ( user_id = 2 fullname = 'Beate Schmidt' )
).

LOOP AT lt_raw_users INTO DATA(ls_raw_user).
  APPEND VALUE #(
    id   = ls_raw_user-user_id
    name = ls_raw_user-fullname
  ) TO lt_target_users.
ENDLOOP.

LOOP AT lt_target_users INTO DATA(ls_target_user).
  WRITE: / 'Zieluser:', ls_target_user-id, ls_target_user-name.
ENDLOOP.


*-----------------------------------------------------------------------
* 19) Hilfreiche Systemfelder
*-----------------------------------------------------------------------
WRITE: / 'Aktuelles Datum:', sy-datum.
WRITE: / 'Aktuelle Uhrzeit:', sy-uzeit.
WRITE: / 'Aktueller Benutzer:', sy-uname.
WRITE: / 'Letzter Rückgabecode sy-subrc:', sy-subrc.


*-----------------------------------------------------------------------
* 20) Mini-Beispiel: Daten suchen und Ergebnis ausgeben
*-----------------------------------------------------------------------
DATA lv_search_name TYPE string VALUE 'Horst'.

READ TABLE lt_people INTO DATA(ls_search_result)
  WITH KEY name = lv_search_name.

IF sy-subrc = 0.
  WRITE: / |{ lv_search_name } wohnt in { ls_search_result-city }.|.
ELSE.
  WRITE: / |{ lv_search_name } wurde nicht gefunden.|.
ENDIF.
