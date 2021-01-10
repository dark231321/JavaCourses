package danil.teterin.atomic.lessons.lessons3.JDBC.dao;

import danil.teterin.atomic.lessons.lessons3.JDBC.model.Message;
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
public class MessageDao implements Dao<Message> {

    private static Logger log = LoggerFactory.getLogger(MessageDao.class);

    private static final String SELECT_ALL_MESSAGES
            = "select m.time, m.value, u.login _LoginFrom, u1.login _LoginTo, u.id _IdFrom, u1.id _idTo " +
                    "from chat.message as m " +
                    "join chat.user as u on m.\"userFrom\" = u.id " +
                    "join chat.user as u1 on m.\"userTo\" = u1.id " +
                    "order by m.time";

    private static final String INSERT_MESSAGE_TEMPLATE
            = "INSERT INTO chat.message (\"userTo\", \"userFrom\", time, value) " + "values (%d, %d, now(),'%s');";

    private static final String GET_ALL_WHERE
            = "select m.*, u.login _LoginFrom, u.id _IdFrom, u1.login _LoginTo, u1.id _idTo  from chat.message as m " +
                "join chat.user as u on m.\"userTo\" = u.id " +
                "join chat.user as u1 on m.\"userFrom\" = u1.id " +
                "WHERE ";

    @Override
    public List<Message> getAll() {
        List<Message> messages = new ArrayList<>();
        try (Connection con = DBConnector.connection();
             Statement stm = con.createStatement()) {
            ResultSet rs = stm.executeQuery(SELECT_ALL_MESSAGES);
            while (rs.next()){
                messages.add(mapToMessage(rs));
            }
        } catch (SQLException throwables) {
            log.error("Connection exception ", throwables);
        }
        return messages;
    }

    @Override
    public List<Message> getAllWhere(String... conditions) {
        List<Message> messages = new ArrayList<>();
        try(Connection connection = DBConnector.connection();
            Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery(GET_ALL_WHERE + String.join(" and ", conditions));
            while (rs.next())
                messages.add(mapToMessage(rs));
        } catch (SQLException exc) {
            log.error("Select where {} exception", Arrays.toString(conditions), exc);
        }
        return messages;
    }

    @Override
    public void insert(Message message) {
        try(Connection connection = DBConnector.connection();
            Statement stm = connection.createStatement()) {
            stm.execute(String.format(INSERT_MESSAGE_TEMPLATE, message.getUserTo().getKey(), message.getUserFrom().getKey(), message.getValue()));
        } catch (SQLException exp){
            log.error("Insert new message failed message {}", message, exp);
        }
    }

    private static Message mapToMessage(ResultSet rs) throws SQLException {
        return new Message()
                .setValue(rs.getString("value"))
                .setTimestamp(rs.getDate("time"))
                .setUserFrom((User) new User()
                        .setLogin(rs.getString("_LoginFrom"))
                        .setKey(rs.getLong("_IdFrom")))
                .setUserTo((User) new User()
                        .setLogin(rs.getString("_LoginTo"))
                        .setKey(rs.getLong("_IdTo")));
    }

}
