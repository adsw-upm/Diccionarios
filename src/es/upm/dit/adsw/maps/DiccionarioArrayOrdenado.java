package es.upm.dit.adsw.maps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class DiccionarioArrayOrdenado
        implements Diccionario {

    private String[] claves;
    private String[] valores;
    private int n;

    public DiccionarioArrayOrdenado(int size) {
        claves  =  new String[size];
        valores =  new String[size];
        n = 0;
    }

    /**
     * busca en el rango [a, z).
     *
     * @return la posición donde debería estar la clave buscada.
     */

    // version iterativa
    private int busca(String clave, int a, int z) {
        while (a < z) {
            int m = (a + z) / 2;
            int cmp = clave.compareTo(claves[m]);
            if (cmp == 0)
                return m;
            else if (cmp < 0)
                z = m;
            else
                a = m + 1;
        }
        return a;
    }

    // version recursiva
    /*private int busca(String clave, int a, int z) {
        if (a >= z)
            return a;
        int m = (a + z) / 2;
        int cmp = clave.compareTo(claves[m]);
        if (cmp == 0)
            return m;
        if (cmp < 0)
            return busca(clave, a, m);
        else
            return busca(clave, m+1, z);
    }*/

    // detecta duplicados: reemplaza el valor
    public void put(String clave, String valor) {
        int idx = busca(clave, 0, n);
        if (claves[idx] != null && claves[idx].equals(clave)) {
            valores[idx] = valor;
            return;
        }
        if (idx < n) {
            System.arraycopy(claves, idx, claves, idx + 1, n - idx);
            System.arraycopy(valores, idx, valores, idx + 1, n - idx);
        }
        claves[idx] = clave;
        valores[idx] = valor;
        n++;
    }

    public String get(String clave) {
        int idx = busca(clave, 0, n);
        if (idx < 0 || idx >= n)
            return null;
        if (claves[idx] != null && claves[idx].equals(clave))
            return valores[idx];
        else
            return null;
    }

    public String remove(String clave) {
        int idx = busca(clave, 0, n);
        if (idx < 0 || idx >= n)
            return null;
        if (claves[idx] == null || !claves[idx].equals(clave))
            return null;
        String valor = valores[idx];
        if (n > 1) {
            System.arraycopy(claves, idx + 1, claves, idx, n - idx - 1);
            System.arraycopy(valores, idx + 1, valores, idx, n - idx - 1);
        }
        n--;
        claves[n] = null;
        valores[n] = null;
        return valor;
    }

    public int size() {
        return n;
    }

    public void clear() {
        Arrays.fill(claves, null);
        Arrays.fill(valores, null);
        n = 0;
    }

}

