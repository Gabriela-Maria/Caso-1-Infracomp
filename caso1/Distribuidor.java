public class Distribuidor extends Thread{

    private String tipo;
    private DepoDistribucion deposito;
    private Boolean continuar = true;

    Distribuidor(String Etipo, DepoDistribucion Edeposito){
        this.tipo = Etipo;
        this.deposito = Edeposito;
    }

    public void run(){
        while (continuar){
            String producto = deposito.retirar(tipo);
            if (producto.equals("FIN_" + tipo)) {
                System.out.println("Distribuidor " + tipo + " recibió el producto de terminación: " + producto);
                continuar = false;
            }    
        }
    }
}
