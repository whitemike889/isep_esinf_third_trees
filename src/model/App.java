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

    private static final String FICH_POL_UNIDADES = "poligonos_prefixo_unidades.txt";
    private static final String FICH_POL_DEZENAS = "poligonos_prefixo_dezenas.txt";
    private static final String FICH_POL_CENTENAS = "poligonos_prefixo_centenas.txt";

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
        if (p.getNumLados() < 0) {              //O(1)
            return false;                       //O(1)
        }
        if (p.getNumLados() < 10) {             //O(1)
            arvore_unidades.insert(p);          //O(logn)
            return true;                        //O(1)
        } else {
            if (p.getNumLados() < 100) {        //O(1)
                arvore_dezenas.insert(p);       //O(logn)
                return true;                    //O(1)
            } else {
                if (p.getNumLados() < 1000) {   //O(1)
                    arvore_centenas.insert(p);  //O(logn)
                    return true;                //O(1)
                }
            }
        }
        return false;                           //O(1)
    }                                           //Total: O(logn)

    //=================================A========================================
    /**
     * Método para ler os ficheiros dos polígonos para as estruturas de dados
     */
    public void lerDados() {
        Ficheiro f = new Ficheiro();                //O(1)
        f.lerPoligonos(FICH_POL_UNIDADES, this);    //O(nlogn)  
        f.lerPoligonos(FICH_POL_DEZENAS, this);     //O(nlogn)
        f.lerPoligonos(FICH_POL_CENTENAS, this);    //O(nlogn)

    }                                               //Total:O(nlogn)

    //=================================B========================================
    /**
     * Método que constrói um nome de um polígono dado o número de lados
     *
     * @param numlados Num de lados de um poligono
     * @return Nome de um poligono
     */
    public String construirNomeDoPoligono(int numlados) {
        if (numlados <= 0 || numlados >= 1000) {            //O(1)
            return "";                                      //O(1)
        }
        final int CASO_ESPECIAL_DEZENAS_LIM_INF = 10;       //O(1)
        final int CASO_ESPECIAL_DEZENAS_LIM_SUP = 29;       //O(1)
        final String SUFIXO = "gon";                        //O(1)
        int centenas = (numlados / 100) * 100;              //O(1)
        int unidades = numlados % 10;                       //O(1)
        int dezenas = numlados - centenas;                  //O(1)

        String expUnidades = "";                            //O(1)
        String expDezenas = "";                             //O(1)
        String expCentenas = "";                            //O(1)

        if (centenas > 0) {                                 //O(1)
            expCentenas = construirNomePolCentenas(centenas);//O(logn)
        }
        if (dezenas > 0) {
            expDezenas = construirNomePolDezenas(dezenas);//O(logn)
        }
        if (unidades > 0) {
            expUnidades = construirNomePolUnidades(unidades);//O(logn)
        }
        if (dezenas >= CASO_ESPECIAL_DEZENAS_LIM_INF
                && dezenas <= CASO_ESPECIAL_DEZENAS_LIM_SUP) {  //O(1)
            return expCentenas + expDezenas + SUFIXO;           //O(1)
        }

        return expCentenas + expDezenas + expUnidades + SUFIXO;//O(1)
    }                                                          //Total:O(logn)

    /**
     * Método que constrói o nome das unidades de um poligono dado o campo das
     * unidades
     *
     * @param unidades Num de lados das unidades de um poligono
     * @return Nome do campo das unidades de um poligono
     */
    private String construirNomePolUnidades(int unidades) {

        Poligono p = new Poligono(unidades, "");    //O(1)
        Poligono el = arvore_unidades.search(p);    //O(logn)
        if (el == null) {                           //O(1)
            return "";                              //O(1)
        }
        return el.getPrefixo();                     //O(1)
    }                                               //Total:O(logn)

    /**
     * Método que constrói o nome das dezenas de um poligono dado o campo das
     * dezenas
     *
     * @param dezenas Num de lados nas dezenas de um poligono
     * @return Nome do campo das dezenas de um poligono
     */
    private String construirNomePolDezenas(int dezenas) {
        Poligono p = new Poligono(dezenas, "");         //O(1)
        Poligono temp;                                  //O(1)
        if (dezenas >= 10 && dezenas <= 29) {           //O(1)
            temp = arvore_dezenas.search(p);            //O(logn)
        } else {
            int unidades = dezenas % 10;                //O(1)
            dezenas = dezenas - unidades;               //O(1)
            p = new Poligono(dezenas, "");              //O(1)
            temp = arvore_dezenas.search(p);            //O(logn)
        }
        if (temp == null) {                             //O(1)
            return "";                                  //O(1)
        }
        return temp.getPrefixo();                       //O(1)
    }                                                   //Total:O(logn)

    /**
     * Método que constrói o nome das dezenas de um poligono dado o campo das
     * centenas
     *
     * @param centenas Num lados nas centenas de um poligono
     * @return Nome do campo das centenas de um poligono
     */
    private String construirNomePolCentenas(int centenas) {
        Poligono p = new Poligono(centenas, "");        //O(1)
        Poligono el = arvore_centenas.search(p);        //O(logn)
        if (el == null) {                               //O(1)
            return "";                                  //O(1)
        }
        return el.getPrefixo();                         //O(1)
    }                                                   //Total:O(logn)
    //=================================C========================================

    /**
     * Constrói duas árvores totais de polígonos de 1 a 999 A árvore
     * arvore_poligonos_por_nome é ordenada pela string do poligono A outra é
     * arvore_poligonos_por_lado que é ordenada pelo número de lados de um
     * poligono
     *
     */
    public void construirArvorePoligonosTotal() {
        AVL<Poligono> arv_pol_lado = new AVL<>();       //O(1)
        AVL<PoligonoString> arv_pol_string = new AVL<>();//O(1)
        final int LIM_INF = 1, LIM_SUP = 999;           //O(1)
        for (int i = LIM_INF; i <= LIM_SUP; i++) {      //O(n)
            String s = construirNomeDoPoligono(i);      //O(logn)
            Poligono p_l = new Poligono(i, s);          //O(1)
            PoligonoString p_s = new PoligonoString(i, s);//O(1)
            arv_pol_lado.insert(p_l);                   //O(logn)
            arv_pol_string.insert(p_s);                 //O(logn)
        }
        arvore_poligonos_por_nome = arv_pol_string;     //O(1)
        arvore_poligonos_por_lado = arv_pol_lado;       //O(1)
    }                                                   //Total:O(nlogn)
    //=================================D========================================

    /**
     * Método que retorna o numero de lados de um poligono a partir do nome.
     *
     * @param nome Nome do poligono
     * @return -1 se não encontrado ou o numero de lados do poligono
     */
    public int numeroLados(String nome) {
        if (arvore_poligonos_por_lado == null) {        //O(1)
            construirArvorePoligonosTotal();            //O(nlogn)
        }
        PoligonoString ps = new PoligonoString(0, nome);//O(1)
        PoligonoString p_encontrado = arvore_poligonos_por_nome.search(ps);//O(logn)
        if (p_encontrado == null) {                     //O(1)
            return -1;                                  //O(1)
        }
        return p_encontrado.getNumLados();              //O(1)
    }                                                   //Pior caso: O(nlogn) + O(logn)
    //Melhor caso: O(logn)

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
        LinkedList<String> listaPoligonos = new LinkedList<>();     //O(1)
        int lim_inf = x1;                                           //O(1)
        int lim_sup = x2;                                           //O(1)
        if (x1 > x2) {                                              //O(1)
            lim_inf = x2;                                           //O(1)
            lim_sup = x1;                                           //O(1)
        }
        if (lim_inf <= 0 || lim_sup >= 1000) {                      //O(1)
            return listaPoligonos;                                  //O(1)
        }
        for (int i = lim_inf; i <= lim_sup; i++) {                  //O(n) * O(logn)
            Poligono p = new Poligono(i, "");                       //O(1)
            Poligono p_encontrado = arvore_poligonos_por_lado.search(p);//O(logn)
            if (p_encontrado != null) {                             //O(1)
                listaPoligonos.push(p_encontrado.getPrefixo());     //O(1)
            }
        }
        return listaPoligonos;                                      //O(1)
    }                                                               //Total:O(nlogn)

//=================================F========================================
    /**
     * Retorna o antecessor comum mais próximo de dois poligonos
     *
     * @param poligono1 Nome de um poligono
     * @param poligono2 Nome de outro poligono
     * @return Antecessor comum mais próximo
     */
    public Poligono lowestCommonAncestor(String poligono1, String poligono2) {
        if (arvore_poligonos_por_lado == null) {            //O(1)
            construirArvorePoligonosTotal();                //O(nlogn)
        }
        PoligonoString p_s1 = arvore_poligonos_por_nome.search(new PoligonoString(0, poligono1));//O(logn)
        PoligonoString p_s2 = arvore_poligonos_por_nome.search(new PoligonoString(0, poligono2));//O(logn)
        if (p_s1 == null || p_s2 == null) {                 //O(1)
            return null;                                    //O(1)
        }
        Poligono p_1 = new Poligono(p_s1);                  //O(1)
        Poligono p_2 = new Poligono(p_s2);                  //O(1)
        Poligono antecessor = arvore_poligonos_por_lado.lowestCommonAncestor(p_1, p_2); //O(logn)
        if (antecessor == null) {                           //O(1)
            return null;                                    //O(1)
        }
        return antecessor;                                  //O(1)
    }                                                       //Pior caso: O(nlogn) + O(logn)
    //Melhor caso: O(logn)

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
            construirArvorePoligonosTotal();                                    //O(nlogn)
        };
        PoligonoString p_s1 = arvore_poligonos_por_nome.search(new PoligonoString(0, poligono1));//O(logn)
        PoligonoString p_s2 = arvore_poligonos_por_nome.search(new PoligonoString(0, poligono2));//O(logn)
        if (p_s1 == null || p_s2 == null) {                                     //O(1)
            return null;                                                        //O(1)
        }
        Poligono p_1 = new Poligono(p_s1);                                      //O(1)
        Poligono p_2 = new Poligono(p_s2);                                      //O(1)
        Poligono antecessor = arvore_test.lowestCommonAncestor(p_1, p_2);       //O(logn)
        if (antecessor == null) {                                               //O(1)
            return null;                                                        //O(1)
        }
        return antecessor;                                                      //O(1)
    }                                                                           //Pior caso: O(nlogn) + O(logn)
                                                                                //Melhor caso: O(logn)

    /**
     * Método para teste que Constrói uma árvore de polígonos de limite inferior
     * a limite superior
     *
     * @param lim_inf limite inferior
     * @param lim_sup limite superior
     * @return Uma árvore com todos os polígonos de lim_inf a lim_sup
     */
    public AVL<Poligono> construirArvorePoligonosRange(int lim_inf, int lim_sup) {
        if (arvore_poligonos_por_lado == null) {                                //O(1)
            construirArvorePoligonosTotal();                                    //O(nlogn)
        }
        AVL<Poligono> arvore = new AVL<>();                                     //O(1)
        for (int i = lim_inf; i <= lim_sup; i++) {                              //O(n) * O(logn) * O(logn)
            Poligono p = arvore_poligonos_por_lado.search(new Poligono(i,""));  //O(logn)
            arvore.insert(p);                                                   //O(logn)
        }
        return arvore;                                                          //O(1)
    }                                                                           //Pior caso: O(nlogn) + (O(n) * O(log^2 n))
                                                                                //Melhor caso: O(n) * O(log^2 n)
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
     * Retorna quantos poligonos da árvore total existem
     *
     * @return
     */
    public int qtdPoligonosTotal() {
        if (arvore_poligonos_por_lado == null) {
            return 0;
        }
        return arvore_poligonos_por_lado.size();

    }

}
