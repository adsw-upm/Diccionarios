package es.upm.dit.adsw.maps;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;
import static org.junit.Assume.*;

/**
 * Created by jpuente on 8/3/17.
 */
public class DiccionarioTest {

    public static final int N_SLOTS = 5;
    private final Random random = new Random();
    private Diccionario diccionario;

    @Before
    public void setUp() {
//        diccionario = new DiccionarioArray(N_SLOTS);
//        diccionario = new DiccionarioArrayOrdenado(N_SLOTS);
//        diccionario = new DiccionarioArbol ();
       diccionario = new DiccionarioHashLists(5);
//        diccionario = new DiccionarioTablaHashAbierta<Integer, String>(5);
//        diccionario = new DiccionarioHashMap ();
//        diccionario = new DiccionarioFastHashMap ();
    }

    @Test
    public void test_put() {
        assertEquals(0, diccionario.size());
        diccionario.put("0", "cero");
        assertEquals(1, diccionario.size());
        diccionario.put("1", "uno");
        assertEquals(2, diccionario.size());
        diccionario.put("0", "cero");
        assertEquals(2, diccionario.size());
    }

    @Test
    public void test_get() {
        assertNull(diccionario.get("0"));
        diccionario.put("0", "cero");
        assertEquals("cero", diccionario.get("0"));
        diccionario.put("1", "uno");
        assertEquals("uno", diccionario.get("1"));
        diccionario.put("0", "modificado");
        assertEquals("modificado", diccionario.get("0"));
    }

    @Test
    public void test_remove() {
        assertEquals(0, diccionario.size());
        diccionario.remove("0");
        assertEquals(0, diccionario.size());
        diccionario.put("0", "cero");
        assertEquals(1, diccionario.size());
        diccionario.remove("0");
        assertEquals(0, diccionario.size());
    }

    @Test
    public void test_clear() {
        assertEquals(0, diccionario.size());
        diccionario.put("0", "cero");
        diccionario.put("1", "uno");
        diccionario.clear();
        assertEquals(0, diccionario.size());
    }

    @Test
    public void test05() {
        int[] datos = {3, 1, 2, 7, 5};
        for (int dato : datos) {
            String k = String.format("[%d]", dato);
            String v = String.valueOf(dato);
            diccionario.put(k, v);
        }
        assertEquals(datos.length, diccionario.size());

        for (int dato : datos) {
            String k = String.format("[%d]", dato);
            String v = String.valueOf(dato);
            assertEquals(v, diccionario.get(k));
        }
        assertNull(diccionario.get("clave"));
        assertNull(diccionario.remove("clave"));

        for (int dato : datos) {
            String k = String.format("[%d]", dato);
            String v = String.valueOf(dato);
            assertEquals(v, diccionario.remove(k));
        }
        assertEquals(0, diccionario.size());
    }

    @Test
    public void test0N() {
        assumeThat(diccionario, instanceOf(Diccionario.class));

        int N = 2 * N_SLOTS;
        int[] datos = mkData(N);
        for (int i = 0; i < datos.length; i++) {
            int dato = datos[i];
            String k = String.format("[%d]", dato);
            String v = Integer.toString(dato);
            diccionario.put(k, v);
            assertEquals(v, diccionario.get(k));
            assertNull(diccionario.get("no esta"));
            assertEquals(i + 1, diccionario.size());
        }
        for (int i = 0; i < datos.length; i++) {
            int dato = datos[i];
            String k = String.format("[%d]", dato);
            String v_2 = Integer.toString(dato) + "_2";
            diccionario.put(k, v_2);
            assertEquals(v_2, diccionario.get(k));
            assertNull(diccionario.get("no esta"));
            assertEquals(datos.length, diccionario.size());
        }
        for (int i = 0; i < datos.length; i++) {
            int dato = datos[i];
            String k = String.format("[%d]", dato);
            String v_2 = Integer.toString(dato) + "_2";
            assertEquals(v_2, diccionario.remove(k));
            assertEquals(datos.length - i - 1, diccionario.size());
        }
        assertEquals(0, diccionario.size());
    }

    @Test
    public void testRemoveN() {
        assumeThat(diccionario, instanceOf(Diccionario.class));
        int N = 2 * N_SLOTS;
        int[] datos = mkData(N);

        for (int i = 0; i < N_SLOTS / 2; i++) {
            int dato = datos[i];
            String k = String.format("[%d]", dato);
            String v = Integer.toString(dato);
            diccionario.put(k, v);
        }
        assertNull(diccionario.get("no esta"));
        assertEquals(N_SLOTS / 2, diccionario.size());
        diccionario.clear();
        assertNull(diccionario.get("no esta"));
        assertEquals(0, diccionario.size());

        for (int i = 0; i < N; i++) {
            int dato = datos[i];
            String k = String.format("[%d]", dato);
            String v = Integer.toString(dato);
            diccionario.put(k, v);
        }
        assertNull(diccionario.get("no esta"));
        assertEquals(N, diccionario.size());
        assertNull(diccionario.get("no esta"));
        diccionario.clear();
        assertEquals(0, diccionario.size());
    }

    private int[] mkData(int n) {
        int[] datos = new int[n];
        for (int i = 0; i < n; i++)
            datos[i] = i;
        for (int i = 0; i < n; i++) {
            int p = random.nextInt(n);
            int q = random.nextInt(n);
            int v = datos[p];
            datos[p] = datos[q];
            datos[q] = v;
        }
        return datos;
    }

}