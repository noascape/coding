package Customer;

import java.util.Scanner;

public class Customer {
    Smartphone smartphone;


    boolean approveTransaction(String pin){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte geben Sie ihren Pin ein, um die Transaktion zu bestätigen: ");
        String customerInput = scanner.nextLine();

        if (customerInput.equals(pin)) {
            System.out.println("Pin korrekt. Transaktion bestätigt!");
            return true;
        } else {
            System.out.println("Falscher Pin. Transaktion abgelehnt!");
            return false;
        }
    }

}
