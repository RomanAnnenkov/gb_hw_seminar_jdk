import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        //Создать справочник сотрудников
        //Необходимо:
        //Создать класс справочник сотрудников, который содержит внутри
        //коллекцию сотрудников - каждый сотрудник должен иметь следующие атрибуты:
        //Табельный номер
        //Номер телефона
        //Имя
        //Стаж
        //Добавить метод, который ищет сотрудника по стажу (может быть список)
        //Добавить метод, который возвращает номер телефона сотрудника по имени (может быть список)
        //Добавить метод, который ищет сотрудника по табельному номеру
        //Добавить метод добавление нового сотрудника в справочник

        EmployeeDirectory employeeDirectory = new EmployeeDirectory();
        employeeDirectory.add(new Employee("+79506575342","Roma", LocalDateTime.now()));
        employeeDirectory.add(new Employee("+79806575342","Roma", LocalDateTime.now()));
        employeeDirectory.add(new Employee("+79678576454","Ivan", LocalDateTime.now()));
        employeeDirectory.add(new Employee("+79356786464","Nikolay", LocalDateTime.now()));
        employeeDirectory.add(new Employee("+79528251342","Olga", LocalDateTime.now()));
        employeeDirectory.add(new Employee("+79118678310","Elena", LocalDateTime.now()));

        System.out.println(employeeDirectory.searchByExperience(0));
        System.out.println(employeeDirectory.getPhoneNumberByName("Roma"));
        System.out.println(employeeDirectory.searchByPersonnelNumber(2));

    }
}
