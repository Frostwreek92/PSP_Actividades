package RA03.ActividadesClienteServidor.EnterosCliente;

import java.net.*;

public class ClienteUDPDouble {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();

        byte[] buffer = java.nio.ByteBuffer.allocate(8).putDouble(9.99).array();

        InetAddress direccion = InetAddress.getByName("localhost");
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, direccion, 6001);

        socket.send(paquete);
        socket.close();
    }
}

