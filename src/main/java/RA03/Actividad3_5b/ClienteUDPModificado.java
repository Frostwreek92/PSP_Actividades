package RA03.Actividad3_5b;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteUDPModificado {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();

            String mensaje = "Hola servidor UDP";
            byte[] buffer = mensaje.getBytes();

            InetAddress direccion = InetAddress.getByName("localhost");

            DatagramPacket paquete = new DatagramPacket(
                    buffer,
                    buffer.length,
                    direccion,
                    5000
            );

            socket.send(paquete);
            System.out.println("Mensaje enviado.");

            // Preparar para recibir respuesta
            byte[] bufferRespuesta = new byte[1024];
            DatagramPacket paqueteRespuesta =
                    new DatagramPacket(bufferRespuesta, bufferRespuesta.length);

            socket.receive(paqueteRespuesta);

            String respuesta = new String(
                    paqueteRespuesta.getData(),
                    0,
                    paqueteRespuesta.getLength()
            );

            System.out.println("Respuesta del servidor: " + respuesta);

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

