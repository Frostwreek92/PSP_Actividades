package RA04.Actividad4_4;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class HandlerAgenda implements HttpHandler {
    // Nuestra "base de datos" en memoria
    private Map<String, String> agenda = new HashMap<>();

    @Override
    public void handle(HttpExchange t) throws IOException {
        String metodo = t.getRequestMethod();
        String respuesta = "";
        int codigo = 200;

        // Leer el cuerpo de la petición (lo que envía el cliente)
        InputStream is = t.getRequestBody();
        String body = new String(is.readAllBytes());

        // Log para ver en consola qué está llegando exactamente
        System.out.println("Petición: " + metodo + " | Cuerpo recibido: \n" + body);
        System.out.println("------------------------------------------------");

        switch (metodo) {
            case "GET":
                respuesta = "CONTACTOS REGISTRADOS:\n" + agenda.values().toString();
                break;

            case "POST":
                // Procesamos los datos que vienen línea a línea (nombre, telefono, email)
                String nombre = null, telefono = null, email = null;
                String[] lineas = body.split("\n"); // El formulario separa campos con saltos de línea

                for (String linea : lineas) {
                    String l = linea.trim(); // Quitamos espacios sobrantes
                    if (l.startsWith("nombre=")) nombre = l.substring(7);
                    if (l.startsWith("telefono=")) telefono = l.substring(9);
                    if (l.startsWith("email=")) email = l.substring(6);
                }

                if (nombre != null && !nombre.isEmpty()) {
                    // Formateamos los datos para guardarlos bonitos
                    String datosGuardados = "{ Nombre: " + nombre + " | Tlf: " + telefono + " | Email: " + email + " }";

                    agenda.put(nombre, datosGuardados);
                    respuesta = "Contacto guardado correctamente:\n" + datosGuardados;
                    codigo = 201; // Created
                } else {
                    respuesta = "Error: El campo 'nombre' es obligatorio para registrar.";
                    codigo = 400; // Bad Request
                }
                break;

            case "PUT":
                // Lógica de actualización. Buscamos el nombre en los datos recibidos.
                String nombrePut = null;
                String[] lineasPut = body.split("\n");
                for (String l : lineasPut) {
                    if (l.trim().startsWith("nombre=")) nombrePut = l.substring(7);
                }

                // Verificamos si existe antes de actualizar
                if (nombrePut != null && agenda.containsKey(nombrePut)) {
                    // Actualizamos parseando el texto plano recibido
                    String nuevosDatos = body.replace("nombre=", "{ Nombre: ")
                            .replace("telefono=", " | Tlf: ")
                            .replace("email=", " | Email: ")
                            .replace("\n", "") + " }";

                    agenda.put(nombrePut, nuevosDatos);
                    respuesta = "Contacto actualizado correctamente: " + nombrePut;
                } else {
                    respuesta = "Error: No se encuentra el contacto o falta el nombre.";
                    codigo = 404; // Not Found
                }
                break;

            default:
                respuesta = "Método no soportado por este servidor";
                codigo = 405; // Method Not Allowed
        }

        // Enviamos la respuesta al cliente
        t.sendResponseHeaders(codigo, respuesta.length());
        OutputStream os = t.getResponseBody();
        os.write(respuesta.getBytes());
        os.close();
    }
}