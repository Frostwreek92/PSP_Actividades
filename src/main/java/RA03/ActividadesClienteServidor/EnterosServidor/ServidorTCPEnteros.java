package RA03.ActividadesClienteServidor.EnterosServidor;

import java.io.*;
import java.net.*;

public class ServidorTCPEnteros {
    public static void main(String[] args) throws Exception {
        ServerSocket servidor = new ServerSocket(5000);
        System.out.println("Servidor TCP esperando...");

        Socket cliente = servidor.accept();
        DataInputStream in = new DataInputStream(cliente.getInputStream());

        int numero = in.readInt();
        System.out.println("Número recibido: " + numero);

        cliente.close();
        servidor.close();
    }
}
