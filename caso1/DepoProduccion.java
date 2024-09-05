import java.util.ArrayList;

public class DepoProduccion {
    private Integer capDepProd;
    private ArrayList<String> productos= new ArrayList<>();

    DepoProduccion(Integer EcapDepProd){
        this.capDepProd = EcapDepProd;
    }

    public synchronized void agregar(String producto){
        //si esta lleno
        while (productos.size() >= capDepProd) {  
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //si sigue con espacio
        productos.add(producto);
        System.out.println("producto agregado al Deposito de Produccion: " + producto);
        notifyAll();
    }

    // hacer semi activa
    public synchronized String retirar(){
        //si esta vacio
        while (productos.isEmpty()) {  
            Thread.yield();
            try {
                wait(100); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //si hay elementos, se quita el primero de la lista
        String producto = productos.remove(0);
        System.out.println("producto eliminado del Deposito de Produccion: " + producto);
        notifyAll();
        return producto;
    }

}
