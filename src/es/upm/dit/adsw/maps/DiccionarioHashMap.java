package es.upm.dit.adsw.maps;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DiccionarioHashMap 
        implements Diccionario {
    
    private final Map<String, String> datos;

    public DiccionarioHashMap() {
        datos = new HashMap<String, String>();
    }

    // detecta duplicados: reemplaza el valor
    public void put(String clave, String valor) {
        datos.put(clave, valor);
    }

    public String get(String clave) {
        return datos.get(clave);
    }

    public String remove(String clave) {
        return datos.remove(clave);
    }

    public int size() {
        return datos.size();
    }

    @Override
    public void clear() {
        datos.clear();
    }

    public void reset() {
        datos.clear();
    }
}
