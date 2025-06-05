package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import domain.Student;
import domain.Group;

/**
* Класс реализации функций взаимодействия с базой данных для таблицы students
*/
public class StudentDbDAO implements RepositoryDAO<Student> {

    // SQL-запросы к таблице students
    private static final String SELECT_ALL_STUDENTS = 
        "SELECT id, last_name, first_name, middle_name, birth_date, phone, email, group_id FROM students ORDER BY last_name ASC";
    
    private static final String SELECT_STUDENT_BY_ID = 
        "SELECT id, last_name, first_name, middle_name, birth_date, phone, email, group_id FROM students WHERE id = ?";
    
    private static final String INSERT_STUDENT = 
        "INSERT INTO students(last_name, first_name, middle_name, birth_date, phone, email, group_id) VALUES(?, ?, ?, ?, ?, ?, ?)";
    
    private static final String UPDATE_STUDENT = 
        "UPDATE students SET last_name = ?, first_name = ?, middle_name = ?, birth_date = ?, phone = ?, email = ?, group_id = ? WHERE id = ?";
    
    private static final String DELETE_STUDENT = 
        "DELETE FROM students WHERE id = ?";
    
    private static final String SELECT_STUDENTS_BY_GROUP = 
        "SELECT id, last_name, first_name, middle_name, birth_date, phone, email, group_id FROM students WHERE group_id = ?";

    private ConnectionBuilder builder = new DbConnectionBuilder();
    
    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public Long insert(Student student) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT_STUDENT, new String[] { "id" })) {
        	System.out.println(student);
            // pst.setLong(1, student.getId());
            pst.setLong(1, 5L);
            pst.setString(1, student.getLastName());
            pst.setString(2, student.getFirstName());
            pst.setString(3, student.getMiddleName());
            pst.setString(4, student.getBirthDate());
            pst.setString(5, student.getPhone());
            pst.setString(6, student.getEmail());
            pst.setLong(7, student.getGroup().getId());
            pst.executeUpdate();
            
            ResultSet gk = pst.getGeneratedKeys();
            Long id = -1L;
            if (gk.next()) {
                id = gk.getLong("id");
            }
            gk.close();
            return id;
            
        } catch (Exception e) {
            throw new Exception(e);
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
            pst.setLong(7, student.getGroup().getId());
            pst.setLong(8, student.getId());
            pst.executeUpdate();
            
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE_STUDENT)) {
            
            pst.setLong(1, id);
            pst.executeUpdate();
            
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public Student findById(Long id) throws Exception {
        Student student = null;
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_STUDENT_BY_ID)) {
            
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                student = fillStudent(rs);
            }
            rs.close();
            
        } catch (Exception e) {
            throw new Exception(e);
        }
        return student;
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
            
        } catch (Exception e) {
            throw new Exception(e);
        }
        return list;
    }

    // Дополнительный метод для получения студентов по группе
    public List<Student> findByGroupId(String groupId) throws Exception {
        List<Student> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_STUDENTS_BY_GROUP)) {
            
            pst.setString(1, groupId);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                list.add(fillStudent(rs));
            }
            rs.close();
            
        } catch (Exception e) {
            throw new Exception(e);
        }
        return list;
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
        
        // Создаем временный объект Group только с id
        Group group = new Group();
        group.setId(rs.getLong("group_id"));
        student.setGroup(group);
        
        return student;
    }

    public Student findById(Long id, List<Student> students) {
        if (students != null) {
            for (Student student : students) {
                if (student.getId().equals(id)) {
                    return student;
                }
            }
        }
        return null;
    }
}