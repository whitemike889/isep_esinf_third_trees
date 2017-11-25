/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import tree.ArvorePoligonos;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class App {

    /**
     * Árvore das unidades
     */
    private ArvorePoligonos arvore_unidades;
    /**
     * Árvore das dezenas
     */
    private ArvorePoligonos arvore_dezenas;
    /**
     * Árvore das centenas
     */
    private ArvorePoligonos arvore_centenas;

    private static final String pol_pref_uni = "poligonos_prefixo_unidades.txt";
    private static final String pol_pref_dez = "poligonos_prefixo_dezenas.txt";
    private static final String pol_pref_cent = "poligonos_prefixo_centenas.txt";

    public App() {
        arvore_unidades = new ArvorePoligonos();
        arvore_dezenas = new ArvorePoligonos();
        arvore_centenas = new ArvorePoligonos();
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
    /**
     * Método que constrói um nome de um polígono dado o número de lados
     *
     * @param numlados Num de lados de um poligono
     * @return Nome de um poligono
     */
    public String construirNomeDoPoligono(int numlados) {
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

        if (dezenas >= 10 && dezenas <= 29) {
            return expCentenas + expDezenas + "gon";
        }

        return expCentenas + expDezenas + expUnidades;
    }

    /**
     * Método que constrói o nome das unidades de um poligono dado o campo das
     * unidades
     *
     * @param unidades Num de lados das unidades de um poligono
     * @return Nome do campo das unidades de um poligono
     */
    private String construirNomePolUnidades(int unidades) {
        String temp = "";
        temp = arvore_unidades.procurarNomePoligonoPorNumero(unidades);
        return temp;

    }

    /**
     * Método que constrói o nome das dezenas de um poligono dado o campo das
     * dezenas
     *
     * @param dezenas Num de lados nas dezenas de um poligono
     * @return Nome do campo das dezenas de um poligono
     */
    private String construirNomePolDezenas(int dezenas) {
        String temp = "";
        if (dezenas >= 10 && dezenas <= 29) {
            temp = arvore_dezenas.procurarNomePoligonoPorNumero(dezenas);
        } else {
            int unidades = dezenas % 10;
            dezenas = dezenas - unidades;
            temp = arvore_dezenas.procurarNomePoligonoPorNumero(dezenas);
        }
        return temp;
    }

    /**
     * Método que constrói o nome das dezenas de um poligono dado o campo das
     * centenas
     *
     * @param centenas Num lados nas centenas de um poligono
     * @return Nome do campo das centenas de um poligono
     */
    private String construirNomePolCentenas(int centenas) {
        String temp = "";
        temp = arvore_centenas.procurarNomePoligonoPorNumero(centenas);
        return temp;
    }
    //=================================C========================================

    public ArvorePoligonos construirArvorePoligonos() {
        ArvorePoligonos arvore = new ArvorePoligonos();
        final int LIM_INF = 1, LIM_SUP = 999;
        for (int i = LIM_INF; i <= LIM_SUP; i++) {
            String temp = construirNomeDoPoligono(i);
            Poligono p = new Poligono(i, temp);
            arvore.insert(p);
        }
        return arvore;
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
