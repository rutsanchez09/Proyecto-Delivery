package estructuras;

import modelos.Pedido;

/**
 * Cola de prioridad implementada con un max-heap manual sobre un arreglo.
 * El pedido con mayor prioridad (numero mas alto) siempre sale primero,
 * sin importar el orden en que llegaron.
 */
public class ColaPrioridadPedidos {

    private Pedido[] heap;
    private int tamano;

    /**
     * @param capacidad maximo de pedidos que puede manejar el heap
     */
    public ColaPrioridadPedidos(int capacidad) {
        this.heap = new Pedido[capacidad];
        this.tamano = 0;
    }

    /** Indica si el heap no tiene pedidos. */
    public boolean estaVacia() {
        return tamano == 0;
    }

    /** Devuelve cuantos pedidos hay actualmente. */
    public int getTamano() {
        return tamano;
    }

    /**
     * Inserta un pedido y lo sube hasta que el heap quede ordenado.
     * @param pedido el pedido a agregar
     */
    public void encolar(Pedido pedido) {
        if (tamano >= heap.length) {
            System.out.println("Advertencia: heap lleno, no se pudo encolar " + pedido.getIdPedido());
            return;
        }
        heap[tamano] = pedido;
        tamano++;
        subirHeap(tamano - 1);
    }

    /**
     * Saca el pedido de mayor prioridad (la raiz) y reorganiza el heap.
     * Devuelve null si esta vacio.
     */
    public Pedido desencolar() {
        if (estaVacia()) return null;
        Pedido raiz = heap[0];
        // Mueve el ultimo elemento a la raiz y baja hasta reordenar
        heap[0] = heap[tamano - 1];
        tamano--;
        bajarHeap(0);
        return raiz;
    }

    /**
     * Sube el nodo en la posicion i hasta que su padre tenga mayor prioridad.
     * Se usa despues de insertar al final del heap.
     */
    private void subirHeap(int i) {
        int padre = (i - 1) / 2;
        while (i > 0 && heap[i].getPrioridad() > heap[padre].getPrioridad()) {
            intercambiar(i, padre);
            i = padre;
            padre = (i - 1) / 2;
        }
    }

    /**
     * Baja el nodo en la posicion i intercambiandolo con el hijo de mayor
     * prioridad hasta que quede en su lugar correcto.
     */
    private void bajarHeap(int i) {
        int mayor = i;
        int hijoIzq = 2 * i + 1;
        int hijoDer = 2 * i + 2;

        if (hijoIzq < tamano && heap[hijoIzq].getPrioridad() > heap[mayor].getPrioridad())
            mayor = hijoIzq;
        if (hijoDer < tamano && heap[hijoDer].getPrioridad() > heap[mayor].getPrioridad())
            mayor = hijoDer;

        if (i != mayor) {
            intercambiar(i, mayor);
            bajarHeap(mayor);
        }
    }

    /** Intercambia dos elementos dentro del arreglo del heap. */
    private void intercambiar(int i, int j) {
        Pedido temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
