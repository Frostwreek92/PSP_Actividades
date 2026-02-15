package RA03.Actividad3_5b;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServidorUDPModificado {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(5000);
            System.out.println("Servidor UDP iniciado...");

            byte[] buffer = new byte[1024];

            while (true) {

                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                socket.receive(paquete);

                String mensaje = new String(paquete.getData(), 0, paquete.getLength());
                System.out.println("Mensaje recibido: " + mensaje);

                InetAddress direccionCliente = paquete.getAddress();
                int puertoCliente = paquete.getPort();

                System.out.println("Desde: " + direccionCliente + ":" + puertoCliente);

                // Crear mensaje de respuesta
                String respuesta = "Hola cliente "
                        + direccionCliente + ":" + puertoCliente;

                byte[] bufferRespuesta = respuesta.getBytes();

                DatagramPacket paqueteRespuesta = new DatagramPacket(
                        bufferRespuesta,
                        bufferRespuesta.length,
                        direccionCliente,
                        puertoCliente
                );

                socket.send(paqueteRespuesta);

                if (mensaje.equalsIgnoreCase("salir")) {
                    break;
                }
            }

            socket.close();
            System.out.println("Servidor cerrado.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
