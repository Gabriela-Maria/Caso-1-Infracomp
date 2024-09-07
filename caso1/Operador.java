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
                // De producto de DepoProduccion a Cinta

                // Retira de producto de DepoProduccion 
                String producto = depoProduccion.retirar();  
                while(producto==null){
                     Thread.yield();
                     producto = depoProduccion.retirar();  // Vuelve a intentar retirar

                    }

                System.out.println("Operador 1                                      depP->cinta(" + producto + ")");

                // Agrega a la cinta
                boolean agregado = cinta.agregar(producto);
                while(!agregado){
                        Thread.yield();
                        agregado = cinta.agregar(producto);  // Vuelve a intentar agregar

                }

                if (producto.contains("FIN_")){
                    finalizados++;
                }
                if (finalizados ==4){
                    continuar = false;
                }

            } else if (depoProduccion==null) {
                // producto de Cinta a DepoDistribucion

                // Retira de la cinta
                String producto = cinta.retirar();
                while(producto==null){
                        Thread.yield();
                        producto = cinta.retirar();  // Vuelve a intentar retirar

                } 

                System.out.println("Operador 2                                      cinta->depD(" + producto + ")" );
                // Agrega a deposito de distribucion
                boolean agregado = depoDistribucion.agregar(producto);
                while(!agregado){
                    Thread.yield();   
                    agregado = depoDistribucion.agregar(producto);  // Vuelve a intentar agregar

                }

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
}
