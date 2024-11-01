package Employee;

import java.util.List;
import java.util.Random;

public class RandomEmployeeSelector {
    private static final Random random = new Random();

    public static Employee selectRandomEmployee(List<Employee> employees) {
        return employees.get(random.nextInt(employees.size()));
    }
}
