import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDirectory {

    private final Map<Integer, Employee> employeeMap = new HashMap<>();

    public void add(Employee employee) {
        employeeMap.put(employee.getPersonnelNumber(), employee);
    }

    public List<Employee> searchByExperience(int days) {
        return employeeMap.values().stream().filter(e -> e.getExperienceInDays() == days).toList();
    }

    public List<String> getPhoneNumberByName(String name) {
        return employeeMap.values().stream().filter(e -> e.getName().equals(name)).map(Employee::getPhoneNumber).toList();
    }

    public Employee searchByPersonnelNumber(int personnelNumber) {
        if (employeeMap.containsKey(personnelNumber)) {
            return employeeMap.get(personnelNumber);
        }
        return null;
    }

}
