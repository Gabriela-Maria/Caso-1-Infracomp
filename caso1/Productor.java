public class Productor extends Thread{
    private String tipo;
    private Integer numProductos;
    private Integer numProdActual =0;
    private DepoProduccion deposito;

    Productor(String Etipo, Integer EnumProductos, DepoProduccion Edeposito){
        this.tipo= Etipo;
        this.numProductos= EnumProductos;
        this.deposito = Edeposito;
    }

    public void run(){
        while (numProdActual< numProductos){
            //creacion del producto
            //ej: "A0", "B67", donde el numero es el numero de productos creados hasta el momento por el productor
            numProdActual++;
            String producto = tipo+numProdActual;
            System.out.println("Productor " + tipo + " produjo: " + producto);
            deposito.agregar(producto);
            
        }
        String ultimoProducto = "FIN_" + tipo;
        System.out.println("Productor " + tipo + " produjo: " + ultimoProducto);
        deposito.agregar(ultimoProducto);
        

    }

}
