package es.upm.dit.adsw.maps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// arbol binario de busqueda
public class DiccionarioArbol
        implements Diccionario {

    private class Nodo {
        String clave;
        String valor;
        Nodo izq;
        Nodo der;
    }

    private Nodo raiz;
    private int n;        //no. de nodos en el arbol

    // detecta duplicados: reemplaza el valor
    public void put(String clave, String valor) {
        raiz = put(raiz, clave, valor);
    }

    private Nodo put(Nodo nodo, String clave, String valor) {

        if (nodo == null) {
            nodo = new Nodo();
            nodo.clave = clave;
            nodo.valor = valor;
            n++;
            return nodo;
        }

        int cmp = nodo.clave.compareTo(clave);
        if (cmp == 0)
            nodo.valor = valor;        // reutilizamos el nodo
        else if (cmp > 0)
            nodo.izq = put(nodo.izq, clave, valor);
        else
            nodo.der = put(nodo.der, clave, valor);
        return nodo;
    }

    public String get(String clave) {
        return get(raiz, clave);
    }

    private String get(Nodo nodo, String clave) {
        if (nodo == null)
            return null;
        int cmp = nodo.clave.compareTo(clave);
        if (cmp == 0)
            return nodo.valor;
        else if (cmp > 0)
            return get(nodo.izq, clave);
        else
            return get(nodo.der, clave);
    }

    public String remove(String clave) {
        String[] valor = new String[1];
        raiz = remove(raiz, clave, valor);
        return valor[0];
    }

    /**
     * Necesitamos lo que devuelve para mantener la estructura del arbol.
     * El argumento auxiliar valor lo usamos para devolver el valor del nodo eliminado.
     */
    private Nodo remove(Nodo nodo, String clave, String[] valor) {
        if (nodo == null)
            return null;
        int cmp = nodo.clave.compareTo(clave);
        if (cmp == 0) {
            valor[0] = nodo.valor;
            if (nodo.izq == null) {
                n--;
                nodo = nodo.der;
            } else if (nodo.der == null) {
                n--;
                nodo = nodo.izq;
            } else {
                Nodo nieto = getNieto(nodo.izq);
                nodo.clave = nieto.clave;
                nodo.valor = nieto.valor;
                nodo.izq = remove(nodo.izq, nieto.clave, new String[1]);
            }
        } else if (cmp > 0)
            nodo.izq = remove(nodo.izq, clave, valor);
        else
            nodo.der = remove(nodo.der, clave, valor);
        return nodo;
    }

    private Nodo getNieto(Nodo nodo) {
        if (nodo.der == null)
            return nodo;
        else
            return getNieto(nodo.der);
    }

    public int size() {
        return n;
    }

    public void clear() {
        raiz = null;
        n = 0;
    }

}
