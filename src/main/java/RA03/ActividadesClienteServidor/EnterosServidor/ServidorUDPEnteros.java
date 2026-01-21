package RA03.ActividadesClienteServidor.EnterosServidor;

import java.net.*;

public class ServidorUDPEnteros {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(6000);

        byte[] buffer = new byte[4];
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);

        socket.receive(paquete);

        int numero = java.nio.ByteBuffer.wrap(buffer).getInt();
        System.out.println("Entero recibido: " + numero);

        socket.close();
    }
}

