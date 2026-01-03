package RA02;

// Clase que implementa Runnable
class MiTarea implements Runnable {
    @Override
    public void run() {
        String nombreHilo = Thread.currentThread().getName();
        for (int i = 1; i <= 5; i++) {
            System.out.println("[" + nombreHilo + "] Contador: " + i);
            try {
                Thread.sleep(1000); // Simula trabajo
            } catch (InterruptedException e) {
                System.out.println("\n[" + nombreHilo + "] Interrumpido.");
            }
        }
        System.out.println("\n[" + nombreHilo + "] ha terminado su ejecución.");
    }
}

public class Ej01Runnable {
    public static void main(String[] args) {
        Thread[] hilos = new Thread[8];

        // Crear y lanzar 8 hilos
        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Thread(new MiTarea(), "Hilo-" + (i + 1));
            hilos[i].start();
        }

        // Bucle en el hilo principal
        for (int i = 1; i <= 5; i++) {
            System.out.println("\n[Hilo principal] Contador: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("\n[Hilo principal] Interrumpido.");
            }
        }

        // Esperar a que todos los hilos terminen
        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.out.println("\n[Hilo principal] Error al esperar el hilo.");
            }
        }
        System.out.println("\nTodos los hilos han terminado. Programa finalizado.");
    }
}

