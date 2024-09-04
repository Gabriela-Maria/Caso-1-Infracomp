public class Operador extends Thread{
    private DepoProduccion depoProduccion;
    private Cinta cinta;
    private DepoDistribucion depoDistribucion;
    private Integer finalizados = 0;

    Operador(DepoProduccion EdepoProduccion, DepoDistribucion EdepoDistribucion, Cinta Ecinta){
        this.depoProduccion = EdepoProduccion;
        this.depoDistribucion = EdepoDistribucion;
        this.cinta = Ecinta;

    }

    public void run(){
        while (true){
            if (depoDistribucion==null) {
                // producto de DepoProduccion a Cinta
                String producto = depoProdACinta();
                if (producto == null) {
                    Thread.yield();
                }
                if (producto.contains("FIN_")){
                    finalizados++;
                }
                if (finalizados ==4){
                    break;
                }
                System.out.println(finalizados+"---opProd");

    
            } else if (depoProduccion==null) {
                // producto de Cinta a DepoDistribucion
                String producto = cintaADepoDist();
                if (producto == null) {
                    Thread.yield();  
                }
                if (producto.contains("FIN_")){
                    finalizados++;
                }
                if (finalizados ==4){
                    break;
                }
                System.out.println(finalizados+"---opDist");

    
            }
        }
    }

    private String depoProdACinta() {
        String producto = depoProduccion.retirar();
        if (producto != null) {
            cinta.agregar(producto);
            System.out.println("Operador movio de depo produccion a cinta: " + producto);
            return producto;
        }
        //si no se pudo hacer
        return null;  
    }

    private String cintaADepoDist() {
        String producto = cinta.retirar();
        if (producto != null) {
            depoDistribucion.agregar(producto);
            System.out.println("Operador movio de cinta a depo distribucion: " + producto);
            return producto;
        }
        return null; 
    }
}
