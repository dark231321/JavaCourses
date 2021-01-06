package danil.teterin.atomic.lessons.lessons3.JDBC.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Created by T.Danil on 07/03/2021
 * */
public class DBConnector {
    private static Logger log = LoggerFactory.getLogger(DBConnector.class);

    private static final String URL_TEMPLATE = "jdbc:postgresql://%s:%d/%s";
    private static final String URL;
    private static final String HOST = "localhost";
    private static final int PORT = 5432;
    private static final String DB_NAME = "postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            log.error("Failed to load jdbc:postgresql driver", e);
            System.exit(-1);
        }
        URL = String.format(URL_TEMPLATE, HOST, PORT, DB_NAME);
        log.info("Success. DbConnector init.");
    }

    public static Connection connection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private DBConnector(){}
}
