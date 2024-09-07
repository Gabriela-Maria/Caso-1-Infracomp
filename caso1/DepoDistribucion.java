import java.util.ArrayList;

public class DepoDistribucion {
    private Integer capDepDist;
    private ArrayList<String> productos = new ArrayList<>();

    DepoDistribucion(Integer EcapDepDist){
        this.capDepDist = EcapDepDist;
    }

    public synchronized Boolean agregar(String producto){
        //si esta lleno
        if (productos.size() >= capDepDist) {
            // Si el depósito está lleno, no se puede agregar
            return false;
        }
        //si hay espacio
        productos.add(producto);
        System.out.println("DepoDistribucion                                agregar(" + producto + ")");
        notify();  // Notifica a cualquier thread en espera
        return true;

    }

    public synchronized String retirar(String tipoProducto){

        //si esta vacio o si el producto no es el del tipo
        while (productos.isEmpty() || !(productos.get(0).contains(tipoProducto))) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //se quita el primero de la lista
        String producto = productos.remove(0);
        System.out.println("DepoDistribucion                                retirar(" + producto + ")");
        notifyAll();
        return producto;
    }
}
