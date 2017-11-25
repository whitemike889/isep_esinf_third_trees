/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

import model.Poligono;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class ArvorePoligonos extends AVL<Poligono> {

    public String procurarNomePoligonoPorNumero(int num) {
        return procurarNomePoligonoPorNumero(num, root);
    }

    private String procurarNomePoligonoPorNumero(int num, Node<Poligono> node) {
        if (node == null) {
            return "";
        }
        if (num == node.getElement().getNumLados()) {
            return node.getElement().getPrefixo();
        }
        if (num < node.getElement().getNumLados()) {
            return procurarNomePoligonoPorNumero(num, node.getLeft());
        } else {
            if (num > node.getElement().getNumLados()) {
                return procurarNomePoligonoPorNumero(num, node.getRight());
            }
        }
        return null;
    }

    public boolean procurarSePoligonoExiste(Poligono p) {
        return procurarSePoligonoExiste(p, root);
    }

    private boolean procurarSePoligonoExiste(Poligono p, Node<Poligono> node) {
        if (node == null) {
            return false;
        }
        int cmp = p.compareTo(node.getElement());
        if (cmp == 0) {
            return true;
        }
        if (cmp < 0) {
            return procurarSePoligonoExiste(p, node.getLeft());
        } else {
            return procurarSePoligonoExiste(p, node.getRight());

        }
    }
}
