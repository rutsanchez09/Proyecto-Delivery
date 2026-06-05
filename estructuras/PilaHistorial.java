package estructuras;

/**
 * Pila de strings para registrar cambios de estado de los pedidos (auditoria).
 * Implementada con nodos enlazados. Guarda maximo 10 eventos recientes.
 * El ultimo evento registrado es el primero en mostrarse.
 */
public class PilaHistorial {

    private Nodo<String> tope;
    private int contador;

    private static final int LIMITE = 10;

    public PilaHistorial() {
        this.tope = null;
        this.contador = 0;
    }

    /** Indica si la pila no tiene eventos. */
    public boolean estaVacia() {
        return tope == null;
    }

    /**
     * Agrega un evento al tope de la pila.
     * Si ya hay 10 eventos, descarta el mas antiguo automaticamente.
     * @param evento descripcion del cambio de estado
     */
    public void apilar(String evento) {
        Nodo<String> nuevo = new Nodo<>(evento);
        nuevo.setSiguiente(tope);
        tope = nuevo;
        contador++;

        // Si se supera el limite, corta el nodo que quedo en la posicion 11
        if (contador > LIMITE) {
            Nodo<String> aux = tope;
            for (int i = 0; i < LIMITE - 1; i++) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(null);
            contador = LIMITE;
        }
    }

    /**
     * Saca y devuelve el evento mas reciente.
     * Devuelve null si la pila esta vacia.
     */
    public String desapilar() {
        if (estaVacia()) return null;
        String evento = tope.getDato();
        tope = tope.getSiguiente();
        contador--;
        return evento;
    }

    /**
     * Muestra todos los eventos guardados sin modificar la pila.
     * El mas reciente aparece primero.
     */
    public void mostrarHistorial() {
        Nodo<String> aux = tope;
        System.out.println("--- HISTORIAL DE CAMBIOS (ULTIMOS " + LIMITE + ") ---");
        while (aux != null) {
            System.out.println("  " + aux.getDato());
            aux = aux.getSiguiente();
        }
    }
}