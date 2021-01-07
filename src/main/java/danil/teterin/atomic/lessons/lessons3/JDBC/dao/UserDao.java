package danil.teterin.atomic.lessons.lessons3.JDBC.dao;


import danil.teterin.atomic.lessons.lessons3.JDBC.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by T.Danil on 07/03/2021
 * */

public class UserDao implements Dao<User> {

    private static Logger log = LoggerFactory.getLogger(UserDao.class);

    private static final String GET_ALL_USER
            = "select * FROM chat.user";

    private static final String INSERT_USER_TEMPLATE =
            "insert into chat.user (login) " +
                    "values ('%s');";

    private static final String GET_ALL_WHERE
            = "SELECT * FROM chat.user WHERE ";

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        try(Connection connection = DBConnector.connection();
            Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery(GET_ALL_USER);
            while (rs.next()){
                userList.add(mapToUser(rs));
            }
        } catch (SQLException throwables) {
            log.error("Connection exception ", throwables);
        }
        return userList;
    }

    @Override
    public List<User> getAllWhere(String... conditions) {
        List<User> users = new ArrayList<>();
        try(Connection connection = DBConnector.connection();
            Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery(GET_ALL_WHERE + String.join(" and ", conditions));
            while (rs.next()){
                users.add(mapToUser(rs));
            }
        } catch (SQLException exp) {
            log.error("Failed to get user with this conditions{}", Arrays.toString(conditions), exp);
        }
        return users;
    }

    @Override
    public void insert(User user) {
        try(Connection connection = DBConnector.connection();
            Statement stm = connection.createStatement()
        ) {
            stm.execute(String.format(INSERT_USER_TEMPLATE, user.getLogin()));
        } catch (SQLException e) {
            log.error("Failed to create user {}", user.getLogin(), e);
        }
    }

    private static User mapToUser(ResultSet rs) throws SQLException {
        return (User) new User()
                .setLogin(rs.getString("login"))
                .setKey(rs.getLong("id"));
    }
}
