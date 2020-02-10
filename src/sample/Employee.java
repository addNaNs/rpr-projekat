package sample;

public class Employee extends Person {
    public Employee(String firstName, String lastName, String email, String profileImagePath, Gender sex) {
        super(firstName, lastName, email, profileImagePath, sex);
    }

    public Employee(int id, String firstName, String lastName, String email, String profileImagePath, Gender sex) {
        super(id, firstName, lastName, email, profileImagePath, sex);
    }

    public Employee(Person person) {
        super(person);
    }
}
