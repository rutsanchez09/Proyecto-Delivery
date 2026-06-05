package estructuras;

/**
 * Nodo generico para listas enlazadas y pilas.
 * Tiene punteros al siguiente y al anterior para soportar listas dobles.
 *
 * @param <T> tipo de dato que guarda el nodo
 */
public class Nodo<T> {
private T dato;
    private Nodo<T> siguiente;
    private Nodo<T> anterior;

    public Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null;
        this.anterior = null;
    }

    public T getDato()                      { return dato; }
    public void setDato(T dato)             { this.dato = dato; }
    public Nodo<T> getSiguiente()           { return siguiente; }
    public void setSiguiente(Nodo<T> sig)   { this.siguiente = sig; }
    public Nodo<T> getAnterior()            { return anterior; }
    public void setAnterior(Nodo<T> ant)    { this.anterior = ant; }
}
