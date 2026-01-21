package RA03.ActividadesClienteServidor.EnterosCliente;

import java.io.*;
import java.net.*;

public class ClienteTCPDouble {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 5001);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        out.writeDouble(3.1416);

        socket.close();
    }
}

