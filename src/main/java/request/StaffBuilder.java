package request;

import java.util.Arrays;
import java.util.List;

public class StaffBuilder {
    private String name;
    private Integer age;
    private Integer salary;
    private List<String> skills;


    public String getName() {
        return name;
    }

    public StaffBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public StaffBuilder withAge(Integer age) {
        this.age = age;
        return this;
    }

    public Integer getSalary() {
        return salary;
    }

    public StaffBuilder withSalary(Integer salary) {
        this.salary = salary;
        return this;
    }

    public Staff build() {
        Staff staff = new Staff();
        staff.setName(name);
        staff.setAge(age);
        staff.setSalary(salary);
        staff.setSkills(skills);
        return staff;
    }

    public List<String> getSkills() {
        return skills;
    }

    public StaffBuilder withSkills(String... skills) {
        this.skills = Arrays.asList(skills);
        return this;
    }
}
