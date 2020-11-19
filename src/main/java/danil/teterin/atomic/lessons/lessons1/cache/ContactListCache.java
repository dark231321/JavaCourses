package danil.teterin.atomic.lessons.lessons1.cache;

import java.util.*;

public class ContactListCache<K,V>
        implements Map<K, V> {

    transient private Node<K, V>[] hashTable;

    transient EntrySet entrySet;

    private static final int DEFAULT_CAPACITY = 16;

    private int capacity;

    private int size = 0;

    private float threshold;

    @SuppressWarnings("unchecked")
    public ContactListCache() {
        this.capacity = DEFAULT_CAPACITY;
        this.hashTable = (Node<K, V>[]) new Node[this.capacity];
        this.threshold = (float) (this.hashTable.length) * 0.75f;
    }

    public EntrySet entrySet(){
        return new EntrySet();
    }

    @SuppressWarnings("unchecked")
    public ContactListCache(int capacity) {
        if(capacity < 0)
            throw new IllegalArgumentException();
        this.capacity = Math.max(capacity, DEFAULT_CAPACITY);
        this.hashTable = (Node<K, V>[]) new Node[this.capacity];
        this.threshold = (float) (this.capacity) * 0.75f;
    }

    @Override
    public boolean put(final K key, final V value){
        return putValue(hash(key), key, value);
    }

    private int hash(Object key){
        return key == null ? 0 : key.hashCode();
    }

    private boolean putValue(final int hash, final K key, final V value) {
        Node<K, V> cur; int position;
        Node<K, V> tmp = new Node<>(key, value);
        if(this.threshold < this.size + 1)
            resize();
        if(this.hashTable[position = hash % 16] == null){
            return simpleAdd(position, tmp);
        }
        if((cur = hashTable[position]).getList() == null){
            putAtList(cur);
        }
        List<Node<K, V>> nodeList = hashTable[position].getList();
        for(Node<K, V> node: nodeList){
            if(keyExistButValueNew(node, tmp, value)
                    ||node.equals(tmp)){
                return true;
            }
        }
        nodeList.add(tmp);
        return true;
    }

    private void putAtList(Node<K, V> cur) {
        cur.list = new LinkedList<>();
        cur.list.add(new Node<>(cur.key, cur.value));
        cur.key = null; cur.value = null;
    }


    @SuppressWarnings("unchecked")
    private Node<K, V>[] resize(){
        Node<K, V>[] oldTable = this.hashTable; List<Node<K, V>> list;
        hashTable = (Node<K, V>[]) new Node[this.capacity = oldTable.length * 2];
        size = 0;
        for(Node<K, V> node
                : oldTable){
            if (node != null){
                if((list = node.getList()) != null) {
                    for (Node<K, V> nodes
                            : list)
                        put(nodes.key, nodes.value);
                    continue;
                }
                put(node.key, node.value);
            }
        }
        this.threshold = (float) this.capacity * 0.75f;
        return hashTable;
    }

    private boolean keyExistButValueNew(final Node<K, V> node,
                                        final Node<K, V> tmp,
                                        final V value) {
        if(node.getKey().equals(tmp.getKey())
                && !node.getValue().equals(tmp.getValue())) {
            node.setValue(value);
            return true;
        }
        return false;
    }

    private boolean simpleAdd(int index, Node<K, V> value){
        this.hashTable[index] = new Node<>(value.key, value.value);
        size++;
        return true;
    }

    @Override
    public boolean containsKey(K key){
        return getValue(hash(key), key) != null;
    }

    @Override
    public V get(K key) {
        return getValue(hash(key), key);
    }

    private V getValue(int hash, K key){
        V list; int position; Node<K, V> tmp;
        if((position = hash % this.capacity) > this.size)
            return null;
        if((tmp = this.hashTable[position]) != null){
            List<Node<K, V>> nodes;
            if((nodes = tmp.getList()) != null){
                for (Node<K, V> node
                        :nodes)
                    if(node.key.equals(key))
                        return node.value;
            }
            return tmp.value;
        }
        else
            return null;
    }

    private int hash(Node<Person, List<? extends Person>> node){
        return node.hash % this.hashTable.length;
    }

    @Override
    public int getSize() { return this.size; }

    @Override
    public int getCapacity() { return this.capacity; }

    private static class Node<K, V> implements Map.Entry<K, V>{
        Node(K key, V value){
            this.key = key;
            this.value = value;
        }

        private List<Node<K,V>> list;
        private K key;
        private V value;
        private int hash;

        public List<Node<K, V>> getList() { return this.list; }

        public void setValue(V value) { this.value = value; }

        public int getHash() { return this.hash; }
        public K getKey() { return this.key; }
        public V getValue() { return this.value; }


        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o instanceof Node) {
                Node<K, V> node = (Node<K, V>) o;
                return (Objects.equals(key, node.key) &&
                        Objects.equals(value,node.value)||
                        Objects.equals(hash,node.hash));
            }
            return false;
        }

        @Override
        public int hashCode() {
            hash = 31;
            hash = hash * 17 + key.hashCode();
            return hash;
        }
    }

    public final class EntrySet implements Iterable<Node<K, V>> {
        private List<Node<K, V>> list;
        private int size;
        public int getSize() { return size; }

        public EntrySet(){
            list = new LinkedList<>();
            for(Node<K, V> it
                    :ContactListCache.this.hashTable){
                if(it != null)
                    if (it.key == null){
                        List<Node<K, V>> nodes = it.getList();
                        for (Node<K, V> node
                                : nodes) {
                            list.add(node);
                        }
                    }
                else list.add(it);
            }
        }

        @Override
        public Iterator<Node<K, V>> iterator() {
            return new Iterator<Node<K, V>>() {
                private Iterator<Node<K, V>> iterator = list.iterator();

                @Override
                public boolean hasNext() {
                    return iterator.hasNext();
                }

                @Override
                public Node<K, V> next() {
                    return iterator.next();
                }
            };
        }
    }
}
