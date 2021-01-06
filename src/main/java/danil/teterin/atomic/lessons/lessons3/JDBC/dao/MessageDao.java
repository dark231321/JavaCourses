package danil.teterin.atomic.lessons.lessons3.JDBC.dao;

import danil.teterin.atomic.lessons.lessons3.JDBC.model.Message;
import danil.teterin.atomic.lessons.lessons3.JDBC.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


/**
 * Created by T.Danil on 07/03/2021
 * */
public class MessageDao implements Dao<Message> {

    private static Logger log = LoggerFactory.getLogger(MessageDao.class);

    //private static final String SELECT_ALL_MESSAGES =
    //        "select m.time, m.value, u.* " +
    //                "from chat.message as m " +
    //                "join chat.user as u " +
    //                "  on m.user = u.id " +
    //                "order by m.time";

    @Override
    public List<Message> getAll() {
        List<Message> messages = null;
        try (Connection con = DBConnector.connection();
             Statement stm = con.createStatement()
        ){
            ResultSet set = stm.executeQuery("select ");

        } catch (SQLException throwables) {
            log.error("Connection exception ", throwables);
        }
        return messages;
    }

    @Override
    public List<Message> getAllWhere(String... conditions) {
        return null;
    }

    @Override
    public void insert(Message message) {

    }

    //private static Message mapToMessage(ResultSet rs) throws SQLException {
    //    return new Message()
    //            .setValue(rs.getString("value"))
    //            .setTimestamp(rs.getDate("time"))
    //            .setUserFrom()
    //}
}
