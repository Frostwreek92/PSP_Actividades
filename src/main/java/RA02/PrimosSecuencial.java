package RA02;

public class PrimosSecuencial {

    public static boolean esPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        for (int i = 2; i < numero; i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        long inicio = System.currentTimeMillis(); // Tiempo inicial

        for (int i = 1; i <= 400000; i++) {
            boolean primo = esPrimo(i);
            // Descomenta la linea de abajo para imprimir los números por pantalla
            // System.out.println(i + (primo ? " es primo" : " no es primo"));
        }

        long fin = System.currentTimeMillis(); // Tiempo final
        long tiempo = fin - inicio;

        System.out.println("Tiempo total de ejecución (secuencial): " + tiempo + " milisegundos");
    }
}

