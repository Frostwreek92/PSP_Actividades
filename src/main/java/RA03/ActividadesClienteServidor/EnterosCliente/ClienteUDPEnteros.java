package RA03.ActividadesClienteServidor.EnterosCliente;

import java.net.*;

public class ClienteUDPEnteros {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();

        byte[] buffer = java.nio.ByteBuffer.allocate(4).putInt(50).array();

        InetAddress direccion = InetAddress.getByName("localhost");
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, direccion, 6000);

        socket.send(paquete);
        socket.close();
    }
}
