public class Principal {
    public static void main(String[] args) {
        int numProductos = 100;
        int capDepProd = 50;
        int capDepDist = 60;

        DepoProduccion depoProduccion = new DepoProduccion(capDepProd);
        DepoDistribucion depoDistribucion = new DepoDistribucion(capDepDist);
        Cinta cinta = new Cinta();

        // Crear productores
        Productor productorA1 = new Productor("A", numProductos, depoProduccion);
        Productor productorA2 = new Productor("A", numProductos, depoProduccion);
        Productor productorB1 = new Productor("B", numProductos, depoProduccion);
        Productor productorB2 = new Productor("B", numProductos, depoProduccion);

        // Crear distribuidores
        Distribuidor distribuidorA1 = new Distribuidor("A", depoDistribucion);
        Distribuidor distribuidorA2 = new Distribuidor("A", depoDistribucion);
        Distribuidor distribuidorB1 = new Distribuidor("B", depoDistribucion);
        Distribuidor distribuidorB2 = new Distribuidor("B", depoDistribucion);

        // Crear operadores
        Operador operadorProd = new Operador(depoProduccion, null, cinta);
        Operador operadorDist = new Operador(null, depoDistribucion, cinta);

        // Iniciar hilos
        productorA1.start();
        productorA2.start();
        productorB1.start();
        productorB2.start();

        distribuidorA1.start();
        distribuidorA2.start();
        distribuidorB1.start();
        distribuidorB2.start();

        operadorProd.start();
        operadorDist.start();
    }
}
