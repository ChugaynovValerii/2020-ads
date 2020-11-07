package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {
    private Node root;
    private int size;
    
    private class Node {
        public Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
        }
        
        Key key;
        Value value;
        Node left;
        Node right;
        int height;
    }
    
    @Override
    public Value get(@NotNull Key key) {
        Node x = get(root, key);
        return x == null ? null : x.value;
    }
    
    private Node get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            return get(x.left, key);
        }
        if (key.compareTo(x.key) > 0) {
            return get(x.right, key);
        }
        return x;
    }
    
    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
    }
    
    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            size++;
            return new Node(key, value, 1);
        }
        if (key.compareTo(x.key) < 0) {
            x.left = put(x.left, key, value);
        } else if (key.compareTo(x.key) > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        fixHeight(x);
        x = balance(x);
        return x;
    }
    
    @Override
    public Value remove(@NotNull Key key) {
        Node removed = get(root, key);
        if (removed == null) {
            return null;
        }
        size--;
        root = remove(root, key);
        return removed.value;
    }
    
    private Node remove(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            x.left = remove(x.left, key);
        } else if (key.compareTo(x.key) > 0) {
            x.right = remove(x.right, key);
        } else {
            x = innerRemove(x);
        }
        return x;
    }
    
    private Node innerRemove(Node x) {
        if (x.right == null) {
            return x.left;
        }
        if (x.left == null) {
            return x.right;
        }
        Node node = x;
        x = min(node.right);
        x.right = removeMin(node.right);
        x.left = node.left;
        return x;
    }
    
    private Node removeMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = removeMin(x.left);
        return x;
    }
    
    private Node rotateRight(Node node) {
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        fixHeight(node);
        fixHeight(x);
        return x;
    }
    
    private Node rotateLeft(Node x) {
        Node node = x.right;
        x.right = node.left;
        node.left = x;
        fixHeight(x);
        fixHeight(node);
        return node;
    }
    
    private int factor(Node x) {
        return height(x.left) - height(x.right);
    }
    
    private Node balance(Node x) {
        if (factor(x) == 2) {
            if (factor(x.left) < 0) {
                x.left = rotateLeft(x.left);
            }
            return rotateRight(x);
        }
        if (factor(x) == -2) {
            if (factor(x.right) > 0) {
                x.right = rotateRight(x.right);
            }
            return rotateLeft(x);
        }
        return x;
    }
    
    @Override
    public Key min() {
        Node min = min(root);
        return min == null ? null : min.key;
    }
    
    @Override
    public Value minValue() {
        Node min = min(root);
        return min == null ? null : min.value;
    }
    
    private Node min(Node x) {
        if (x == null) {
            return null;
        }
        return x.left == null ? x : min(x.left);
    }
    
    @Override
    public Key max() {
        Node max = max(root);
        return max == null ? null : max.key;
    }
    
    @Override
    public Value maxValue() {
        Node max = max(root);
        return max == null ? null : max.value;
    }
    
    private Node max(Node x) {
        if (x == null) {
            return null;
        }
        return x.right == null ? x : max(x.right);
    }
    
    @Override
    public Key floor(@NotNull Key key) {
        return floor(root, key, null);
    }
    
    private Key floor(Node x, Key key, Key max) {
        if (x == null) {
            return max;
        }
        if (key.compareTo(x.key) < 0) {
            max = floor(x.left, key, max);
        } else if (key.compareTo(x.key) > 0) {
            if (max == null || max.compareTo(x.key) < 0) {
                max = x.key;
            }
            max = floor(x.right, key, max);
        } else {
            max = x.key;
        }
        return max;
    }
    
    @Override
    public Key ceil(@NotNull Key key) {
        return ceil(root, key, null);
    }
    
    private Key ceil(Node x, Key key, Key min) {
        if (x == null) {
            return min;
        }
        if (key.compareTo(x.key) > 0) {
            min = ceil(x.right, key, min);
        } else if (key.compareTo(x.key) < 0) {
            if (min == null || min.compareTo(x.key) > 0) {
                min = x.key;
            }
            min = ceil(x.left, key, min);
        } else {
            min = x.key;
        }
        return min;
    }
    
    @Override
    public int size() {
        return size;
    }
    
    @Override
    public int height() {
        return height(root);
    }
    
    private int height(Node x) {
        return x == null ? 0 : x.height;
    }
    
    private void fixHeight(Node x) {
        x.height = 1 + Math.max(height(x.left), height(x.right));
    }
}
