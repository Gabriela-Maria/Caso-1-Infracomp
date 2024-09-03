public class Operador extends Thread{
    private DepoProduccion depoProduccion;
    private Cinta cinta;
    private DepoDistribucion depoDistribucion;

    Operador(DepoProduccion EdepoProduccion, DepoDistribucion EdepoDistribucion, Cinta Ecinta){
        this.depoProduccion = EdepoProduccion;
        this.depoDistribucion = EdepoDistribucion;
        this.cinta = Ecinta;

    }

    public void run(){

    }

}
