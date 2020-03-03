package service;

import dao.UserDAO;
import model.User;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    public List<User> getAllUsers() {
        return getUserDAO().getAllUsers();
    }

    public void addUser(User user) {
        getUserDAO().addUser(user);
    }

    public void deleteUser(int id) {
        getUserDAO().deleteUser(id);
    }

    public void updateUser(User user) {
        getUserDAO().updateUser(user);
    }

    public static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());
            StringBuilder url = new StringBuilder();
            url.
                    append("jdbc:mysql://").
                    append("localhost:").
                    append("3306/").
                    append("pp1?").
                    append("user=root&").
                    append("password=root").
                    append("&serverTimezone=UTC");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private static UserDAO getUserDAO() {
        return new UserDAO(getMysqlConnection());
    }

}
