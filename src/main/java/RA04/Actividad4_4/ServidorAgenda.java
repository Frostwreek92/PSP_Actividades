package RA04.Actividad4_4;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class ServidorAgenda {
    public static void main(String[] args) throws IOException {
        // 1. Crear el servidor en el puerto 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // 2. Asignar el manejador a la ruta "/agenda"
        server.createContext("/agenda", new HandlerAgenda());

        // 3. Arrancar el servidor
        server.setExecutor(null);
        server.start();

        System.out.println("Servidor iniciado en http://localhost:8000/agenda");
    }
}