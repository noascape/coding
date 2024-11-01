package Application;

import PA.*;
import Employee.*;
import Customer.*;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) {

        DepositMachine depositMachine = new DepositMachine();

        List<Employee> employees = EmployeeHelper.createEmployees();
        EmployeeHelper.registerEmployees(depositMachine, employees);
        EmployeeHelper.authenticateRandomEmployee(depositMachine.getReader(), employees);

        List<Customer> customers = CustomerHelper.createCustomers();
        InsertionSlot insertionSlot = depositMachine.getInsertionSlot();

        for (Customer customer : customers) {
            log.info("\nKunde: {} " , customer.getName());
            insertionSlot.setCustomer(customer);
            customer.insertAllItems(insertionSlot, depositMachine);
        }
    }
}


