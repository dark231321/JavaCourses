package danil.teterin.atomic.lessons.lessons2.mm.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;


public class GameSession {

    private static final Logger log = LoggerFactory.getLogger(GameSession.class);

    private static AtomicLong idGenerator = new AtomicLong();

    private final User[] users;

    private final long id = idGenerator.getAndIncrement();

    public static final byte PLAYERS_IN_GAME = 4;

    public GameSession(User[] users){
        this.users = users;
    }

    public User[] getUsers() { return this.users; }

    public long getId() { return this.id; }

    @Override
    public String toString() {
        return "GameSession{" +
                "users=" + Arrays.toString(users) +
                ", id=" + id +
                '}';
    }
}
