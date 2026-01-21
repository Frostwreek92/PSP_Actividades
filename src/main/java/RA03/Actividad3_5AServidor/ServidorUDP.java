package RA03.Actividad3_5AServidor;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServidorUDP {
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
                System.out.println("Desde: " + paquete.getAddress());

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
