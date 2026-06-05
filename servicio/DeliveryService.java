package servicio;

import estructuras.*;
import modelos.*;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Servicio principal del sistema de delivery para simular el flujo completo:
 * recepcion de pedidos -> cocina -> asignacion de repartidor -> entrega.
 */
public class DeliveryService {

    /** Zona del mapa donde esta ubicado el restaurante. Es el origen de todas las rutas. */
    private static final String ZONA_RESTAURANTE = "Centro";

    private ColaPedidos      cocinaNormal;
    private ColaPrioridadPedidos cocinaVIP;
    private TablaHashPedidos registroPedidos;
    private ListaRepartidores flota;
    private PilaHistorial    auditoria;
    private GrafoMapa        mapaCiudad;
    private ArbolRanking     rankingRestaurantes;

    public DeliveryService() {
        cocinaNormal        = new ColaPedidos();
        cocinaVIP           = new ColaPrioridadPedidos(50);
        registroPedidos     = new TablaHashPedidos();
        flota               = new ListaRepartidores();
        auditoria           = new PilaHistorial();
        rankingRestaurantes = new ArbolRanking();
        inicializarMapa();
    }

    /**
     * Construye el grafo con los 10 barrios de la ciudad y sus conexiones.
     * Los pesos representan minutos de trayecto en trafico normal.
     */
    private void inicializarMapa() {
        mapaCiudad = new GrafoMapa(10);

        mapaCiudad.agregarVertice("Centro");
        mapaCiudad.agregarVertice("Cadillal");
        mapaCiudad.agregarVertice("Norte");
        mapaCiudad.agregarVertice("Campamento");
        mapaCiudad.agregarVertice("La playa");
        mapaCiudad.agregarVertice("Palace");
        mapaCiudad.agregarVertice("Esmeralda");
        mapaCiudad.agregarVertice("Vereda pomona");
        mapaCiudad.agregarVertice("Pandiguando");
        mapaCiudad.agregarVertice("Maria Oriente");

        mapaCiudad.agregarArista("Centro",      "Cadillal",      4);
        mapaCiudad.agregarArista("Centro",      "Norte",        12);
        mapaCiudad.agregarArista("Centro",      "Esmeralda",     8);
        mapaCiudad.agregarArista("Cadillal",    "Campamento",    3);
        mapaCiudad.agregarArista("Campamento",  "La playa",      6);
        mapaCiudad.agregarArista("Campamento",  "Norte",         7);
        mapaCiudad.agregarArista("Norte",       "Palace",        5);
        mapaCiudad.agregarArista("Esmeralda",   "Vereda pomona",      4);
        mapaCiudad.agregarArista("Vereda pomona",    "Pandiguando",   5);
        mapaCiudad.agregarArista("Pandiguando", "Maria Oriente", 9);
    }

    /**
     * Registra un restaurante en el arbol de ranking.
     * @param nombre       nombre del restaurante
     * @param calificacion puntaje de 0.0 a 5.0
     */
    public void registrarRestaurante(String nombre, double calificacion) {
        rankingRestaurantes.insertar(nombre, calificacion);
    }

    /**
     * Agrega un repartidor a la flota activa.
     * @param repartidor nuevo repartidor del equipo
     */
    public void agregarRepartidor(Repartidor repartidor) {
        flota.agregar(repartidor);
    }

    /**
     * Recibe un pedido nuevo: lo guarda en la tabla hash y lo encola
     * en la cocina que corresponda segun su prioridad.
     * Pedidos con prioridad >= 4 van a la cola VIP (heap).
     *
     * @param pedido el pedido que acaba de llegar
     */
    public void recibirPedido(Pedido pedido) {
        registroPedidos.put(pedido.getIdPedido(), pedido);
        auditoria.apilar("Pedido #" + pedido.getIdPedido() + " ingresado -> " + pedido.getCliente());

        if (pedido.getPrioridad() >= 4) {
            cocinaVIP.encolar(pedido);
            System.out.println("  [VIP]      " + pedido);
        } else {
            cocinaNormal.encolar(pedido);
            System.out.println("  [NORMAL]   " + pedido);
        }
    }

    /**
     * Primero revisa la cocina VIP; si esta vacia, toma de la normal.
     * Busca un repartidor libre, calcula la ruta con Dijkstra y simula la entrega.
     */
    public void despacharSiguientePedido() {
        // La cocina VIP tiene siempre prioridad sobre la normal
        Pedido pedido = cocinaVIP.desencolar();
        if (pedido == null) {
            pedido = cocinaNormal.desencolar();
        }

        if (pedido == null) {
            System.out.println("  No hay pedidos pendientes en cocina.");
            return;
        }

        System.out.println("\n  Procesando: " + pedido);
        pedido.setEstado(EstadoPedido.EN_PREPARACION);
        auditoria.apilar("Pedido #" + pedido.getIdPedido() + " -> EN_PREPARACION");

        Repartidor repartidor = flota.obtenerDisponible();
        if (repartidor == null) {
            System.out.println("  Listo en cocina pero sin repartidores libres. Vuelve a la cola.");
            // Regresa a la cola correspondiente para no perder el pedido
            if (pedido.getPrioridad() >= 4) cocinaVIP.encolar(pedido);
            else cocinaNormal.encolar(pedido);
            return;
        }

        // Asigna el repartidor y calcula la ruta mas corta desde el restaurante
        repartidor.setDisponible(false);
        pedido.setEstado(EstadoPedido.EN_CAMINO);
        auditoria.apilar("Pedido #" + pedido.getIdPedido() + " asignado a " + repartidor.getNombre());

        String ruta = mapaCiudad.calcularDijkstra(ZONA_RESTAURANTE, pedido.getBarrioDestino());
        System.out.println("  Repartidor: " + repartidor.getNombre());
        System.out.println("  Ruta: " + ruta);

        // Entrega simulada (en un sistema real aqui iria un hilo o temporizador)
        pedido.setEstado(EstadoPedido.ENTREGADO);
        repartidor.setDisponible(true);
        auditoria.apilar("Pedido #" + pedido.getIdPedido() + " ENTREGADO por " + repartidor.getNombre());
        System.out.println("  Entregado con exito.");
    }

    /** Muestra los ultimos cambios de estado registrados en la pila. */
    public void mostrarAuditoria() {
        auditoria.mostrarHistorial();
    }

    /** Muestra el ranking de restaurantes segun calificacion. */
    public void verRanking() {
        rankingRestaurantes.mostrarRanking();
    }

    /** Muestra el estado de todos los repartidores. */
    public void verFlota() {
        flota.mostrarRepartidores();
    }

    /**
     * Busca un pedido por su ID directamente en la tabla hash.
     * @param id el ID del pedido a consultar
     */
    public void buscarPedidoPorID(int id) {
        Pedido encontrado = registroPedidos.get(id);
        if (encontrado != null)
            System.out.println("  Encontrado: " + encontrado);
        else
            System.out.println("  El pedido #" + id + " no existe en el sistema.");
    }

    /**
     * Genera el archivo HTML del simulador visual y lo abre en el navegador.
     * La construcción del HTML se delega a HtmlGenerador para mantener
     * este servicio enfocado solo en la lógica de negocio.
     */
    public void abrirSimulador() {
        try {
            File archivo = new File("simulador_delivery.html");
            FileWriter fw = new FileWriter(archivo);
            fw.write(new HtmlGenerador().construir());
            fw.close();
            System.out.println("\n  [SIMULADOR] Archivo generado: " + archivo.getAbsolutePath());
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(archivo.toURI());
                System.out.println("  [SIMULADOR] Abierto en el navegador.");
            } else {
                System.out.println("  [SIMULADOR] Abre manualmente: simulador_delivery.html");
            }
        } catch (IOException e) {
            System.out.println("  [SIMULADOR] Error al generar el simulador: " + e.getMessage());
        }
    }
}
