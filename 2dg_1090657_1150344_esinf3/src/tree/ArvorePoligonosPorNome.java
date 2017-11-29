/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

import model.PoligonoString;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class ArvorePoligonosPorNome extends AVL<PoligonoString> {

    public int procurarNumLadosPoligonosPorNome(String nome_poligono) {
        return procurarNumLadosPoligonosPorNome(nome_poligono, root);
    }

    private int procurarNumLadosPoligonosPorNome(String nome_poligono, Node<PoligonoString> node) {
        if (node == null) {
            return -1;
        }
        int cmp = nome_poligono.compareTo(node.getElement().getPrefixo());
        if (cmp == 0) {
            return node.getElement().getNumLados();
        }
        if (cmp < 0) {
            return procurarNumLadosPoligonosPorNome(nome_poligono, node.getLeft());
        } else {
            return procurarNumLadosPoligonosPorNome(nome_poligono, node.getRight());

        }
    }

    public PoligonoString procurarPoligonoStringPorNome(String p) {
        return procurarPoligonoStringPorNome(p, root);
    }

    private PoligonoString procurarPoligonoStringPorNome(String p, Node<PoligonoString> node) {
        if (node == null) {
            return null;
        }
        int cmp = p.compareTo(node.getElement().getPrefixo());
        if (cmp == 0) {
            return node.getElement();
        }
        if (cmp < 0) {
            return procurarPoligonoStringPorNome(p, node.getLeft());
        } else {
            return procurarPoligonoStringPorNome(p, node.getRight());

        }
    }
}
