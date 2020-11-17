package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {
    private Node root;
    private int size;
    private static final boolean BLACK = false;
    private static final boolean RED = true;
    
    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;
        
        Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }
    
    
    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Node x = get(root, key);
        return x == null ? null : x.value;
    }
    
    private Node get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int res = key.compareTo(x.key);
        if (res < 0) {
            return get(x.left, key);
        } else if (res > 0) {
            return get(x.right, key);
        }
        return x;
    }
    
    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }
    
    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            ++size;
            return new Node(key, value, RED);
        }
        int res = key.compareTo(x.key);
        if (res < 0) {
            x.left = put(x.left, key, value);
        } else if (res > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        return fixUp(x);
    }
    
    private Node rotateLeft(Node x) {
        Node right = x.right;
        x.right = right.left;
        right.left = x;
        right.color = x.color;
        x.color = RED;
        return right;
    }
    
    private Node rotateRight(Node x) {
        Node left = x.left;
        x.left = left.right;
        left.right = x;
        left.color = x.color;
        x.color = RED;
        return left;
    }
    
    private boolean isRed(Node x) {
        return x != null && x.color == RED;
    }
    
    private Node fixUp(Node x) {
        if (isRed(x.right) && !isRed(x.left)) {
            x = rotateLeft(x);
        }
        if (isRed(x.left) && isRed(x.left.left)) {
            x = rotateRight(x);
        }
        if (isRed(x.left) && isRed(x.right)) {
            flipColors(x);
        }
        return x;
    }
    
    private Node flipColors(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
        return x;
    }
    
    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        if (root == null) {
            return null;
        }
        Value xValue = get(key);
        if (xValue != null) {
            root = remove(root, key);
            size--;
        }
        return xValue;
    }
    
    public void removeMin() {
        root = removeMin(root);
        if (root != null) {
            root.color = BLACK;
        }
    }
    
    private Node removeMin(Node x) {
        if (x.left == null) {
            return null;
        }
        if (!isRed(x.left) && !isRed(x.left.left)) {
            x = moveRedLeft(x);
        }
        x.left = removeMin(x.left);
        return fixUp(x);
    }
    
    private Node moveRedLeft(Node x) {
        flipColors(x);
        if (isRed(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColors(x);
        }
        return x;
    }
    
    public void removeMax() {
        root = removeMax(root);
        if (root != null) {
            root.color = BLACK;
        }
    }
    
    private Node removeMax(Node x) {
        if (isRed(x.left)) {
            x = rotateRight(x);
        }
        if (x.right == null) {
            return null;
        }
        if (!isRed(x.right) && !isRed(x.right.left)) {
            x = moveRedRight(x);
        }
        x.right = removeMax(x.right);
        return fixUp(x);
    }
    
    private Node moveRedRight(Node x) {
        flipColors(x);
        if (isRed(x.left.left)) {
            x = rotateRight(x);
            flipColors(x);
        }
        return x;
    }
    
    private Node remove(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int res = key.compareTo(x.key);
        if (res < 0) {
            if (x.left != null) {
                if (!isRed(x.left) && !isRed((x.left.left))) {
                    x = moveRedLeft(x);
                }
                x.left = remove(x.left, key);
            }
        } else {
            if (isRed(x.left)) {
                x = rotateRight(x);
                x.right = remove(x.right, key);
            } else if (res == 0 && x.right == null) {
                return null;
            } else {
                if (x.right != null && !isRed(x.right) && !isRed(x.right.left)) {
                    x = moveRedRight(x);
                }
                if (x.key == key) {
                    Node min = min(x.right);
                    x.key = min.key;
                    x.value = min.value;
                    x.right = removeMin(x.right);
                } else {
                    x.right = remove(x.right, key);
                }
            }
        }
        return fixUp(x);
    }
    
    @Nullable
    @Override
    public Key min() {
        Node x = min(root);
        return x == null ? null : x.key;
    }
    
    private Node min(Node x) {
        if (x == null) {
            return null;
        }
        return x.left == null ? x : min(x.left);
    }
    
    @Nullable
    @Override
    public Value minValue() {
        Node x = min(root);
        return x == null ? null : x.value;
    }
    
    @Nullable
    @Override
    public Key max() {
        Node x = max(root);
        return x == null ? null : x.key;
    }
    
    private Node max(Node x) {
        if (x == null) {
            return null;
        }
        return x.right == null ? x : max(x.right);
    }
    
    @Nullable
    @Override
    public Value maxValue() {
        Node x = max(root);
        return x == null ? null : x.value;
    }
    
    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        Node x = floor(root, key);
        return x == null ? null : x.key;
    }
    
    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int res = key.compareTo(x.key);
        if (res == 0) {
            return x;
        }
        if (res < 0) {
            return floor(x.left, key);
        }
        Node rightFloor = floor(x.right, key);
        return rightFloor == null ? x : rightFloor;
    }
    
    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node x = ceil(root, key);
        return x == null ? null : x.key;
    }
    
    private Node ceil(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int res = key.compareTo(x.key);
        if (res == 0) {
            return x;
        }
        if (res > 0) {
            return ceil(x.right, key);
        }
        Node leftCeil = ceil(x.left, key);
        return leftCeil == null ? x : leftCeil;
    }
    
    @Override
    public int size() {
        return size;
    }
}

