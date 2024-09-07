import java.util.ArrayList;

public class DepoProduccion {
    private Integer capDepProd;
    private ArrayList<String> productos= new ArrayList<>();

    DepoProduccion(Integer EcapDepProd){
        this.capDepProd = EcapDepProd;
    }

    public synchronized void agregar(String producto, int id){
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
        System.out.println("DepoProduccion                                  agregar(" + producto + ")");

        notifyAll();
    }


    public synchronized String retirar(){
        if (productos.isEmpty()) {
            return null;
        }
        //si hay elementos, se quita el primero de la lista
        String producto = productos.remove(0);
        System.out.println("DepoProduccion                                  retirar(" + producto + ")" );
        notify(); 
        return producto;
    }

}
