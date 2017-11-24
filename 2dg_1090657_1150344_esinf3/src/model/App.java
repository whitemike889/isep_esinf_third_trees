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

    /**
     * Método para inserir um determinado polígono na árvore adequada
     *
     * @param p Poligono a inserir
     * @return true or false
     */
    public boolean inserirPoligono(Poligono p) {
        if (p.getNumLados() < 0) {
            return false;
        }
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

    //=================================A========================================
    /**
     * Método para ler os ficheiros dos polígonos para as estruturas de dados
     */
    public void lerDados() {
        Ficheiro f = new Ficheiro();
        f.lerPoligonos(pol_pref_uni, this);
        f.lerPoligonos(pol_pref_dez, this);
        f.lerPoligonos(pol_pref_cent, this);

    }
    //=================================B========================================

    public String construirPoligono(int numlados) {
        if (numlados <= 0 || numlados >= 1000) {
            return "";
        }
        int centenas = (numlados / 100) * 100;
        int unidades = numlados % 10;
        int dezenas = numlados - centenas;

        String expUnidades = "";
        String expDezenas = "";
        String expCentenas = "";

        expCentenas = construirNomePolCentenas(centenas);
        expDezenas = construirNomePolDezenas(dezenas);
        expUnidades = construirNomePolUnidades(unidades);
        
        if(dezenas >= 10 && dezenas <= 29)
            return expCentenas+expDezenas+"gon";
        
        return expCentenas+expDezenas+expUnidades;
    }

    private String construirNomePolDezenas(int dezenas) {
        String temp = "";
        if (dezenas >= 10 && dezenas <= 29) {
            for (Poligono p : arvore_dezenas.inOrder()) {
                if (dezenas == p.getNumLados()) {
                    temp = p.getPrefixo();
                }
            }

        } else {
            int unidades = dezenas % 10;
            dezenas = dezenas - unidades;
            for (Poligono p : arvore_dezenas.inOrder()) {
                if (dezenas == p.getNumLados()) {
                    temp = p.getPrefixo();
                }
            }
        }
        return temp;
    }

    private String construirNomePolCentenas(int centenas) {
        String temp = "";
        for (Poligono p : arvore_centenas.inOrder()) {
            if (centenas == p.getNumLados()) {
                temp = p.getPrefixo();
            }
        }
        return temp;
    }
    
    private String construirNomePolUnidades(int unidades){
            String temp = "";
        for (Poligono p : arvore_unidades.inOrder()) {
            if (unidades == p.getNumLados()) {
                temp = p.getPrefixo()+"gon";
            }
        }
        return temp;
    
    }

    /**
     * Retorna quantos poligonos das unidades existem
     *
     * @return
     */
    public int qtdPoligonosUnidades() {
        return arvore_unidades.size();
    }

    /**
     * Retorna quantos poligonos das dezenas existem
     *
     * @return
     */
    public int qtdPoligonosDezenas() {
        return arvore_dezenas.size();
    }

    /**
     * Retorna quantos poligonos das centenas existem
     *
     * @return
     */
    public int qtdPoligonosCentenas() {
        return arvore_centenas.size();
    }

    /**
     * Retorna quantos poligonos totais existem
     *
     * @return
     */
    public int qtdPoligonosTotal() {
        return arvore_unidades.size() + arvore_dezenas.size() + arvore_centenas.size();

    }
}
