public class Operador extends Thread{
    private DepoProduccion depoProduccion;
    private Cinta cinta;
    private DepoDistribucion depoDistribucion;
    private Integer finalizados = 0;
    private Boolean continuar = true;

    Operador(DepoProduccion EdepoProduccion, DepoDistribucion EdepoDistribucion, Cinta Ecinta){
        this.depoProduccion = EdepoProduccion;
        this.depoDistribucion = EdepoDistribucion;
        this.cinta = Ecinta;
    }

    public void run(){
        while (continuar){
            if (depoDistribucion==null) {
                // producto de DepoProduccion a Cinta
                String producto = depoProdACinta();
                if (producto.contains("FIN_")){
                    finalizados++;
                }
                if (finalizados ==4){
                    System.out.println(finalizados+"---opProd");
                    continuar = false;
                }

            } else if (depoProduccion==null) {
                // producto de Cinta a DepoDistribucion
                String producto = cintaADepoDist();
                if (producto.contains("FIN_")){
                    finalizados++;
                }
                if (finalizados ==4){
                    System.out.println(finalizados+"---opDist");
                    continuar=false;
                }
    
            }
        }
    }

    private String depoProdACinta() {
        String producto = depoProduccion.retirar();
        cinta.agregar(producto);
        System.out.println("Operador movio de depo produccion a cinta: " + producto);
        return producto;
       
    }

    private String cintaADepoDist() {
        String producto = cinta.retirar();
        depoDistribucion.agregar(producto);
        System.out.println("Operador movio de cinta a depo distribucion: " + producto);
        return producto;
        
    }
}
