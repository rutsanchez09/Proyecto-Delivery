package estructuras;

/**
 * Arbol binario de busqueda para rankear restaurantes por calificacion.
 */
public class ArbolRanking {

    // Clase interna: nodo propio del arbol, distinto al Nodo generico de las listas
    private class NodoArbol {
        String restaurante;
        double calificacion;
        NodoArbol izq;
        NodoArbol der;

        NodoArbol(String restaurante, double calificacion) {
            this.restaurante = restaurante;
            this.calificacion = calificacion;
        }
    }

    private NodoArbol raiz;

    /**
     * Inserta un restaurante en el arbol segun su calificacion.
     * @param nombre      nombre del restaurante
     * @param calificacion valor entre 0.0 y 5.0
     */
    public void insertar(String nombre, double calificacion) {
        raiz = insertarRec(raiz, nombre, calificacion);
    }

    private NodoArbol insertarRec(NodoArbol nodo, String nombre, double calificacion) {
        if (nodo == null) return new NodoArbol(nombre, calificacion);
        if (calificacion > nodo.calificacion)
            nodo.der = insertarRec(nodo.der, nombre, calificacion);
        else
            nodo.izq = insertarRec(nodo.izq, nombre, calificacion);
        return nodo;
    }

    /**
     * Muestra el ranking de mejor a peor calificacion.
     * Usa recorrido inorden inverso (der -> raiz -> izq).
     */
    public void mostrarRanking() {
        System.out.println("--- RANKING DE RESTAURANTES (mejor a peor) ---");
        inordenInverso(raiz, new int[]{1});
    }

    private void inordenInverso(NodoArbol nodo, int[] posicion) {
        if (nodo != null) {
            inordenInverso(nodo.der, posicion);
            System.out.println("  " + posicion[0] + ". " + nodo.restaurante +
                               " - Calificacion: " + nodo.calificacion);
            posicion[0]++;
            inordenInverso(nodo.izq, posicion);
        }
    }
}
