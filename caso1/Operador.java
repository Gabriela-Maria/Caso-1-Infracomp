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

    @Override
    public void run(){
        while (continuar){
            if (depoDistribucion==null) {
                // producto de DepoProduccion a Cinta
                String producto = depoProdACinta();
                if (producto.contains("FIN_")){
                    finalizados++;
                }
                if (finalizados ==4){
                    continuar = false;
                }

            } else if (depoProduccion==null) {
                // producto de Cinta a DepoDistribucion
                String producto = cintaADepoDist();
                if (producto.contains("FIN_")){
                    finalizados++;
                }
                if (finalizados ==4){
                    continuar=false;
                }
    
            }
        }

        System.out.println("Operador ha finalizado su ejecucion");
    }

    private String depoProdACinta() {
        String producto = depoProduccion.retirar(); 
        System.out.println("Operador 1                                      depP->cinta(" + producto + ")" );
        cinta.agregar(producto);
        return producto;
       
    }

    private String cintaADepoDist() {
        String producto = cinta.retirar();
        System.out.println("Operador 2                                      cinta->depD(" + producto + ")" );
        depoDistribucion.agregar(producto);
        return producto;
        
    }
}
