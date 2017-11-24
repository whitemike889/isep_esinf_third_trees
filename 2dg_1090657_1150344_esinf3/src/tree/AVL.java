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

        throw new UnsupportedOperationException("Not supported yet.");
    }

    private Node<E> rightRotation(Node<E> node) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    private Node<E> leftRotation(Node<E> node) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    private Node<E> twoRotations(Node<E> node) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    private Node<E> balanceNode(Node<E> node) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void insert(E element) {
        root = insert(element, root);
    }

    private Node<E> insert(E element, Node<E> node) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remove(E element) {
        root = remove(element, root());
    }

    private Node<E> remove(E element, BST.Node<E> node) {
        if (node == null) {
            return null;
        }
        if (node.getElement().equals(element)) {
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }
            if (node.getLeft() == null) {
                return node.getRight();
            }
            if (node.getRight() == null) {
                return node.getLeft();
            }
            E smallElement = smallestElement(node.getRight());
            node.setElement(smallElement);
            node.setRight(remove(smallElement, node.getRight()));
            node = balanceNode(node);
        } else {
            if (node.getElement().compareTo(element) > 0) {
                node.setLeft(remove(element, node.getLeft()));
                node = balanceNode(node);
            } else {
                node.setRight(remove(element, node.getRight()));
                node = balanceNode(node);
            }

        }
        return node;
    }

    public boolean equals(AVL<E> second) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean equals(Node<E> root1, Node<E> root2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
