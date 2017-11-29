public class Program {
    public static void main(String[] NOc) {
        Arbol Ar = new Arbol();
        Ar.insertar(10);
        Ar.insertar(5);
        Ar.insertar(13);
        Ar.insertar(1);
        Ar.insertar(6);
        Ar.insertar(17);
        
        Ar.preOrden(Ar.ObtRaiz());
      
        
    }
}
