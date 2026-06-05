import modelos.*;
import servicio.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("=================================================");
        System.out.println("        SIMULADOR LOGISTICO DE DELIVERY          ");
        System.out.println("=================================================\n");

        DeliveryService servicio = new DeliveryService();

        //  Flota de repartidores
        servicio.agregarRepartidor(new Repartidor(1, "Andres"));
        servicio.agregarRepartidor(new Repartidor(2, "Camila"));
        servicio.agregarRepartidor(new Repartidor(3, "Laura"));

        servicio.verFlota();
        System.out.println();

        //  Restaurantes en el arbol de ranking 
        servicio.registrarRestaurante("Alpama Lacteos",      4.8);
        servicio.registrarRestaurante("Burgers Centro",      4.2);
        servicio.registrarRestaurante("Pizza Express Norte", 4.5);
        servicio.registrarRestaurante("Arepas del Sur",      3.9);

        servicio.verRanking();
        System.out.println();

        //  Fase 1: recepcion de pedidos  
        System.out.println("--- ENTRADA DE PEDIDOS ---");
        servicio.recibirPedido(new Pedido(101, "Rut",      "Norte",        1));
        servicio.recibirPedido(new Pedido(102, "Santiago", "Maria Oriente",5));  // VIP
        servicio.recibirPedido(new Pedido(103, "Nicole",   "Esmeralda",   2));
        servicio.recibirPedido(new Pedido(104, "Andres",    "Pandiguando",     4));  // VIP

        //  Fase 2: ciclo de atencion 
        // Los pedidos 102 y 104 deben salir primero por ser VIP 
        System.out.println("\n--- CICLO DE ATENCION EN COCINA ---");
        servicio.despacharSiguientePedido();
        servicio.despacharSiguientePedido();
        servicio.despacharSiguientePedido();
        servicio.despacharSiguientePedido();

        //  Auditoria con la pila y busqueda directa en la tabla hash 
        System.out.println("\n--- AUDITORIA Y CONSULTAS ---");
        servicio.mostrarAuditoria();

        System.out.println();
        servicio.buscarPedidoPorID(102);
        servicio.buscarPedidoPorID(999);  // ID inexistente para probar el caso negativo

        //  Simulador visual HTML 
        System.out.println("\n--- SIMULADOR VISUAL ---");
        servicio.abrirSimulador();
    }
}
