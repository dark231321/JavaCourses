package danil.teterin.atomic.lessons.lessons2.mm.service;

import danil.teterin.atomic.lessons.lessons2.mm.model.User;
import danil.teterin.atomic.lessons.lessons2.mm.repository.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ConnectionProducer {
    private static final Logger log = LoggerFactory.getLogger(ConnectionProducer.class);

    @Autowired
    private ConnectionPool connectionPool;

    public void putPlayer(User user){ this.connectionPool.putConnection(user); }

    public Collection<?> getAll() {
        return this.connectionPool.getAll();
    }
}
