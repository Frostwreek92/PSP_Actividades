package RA03.Tema3_4B_Servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            while (true) {
                System.out.println("Esperando conexiones");
                Socket clientSocket = serverSocket.accept();
                InputStream inputStream = clientSocket.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
                String respuesta = in.readLine();
                System.out.println("Mensaje del cliente: " + respuesta);

                clientSocket.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

