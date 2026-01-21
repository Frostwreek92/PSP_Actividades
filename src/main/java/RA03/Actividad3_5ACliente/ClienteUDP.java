package RA03.Actividad3_5ACliente;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteUDP {
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

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
