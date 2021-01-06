package danil.teterin.atomic.lessons.lessons2.mm.repository;

import danil.teterin.atomic.lessons.lessons2.mm.model.GameSession;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class GameRepository {
    private ConcurrentHashMap<Long, GameSession> gameRepository = new ConcurrentHashMap<>();

    public void put(GameSession gameSession){
        gameRepository.put(gameSession.getId(), gameSession);
    }

    public Collection<GameSession> getAll() {
        System.out.println(gameRepository.values());
        return gameRepository.values();
    }

    public GameSession getById(long id) {
        return gameRepository.get(id);
    }

}
