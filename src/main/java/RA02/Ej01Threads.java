package RA02;

// Clase que extiende Thread
class MiTareaThread extends Thread {
    public MiTareaThread(String nombre) {
        super(nombre);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("[" + getName() + "] Contador: " + i);
            try {
                Thread.sleep(1000); // Simula trabajo
            } catch (InterruptedException e) {
                System.out.println("\n[" + getName() + "] Interrumpido.");
            }
        }
        System.out.println("\n[" + getName() + "] ha terminado su ejecución.");
    }
}

public class Ej01Threads {
    public static void main(String[] args) {
        MiTareaThread[] hilos = new MiTareaThread[8];

        // Crear y lanzar 8 hilos
        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new MiTareaThread("Hilo-" + (i + 1));
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
        for (MiTareaThread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.out.println("\n[Hilo principal] Error al esperar el hilo.");
            }
        }

        System.out.println("\nTodos los hilos han terminado. Programa finalizado.");
    }
}

