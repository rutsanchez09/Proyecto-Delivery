# Simulador Logístico de Delivery — Proyecto 2

Simulador de un sistema de delivery implementado en Java, donde cada estructura de datos fue construida desde cero sin usar las clases de la API estándar de Java (`Stack`, `Queue`, `LinkedList`, `Hashtable`, `PriorityQueue`, `TreeMap`, etc.).

---

## Descripción del proyecto

El sistema simula el flujo completo de un restaurante de delivery: los pedidos llegan, se procesan en cocina según su prioridad, se asignan a un repartidor disponible y se calcula la ruta más corta hacia el cliente usando el algoritmo de Dijkstra sobre un grafo de barrios.

Cada pedido pasa por cuatro estados: `PENDIENTE → EN_PREPARACIÓN → EN_CAMINO → ENTREGADO`, y cada cambio queda registrado en una pila de auditoría.

---

## Estructura del proyecto

```
Proyecto Delivery/
├── Main.java
├── modelos/
│   ├── Pedido.java
│   ├── Repartidor.java
│   └── EstadoPedido.java
├── estructuras/
│   ├── Nodo.java
│   ├── ColaPedidos.java
│   ├── ColaPrioridadPedidos.java
│   ├── PilaHistorial.java
│   ├── TablaHashPedidos.java
│   ├── ListaRepartidores.java
│   ├── ArbolRanking.java
│   └── GrafoMapa.java
└── servicio/
    ├── DeliveryService.java
    └── HtmlGenerador.java
```

## Estructuras de datos implementadas

Todas las estructuras fueron escritas a mano. La clase `Nodo<T>` es el bloque base genérico que reutilizan varias de ellas.

**Lista doblemente enlazada** | `ListaRepartidores` -> Gestiona la flota de repartidores; permite recorrer y marcar libre/ocupado sin reasignar arreglos 

**Cola FIFO** | `ColaPedidos` -> Pedidos normales: el primero en llegar es el primero en ser atendido 

**Cola de prioridad (max-heap)** | `ColaPrioridadPedidos` -> Pedidos VIP (prioridad ≥ 4) salen primero sin importar el orden de llegada 

**Pila LIFO** | `PilaHistorial` -> Registra los últimos 10 cambios de estado para auditoría 

**Tabla hash con chaining** | `TablaHashPedidos` -> Búsqueda de pedido por ID en O(1) promedio; colisiones resueltas con lista enlazada por bucket 

**Árbol binario de búsqueda** | `ArbolRanking` -> Ranking de restaurantes por calificación; recorrido inorden inverso para mostrar de mayor a menor 

**Grafo ponderado + Dijkstra** | `GrafoMapa` -> Mapa de 10 barrios con lista de adyacencia; calcula la ruta más rápida entre zonas 

---

## Cómo funciona el flujo principal

1. Se registran repartidores en la lista enlazada y restaurantes en el árbol BST.
2. Al llegar un pedido, se guarda en la tabla hash y se encola: cola VIP si `prioridad >= 4`, cola normal si no.
3. Al despachar, la cocina VIP tiene siempre prioridad sobre la normal. Se busca el primer repartidor libre en la lista.
4. Se calcula la ruta con Dijkstra desde la zona del restaurante (`ZONA_RESTAURANTE`) hasta el barrio del cliente.
5. Cada cambio de estado queda apilado en el historial de auditoría (máximo 10 eventos, LIFO).

---

## Cómo ejecutar

**Requisitos:** Java 11 o superior. No requiere dependencias externas.

```bash
# Desde la carpeta raíz del proyecto
javac -d out modelos/*.java estructuras/*.java servicio/*.java Main.java
java -cp out Main
```

Al finalizar, se genera el archivo `simulador_delivery.html` en el directorio de ejecución y se abre automáticamente en el navegador por defecto.

---

## Simulador visual (HTML)

El proyecto incluye un simulador interactivo en HTML que visualiza en tiempo real las mismas estructuras implementadas en Java. Lo genera `HtmlGenerador.java` al ejecutar el programa.

- **Pestaña Panel:** métricas globales, estado de la flota, cola activa y pila de auditoría en vivo
- **Pestaña Estructuras:** visualización del heap interno celda por celda, la tabla hash con sus buckets encadenados y el árbol BST
- **Pestaña Mapa / Dijkstra:** grafo de barrios con las mismas aristas y pesos de `GrafoMapa.java`; al calcular una ruta, los nodos del camino óptimo se resaltan en el SVG
- **Pestaña Nuevo pedido:** permite agregar pedidos manualmente y ejecutar el ciclo de despacho de forma interactiva

El simulador usa los mismos algoritmos que el código Java (Dijkstra, max-heap, encadenamiento hash), reimplementados en JavaScript para poder visualizarlos en el navegador sin depender de ninguna librería externa.

### Uso de IA en el simulador HTML

El simulador visual fue desarrollado con asistencia de IA (Claude de Anthropic). La lógica de negocio, las estructuras de datos y el código Java principal fueron escritos y entendidos por mí; la IA se usó para acelerar la parte de presentación visual (HTML/CSS/JS), que está fuera de los criterios evaluados del proyecto. Antes de incluirlo, verifiqué que los algoritmos del simulador (Dijkstra en JS, heap en JS) coincidieran con mi implementación Java y que los datos de prueba fueran los mismos que en `Main.java`.

---

## Autores
   Universidad Colegio Mayor del Cauca · Ingeniería Informática · Cuarto semestre · 2026-1

   - Alejandro Enriquez Botina
   - Kevin Santiago Gallego
   - Nicole Vanessa García Velasco
   - Carlos Andrés Reyes
   - Rut Ester Sánchez Arroyo