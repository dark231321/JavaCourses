package danil.teterin.atomic.lessons.lessons3.JDBC.model;


import java.util.Date;

public class Message extends Entity<Long> {
    private User userTo;

    private User userFrom;

    private Date timestamp;

    private String value;

    public Message(Long key, User userTo, User userFrom, Date timestamp, String value) {
        super(key);
        this.timestamp = timestamp;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.value = value;
    }

    public Message() {
        super(0L);
    }

    public String getValue() { return this.value; }

    public User getUserFrom() { return this.userFrom; }

    public User getUserTo() { return this.userTo; }

    public Date getTimestamp() { return this.timestamp; }

    public Message setTimestamp(Date timestamp) { this.timestamp = timestamp; return this; }

    public Message setUserFrom(User userFrom) { this.userFrom = userFrom; return this; }

    public Message setUserTo(User userTo) { this.userTo = userTo; return this; }

    public Message setValue(String value) { this.value = value; return this; }

    @Override
    public String toString() {
        return "Message{" +
                "userTo=" + userTo +
                ", userFrom=" + userFrom +
                ", timestamp=" + timestamp +
                ", value='" + value + '\'' +
                '}';
    }
}
