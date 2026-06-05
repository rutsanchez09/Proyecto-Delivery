package estructuras;

/**
 * Grafo no dirigido y ponderado que representa el mapa de la ciudad.
 * Representacion interna: matriz de adyacencia cuadrada.
 * Algoritmo de ruta mas corta: Dijkstra.
 */
public class GrafoMapa {

    private String[] vertices;
    private int[][] matrizAdyacencia;
    private int numVertices;

    private static final int INF = 999999;

    /**
     * @param maxVertices capacidad maxima de zonas en el mapa
     */
    public GrafoMapa(int maxVertices) {
        vertices = new String[maxVertices];
        matrizAdyacencia = new int[maxVertices][maxVertices];
        numVertices = 0;

        // Inicializa sin conexiones: diagonal en 0, el resto en infinito
        for (int i = 0; i < maxVertices; i++) {
            for (int j = 0; j < maxVertices; j++) {
                matrizAdyacencia[i][j] = (i == j) ? 0 : INF;
            }
        }
    }

    /**
     * Agrega una nueva zona al mapa.
     * @param nombre nombre del barrio o zona
     */
    public void agregarVertice(String nombre) {
        if (numVertices < vertices.length) {
            vertices[numVertices] = nombre;
            numVertices++;
        }
    }

    /**
     * Conecta dos zonas con una ruta de doble sentido.
     * @param origen  nombre de la zona de partida
     * @param destino nombre de la zona de llegada
     * @param minutos tiempo estimado de viaje entre ambas zonas
     */
    public void agregarArista(String origen, String destino, int minutos) {
        int i = buscarIndice(origen);
        int j = buscarIndice(destino);
        if (i != -1 && j != -1) {
            matrizAdyacencia[i][j] = minutos;
            matrizAdyacencia[j][i] = minutos;
        }
    }

    /**
     * Calcula la ruta mas rapida entre dos zonas usando Dijkstra.
     * Devuelve la ruta como texto con el tiempo total.
     *
     * @param origen  zona donde esta el restaurante
     * @param destino zona donde vive el cliente
     * @return texto con la ruta paso a paso y el tiempo total en minutos
     */
    public String calcularDijkstra(String origen, String destino) {
        int inicio = buscarIndice(origen);
        int fin    = buscarIndice(destino);

        if (inicio == -1 || fin == -1) return "Ruta no encontrada: zona inexistente.";

        int[] distancias = new int[numVertices];
        boolean[] visitados = new boolean[numVertices];
        int[] padres = new int[numVertices];

        for (int i = 0; i < numVertices; i++) {
            distancias[i] = INF;
            visitados[i] = false;
            padres[i] = -1;
        }
        distancias[inicio] = 0;

        for (int paso = 0; paso < numVertices - 1; paso++) {
            int u = verticeConMenorDistancia(distancias, visitados);
            if (u == -1) break;
            visitados[u] = true;

            for (int v = 0; v < numVertices; v++) {
                boolean hayRuta = matrizAdyacencia[u][v] != INF;
                boolean mejora  = distancias[u] + matrizAdyacencia[u][v] < distancias[v];
                if (!visitados[v] && hayRuta && distancias[u] != INF && mejora) {
                    distancias[v] = distancias[u] + matrizAdyacencia[u][v];
                    padres[v] = u;
                }
            }
        }

        return construirRuta(padres, fin, distancias[fin]);
    }

    /**
     * Encuentra el vertice no visitado con la menor distancia acumulada.
     * Es el paso central del algoritmo de Dijkstra.
     */
    private int verticeConMenorDistancia(int[] distancias, boolean[] visitados) {
        int minVal = INF;
        int minIdx = -1;
        for (int v = 0; v < numVertices; v++) {
            if (!visitados[v] && distancias[v] <= minVal) {
                minVal = distancias[v];
                minIdx = v;
            }
        }
        return minIdx;
    }

    /**
     * Reconstruye el camino desde el destino hasta el origen
     * siguiendo el arreglo de padres, y lo devuelve como String legible.
     */
    private String construirRuta(int[] padres, int destino, int tiempoTotal) {
        if (tiempoTotal == INF) return "Sin ruta posible entre esas zonas.";

        String ruta = vertices[destino];
        int actual = destino;
        while (padres[actual] != -1) {
            actual = padres[actual];
            ruta = vertices[actual] + " -> " + ruta;
        }
        return ruta + "  (" + tiempoTotal + " min)";
    }

    /** Busca el indice de una zona por su nombre. Devuelve -1 si no existe. */
    private int buscarIndice(String nombre) {
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(nombre)) return i;
        }
        return -1;
    }
}


