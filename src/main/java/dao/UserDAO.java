package dao;

import model.User;
import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();
    void addUser(User user);
    void deleteUser(int id);
    void updateUser(User user);


}