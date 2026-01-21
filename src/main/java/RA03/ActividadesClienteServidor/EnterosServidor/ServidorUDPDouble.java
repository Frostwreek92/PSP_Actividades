package RA03.ActividadesClienteServidor.EnterosServidor;

import java.net.*;

public class ServidorUDPDouble {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(6001);

        byte[] buffer = new byte[8];
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);

        socket.receive(paquete);

        double valor = java.nio.ByteBuffer.wrap(buffer).getDouble();
        System.out.println("Double recibido: " + valor);

        socket.close();
    }
}

