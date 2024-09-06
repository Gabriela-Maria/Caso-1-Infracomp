public class Cinta {
    private String producto;

    public synchronized void agregar(String producto) {
        //espera hbasta que haya un producto
        while (this.producto != null) {
            Thread.yield();
            try {
                wait(100); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.producto = producto;
        System.out.println("Se agrego el producto "+ producto + " a la CINTA");
        notifyAll();
    }

    public synchronized String retirar(){
        //espera hasta que haya un producto
        while (this.producto == null) { 
            Thread.yield();
            try {
                wait(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String prod = this.producto;
        System.out.println("Se elimino el producto "+ producto + " de la CINTA");
        //lo retira
        this.producto = null;
        notifyAll();
        return prod;
    }
}
