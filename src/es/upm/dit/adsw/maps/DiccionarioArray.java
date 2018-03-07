package es.upm.dit.adsw.maps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class DiccionarioArray

        implements Diccionario {
    private String[] claves;
    private String[] valores;
    private int n;

    public DiccionarioArray(int size) {
        claves = new String[size];
        valores = new String[size];
        n = 0;
    }

    // duplicado: cambia el valor anterior
    public void put(String clave, String valor) {
        for (int i = 0; i < n; i++) {
            if (claves[i].equals(clave)) {
                valores[i] = valor;
                return;
            }
        }

        claves[n] = clave;
        valores[n] = valor;
        n++;
    }

    public String get(String clave) {
        for (int i = 0; i < n; i++) {
            if (claves[i].equals(clave))
                return valores[i];
        }
        return null;
    }

    public String remove(String clave) {
        for (int i = 0; i < n; i++) {
            if (claves[i].equals(clave)) {
                String valor = valores[i];
                if (n > 1) {
                    claves[i] = claves[n - 1];
                    valores[i] = valores[n - 1];
                }
                n--;
                claves[n] = null;
                valores[n] = null;
                return valor;
            }
        }
        return null;
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

