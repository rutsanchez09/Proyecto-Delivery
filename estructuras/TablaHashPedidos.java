package estructuras;

import modelos.Pedido;

/**
 * Tabla hash con encadenamiento (chaining) para buscar pedidos por ID en O(1) promedio.
 * Cada posicion del arreglo es el inicio de una lista enlazada de nodos.
 * Cuando dos IDs caen en el mismo bucket, se encadenan en esa lista.
 */
public class TablaHashPedidos {

    private Nodo<Pedido>[] buckets;
    private static final int CAPACIDAD = 16;

    @SuppressWarnings("unchecked")
    public TablaHashPedidos() {
        this.buckets = new Nodo[CAPACIDAD];
    }

    /**
     * Funcion hash: distribuye los IDs entre los buckets disponibles.
     * @param key el ID del pedido
     * @return indice del bucket correspondiente
     */
    private int hash(int key) {
        return Math.abs(key) % CAPACIDAD;
    }

    /**
     * Guarda o actualiza un pedido en la tabla.
     * Si ya existe un pedido con ese ID, reemplaza el dato.
     * @param key    ID del pedido
     * @param pedido objeto pedido a guardar
     */
    public void put(int key, Pedido pedido) {
        int idx = hash(key);

        // Busca si ya existe ese ID en la cadena de ese bucket
        Nodo<Pedido> aux = buckets[idx];
        while (aux != null) {
            if (aux.getDato().getIdPedido() == key) {
                aux.setDato(pedido);   // actualiza si ya existe
                return;
            }
            aux = aux.getSiguiente();
        }

        // No encontro la clave: agrega al inicio del bucket (mas rapido)
        Nodo<Pedido> nuevo = new Nodo<>(pedido);
        nuevo.setSiguiente(buckets[idx]);
        buckets[idx] = nuevo;
    }

    /**
     * Busca un pedido por su ID.
     * @param key ID del pedido
     * @return el pedido si existe, null si no se encontro
     */
    public Pedido get(int key) {
        int idx = hash(key);
        Nodo<Pedido> aux = buckets[idx];
        while (aux != null) {
            if (aux.getDato().getIdPedido() == key) return aux.getDato();
            aux = aux.getSiguiente();
        }
        return null;
    }

    /**
     * Elimina un pedido de la tabla por su ID.
     * @param key ID del pedido a eliminar
     * @return true si se elimino, false si no existia
     */
    public boolean remove(int key) {
        int idx = hash(key);
        Nodo<Pedido> aux = buckets[idx];
        Nodo<Pedido> anterior = null;

        while (aux != null) {
            if (aux.getDato().getIdPedido() == key) {
                if (anterior == null) {
                    buckets[idx] = aux.getSiguiente();  // era el primero del bucket
                } else {
                    anterior.setSiguiente(aux.getSiguiente());
                }
                return true;
            }
            anterior = aux;
            aux = aux.getSiguiente();
        }
        return false;
    }
}
