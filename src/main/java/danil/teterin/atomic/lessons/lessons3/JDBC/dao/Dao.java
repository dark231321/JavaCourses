package danil.teterin.atomic.lessons.lessons3.JDBC.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by T.Danil on 07/03/2021
 * */
public interface Dao<T> {

    /**
     * SELECT * FROM <Table>
     * */
    List<T> getAll();

    /**
     * SELECT * FROM <Table> WHERE <condition0> and ... <conditionN>
     * */
    List<T> getAllWhere(String... conditions);

    /**
     * SELECT * from ... WHERE id=
     * @return Optional.empty() if nothing found
     * */
    default Optional<T> findById(int id){
        return Optional.ofNullable(
                getAllWhere("id=" + id).get(0)
        );
    }

    /**
     * INSERT INTO ...
     */
    void insert(T t) throws SQLException;
}
