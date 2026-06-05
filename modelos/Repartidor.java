package modelos;

/**
 * Representa a un repartidor de la flota.
 * Tiene un estado de disponibilidad que cambia al asignarle un pedido.
 */
public class Repartidor {

    private int id;
    private String nombre;
    private boolean disponible;

    public Repartidor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.disponible = true;
    }

    public int getId()               { return id; }
    public String getNombre()        { return nombre; }
    public boolean isDisponible()    { return disponible; }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Repartidor #" + id + ": " + nombre +
               " [" + (disponible ? "LIBRE" : "OCUPADO") + "]";
    }
}
