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

    private static final String pol_pref_uni = "poligonos_prefixo_unidades.txt";
    private static final String pol_pref_dez = "poligonos_prefixo_dezenas.txt";
    private static final String pol_pref_cent = "poligonos_prefixo_centenas.txt";

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

    public void lerDados() {
        Ficheiro f = new Ficheiro();
        f.lerPoligonos(pol_pref_uni, this);
        f.lerPoligonos(pol_pref_dez, this);
        f.lerPoligonos(pol_pref_cent, this);

    }

    public int qtdPoligonosUnidades() {
        return arvore_unidades.size();
    }

    public int qtdPoligonosDezenas() {
        return arvore_dezenas.size();
    }

    public int qtdPoligonosCentenas() {
        return arvore_centenas.size();
    }

    public int qtdPoligonosTotal() {
        return arvore_unidades.size() + arvore_dezenas.size() + arvore_centenas.size();

    }
}
