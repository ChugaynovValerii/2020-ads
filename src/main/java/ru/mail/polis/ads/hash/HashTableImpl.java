package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HashTableImpl<K, V> implements HashTable<K, V> {
    
    private static class Node<K, V> {
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
        
        K key;
        V value;
        Node<K, V> next;
    }
    
    private static final int INIT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private int size;
    
    @SuppressWarnings(value = "unchecked")
    private Node<K, V>[] table = (Node<K, V>[]) new Node[INIT_CAPACITY];
    
    @Override
    public @Nullable V get(@NotNull K k) {
        Node<K, V> node = table[getHashIndex(k)];
        if (node == null) {
            return null;
        }
        while (!node.key.equals(k)) {
            if (node.next == null) {
                return null;
            }
            node = node.next;
        }
        return node.value;
    }
    
    @Override
    public void put(@NotNull K k, @NotNull V v) {
        size++;
        int hashIndex = getHashIndex(k);
        Node<K, V> node = table[hashIndex];
        if (node == null) {
            table[hashIndex] = new Node<>(k, v);
            return;
        }
        while (!node.key.equals(k)) {
            if (node.next == null) {
                node.next = new Node<>(k, v);
                if ((double) size / table.length > LOAD_FACTOR) {
                    rebuild();
                }
                return;
            }
            node = node.next;
        }
        node.value = v;
        size--;
    }
    
    @SuppressWarnings(value = "unchecked")
    private void rebuild() {
        size = 0;
        Node<K, V>[] oldTable = table;
        table = (Node<K, V>[]) new Node[table.length * 2];
        for (Node<K, V> node : oldTable) {
            while (node != null) {
                put(node.key, node.value);
                node = node.next;
            }
        }
    }
    
    @Override
    public @Nullable V remove(@NotNull K k) {
        int hashIndex = getHashIndex(k);
        Node<K, V> node = table[hashIndex];
        if (node == null) {
            return null;
        }
        if (node.key.equals(k)) {
            table[hashIndex] = node.next;
            size--;
            return node.value;
        }
        
        while (node.next != null && !node.next.key.equals(k)) {
            node = node.next;
        }
        if (node.next == null) {
            return null;
        }
        V value = node.next.value;
        node.next = node.next.next;
        return value;
    }
    
    @Override
    public int size() {
        return size;
    }
    
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    private int getHashIndex(K k) {
        return (k.hashCode() & 0x7fffffff) % table.length;
    }
}
