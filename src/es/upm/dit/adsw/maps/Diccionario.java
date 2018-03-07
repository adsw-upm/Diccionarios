package es.upm.dit.adsw.maps;

/**
 * Interfaz para diccionarios de texto
 *
 * Created by jpuente on 28/2/17.
 */
public interface Diccionario {
    /**
     * Añadir una clave y un valor al dicicnoario
     *
     * @param clave
     * @param valor
     */
    void put(String clave, String valor);

    /**
     * Obtener el valor asociado a una clave
     *
     * @param clave
     * @return el valor de la clave
     */
    String get(String clave);

    /**
     * Eliminar una clave y su valor
     *
     * @param clave
     * @return el valor que tenía la clave
     */
    String remove(String clave);

    /**
     * Tamaño del diccionario
     *
     * @return el número de entradas
     */
    int size();

    /**
     * Borrar todo el diccionario
     */
    void clear();

}
