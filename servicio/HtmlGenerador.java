package servicio;

/**
 * Genera el contenido HTML del simulador visual de delivery.
 */
public class HtmlGenerador {

    /**
     * Construye y devuelve el HTML completo del simulador.
     * @return cadena con el documento HTML listo para escribirse a disco
     */
    public String construir() {
        StringBuilder sb = new StringBuilder();
        agregarCabecera(sb);
        agregarCuerpo(sb);
        agregarJavaScript(sb);
        sb.append("</script>\n</body>\n</html>\n");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // Secciones del HTML
    // -------------------------------------------------------------------------

    /** Escribe el DOCTYPE, head y estilos CSS. */
    private void agregarCabecera(StringBuilder sb) {
        sb.append("<!DOCTYPE html>\n<html lang=\"es\">\n<head>\n");
        sb.append("<meta charset=\"UTF-8\">\n<meta name=\"viewport\" content=\"width=device-width,initial-scale=1\">\n");
        sb.append("<title>Simulador Logistico de Delivery</title>\n");
        sb.append("<style>\n");
        sb.append("*{box-sizing:border-box;margin:0;padding:0}\n");
        sb.append("body{font-family:system-ui,sans-serif;background:#f5f5f7;color:#1d1d1f}\n");
        sb.append("h1{font-size:20px;font-weight:600;padding:16px 20px;background:#fff;border-bottom:1px solid #e0e0e0}\n");
        sb.append("h1 span{font-size:12px;font-weight:400;color:#888;margin-left:10px}\n");
        sb.append(".panel{max-width:960px;margin:0 auto;padding:16px}\n");
        sb.append(".section-title{font-size:12px;font-weight:500;color:#666;text-transform:uppercase;letter-spacing:.06em;margin-bottom:10px}\n");
        sb.append(".card{background:#fff;border:1px solid #e5e5e5;border-radius:12px;padding:16px 20px;margin-bottom:14px}\n");
        sb.append(".grid2{display:grid;grid-template-columns:1fr 1fr;gap:14px;margin-bottom:14px}\n");
        sb.append(".grid3{display:grid;grid-template-columns:1fr 1fr 1fr;gap:10px;margin-bottom:14px}\n");
        sb.append(".metric{background:#f5f5f7;border-radius:10px;padding:12px 16px}\n");
        sb.append(".metric-label{font-size:12px;color:#888;margin-bottom:4px}\n");
        sb.append(".metric-val{font-size:24px;font-weight:600;color:#1d1d1f}\n");
        sb.append(".badge{display:inline-block;font-size:11px;font-weight:500;padding:2px 8px;border-radius:6px}\n");
        sb.append(".b-pend{background:#EEEDFE;color:#3C3489}.b-prep{background:#FAEEDA;color:#633806}\n");
        sb.append(".b-cam{background:#E6F1FB;color:#0C447C}.b-ent{background:#EAF3DE;color:#27500A}\n");
        sb.append(".b-vip{background:#FAECE7;color:#993C1D}.b-norm{background:#F1EFE8;color:#444441}\n");
        sb.append(".btn{cursor:pointer;font-size:13px;font-weight:500;padding:8px 18px;border-radius:8px;border:1px solid #d0d0d0;background:#fff;color:#1d1d1f}\n");
        sb.append(".btn-primary{background:#534AB7;color:#fff;border-color:#534AB7}\n");
        sb.append(".row{display:flex;align-items:center;gap:10px;padding:9px 0;border-bottom:1px solid #f0f0f0}\n");
        sb.append(".row:last-child{border-bottom:none}\n");
        sb.append(".rep-dot{width:9px;height:9px;border-radius:50%;flex-shrink:0}\n");
        sb.append(".dot-libre{background:#52A942}.dot-ocup{background:#D93025}\n");
        sb.append(".form-row{display:flex;gap:8px;flex-wrap:wrap;align-items:flex-end;margin-bottom:10px}\n");
        sb.append(".form-group{display:flex;flex-direction:column;gap:4px}\n");
        sb.append(".form-group label{font-size:12px;color:#666}\n");
        sb.append(".form-group input,.form-group select{font-size:13px;padding:7px 10px;border-radius:8px;border:1px solid #d0d0d0;background:#fff;color:#1d1d1f;width:100%}\n");
        sb.append(".log-box{background:#f5f5f7;border-radius:8px;padding:12px 14px;font-size:12px;font-family:ui-monospace,monospace;max-height:180px;overflow-y:auto;color:#555}\n");
        sb.append(".log-line{margin-bottom:3px;line-height:1.5}.log-ok{color:#2D6E12}.log-warn{color:#854F0B}.log-info{color:#185FA5}\n");
        sb.append(".tabs{display:flex;gap:2px;margin-bottom:14px;border-bottom:1px solid #e0e0e0}\n");
        sb.append(".tab{font-size:13px;padding:8px 16px;cursor:pointer;border-bottom:2px solid transparent;color:#888;background:none;border-top:none;border-left:none;border-right:none;font-weight:400}\n");
        sb.append(".tab.active{color:#1d1d1f;border-bottom-color:#534AB7;font-weight:500}\n");
        sb.append(".tab-panel{display:none}.tab-panel.active{display:block}\n");
        sb.append(".ruta-box{font-size:13px;padding:10px 14px;background:#f5f5f7;border-radius:8px;color:#1d1d1f;margin-top:8px;min-height:34px}\n");
        sb.append(".heap-grid{display:grid;grid-template-columns:repeat(5,1fr);gap:6px}\n");
        sb.append(".heap-cell{font-size:11px;text-align:center;padding:7px 4px;border-radius:8px;border:1px solid #e5e5e5;background:#f5f5f7}\n");
        sb.append(".heap-prio{font-size:17px;font-weight:600;color:#534AB7}\n");
        sb.append(".hash-table{display:grid;grid-template-columns:44px 1fr;gap:4px;align-items:start}\n");
        sb.append(".hash-idx{font-size:11px;font-family:ui-monospace,monospace;color:#aaa;text-align:right;padding-top:5px}\n");
        sb.append(".hash-bucket{display:flex;gap:4px;flex-wrap:wrap}\n");
        sb.append(".hash-node{font-size:11px;padding:3px 8px;border-radius:6px;background:#E6F1FB;color:#0C447C;font-family:ui-monospace,monospace}\n");
        sb.append(".tree-svg{width:100%;height:160px}\n");
        sb.append("</style>\n</head>\n<body>\n");
    }

    /** Escribe el body HTML: título, pestañas y contenido de cada panel. */
    private void agregarCuerpo(StringBuilder sb) {
        sb.append("<h1>Simulador Log\u00edstico de Delivery <span>Java + Visualizaci\u00f3n HTML</span></h1>\n");
        sb.append("<div class=\"panel\">\n");

        // Barra de pestañas
        sb.append("<div class=\"tabs\">\n");
        sb.append("  <button class=\"tab active\" onclick=\"switchTab('dashboard')\">Panel</button>\n");
        sb.append("  <button class=\"tab\" onclick=\"switchTab('estructuras')\">Estructuras</button>\n");
        sb.append("  <button class=\"tab\" onclick=\"switchTab('grafo')\">Mapa / Dijkstra</button>\n");
        sb.append("  <button class=\"tab\" onclick=\"switchTab('nuevo')\">Nuevo pedido</button>\n");
        sb.append("</div>\n");

        agregarTabDashboard(sb);
        agregarTabEstructuras(sb);
        agregarTabGrafo(sb);
        agregarTabNuevoPedido(sb);

        sb.append("</div>\n"); // .panel
    }

    /** Panel principal: métricas, flota, cola activa, auditoría. */
    private void agregarTabDashboard(StringBuilder sb) {
        sb.append("<div id=\"tab-dashboard\" class=\"tab-panel active\">\n");
        sb.append("  <div class=\"grid3\">\n");
        sb.append("    <div class=\"metric\"><div class=\"metric-label\">Pedidos totales</div><div class=\"metric-val\" id=\"m-total\">0</div></div>\n");
        sb.append("    <div class=\"metric\"><div class=\"metric-label\">En cola cocina</div><div class=\"metric-val\" id=\"m-cola\">0</div></div>\n");
        sb.append("    <div class=\"metric\"><div class=\"metric-label\">Entregados</div><div class=\"metric-val\" id=\"m-ent\">0</div></div>\n");
        sb.append("  </div>\n");
        sb.append("  <div class=\"grid2\">\n");
        sb.append("    <div class=\"card\"><div class=\"section-title\">Flota de repartidores (ListaRepartidores)</div><div id=\"flota-list\"></div></div>\n");
        sb.append("    <div class=\"card\"><div class=\"section-title\">Cola activa (siguiente a salir)</div>");
        sb.append("<div id=\"cola-preview\" style=\"font-size:13px;color:#888\">\u2014</div>");
        sb.append("<div style=\"margin-top:12px\"><button class=\"btn btn-primary\" onclick=\"despachar()\">Despachar siguiente &rarr;</button></div></div>\n");
        sb.append("  </div>\n");
        sb.append("  <div class=\"card\"><div class=\"section-title\">Pedidos activos</div><div id=\"pedidos-list\"></div></div>\n");
        sb.append("  <div class=\"card\"><div class=\"section-title\">Auditoria \u2014 PilaHistorial LIFO (\u00faltimos 10)</div><div class=\"log-box\" id=\"log-box\"></div></div>\n");
        sb.append("</div>\n");
    }

    /** Pestaña que visualiza el heap, la tabla hash y el árbol BST. */
    private void agregarTabEstructuras(StringBuilder sb) {
        sb.append("<div id=\"tab-estructuras\" class=\"tab-panel\">\n");
        sb.append("  <div class=\"card\"><div class=\"section-title\">ColaPrioridadPedidos \u2014 heap max-prioridad (arreglo interno)</div>");
        sb.append("<div class=\"heap-grid\" id=\"heap-vis\"></div>");
        sb.append("<p style=\"font-size:12px;color:#888;margin-top:8px\">El elemento [0] siempre tiene la mayor prioridad. Cada padre &ge; sus hijos.</p></div>\n");
        sb.append("  <div class=\"card\"><div class=\"section-title\">TablaHashPedidos \u2014 buckets con encadenamiento (capacidad 16)</div>");
        sb.append("<div class=\"hash-table\" id=\"hash-vis\"></div>");
        sb.append("<p style=\"font-size:12px;color:#888;margin-top:8px\">bucket = idPedido % 16 &nbsp;&middot;&nbsp; colisiones se encadenan en lista enlazada</p></div>\n");
        sb.append("  <div class=\"card\"><div class=\"section-title\">ArbolRanking \u2014 inorden inverso (mayor calificaci\u00f3n primero)</div>");
        sb.append("<svg class=\"tree-svg\" id=\"tree-svg\" viewBox=\"0 0 600 160\"></svg></div>\n");
        sb.append("</div>\n");
    }

    /** Pestaña del grafo con selector de ruta Dijkstra y SVG del mapa. */
    private void agregarTabGrafo(StringBuilder sb) {
        // Mismos barrios que DeliveryService.inicializarMapa()
        String[] barrios = {"Centro","Cadillal","Norte","Campamento","La playa",
                            "Palace","Esmeralda","Vereda pomona","Pandiguando","Maria Oriente"};

        sb.append("<div id=\"tab-grafo\" class=\"tab-panel\">\n");
        sb.append("  <div class=\"card\"><div class=\"section-title\">GrafoMapa \u2014 Dijkstra (mismos pesos que GrafoMapa.java)</div>\n");
        sb.append("  <div class=\"form-row\">\n");

        // Select origen
        sb.append("    <div class=\"form-group\" style=\"flex:1\"><label>Origen</label><select id=\"g-origen\">");
        for (String b : barrios) sb.append("<option>").append(b).append("</option>");
        sb.append("</select></div>\n");

        // Select destino (empieza por Maria Oriente para que el ejemplo sea más largo)
        sb.append("    <div class=\"form-group\" style=\"flex:1\"><label>Destino</label><select id=\"g-destino\">");
        sb.append("<option>Maria Oriente</option>");
        for (String b : barrios) {
            if (!b.equals("Maria Oriente")) sb.append("<option>").append(b).append("</option>");
        }
        sb.append("</select></div>\n");

        sb.append("    <button class=\"btn btn-primary\" onclick=\"calcularRuta()\">Calcular</button>\n");
        sb.append("  </div>\n");
        sb.append("  <div class=\"ruta-box\" id=\"ruta-result\">Selecciona origen y destino para ver la ruta.</div></div>\n");
        sb.append("  <div class=\"card\"><div class=\"section-title\">Mapa del grafo (mismas aristas de GrafoMapa.java)</div>");
        sb.append("<svg id=\"grafo-svg\" viewBox=\"0 0 600 300\" style=\"width:100%;height:300px\"></svg></div>\n");
        sb.append("</div>\n");
    }

    /** Pestaña para registrar nuevos pedidos y ver el ranking de restaurantes. */
    private void agregarTabNuevoPedido(StringBuilder sb) {
        // Mismos barrios que DeliveryService.inicializarMapa()
        String[] barrios = {"Centro","Cadillal","Norte","Campamento","La playa",
                            "Palace","Esmeralda","Vereda pomona","Pandiguando","Maria Oriente"};

        sb.append("<div id=\"tab-nuevo\" class=\"tab-panel\">\n");
        sb.append("  <div class=\"card\"><div class=\"section-title\">Registrar nuevo pedido</div>\n");
        sb.append("  <div class=\"form-row\">\n");
        sb.append("    <div class=\"form-group\" style=\"flex:1.2\"><label>Cliente</label><input type=\"text\" id=\"n-cliente\" placeholder=\"Nombre del cliente\"></div>\n");
        sb.append("    <div class=\"form-group\" style=\"flex:1.2\"><label>Barrio destino</label><select id=\"n-barrio\">");
        for (String b : barrios) sb.append("<option>").append(b).append("</option>");
        sb.append("</select></div>\n");
        sb.append("    <div class=\"form-group\" style=\"width:90px\"><label>Prioridad</label><select id=\"n-prio\">");
        for (int i = 1; i <= 5; i++) sb.append("<option value=\"").append(i).append("\">").append(i).append("</option>");
        sb.append("</select></div>\n");
        sb.append("    <button class=\"btn btn-primary\" onclick=\"agregarPedido()\">Agregar +</button>\n");
        sb.append("  </div>\n");
        sb.append("  <p style=\"font-size:12px;color:#888\">Prioridad 4-5 = VIP (heap) &nbsp;&middot;&nbsp; 1-3 = normal (FIFO)</p></div>\n");
        sb.append("  <div class=\"card\"><div class=\"section-title\">Ranking de restaurantes (ArbolRanking)</div><div id=\"ranking-list\"></div></div>\n");
        sb.append("</div>\n");
    }

    /** Escribe el bloque <script> con toda la lógica JavaScript del simulador. */
    private void agregarJavaScript(StringBuilder sb) {
        sb.append("<script>\n");
        // Datos del grafo (mismos que GrafoMapa.java)
        sb.append("const BARRIOS=[\"Centro\",\"Cadillal\",\"Norte\",\"Campamento\",\"La playa\",\"Palace\",\"Esmeralda\",\"Vereda pomona\",\"Pandiguando\",\"Maria Oriente\"];\n");
        sb.append("const ARISTAS=[[\"Centro\",\"Cadillal\",4],[\"Centro\",\"Norte\",12],[\"Centro\",\"Esmeralda\",8],[\"Cadillal\",\"Campamento\",3],[\"Campamento\",\"La playa\",6],[\"Campamento\",\"Norte\",7],[\"Norte\",\"Palace\",5],[\"Esmeralda\",\"Vereda pomona\",4],[\"Vereda pomona\",\"Pandiguando\",5],[\"Pandiguando\",\"Maria Oriente\",9]];\n");
        sb.append("const INF=999999;\n");
        // Estado de la aplicación
        sb.append("let nextId=101,pedidos=[],colaVIP=[],colaNormal=[],entregados=0,historial=[];\n");
        sb.append("let repartidores=[{id:1,nombre:\"Carlos\",libre:true},{id:2,nombre:\"Andres\",libre:true},{id:3,nombre:\"Laura\",libre:true}];\n");
        sb.append("let restaurantes=[{n:\"Alpama Lacteos\",c:4.8},{n:\"Pizza Express Norte\",c:4.5},{n:\"Burgers Centro\",c:4.2},{n:\"Arepas del Sur\",c:3.9}];\n");
        // Funciones: log, heap, dijkstra, render, draw
        sb.append("function log(msg,tipo=\"info\"){const d=new Date();const t=d.getHours()+\":\"+String(d.getMinutes()).padStart(2,\"0\")+\":\"+String(d.getSeconds()).padStart(2,\"0\");historial.unshift(\"[\"+t+\"] \"+msg);if(historial.length>10)historial.pop();const box=document.getElementById(\"log-box\");box.innerHTML=historial.map(h=>`<div class=\"log-line log-${tipo}\">${h}</div>`).join(\"\");}\n");
        sb.append("function heapPush(h,p){h.push(p);let i=h.length-1;while(i>0){let pa=Math.floor((i-1)/2);if(h[i].prio>h[pa].prio){[h[i],h[pa]]=[h[pa],h[i]];i=pa;}else break;}}\n");
        sb.append("function heapPop(h){if(!h.length)return null;const top=h[0];h[0]=h[h.length-1];h.pop();let i=0;while(true){let l=2*i+1,r=2*i+2,m=i;if(l<h.length&&h[l].prio>h[m].prio)m=l;if(r<h.length&&h[r].prio>h[m].prio)m=r;if(m===i)break;[h[i],h[m]]=[h[m],h[i]];i=m;}return top;}\n");
        sb.append("function dijkstra(ori,dst){const idx=b=>BARRIOS.indexOf(b);const n=BARRIOS.length;const dist=Array(n).fill(INF);const vis=Array(n).fill(false);const par=Array(n).fill(-1);const g=Array.from({length:n},()=>Array(n).fill(INF));BARRIOS.forEach((_,i)=>g[i][i]=0);ARISTAS.forEach(([a,b,w])=>{g[idx(a)][idx(b)]=w;g[idx(b)][idx(a)]=w;});const s=idx(ori),e=idx(dst);dist[s]=0;for(let k=0;k<n-1;k++){let u=-1,min=INF;for(let v=0;v<n;v++)if(!vis[v]&&dist[v]<=min){min=dist[v];u=v;}if(u===-1)break;vis[u]=true;for(let v=0;v<n;v++){if(!vis[v]&&g[u][v]!==INF&&dist[u]+g[u][v]<dist[v]){dist[v]=dist[u]+g[u][v];par[v]=u;}}}if(dist[e]===INF)return{ruta:[],tiempo:INF};let path=[],cur=e;while(cur!==-1){path.unshift(BARRIOS[cur]);cur=par[cur];}return{ruta:path,tiempo:dist[e]};}\n");
        sb.append("function agregarPedido(){const cl=document.getElementById(\"n-cliente\").value.trim()||\"Cliente\"+nextId;const ba=document.getElementById(\"n-barrio\").value;const pr=parseInt(document.getElementById(\"n-prio\").value);const p={id:nextId++,cliente:cl,barrio:ba,prio:pr,estado:\"PENDIENTE\"};pedidos.push(p);if(pr>=4)heapPush(colaVIP,p);else colaNormal.push(p);log(\"Pedido #\"+p.id+\" de \"+cl+\" ingresado [prio \"+pr+\"]\",\"info\");render();}\n");
        sb.append("function despachar(){let p=heapPop(colaVIP);if(!p&&colaNormal.length)p=colaNormal.shift();if(!p){log(\"Sin pedidos en cocina\",\"warn\");return;}p.estado=\"EN_PREPARACION\";log(\"Pedido #\"+p.id+\" -> EN_PREPARACION\",\"info\");const rep=repartidores.find(r=>r.libre);if(!rep){log(\"Sin repartidores libres\",\"warn\");if(p.prio>=4)heapPush(colaVIP,p);else colaNormal.unshift(p);render();return;}rep.libre=false;p.estado=\"EN_CAMINO\";log(\"Pedido #\"+p.id+\" asignado a \"+rep.nombre,\"info\");const {ruta,tiempo}=dijkstra(\"Centro\",p.barrio);log(\"Ruta: \"+ruta.join(\" -> \")+\" (\"+tiempo+\" min)\",\"ok\");setTimeout(()=>{p.estado=\"ENTREGADO\";rep.libre=true;entregados++;log(\"Pedido #\"+p.id+\" ENTREGADO por \"+rep.nombre,\"ok\");render();},800);render();}\n");
        sb.append("function calcularRuta(){const o=document.getElementById(\"g-origen\").value,d=document.getElementById(\"g-destino\").value;const {ruta,tiempo}=dijkstra(o,d);const el=document.getElementById(\"ruta-result\");if(!ruta.length){el.textContent=\"Sin ruta posible.\";return;}el.innerHTML=ruta.map((z,i)=>`<span style=\"display:inline-block;font-size:12px;font-weight:500;padding:3px 10px;border-radius:20px;background:#EEEDFE;color:#3C3489;margin:2px\">${z}</span>${i<ruta.length-1?'<span style=\"color:#aaa;margin:0 2px\">&rarr;</span>':''}`).join(\"\")+`<span style=\"font-size:12px;color:#888;margin-left:8px\">${tiempo} min</span>`;drawGrafo(ruta);}\n");
        sb.append("function drawGrafo(rutaActiva=[]){const svg=document.getElementById(\"grafo-svg\");svg.innerHTML=\"\";const pos={Centro:[300,40],Cadillal:[180,100],Norte:[420,100],Campamento:[160,175],\"La playa\":[120,255],Palace:[460,175],Esmeralda:[300,175],\"Vereda pomona\":[380,255],Pandiguando:[260,255],\"Maria Oriente\":[180,255]};ARISTAS.forEach(([a,b,w])=>{if(!pos[a]||!pos[b])return;const enRuta=rutaActiva.includes(a)&&rutaActiva.includes(b)&&Math.abs(rutaActiva.indexOf(a)-rutaActiva.indexOf(b))===1;const line=document.createElementNS(\"http://www.w3.org/2000/svg\",\"line\");line.setAttribute(\"x1\",pos[a][0]);line.setAttribute(\"y1\",pos[a][1]);line.setAttribute(\"x2\",pos[b][0]);line.setAttribute(\"y2\",pos[b][1]);line.setAttribute(\"stroke\",enRuta?\"#534AB7\":\"#d0d0d0\");line.setAttribute(\"stroke-width\",enRuta?\"2.5\":\"1\");svg.appendChild(line);const mx=(pos[a][0]+pos[b][0])/2,my=(pos[a][1]+pos[b][1])/2;const txt=document.createElementNS(\"http://www.w3.org/2000/svg\",\"text\");txt.setAttribute(\"x\",mx);txt.setAttribute(\"y\",my-3);txt.setAttribute(\"text-anchor\",\"middle\");txt.setAttribute(\"font-size\",\"10\");txt.setAttribute(\"fill\",\"#aaa\");txt.textContent=w+\"m\";svg.appendChild(txt);});Object.entries(pos).forEach(([name,[x,y]])=>{const enRuta=rutaActiva.includes(name);const c=document.createElementNS(\"http://www.w3.org/2000/svg\",\"circle\");c.setAttribute(\"cx\",x);c.setAttribute(\"cy\",y);c.setAttribute(\"r\",\"18\");c.setAttribute(\"fill\",enRuta?\"#EEEDFE\":\"#f5f5f7\");c.setAttribute(\"stroke\",enRuta?\"#534AB7\":\"#d0d0d0\");c.setAttribute(\"stroke-width\",enRuta?\"2\":\"1\");svg.appendChild(c);const t=document.createElementNS(\"http://www.w3.org/2000/svg\",\"text\");t.setAttribute(\"x\",x);t.setAttribute(\"y\",y+4);t.setAttribute(\"text-anchor\",\"middle\");t.setAttribute(\"font-size\",\"9\");t.setAttribute(\"font-weight\",\"500\");t.setAttribute(\"fill\",enRuta?\"#3C3489\":\"#666\");t.textContent=name.length>8?name.slice(0,8)+\"\\u2026\":name;svg.appendChild(t);});}\n");
        sb.append("function drawTree(){const svg=document.getElementById(\"tree-svg\");svg.innerHTML=\"\";const sorted=[...restaurantes].sort((a,b)=>b.c-a.c);if(!sorted.length)return;const W=600,nw=80,nh=32;const spacing=W/(sorted.length+1);sorted.forEach((r,i)=>{const x=spacing*(i+1)-nw/2,y=65;if(i>0){const px=spacing*i-nw/2+nw/2,py=65+nh/2;const line=document.createElementNS(\"http://www.w3.org/2000/svg\",\"line\");line.setAttribute(\"x1\",px);line.setAttribute(\"y1\",py);line.setAttribute(\"x2\",x+nw/2);line.setAttribute(\"y2\",y+nh/2);line.setAttribute(\"stroke\",\"#d0d0d0\");line.setAttribute(\"stroke-width\",\"1\");svg.appendChild(line);}const rect=document.createElementNS(\"http://www.w3.org/2000/svg\",\"rect\");rect.setAttribute(\"x\",x);rect.setAttribute(\"y\",y);rect.setAttribute(\"width\",nw);rect.setAttribute(\"height\",nh);rect.setAttribute(\"rx\",\"6\");rect.setAttribute(\"fill\",\"#EEEDFE\");rect.setAttribute(\"stroke\",\"#AFA9EC\");rect.setAttribute(\"stroke-width\",\"1\");svg.appendChild(rect);const t1=document.createElementNS(\"http://www.w3.org/2000/svg\",\"text\");t1.setAttribute(\"x\",x+nw/2);t1.setAttribute(\"y\",y+13);t1.setAttribute(\"text-anchor\",\"middle\");t1.setAttribute(\"font-size\",\"9\");t1.setAttribute(\"fill\",\"#3C3489\");t1.setAttribute(\"font-weight\",\"500\");t1.textContent=r.n.length>10?r.n.slice(0,10)+\"\\u2026\":r.n;svg.appendChild(t1);const t2=document.createElementNS(\"http://www.w3.org/2000/svg\",\"text\");t2.setAttribute(\"x\",x+nw/2);t2.setAttribute(\"y\",y+25);t2.setAttribute(\"text-anchor\",\"middle\");t2.setAttribute(\"font-size\",\"9\");t2.setAttribute(\"fill\",\"#534AB7\");t2.textContent=\"\\u2605 \"+r.c.toFixed(1);svg.appendChild(t2);});}\n");
        sb.append("function estadoBadge(e){const m={PENDIENTE:\"b-pend\",EN_PREPARACION:\"b-prep\",EN_CAMINO:\"b-cam\",ENTREGADO:\"b-ent\"};return`<span class=\"badge ${m[e]||'b-norm'}\">${e}</span>`;}\n");
        sb.append("function render(){document.getElementById(\"m-total\").textContent=pedidos.length;document.getElementById(\"m-cola\").textContent=colaVIP.length+colaNormal.length;document.getElementById(\"m-ent\").textContent=entregados;document.getElementById(\"flota-list\").innerHTML=repartidores.map(r=>`<div class=\"row\"><div class=\"rep-dot ${r.libre?'dot-libre':'dot-ocup'}\"></div><span style=\"font-size:13px;flex:1\">${r.nombre}</span><span style=\"font-size:12px;color:#888\">${r.libre?\"Libre\":\"Ocupado\"}</span></div>`).join(\"\");const next=colaVIP.length?colaVIP[0]:colaNormal.length?colaNormal[0]:null;const cp=document.getElementById(\"cola-preview\");if(next)cp.innerHTML=`<span style=\"font-size:13px\">#${next.id} <b>${next.cliente}</b> &rarr; ${next.barrio}</span><br><span style=\"font-size:12px;margin-top:4px;display:inline-block\">${next.prio>=4?'<span class=\"badge b-vip\">VIP</span>':'<span class=\"badge b-norm\">Normal</span>'} prio ${next.prio}</span>`;else cp.textContent=\"Cola vac\\u00eda\";const pl=document.getElementById(\"pedidos-list\");const activos=pedidos.filter(p=>p.estado!==\"ENTREGADO\");if(!activos.length)pl.innerHTML=`<p style=\"font-size:13px;color:#888\">Sin pedidos activos.</p>`;else pl.innerHTML=activos.map(p=>`<div class=\"row\"><span style=\"font-size:13px;flex:1\">#${p.id} <b>${p.cliente}</b> &rarr; ${p.barrio}</span>${estadoBadge(p.estado)}<span style=\"font-size:11px;color:#aaa;margin-left:4px\">prio ${p.prio}</span></div>`).join(\"\");const hv=document.getElementById(\"heap-vis\");if(!colaVIP.length)hv.innerHTML=`<p style=\"font-size:12px;color:#888;grid-column:1/-1\">Heap vac\\u00edo</p>`;else hv.innerHTML=colaVIP.map((p,i)=>`<div class=\"heap-cell\"><div class=\"heap-prio\">${p.prio}</div><div style=\"font-size:10px;color:#888\">#${p.id}</div><div style=\"font-size:9px;color:#aaa\">[${i}]</div></div>`).join(\"\");const hh=document.getElementById(\"hash-vis\");const buckets=Array(16).fill(null).map(()=>[]);pedidos.forEach(p=>buckets[Math.abs(p.id)%16].push(p));const occupied=buckets.map((b,i)=>b.length?{i,b}:null).filter(Boolean).slice(0,6);if(!occupied.length)hh.innerHTML=`<p style=\"font-size:12px;color:#888;grid-column:1/-1\">Tabla vac\\u00eda</p>`;else hh.innerHTML=occupied.map(({i,b})=>`<div class=\"hash-idx\">[${i}]</div><div class=\"hash-bucket\">${b.map(p=>`<span class=\"hash-node\">#${p.id}</span>`).join('<span style=\"font-size:11px;color:#aaa;padding:3px 2px\">&rarr;</span>')}</div>`).join(\"\");const rl=document.getElementById(\"ranking-list\");[...restaurantes].sort((a,b)=>b.c-a.c).forEach((r,i)=>{rl.innerHTML+=\"<div class='row'><span style='font-size:13px;color:#aaa;width:18px'>\"+(i+1)+\".</span><span style='font-size:13px;flex:1'>\"+r.n+\"</span><span style='font-size:13px;font-weight:600;color:#534AB7'>&#9733; \"+r.c.toFixed(1)+\"</span></div>\";});drawTree();drawGrafo();}\n");
        sb.append("function switchTab(t){document.querySelectorAll(\".tab\").forEach(b=>b.classList.remove(\"active\"));document.querySelectorAll(\".tab-panel\").forEach(p=>p.classList.remove(\"active\"));document.querySelector(`[onclick=\"switchTab('${t}')\"]`).classList.add(\"active\");document.getElementById(\"tab-\"+t).classList.add(\"active\");if(t===\"grafo\")drawGrafo();if(t===\"estructuras\")drawTree();}\n");
        // Datos iniciales sincronizados con Main.java
        sb.append("pedidos.push({id:101,cliente:\"Rut\",barrio:\"Norte\",prio:1,estado:\"PENDIENTE\"});\n");
        sb.append("pedidos.push({id:102,cliente:\"Santiago\",barrio:\"Maria Oriente\",prio:5,estado:\"PENDIENTE\"});\n");
        sb.append("pedidos.push({id:103,cliente:\"Nicole\",barrio:\"Esmeralda\",prio:2,estado:\"PENDIENTE\"});\n");
        sb.append("pedidos.push({id:104,cliente:\"Andres\",barrio:\"Pandiguando\",prio:4,estado:\"PENDIENTE\"});\n");
        sb.append("nextId=105;\n");
        sb.append("heapPush(colaVIP,pedidos[1]);heapPush(colaVIP,pedidos[3]);\n");
        sb.append("colaNormal.push(pedidos[0]);colaNormal.push(pedidos[2]);\n");
        sb.append("log(\"Sistema iniciado \u2014 mismos datos que Main.java\",\"ok\");\n");
        sb.append("render();\n");
    }
}