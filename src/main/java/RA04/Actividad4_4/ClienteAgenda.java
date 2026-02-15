package RA04.Actividad4_4;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;

public class ClienteAgenda {
    public static void main(String[] args) {
        try {
            // 1. Crear el cliente HTTP
            HttpClient cliente = HttpClient.newHttpClient();

            // 2. Definir los datos a modificar (Payload)

            // Simulamos el formato "text/plain" que enviaba el navegador:
            // Usamos \n para separar las líneas.
            String datosNuevos = "nombre=Pepe\ntelefono=999999999\nemail=pepe_modificado@mail.com";

            System.out.println("--- Conectando para actualizar contacto (PUT) ---");

            // 3. Construir la Petición
            // Al ser código Java, podemos elegir explícitamente el método PUT
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8000/agenda"))
                    .header("Content-Type", "text/plain")
                    .PUT(BodyPublishers.ofString(datosNuevos))
                    .build();

            // 4. Enviar y recibir respuesta
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());

            // 5. Mostrar resultado
            System.out.println("Código respuesta: " + response.statusCode());
            System.out.println("Cuerpo respuesta: " + response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}