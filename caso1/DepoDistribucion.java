import java.util.ArrayList;

public class DepoDistribucion {
    private Integer capDepDist;
    private ArrayList<String> productos = new ArrayList<>();

    DepoDistribucion(Integer EcapDepDist){
        this.capDepDist = EcapDepDist;
    }

    public synchronized void agregar(String producto){
        //si esta lleno
        while (productos.size() >= capDepDist) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //si hay espacio
        productos.add(producto);
        System.out.println("Producto agregado al DepoDistribucion: " + producto);
        notifyAll();
    }

    public synchronized String retirar(){
        //si esta vacio
        while (productos.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //si hay elementos, se quita el primero de la lista
        String producto = productos.remove(0);
        notifyAll();
        return producto;
    }
}
