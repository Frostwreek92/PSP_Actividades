package RA02.Ej02_5;

import java.util.Scanner;

public class Ejercicio2_5 {

    // ==== EJERCICIO 1: REPARTO POR BLOQUES ====
    static class HiloBloques extends Thread {
        private int hilo_id, trabajos, hilos;

        public HiloBloques(int hilo_id, int trabajos, int hilos) {
            this.hilo_id = hilo_id;
            this.trabajos = trabajos;
            this.hilos = hilos;
        }

        @Override
        public void run() {
            int base = trabajos / hilos;
            int resto = trabajos % hilos;

            // Calcular inicio y fin del bloque
            int inicio = hilo_id * base + Math.min(hilo_id, resto);
            int fin = inicio + base + (hilo_id < resto ? 1 : 0);

            for (int i = inicio; i < fin; i++) {
                System.out.println("Soy el hilo " + hilo_id +
                        ", digo el num " + i +
                        ", y el cuadrado es " + (i * i));
                try { Thread.sleep(50); } catch (InterruptedException e) {}
            }
        }
    }

    // ==== EJERCICIO 2: REPARTO CÍCLICO ====
    static class HiloCiclico extends Thread {
        private int idHilo, trabajos, hilos;

        public HiloCiclico(int idHilo, int trabajos, int hilos) {
            this.idHilo = idHilo;
            this.trabajos = trabajos;
            this.hilos = hilos;
        }

        @Override
        public void run() {
            for (int i = idHilo; i < trabajos; i += hilos) {
                System.out.println("Soy el hilo " + idHilo +
                        ", digo el num " + i +
                        ", y el cuadrado es " + (i * i));
                try { Thread.sleep(50); } catch (InterruptedException e) {}
            }
        }
    }

    // ==== PROGRAMA PRINCIPAL ====
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce la cantidad total de trabajo: ");
        int trabajos = sc.nextInt();

        System.out.print("Introduce el número de hilos: ");
        int hilos = sc.nextInt();

        System.out.println("\nCOMIENZA LA IMPLEMENTACIÓN POR BLOQUES");

        Thread[] hilosBloques = new Thread[hilos];
        for (int i = 0; i < hilos; i++) {
            hilosBloques[i] = new HiloBloques(i, trabajos, hilos);
            hilosBloques[i].start();
        }

        // Esperar a que terminen todos los hilos
        for (int i = 0; i < hilos; i++) {
            try { hilosBloques[i].join(); } catch (InterruptedException e) {}
        }

        System.out.println("\nCOMIENZA LA IMPLEMENTACIÓN CÍCLICA");

        Thread[] hilosCiclico = new Thread[hilos];
        for (int i = 0; i < hilos; i++) {
            hilosCiclico[i] = new HiloCiclico(i, trabajos, hilos);
            hilosCiclico[i].start();
        }

        // Esperar a que terminen todos los hilos
        for (int i = 0; i < hilos; i++) {
            try { hilosCiclico[i].join(); } catch (InterruptedException e) {}
        }

        System.out.println("FIN DEL PROGRAMA");

        sc.close();
    }
}
