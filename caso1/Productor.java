public class Productor extends Thread{
    private String tipo;
    private Integer numProductos;
    private Integer numProdActual =1;
    private DepoProduccion deposito;

    Productor(String Etipo, Integer EnumProductos, DepoProduccion Edeposito){
        this.tipo= Etipo;
        this.numProductos= EnumProductos;
        this.deposito = Edeposito;
    }

    @Override
    public void run(){
        while (numProdActual< numProductos){
            //creacion del producto
            //ej: "A1", "B67", donde el numero es el numero de productos creados hasta el momento por el productor
           
            int id = Principal.getNextID();
            String producto = tipo+numProdActual;
            System.out.println("Productor                                       producir(" + producto + ")         ID : " + id);
            deposito.agregar(producto, id);
            numProdActual++;
        }
        
        String ultimoProducto = "FIN_" + tipo;
        int idFinal = Principal.getNextID();
        System.out.println("Productor                                       producir(" + ultimoProducto + ")      ID : " + idFinal);
        deposito.agregar(ultimoProducto, idFinal);
        System.out.println("Productor "+ tipo + " ha finalizado su ejecucion");
        

    }

}
