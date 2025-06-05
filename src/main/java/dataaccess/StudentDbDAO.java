package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import domain.Student;
import domain.Group;

public class StudentDbDAO implements RepositoryDAO<Student> {

    // SQL-запросы к таблице students
    private static final String SELECT_ALL_STUDENTS = 
        "SELECT id, last_name, first_name, middle_name, birth_date, phone, email, group_name FROM students";
    
    private static final String SELECT_STUDENT_BY_ID = 
        "SELECT id, last_name, first_name, middle_name, birth_date, phone, email, group_name FROM students WHERE id = ?";
    
    private static final String INSERT_STUDENT = 
        "INSERT INTO students(last_name, first_name, middle_name, birth_date, phone, email, group_name) " +
        "VALUES(?, ?, ?, ?, ?, ?, ?)";
    
    private static final String UPDATE_STUDENT = 
        "UPDATE students SET last_name = ?, first_name = ?, middle_name = ?, birth_date = ?, " +
        "phone = ?, email = ?, group_name = ? WHERE id = ?";
    
    private static final String DELETE_STUDENT = 
        "DELETE FROM students WHERE id = ?";
    
    private static final String SELECT_STUDENTS_BY_GROUP = 
        "SELECT id, last_name, first_name, middle_name, birth_date, phone, email, group_name " +
        "FROM students WHERE group_name = ?";

    private ConnectionBuilder builder = new DbConnectionBuilder();
    
    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public Long insert(Student student) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT_STUDENT, new String[] { "id" })) {
            
            pst.setString(1, student.getLastName());
            pst.setString(2, student.getFirstName());
            pst.setString(3, student.getMiddleName());
            pst.setString(4, student.getBirthDate());
            pst.setString(5, student.getPhone());
            pst.setString(6, student.getEmail());
            pst.setString(7, student.getGroup().getGroupName()); // Используем имя группы
            
            pst.executeUpdate();
            
            try (ResultSet gk = pst.getGeneratedKeys()) {
                if (gk.next()) {
                    return gk.getLong("id");
                }
            }
            return -1L;
        } catch (Exception e) {
            throw new Exception("Ошибка при добавлении студента", e);
        }
    }

    @Override
    public void update(Student student) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE_STUDENT)) {
            
            pst.setString(1, student.getLastName());
            pst.setString(2, student.getFirstName());
            pst.setString(3, student.getMiddleName());
            pst.setString(4, student.getBirthDate());
            pst.setString(5, student.getPhone());
            pst.setString(6, student.getEmail());
            pst.setString(7, student.getGroup().getGroupName());
            pst.setLong(8, student.getId());
            
            pst.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Ошибка при обновлении студента", e);
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE_STUDENT)) {
            
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Ошибка при удалении студента", e);
        }
    }

    @Override
    public Student findById(Long id) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_STUDENT_BY_ID)) {
            
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return fillStudent(rs);
                }
            }
            return null;
        } catch (Exception e) {
            throw new Exception("Ошибка при поиске студента по ID", e);
        }
    }

    @Override
    public List<Student> findAll() throws Exception {
        List<Student> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_ALL_STUDENTS);
             ResultSet rs = pst.executeQuery()) {
            
            while (rs.next()) {
                list.add(fillStudent(rs));
            }
            return list;
        } catch (Exception e) {
            throw new Exception("Ошибка при получении списка студентов", e);
        }
    }

    public List<Student> findByGroupName(String groupName) throws Exception {
        List<Student> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_STUDENTS_BY_GROUP)) {
            
            pst.setString(1, groupName);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    list.add(fillStudent(rs));
                }
            }
            return list;
        } catch (Exception e) {
            throw new Exception("Ошибка при поиске студентов по группе", e);
        }
    }

    private Student fillStudent(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setId(rs.getLong("id"));
        student.setLastName(rs.getString("last_name"));
        student.setFirstName(rs.getString("first_name"));
        student.setMiddleName(rs.getString("middle_name"));
        student.setBirthDate(rs.getString("birth_date"));
        student.setPhone(rs.getString("phone"));
        student.setEmail(rs.getString("email"));
        
        // Создаем временный объект Group только с именем
        Group group = new Group();
        group.setName(rs.getString("group_name"));
        student.setGroup(group);
        
        return student;
    }
}