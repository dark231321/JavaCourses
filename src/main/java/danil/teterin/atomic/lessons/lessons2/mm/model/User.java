package danil.teterin.atomic.lessons.lessons2.mm.model;

public class User {
    private final long playerId;

    private final String playerNickName;

    public User(long playerId,
                String playerNickName){
        this.playerId = playerId;
        this.playerNickName = playerNickName;
    }


    public long getPlayerId() { return this.playerId; }

    public String getPlayerNickName() { return this.playerNickName; }

    @Override
    public String toString() {
        return "User{" +
                "playerId=" + playerId +
                ", playerNickName='" + playerNickName + '\'' +
                '}';
    }
}
