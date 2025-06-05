package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import domain.Group;

/**
* Класс реализации функций взаимодействия с базой данных для таблицы groups
*/
public class GroupDbDAO implements RepositoryDAO<Group> {
    
    // SQL-запросы к таблице groups
    private static final String SELECT_ALL_GROUPS = 
        "SELECT id, name, faculty, course, education_type FROM groups ORDER BY name ASC";
    
    private static final String SELECT_GROUP_BY_ID = 
        "SELECT id, name, faculty, course, education_type FROM groups WHERE id = ?";
    
    private static final String INSERT_GROUP = 
        "INSERT INTO groups(name, faculty, course, education_type) VALUES(?, ?, ?, ?)";
    
    private static final String UPDATE_GROUP = 
        "UPDATE groups SET name = ?, faculty = ?, course = ?, education_type = ? WHERE id = ?";
    
    private static final String DELETE_GROUP = 
        "DELETE FROM groups WHERE id = ?";
    
    private ConnectionBuilder builder = new DbConnectionBuilder();
    
    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public Long insert(Group group) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT_GROUP, new String[] { "id" })) {
            
            pst.setString(1, group.getGroupName());
            pst.setString(2, group.getFaculty());
            pst.setInt(3, group.getCourse());
            pst.setString(4, group.getEducationType());
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
    public void update(Group group) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE_GROUP)) {
            
            pst.setString(1, group.getGroupName());
            pst.setString(2, group.getFaculty());
            pst.setInt(3, group.getCourse());
            pst.setString(4, group.getEducationType());
            pst.setLong(5, group.getId());
            pst.executeUpdate();
            
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE_GROUP)) {
            
            pst.setLong(1, id);
            pst.executeUpdate();
            
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public Group findById(Long id) throws Exception {
        Group group = null;
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_GROUP_BY_ID)) {
            
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                group = fillGroup(rs);
            }
            rs.close();
            
        } catch (Exception e) {
            throw new Exception(e);
        }
        return group;
    }

    @Override
    public List<Group> findAll() throws Exception {
        List<Group> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_ALL_GROUPS);
             ResultSet rs = pst.executeQuery()) {
            
            while (rs.next()) {
                list.add(fillGroup(rs));
            }
            
        } catch (Exception e) {
            throw new Exception(e);
        }
        return list;
    }

    private Group fillGroup(ResultSet rs) throws SQLException {
        Group group = new Group();
        group.setId(rs.getLong("id"));
        group.setName(rs.getString("name"));
        group.setFaculty(rs.getString("faculty"));
        group.setCourse(rs.getInt("course"));
        group.setEducationType(rs.getString("education_type"));
        return group;
    }

    public Group findById(Long id, List<Group> groups) {
        if (groups != null) {
            for (Group group : groups) {
                if (group.getId().equals(id)) {
                    return group;
                }
            }
        }
        return null;
    }
}