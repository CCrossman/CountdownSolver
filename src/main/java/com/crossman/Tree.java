package com.crossman;

import java.util.Objects;
import java.util.function.Consumer;

public class Tree<T> {
    private Node<T> root;

    public Tree() {
        this.root = null;
    }

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }

    public void postOrderVisit(Consumer<T> onVisit) {
        _postOrderVisit(root,onVisit);
    }

    public void preOrderVisit(Consumer<T> onVisit) {
        _preOrderVisit(root,onVisit);
    }

    private void _postOrderVisit(Node<T> curr, Consumer<T> onVisit) {
        if (curr == null) {
            return;
        }
        _postOrderVisit(curr.getLeft(),onVisit);
        _postOrderVisit(curr.getRight(),onVisit);
        onVisit.accept(curr.getValue());
    }

    private void _preOrderVisit(Node<T> curr, Consumer<T> onVisit) {
        if (curr == null) {
            return;
        }
        onVisit.accept(curr.getValue());
        _preOrderVisit(curr.getLeft(),onVisit);
        _preOrderVisit(curr.getRight(),onVisit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tree<?> tree = (Tree<?>) o;
        return Objects.equals(root, tree.root);
    }

    @Override
    public int hashCode() {
        return Objects.hash(root);
    }

    @Override
    public String toString() {
        return "Tree{" +
                "root=" + root +
                '}';
    }

    public static class Node<T> {
        private final T value;
        private Node<T> left, right;

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public Node<T> getLeft() {
            return left;
        }

        public Node<T> getRight() {
            return right;
        }

        public void setLeft(Node<T> left) {
            this.left = left;
        }

        public void setRight(Node<T> right) {
            this.right = right;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(value, node.value) && Objects.equals(left, node.left) && Objects.equals(right, node.right);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, left, right);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
