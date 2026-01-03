package RA02;

class TareaPrimos implements Runnable {
    private int inicio;
    private int fin;

    public TareaPrimos(int inicio, int fin) {
        this.inicio = inicio;
        this.fin = fin;
    }

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

    @Override
    public void run() {
        for (int i = inicio; i <= fin; i++) {
            boolean primo = esPrimo(i);
            // Descomenta la linea de abajo para imprimir los números por pantalla
            // System.out.println("[" + Thread.currentThread().getName() + "] " + i + (primo ? " es primo" : " no es primo"));
        }
    }
}

public class PrimosConcurrente {
    public static void main(String[] args) {
        long inicio = System.currentTimeMillis(); // Tiempo inicial

        // Crear hilos
        Thread h1 = new Thread(new TareaPrimos(1, 100000), "Hilo-1");
        Thread h2 = new Thread(new TareaPrimos(100001, 200000), "Hilo-2");
        Thread h3 = new Thread(new TareaPrimos(200001, 300000), "Hilo-3");
        Thread h4 = new Thread(new TareaPrimos(300001, 400000), "Hilo-4");

        // Iniciar hilos
        h1.start();
        h2.start();
        h3.start();
        h4.start();

        // Esperar a que terminen
        try {
            h1.join();
            h2.join();
            h3.join();
            h4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long fin = System.currentTimeMillis(); // Tiempo final
        long tiempo = fin - inicio;

        System.out.println("Tiempo total de ejecución (con hilos): " + tiempo + " milisegundos");
    }
}

