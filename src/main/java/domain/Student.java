package domain;
/**
 * Класс данных о студентах
 */
public class Student {
    // Идентификатор студента
    private Long id;
    // Фамилия и имя студента
    private String lastName;
    private String firstName;
    // Группа студента
    private String group;
    // Дата поступления
    private String admissionDate;
    public Student() {
    }
    public Student(String lastName, String firstName, String group) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.group = group;
    }
    public Student(Long id, String lastName, String firstName, String group, String admissionDate) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.group = group;
        this.admissionDate = admissionDate;
    }
    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getGroup() {
        return group;
    }
    public void setGroup(String group) {
        this.group = group;
    }
    public String getAdmissionDate() {
        return admissionDate;
    }
    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }
    @Override
    public String toString() {
        return "Student {" +
               "id=" + id +
               ", lastName='" + lastName + '\'' +
               ", firstName='" + firstName + '\'' +
               ", group='" + group + '\'' +
               ", admissionDate='" + admissionDate + '\'' +
               '}';
    }
}