package RA03.ActividadesClienteServidor.EnterosCliente;

import java.io.*;
import java.net.*;

public class ClienteTCPEnteros {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 5000);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        out.writeInt(25);

        socket.close();
    }
}

