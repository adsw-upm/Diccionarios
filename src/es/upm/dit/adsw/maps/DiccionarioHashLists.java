package es.upm.dit.adsw.maps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class DiccionarioHashLists
        implements Diccionario {

    private class Nodo {
        String clave;
        String valor;

        private Nodo(String clave, String valor) {
            this.clave = clave;
            this.valor = valor;
        }
    }

    private final List<Nodo>[] tabla;
    private int n;

    public DiccionarioHashLists(int size) {
        tabla = new List[size];
    }

    // duplicados: machaca el valor anterior
    public void put(String clave, String valor) {
        int pos = Math.abs(clave.hashCode() % tabla.length);
        List<Nodo> lista = tabla[pos];
        if (lista == null) {
            lista = new ArrayList<Nodo>();
            tabla[pos] = lista;
        }
        for (Nodo nodo : lista) {
            if (nodo.clave.equals(clave)) {
                nodo.valor = valor;
                return;
            }
        }
        Nodo nodo = new Nodo(clave, valor);
        lista.add(nodo);
        n++;
    }

    public String get(String clave) {
        int pos = Math.abs(clave.hashCode() % tabla.length);
        List<Nodo> lista = tabla[pos];
        if (lista == null)
            return null;
        for (Nodo nodo : lista) {
            if (nodo.clave.equals(clave))
                return nodo.valor;
        }
        return null;
    }

    public String remove(String clave) {
        int pos = Math.abs(clave.hashCode() % tabla.length);
        List<Nodo> lista = tabla[pos];
        if (lista == null)
            return null;
        int idx = -1;
        for (int i = 0; i < lista.size(); i++) {
            Nodo nodo = lista.get(i);
            if (nodo.clave.equals(clave)) {
                idx = i;
                break;
            }
        }
        if (idx < 0)
            return null;
        n--;
        Nodo nodo = lista.remove(idx);
        return nodo.valor;
    }

    public int size() {
        return n;
    }

    public void clear() {
        Arrays.fill(tabla, null);
        n = 0;
    }

}
