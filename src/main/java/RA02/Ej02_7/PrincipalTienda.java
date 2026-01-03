package RA02.Ej02_7;

// PrincipalTienda.java
public class PrincipalTienda {
    public static void main(String[] args) {
        Tienda tienda = new Tienda(10); // stock inicial de 10

        // Crear varios clientes (hilos)
        Cliente c1 = new Cliente("Cliente 1", tienda, 5);
        Cliente c2 = new Cliente("Cliente 2", tienda, 8);
        Cliente c3 = new Cliente("Cliente 3", tienda, 3);

        // Iniciar los hilos
        c1.start();
        c3.start();
        c2.start();
    }
}

// Tienda.java
class Tienda {
    private int stock;

    public Tienda(int stockInicial) {
        this.stock = stockInicial;
    }

    // Método sincronizado para evitar condiciones de carrera
    public synchronized void comprarProducto(String nombreCliente, int cantidad) {
        if (cantidad <= stock) {
            stock -= cantidad;
            System.out.println(nombreCliente + " compró " + cantidad + " productos.");
            System.out.println("Stock disponible: " + stock);
        } else {
            System.out.println(nombreCliente + " intentó comprar " + cantidad +
                    " productos, pero no hay suficiente stock.");
        }
    }
}

// Cliente.java
class Cliente extends Thread {
    private Tienda tienda;
    private int cantidad;

    public Cliente(String nombre, Tienda tienda, int cantidad) {
        super(nombre); // asigna el nombre del hilo (cliente)
        this.tienda = tienda;
        this.cantidad = cantidad;
    }

    @Override
    public void run() {
        tienda.comprarProducto(getName(), cantidad);
    }
}