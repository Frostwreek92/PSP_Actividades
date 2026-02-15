//package RA04.Actividad4_2;
//
//import java.io.*;
//
//import org.apache.commons.net.ftp.FTPClient;
//import org.apache.commons.net.ftp.FTPReply;
//import org.apache.commons.net.ftp.FTPFile;
//import org.apache.commons.net.ftp.FTP;
//
//public class MiFTPClient {
//
//    public static void main(String[] args) {
//
//        if (args.length < 1) {
//            System.out.println("ERROR: indicar como parámetros:");
//            System.out.println("servidor usuario(opcional) contraseña(opcional)");
//            System.exit(1);
//        }
//
//        String servidorFTP = args[0];
//        String usuario = (args.length >= 2) ? args[1] : "anonymous";
//        String password = (args.length >= 3) ? args[2] : "";
//
//        FTPClient clienteFTP = new FTPClient();
//
//        try {
//            clienteFTP.connect(servidorFTP, 2221);
//
//            int codResp = clienteFTP.getReplyCode();
//            if (!FTPReply.isPositiveCompletion(codResp)) {
//                System.out.printf("ERROR: Conexión rechazada (%d)\n", codResp);
//                return;
//            }
//
//            clienteFTP.enterLocalPassiveMode();
//            clienteFTP.setFileType(FTP.BINARY_FILE_TYPE);
//
//            if (clienteFTP.login(usuario, password)) {
//                System.out.println("INFO: Login correcto.");
//            } else {
//                System.out.println("ERROR: Login incorrecto.");
//                return;
//            }
//
//            // 1️⃣ Crear carpeta desdeJava
//            clienteFTP.makeDirectory("desdeJava");
//            System.out.println("Carpeta 'desdeJava' creada.");
//
//            // 2️⃣ Entrar en la carpeta
//            clienteFTP.changeWorkingDirectory("desdeJava");
//            System.out.println("Directorio actual: " + clienteFTP.printWorkingDirectory());
//
//            // 3️⃣ Crear y subir fichero
//            String contenido = "texto";
//            InputStream input = new ByteArrayInputStream(contenido.getBytes());
//
//            boolean subidaOK = clienteFTP.storeFile("desdeElPrograma.txt", input);
//            input.close();
//
//            if (subidaOK)
//                System.out.println("Fichero subido correctamente.");
//
//            // 4️⃣ Recuperar fichero y mostrar contenido
//            ByteArrayOutputStream output = new ByteArrayOutputStream();
//            boolean descargaOK = clienteFTP.retrieveFile("desdeElPrograma.txt", output);
//
//            if (descargaOK) {
//                String textoRecuperado = output.toString();
//                System.out.println("Contenido recuperado:");
//                System.out.println(textoRecuperado);
//            }
//
//            output.close();
//
//            // 5️⃣ Listar contenido carpeta actual
//            System.out.println("\nContenido carpeta actual:");
//            listarContenido(clienteFTP);
//
//            // 6️⃣ Volver al directorio padre
//            clienteFTP.changeToParentDirectory();
//            System.out.println("\nVolvemos al directorio padre: "
//                    + clienteFTP.printWorkingDirectory());
//
//            // 7️⃣ Listar contenido carpeta padre
//            System.out.println("\nContenido carpeta padre:");
//            listarContenido(clienteFTP);
//
//            clienteFTP.logout();
//
//        } catch (IOException e) {
//            System.out.println("ERROR: conectando al servidor");
//            e.printStackTrace();
//        } finally {
//            try {
//                clienteFTP.disconnect();
//                System.out.println("INFO: conexión cerrada.");
//            } catch (IOException e) {
//                System.out.println("AVISO: no se pudo cerrar la conexión.");
//            }
//        }
//    }
//
//    // Método auxiliar para listar contenido
//    private static void listarContenido(FTPClient clienteFTP) throws IOException {
//        FTPFile[] archivos = clienteFTP.listFiles();
//
//        for (FTPFile f : archivos) {
//            String tipo = "";
//
//            if (f.isDirectory())
//                tipo = "/";
//            else if (f.isSymbolicLink())
//                tipo = " -> " + f.getLink();
//
//            System.out.println(f.getName() + tipo);
//        }
//    }
//}
