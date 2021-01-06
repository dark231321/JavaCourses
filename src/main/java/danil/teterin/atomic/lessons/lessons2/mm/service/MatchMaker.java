package danil.teterin.atomic.lessons.lessons2.mm.service;

import danil.teterin.atomic.lessons.lessons2.mm.model.GameSession;
import danil.teterin.atomic.lessons.lessons2.mm.model.User;
import danil.teterin.atomic.lessons.lessons2.mm.repository.ConnectionPool;
import danil.teterin.atomic.lessons.lessons2.mm.repository.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MatchMaker implements Runnable {

    private final Logger log = LoggerFactory.getLogger(MatchMaker.class);

    @Autowired
    private ConnectionPool connectionController;

    @Autowired
    private GameRepository gameRepository;

    @PostConstruct
    public void startThread() {
        log.info("Start - Thread method");
        new Thread(this,"Match-Maker").start();
    }

    @Override
    public void run() {
        List<User> candidates = new ArrayList<>(GameSession.PLAYERS_IN_GAME); User user;
        log.info("Start MatchMaker run");
        while (!Thread.currentThread().isInterrupted()){
            if((user = connectionController.getConnection()) != null) {
                log.info("Add at queue new Player {}", user.getPlayerNickName());
                candidates.add(user);
                if (candidates.size() == GameSession.PLAYERS_IN_GAME){
                    GameSession gameSession = new GameSession(candidates.toArray(new User[0]));
                    log.info("New session: " + gameSession.toString());
                    gameRepository.put(gameSession);
                    candidates.clear();
                }
            }
        }
    }
}
