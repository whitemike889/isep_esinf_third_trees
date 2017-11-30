/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class PoligonoString implements Comparable<PoligonoString> {

    private int num_lados;
    private String prefixo;

    public PoligonoString(int num_lados, String prefixo) {
        this.num_lados = num_lados;
        this.prefixo = prefixo;
    }

    public int getNumLados() {
        return num_lados;
    }

    public String getPrefixo() {
        return prefixo;
    }

    @Override
    public String toString() {
        return "{Lados:" + num_lados + " Prefixo:" + prefixo + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PoligonoString other = (PoligonoString) obj;
        return prefixo.equals(other.prefixo);
    }

    @Override
    public int compareTo(PoligonoString o) {
        return this.prefixo.compareTo(o.prefixo);
    }

}
