package domain;

/**
 * Класс данных о группах
 */
public class Group {
    // Идентификатор группы
    private Long id;
    // Наименование группы
    private String name;
    // Факультет
    private String faculty;
    // Курс
    private Integer course;
    // Вид обучения
    private String educationType;

    public Group() {
    }

    public Group(String name, String faculty, Integer course, String educationType) {
        this.name = name;
        this.faculty = faculty;
        this.course = course;
        this.educationType = educationType;
    }

    public Group(Long id, String name, String faculty, Integer course, String educationType) {
        this.id = id;
        this.name = name;
        this.faculty = faculty;
        this.course = course;
        this.educationType = educationType;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public String getEducationType() {
        return educationType;
    }

    public void setEducationType(String educationType) {
        this.educationType = educationType;
    }

    @Override
    public String toString() {
        return "Group {" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", faculty='" + faculty + '\'' +
               ", course=" + course +
               ", educationType='" + educationType + '\'' +
               '}';
    }
}