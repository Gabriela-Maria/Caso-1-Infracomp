public class Cinta {
    private String producto;

    public synchronized Boolean agregar(String producto) {
        //espera hasta que haya un producto
        if (this.producto != null) {
            return false;  // No se puede agregar, cinta ocupada
        }
        this.producto = producto;
        System.out.println("Cinta                                           agregar(" + producto + ")");
        notify();
        return true;

    }

    public synchronized String retirar(){
        //espera hasta que haya un producto
        if (this.producto == null) {
            return null;  // Cinta vacía
        }
        String prod = this.producto;

        System.out.println("Cinta                                           retirar(" + producto + ")");

        this.producto = null;

        //lo retira
        notify();
        return prod;
    }
}
