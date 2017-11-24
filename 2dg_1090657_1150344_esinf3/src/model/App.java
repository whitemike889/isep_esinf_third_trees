/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import tree.AVL;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class App {

    private AVL<Poligono> arvore_unidades;
    private AVL<Poligono> arvore_dezenas;
    private AVL<Poligono> arvore_centenas;

    public App() {
        arvore_unidades = new AVL<>();
        arvore_dezenas = new AVL<>();
        arvore_centenas = new AVL<>();
    }

    public boolean inserirPoligono(Poligono p) {
        if (p.getNumLados() < 10) {
            arvore_unidades.insert(p);
            return true;
        } else {
            if (p.getNumLados() < 100) {
                arvore_dezenas.insert(p);
                return true;
            } else {
                if (p.getNumLados() < 1000) {
                    arvore_centenas.insert(p);
                    return true;
                }
            }
        }
        return false;
    }

}
