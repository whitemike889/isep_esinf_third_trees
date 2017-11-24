/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

/**
 *
 * @author DEI-ESINF
 * @param <E>
 */
public class AVL<E extends Comparable<E>> extends BST<E> {

    private int balanceFactor(Node<E> node) {

        return height(node.getRight()) - height(node.getLeft());
    }

    private Node<E> rightRotation(Node<E> node) {
        Node<E> leftson = node.getLeft();
        node.setLeft(leftson.getRight());
        leftson.setRight(node);
        node = leftson;
        return node;
    }

    private Node<E> leftRotation(Node<E> node) {
        Node<E> rightson = node.getRight();
        node.setLeft(rightson.getLeft());
        rightson.setLeft(node);
        node = rightson;
        return node;
    }

    private Node<E> twoRotations(Node<E> node) {

        if (balanceFactor(node) < 0) {
            node.setLeft(leftRotation(node.getLeft()));
            node = rightRotation(node);
        } else {
            node.setRight(rightRotation(node.getRight()));
            node = leftRotation(node);
        }
        return node;
    }

    private Node<E> balanceNode(Node<E> node) {
        if (balanceFactor(node) < -1) {
            if (balanceFactor(node.getLeft()) <= 0) {
                node = rightRotation(node);
            } else {
                node = twoRotations(node);
            }
        } else if (balanceFactor(node) > 1) {
            if (balanceFactor(node.getRight()) >= 0) {
                node = leftRotation(node);
            } else {
                node = twoRotations(node);
            }
        }
        return node;
    }

    @Override
    public void insert(E element) {
        root = insert(element, root);
    }

    private Node<E> insert(E element, Node<E> node) {
        if (node == null) {
            return new Node<>(element, null, null);
        }
        int compareResult = element.compareTo(node.getElement());
        if (compareResult == 0) {
            node.setElement(element);
        } else {
            if (compareResult < 0) {
                node.setLeft(insert(element, node.getLeft()));
            } else if (compareResult > 0) {
                node.setRight(insert(element, node.getRight()));
            }
        }
        return balanceNode(node);
    }

    @Override
    public void remove(E element) {
        root = remove(element, root());
    }

    private Node<E> remove(E element, BST.Node<E> node) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean equals(AVL<E> second) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean equals(Node<E> root1, Node<E> root2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
