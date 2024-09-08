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

            // producto de DepoProduccion a Cinta
            if (depoDistribucion==null) {

                // intenta retirar producto de DepoProduccion 
                String producto = depoProduccion.retirar();  
                while(producto==null){
                     Thread.yield();
                     producto = depoProduccion.retirar();  // Vuelve a intentar retirar

                    }

                System.out.println("Operador 1                                      depP->cinta(" + producto + ")");

                // intenta agregar producto a la Cinta
                boolean agregado = cinta.agregar(producto);
                while(!agregado){
                        Thread.yield();
                        agregado = cinta.agregar(producto);  // Vuelve a intentar agregar

                }

                if (producto.contains("FIN_")) finalizados++;
                
                if (finalizados ==4) continuar = false; //cuando todos los productos finales pasen a la Cinta


            } 

            // producto de Cinta a DepoDistribucion
            else if (depoProduccion==null) {

                // intenta retirar producto de la Cinta
                String producto = cinta.retirar();
                while(producto==null){
                        Thread.yield();
                        producto = cinta.retirar();  // Vuelve a intentar retirar

                } 

                System.out.println("Operador 2                                      cinta->depD(" + producto + ")" );
                
                // intenta agrega a DepoDistribucion
                boolean agregado = depoDistribucion.agregar(producto);
                while(!agregado){
                    Thread.yield();   
                    agregado = depoDistribucion.agregar(producto);  // Vuelve a intentar agregar

                }

                if (producto.contains("FIN_")) finalizados++;
                
                if (finalizados == 4) continuar=false; // cuando todos los productos finales pasen a DepoDistribucion
                
    
            }
        }

        System.out.println("Operador ha finalizado su ejecucion");
    }
}
