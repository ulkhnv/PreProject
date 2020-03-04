package dao;

import org.hibernate.SessionFactory;
import util.DBHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDAOFactory {
    private DBHelper helper = DBHelper.getInstance();
    private SessionFactory sessionFactory = helper.getSessionFactory();

    public UserDAO createUserDAO() {
        Properties properties = new Properties();
        try (InputStream is = this.getClass().getResourceAsStream("/config.properties")) {
            properties.load(is);
            switch (properties.getProperty("typeOfDao")) {
                case "jdbc":
                    return new UserJdbcDAO(helper.getConnection());
                case "hibernate":
                    return new UserHibernateDAO(sessionFactory.openSession());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Unknown DAO");
    }
}
