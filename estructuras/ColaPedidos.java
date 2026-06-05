package estructuras;

import modelos.Pedido;

/**
 * Cola simple de pedidos implementada con nodos enlazados (FIFO).
 * El primero en llegar es el primero en ser atendido.
 */
public class ColaPedidos {

    private Nodo<Pedido> primero;
    private Nodo<Pedido> ultimo;
    private int tamano;

    public ColaPedidos() {
        this.primero = null;
        this.ultimo = null;
        this.tamano = 0;
    }

    /** Indica si la cola no tiene pedidos. */
    public boolean estaVacia() {
        return primero == null;
    }

    /** Devuelve cuantos pedidos hay en la cola sin modificarla. */
    public int getTamano() {
        return tamano;
    }

    /**
     * Mira el primer pedido sin sacarlo de la cola.
     * Devuelve null si la cola esta vacia.
     */
    public Pedido verPrimero() {
        if (estaVacia()) return null;
        return primero.getDato();
    }

    /**
     * Agrega un pedido al final de la cola.
     * @param pedido el pedido que llega al restaurante
     */
    public void encolar(Pedido pedido) {
        Nodo<Pedido> nuevo = new Nodo<>(pedido);
        if (estaVacia()) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.setSiguiente(nuevo);
            ultimo = nuevo;
        }
        tamano++;
    }

    /**
     * Saca y devuelve el pedido que lleva mas tiempo esperando.
     * Devuelve null si la cola esta vacia.
     */
    public Pedido desencolar() {
        if (estaVacia()) return null;
        Pedido pedido = primero.getDato();
        primero = primero.getSiguiente();
        if (primero == null) ultimo = null;
        tamano--;
        return pedido;
    }
}
