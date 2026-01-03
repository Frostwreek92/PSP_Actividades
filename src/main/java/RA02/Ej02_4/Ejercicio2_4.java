package RA02.Ej02_4;

class MiTareaBloques implements Runnable {
    private int hilo_id;
    private int trabajos = 40;
    private int hilos = 4;

    public MiTareaBloques(int id) {
        this.hilo_id = id;
    }

    public void run() {
        try {
            int base = trabajos / hilos;
            int resto = trabajos % hilos;

            int inicio = hilo_id * base + Math.min(hilo_id, resto);
            int fin = inicio + base + (hilo_id < resto ? 1 : 0);

            for (int i = inicio; i < fin; i++) {
                Thread.sleep(500);
                System.out.println("hilo: " + hilo_id + ", iteración nº: " + (i + 1));
            }

        } catch (InterruptedException e) {
            System.out.println("Can't wait");
        }
    }
}

public class Ejercicio2_4 {
    public static void main(String[] args) {
        // Crear las tareas
        Runnable tarea0 = new MiTareaBloques(0);
        Runnable tarea1 = new MiTareaBloques(1);
        Runnable tarea2 = new MiTareaBloques(2);
        Runnable tarea3 = new MiTareaBloques(3);

        // Crear los hilos
        Thread hilo0 = new Thread(tarea0);
        Thread hilo1 = new Thread(tarea1);
        Thread hilo2 = new Thread(tarea2);
        Thread hilo3 = new Thread(tarea3);

        // Iniciar los hilos
        hilo0.start();
        hilo1.start();
        hilo2.start();
        hilo3.start();

        System.out.println("Programa finalizado");
    }
}
