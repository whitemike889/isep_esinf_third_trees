/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.LinkedList;
import tree.AVL;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class App {

    /**
     * Árvore das unidades
     */
    private AVL<Poligono> arvore_unidades;
    /**
     * Árvore das dezenas
     */
    private AVL<Poligono> arvore_dezenas;
    /**
     * Árvore das centenas
     */
    private AVL<Poligono> arvore_centenas;

    /**
     * Árvore construida ordenado por String
     */
    private AVL<PoligonoString> arvore_poligonos_por_nome;

    /**
     * Árvore construida ordenada Por Num de lados
     */
    private AVL<Poligono> arvore_poligonos_por_lado;

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
        final int CASO_ESPECIAL_DEZENAS_LIM_INF = 10;
        final int CASO_ESPECIAL_DEZENAS_LIM_SUP = 29;
        final String SUFIXO = "gon";
        int centenas = (numlados / 100) * 100;
        int unidades = numlados % 10;
        int dezenas = numlados - centenas;

        String expUnidades = "";
        String expDezenas = "";
        String expCentenas = "";

        if (centenas > 0) {
            expCentenas = construirNomePolCentenas(centenas);
        }
        if (dezenas > 0) {
            expDezenas = construirNomePolDezenas(dezenas);
        }
        if (unidades > 0) {
            expUnidades = construirNomePolUnidades(unidades);
        }
        if (dezenas >= CASO_ESPECIAL_DEZENAS_LIM_INF && dezenas <= CASO_ESPECIAL_DEZENAS_LIM_SUP) {
            return expCentenas + expDezenas + SUFIXO;
        }

        return expCentenas + expDezenas + expUnidades + SUFIXO;
    }

    /**
     * Método que constrói o nome das unidades de um poligono dado o campo das
     * unidades
     *
     * @param unidades Num de lados das unidades de um poligono
     * @return Nome do campo das unidades de um poligono
     */
    private String construirNomePolUnidades(int unidades) {

        Poligono p = new Poligono(unidades, "");
        Poligono el = arvore_unidades.search(p);
        if (el == null) {
            return "";
        }
        return el.getPrefixo();

    }

    /**
     * Método que constrói o nome das dezenas de um poligono dado o campo das
     * dezenas
     *
     * @param dezenas Num de lados nas dezenas de um poligono
     * @return Nome do campo das dezenas de um poligono
     */
    private String construirNomePolDezenas(int dezenas) {
        Poligono p = new Poligono(dezenas, "");
        Poligono temp = null;
        if (dezenas >= 10 && dezenas <= 29) {
            temp = arvore_dezenas.search(p);
        } else {
            int unidades = dezenas % 10;
            dezenas = dezenas - unidades;
            p = new Poligono(dezenas, "");
            temp = arvore_dezenas.search(p);
        }
        if (temp == null) {
            return "";
        }
        return temp.getPrefixo();
    }

    /**
     * Método que constrói o nome das dezenas de um poligono dado o campo das
     * centenas
     *
     * @param centenas Num lados nas centenas de um poligono
     * @return Nome do campo das centenas de um poligono
     */
    private String construirNomePolCentenas(int centenas) {
        Poligono p = new Poligono(centenas, "");
        Poligono el = arvore_centenas.search(p);
        if (el == null) {
            return "";
        }
        return el.getPrefixo();
    }
    //=================================C========================================

    /**
     * Constrói a árvore total de polígonos de 1 a 999
     *
     * @return Uma árvore com todos os polígonos de 1 a 999
     */
    public void construirArvorePoligonosTotal() {
        AVL<Poligono> arv_pol_lado = new AVL<>();
        AVL<PoligonoString> arv_pol_string = new AVL<>();
        final int LIM_INF = 1, LIM_SUP = 999;
        for (int i = LIM_INF; i <= LIM_SUP; i++) {
            String s = construirNomeDoPoligono(i);
            Poligono p_l = new Poligono(i, s);
            PoligonoString p_s = new PoligonoString(i, s);
            arv_pol_lado.insert(p_l);
            arv_pol_string.insert(p_s);
        }
        arvore_poligonos_por_nome = arv_pol_string;
        arvore_poligonos_por_lado = arv_pol_lado;
    }
    //=================================D========================================

    /**
     * Método que retorna o numero de lados de um poligono a partir do nome.
     *
     * @param nome Nome do poligono
     * @return numero de lados do poligono
     */
    public int numeroLados(String nome) {
        if (arvore_poligonos_por_lado == null) {
            construirArvorePoligonosTotal();
        }
        PoligonoString ps = new PoligonoString(0, nome);
        PoligonoString p_encontrado = arvore_poligonos_por_nome.search(ps);
        if (p_encontrado == null) {
            return -1;
        }
        return p_encontrado.getNumLados();
    }

    //=================================E========================================
    /**
     * Método que retorna o nome dos poligonos no intervalo definido em ordem
     * decrescente.
     *
     * @param x1 Primeiro numero do intervalo
     * @param x2 Segundo numero do intervalo
     * @return Lista dos nomes dos poligonos no intervalo pretendido em ordem
     * decrescente
     */
    public Iterable<String> poligonosIntervaloOrdemDecrescente(int x1, int x2) {
        LinkedList<String> listaPoligonos = new LinkedList<>();
        int lim_inf = x1;
        int lim_sup = x2;
        if (x1 > x2) {
            lim_inf = x2;
            lim_sup = x1;
        }
        for (int i = lim_inf; i <= lim_sup; i++) {
            Poligono p = new Poligono(i, "");
            Poligono p_encontrado = arvore_poligonos_por_lado.search(p);
            if (p_encontrado != null) {
                listaPoligonos.push(p_encontrado.getPrefixo());
            }
        }
        return listaPoligonos;
    }

//=================================F========================================
    /**
     * Retorna o antecessor comum mais próximo de dois poligonos
     *
     * @param poligono1 Nome de um poligono
     * @param poligono2 Nome de outro poligono
     * @return Antecessor comum mais próximo
     */
    public Poligono lowestCommonAncestor(String poligono1, String poligono2) {
        if (arvore_poligonos_por_lado == null) {
            construirArvorePoligonosTotal();
        }
        PoligonoString p_s1 = arvore_poligonos_por_nome.search(new PoligonoString(0, poligono1));
        PoligonoString p_s2 = arvore_poligonos_por_nome.search(new PoligonoString(0, poligono2));
        if (p_s1 == null || p_s2 == null) {
            return null;
        }
        Poligono p_1 = new Poligono(p_s1);
        Poligono p_2 = new Poligono(p_s2);
        Poligono antecessor = arvore_poligonos_por_lado.lowestCommonAncestor(p_1, p_2);
        if (antecessor == null) {
            return null;
        }
        return antecessor;
    }

    /**
     * Método para testar lowestCommonAncestor
     *
     * @param arvore_test Árvore a testar
     * @param poligono1 Poligono 1
     * @param poligono2 Poligono 2
     * @return
     */
    public Poligono lowestCommonAncestorTest(AVL<Poligono> arvore_test, String poligono1, String poligono2) {

        if (arvore_poligonos_por_lado == null) {
            construirArvorePoligonosTotal();
        };
        PoligonoString p_s1 = arvore_poligonos_por_nome.search(new PoligonoString(0, poligono1));
        PoligonoString p_s2 = arvore_poligonos_por_nome.search(new PoligonoString(0, poligono2));
        if (p_s1 == null || p_s2 == null) {
            return null;
        }
        Poligono p_1 = new Poligono(p_s1);
        Poligono p_2 = new Poligono(p_s2);
        Poligono antecessor = arvore_test.lowestCommonAncestor(p_1, p_2);
        if (antecessor == null) {
            return null;
        }
        return antecessor;
    }

    /**
     * Método para teste que Constrói uma árvore de polígonos de limite inferior
     * a limite superior
     *
     * @param lim_inf limite inferior
     * @param lim_sup limite superior
     * @return Uma árvore com todos os polígonos de lim_inf a lim_sup
     */
    public AVL<Poligono> construirArvorePoligonosRange(int lim_inf, int lim_sup) {
        AVL<Poligono> arvore = new AVL<>();
        for (int i = lim_inf; i <= lim_sup; i++) {
            String s = construirNomeDoPoligono(i);
            Poligono p = new Poligono(i, s);
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
        return arvore_poligonos_por_lado.size();

    }

}
