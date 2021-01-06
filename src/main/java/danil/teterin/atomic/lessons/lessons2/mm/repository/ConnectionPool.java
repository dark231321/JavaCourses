package danil.teterin.atomic.lessons.lessons2.mm.repository;

import danil.teterin.atomic.lessons.lessons2.mm.model.User;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Repository
public class ConnectionPool {
    private BlockingQueue<User> connectionPool = new LinkedBlockingQueue<>();

    public User getConnection() { return this.connectionPool.poll(); }

    public void putConnection(User connection) { this.connectionPool.add(connection); }

    public Collection<?> getAll() { return Arrays.asList(this.connectionPool.toArray());}

    public int size() { return connectionPool.size(); }
}
