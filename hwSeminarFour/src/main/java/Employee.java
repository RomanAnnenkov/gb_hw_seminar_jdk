import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;


@Data
public class Employee {
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private static int employeeCounter;
    @Setter(AccessLevel.NONE)
    private int personnelNumber;
    private String phoneNumber;
    private String name;
    private LocalDateTime employmentDate;

    public Employee(String phoneNumber, String name, LocalDateTime employmentDate) {
        employeeCounter++;
        this.personnelNumber = employeeCounter;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.employmentDate = employmentDate;
    }

    public int getExperienceInDays() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return (int) Duration.between(currentDateTime, employmentDate).toDays();
    }
}
