package danil.teterin.atomic.lessons.lessons3.JDBC.model;

public class User extends Entity<Long> {

    private String login;

    public User(Long key, String login) {
        super(key);
        this.login = login;
    }

    public User() {
        super(0L);
    }

    public String getLogin() { return this.login; }

    public User setLogin(String login) { this.login = login; return this; }

}
