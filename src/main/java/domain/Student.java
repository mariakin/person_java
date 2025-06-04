package domain;

/**
 * Класс данных о студентах
 */
public class Student {
    // Идентификатор студента
    private Long id;
    // Фамилия, имя и отчество студента
    private String lastName;
    private String firstName;
    private String middleName;
    // Дата рождения
    private String birthDate;
    // Контактные данные
    private String phone;
    private String email;
    // Группа студента
    private String group;

    public Student() {
    }

    public Student(String lastName, String firstName, String middleName, String group) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.group = group;
    }

    public Student(Long id, String lastName, String firstName, String middleName, 
                  String birthDate, String phone, String email, String group) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
        this.group = group;
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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Student {" +
               "id=" + id +
               ", lastName='" + lastName + '\'' +
               ", firstName='" + firstName + '\'' +
               ", middleName='" + middleName + '\'' +
               ", birthDate='" + birthDate + '\'' +
               ", phone='" + phone + '\'' +
               ", email='" + email + '\'' +
               ", group='" + group + '\'' +
               '}';
    }
}