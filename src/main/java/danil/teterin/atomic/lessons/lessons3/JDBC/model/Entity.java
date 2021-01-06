package danil.teterin.atomic.lessons.lessons3.JDBC.model;

public class Entity<K> {
    private K key;

    public Entity(K key) { this.key = key; }

    public K getKey() { return this.key; }

    public Entity<K> setKey(K key) { this.key = key; return this; }

}
