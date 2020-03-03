package service;

import dao.UserHibernateDAO;
import model.User;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;

public class UserService {
    private static UserService userService;
    private SessionFactory sessionFactory;

    private UserService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService(DBHelper.getSessionFactory());
        }
        return userService;
    }

    public List<User> getAllUsers() {
        return getUserHibernateDAO().getAllUsers();
    }

    public void addUser(User user) {
        getUserHibernateDAO().addUser(user);
    }

    public void deleteUser(int id) {
        getUserHibernateDAO().deleteUser(id);
    }

    public void updateUser(User user) {
        getUserHibernateDAO().updateUser(user);
    }

    public UserHibernateDAO getUserHibernateDAO() {
        return new UserHibernateDAO(getInstance().sessionFactory.openSession());
    }
}
