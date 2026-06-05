package modelos;

/**
 * Estados posibles de un pedido durante su ciclo de vida.
 * Usar un enum evita errores de tipeo con Strings sueltos.
 */
public enum EstadoPedido {
    PENDIENTE,
    EN_PREPARACION,
    EN_CAMINO,
    ENTREGADO
}
