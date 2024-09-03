public class Cinta {
    private String producto;

    public synchronized void agregar(String producto) {
        //espera hbasta que haya un producto
        while (this.producto != null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.producto = producto;
        notifyAll();
    }

    public synchronized String retirar(){
        //espera hasta que haya un producto
        while (this.producto == null) { 
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String prod = this.producto;
        //lo retira
        this.producto = null;
        notifyAll();
        return prod;
    }
}
