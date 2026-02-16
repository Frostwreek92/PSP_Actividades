package RA03.Tema3_4B_Cliente;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteTCP {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter out = new PrintWriter(outputStream,
                    true);
            out.println("Hola, patata!");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}