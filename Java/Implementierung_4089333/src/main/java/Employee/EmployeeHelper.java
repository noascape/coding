package Employee;

import PA.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EmployeeHelper {

    public static void registerEmployees(DepositMachine depositMachine, List<Employee> employees) {
        for (Employee employee : employees) {
            depositMachine.getCentralUnit().registerCard(employee.getCardId());
        }
    }

    public static void authenticateRandomEmployee(Reader reader, List<Employee> employees) {
        Employee randomEmployee = employees.get(new Random().nextInt(employees.size()));
        randomEmployee.authenticate(reader);
    }

    public static List<Employee> createEmployees() {
        return Arrays.asList(new Employee("Andy"), new Employee("Bob"), new Employee("Charlie"));
    }
}
