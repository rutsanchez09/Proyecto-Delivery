package modelos;

/**
 * Representa un pedido hecho por un cliente.
 * Guarda el destino, la prioridad y el estado actual del pedido.
 */
public class Pedido {

    private int idPedido;
    private String cliente;
    private String barrioDestino;
    private int prioridad;       // 1 = normal, 5 = VIP/urgente
    private EstadoPedido estado;

    public Pedido(int idPedido, String cliente, String barrioDestino, int prioridad) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.barrioDestino = barrioDestino;
        this.prioridad = prioridad;
        this.estado = EstadoPedido.PENDIENTE;
    }

    public int getIdPedido()          { return idPedido; }
    public String getCliente()        { return cliente; }
    public String getBarrioDestino()  { return barrioDestino; }
    public int getPrioridad()         { return prioridad; }
    public EstadoPedido getEstado()   { return estado; }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Pedido #" + idPedido + " | " + cliente +
               " -> " + barrioDestino +
               " [" + estado + "] (Prio: " + prioridad + ")";
    }
}
