package dao;

import model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {
    private Connection connection;

    public UserJdbcDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet r = stmt.executeQuery("SELECT * FROM users")) {
            while (r.next()) {
                list.add(new User(r.getInt(1), r.getString(2), r.getString(3),r.getString(4),r.getInt(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void addUser(User user) {
        String query = "INSERT INTO users (name,password,role,age) VALUES (?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
            stmt.setInt(4, user.getAge());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        String query = "DELETE FROM users WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        String query = "UPDATE users SET name=?, password=?, role=?, age=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
            stmt.setInt(4, user.getAge());
            stmt.setInt(5, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean validateUser(String name, String password) {
        String query = "SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Users u " +
                "WHERE u.name=?, u.password=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, password);
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    return result.getBoolean(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public String getUserRole(String name) {
        String query = "SELECT role FROM users WHERE name=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    return result.getString(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
