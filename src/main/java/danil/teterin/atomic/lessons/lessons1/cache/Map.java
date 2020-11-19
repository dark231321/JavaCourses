package danil.teterin.atomic.lessons.lessons1.cache;

public interface Map<K, V> {
    int getSize();

    boolean put(K key, V value);

    int getCapacity();

    V get(K key);

    boolean containsKey(K key);

    interface Entry<K, V> {
        K getKey();

        V getValue();
    }
}
