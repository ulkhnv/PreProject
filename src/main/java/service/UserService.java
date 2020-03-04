package service;

import dao.UserDAO;
import dao.UserDAOFactory;
import model.User;

import java.util.List;

public class UserService {

    private static UserService userService;
    private UserDAOFactory factory;

    private UserService() {
        factory = new UserDAOFactory();
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

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

    public UserDAO getUserDAO() {
        return factory.createUserDAO();
    }

}
