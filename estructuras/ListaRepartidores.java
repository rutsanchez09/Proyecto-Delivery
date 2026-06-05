package estructuras;

import modelos.Repartidor;

/**
 * Lista doblemente enlazada de repartidores.
 * Permite agregar repartidores, recorrer la flota y encontrar
 * el primero disponible sin necesidad de volver a asignar arreglos.
 */
public class ListaRepartidores {

    private Nodo<Repartidor> cabeza;
    private int cantidad;

    public ListaRepartidores() {
        this.cabeza = null;
        this.cantidad = 0;
    }

    /** Devuelve cuantos repartidores hay en la flota. */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Agrega un repartidor al final de la lista.
     * @param repartidor el nuevo integrante de la flota
     */
    public void agregar(Repartidor repartidor) {
        Nodo<Repartidor> nuevo = new Nodo<>(repartidor);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo<Repartidor> aux = cabeza;
            while (aux.getSiguiente() != null) aux = aux.getSiguiente();
            aux.setSiguiente(nuevo);
            nuevo.setAnterior(aux);
        }
        cantidad++;
    }

    /**
     * Recorre la lista y devuelve el primer repartidor que este libre.
     * Devuelve null si todos estan ocupados o la lista esta vacia.
     */
    public Repartidor obtenerDisponible() {
        Nodo<Repartidor> aux = cabeza;
        while (aux != null) {
            if (aux.getDato().isDisponible()) return aux.getDato();
            aux = aux.getSiguiente();
        }
        return null;
    }

    /** Imprime el estado actual de todos los repartidores de la flota. */
    public void mostrarRepartidores() {
        Nodo<Repartidor> aux = cabeza;
        System.out.println("--- FLOTA DE REPARTIDORES ---");
        while (aux != null) {
            System.out.println("  " + aux.getDato());
            aux = aux.getSiguiente();
        }
    }
}