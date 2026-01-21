package RA03.ActividadesClienteServidor.EnterosServidor;

import java.io.*;
import java.net.*;

public class ServidorTCPDouble {
    public static void main(String[] args) throws Exception {
        ServerSocket servidor = new ServerSocket(5001);
        System.out.println("Servidor TCP esperando double...");

        Socket cliente = servidor.accept();
        DataInputStream in = new DataInputStream(cliente.getInputStream());

        double valor = in.readDouble();
        System.out.println("Double recibido: " + valor);

        cliente.close();
        servidor.close();
    }
}

