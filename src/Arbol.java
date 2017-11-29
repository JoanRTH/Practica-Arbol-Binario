public class Arbol {
    
    private NodosArbol raiz;
    public Arbol(){
        raiz=null;
    }
    
    public NodosArbol ObtRaiz(){
        return raiz;
    }
    //para buscar
    public NodosArbol busqueda(int d, NodosArbol r){
        if (raiz==null){
            return null;
        }else if(r.dato==d){
            return r;
        }else if (r.dato<d) {
            return busqueda(d, r.HJDer);
        }else{
            return busqueda(d, r.HJIzqu);
        }
    }
    
    //Obtener factor Equilibrio
    public int ObtenerFE(NodosArbol W){
        if (W == null){
        return -1;
        }else{
            return W.facEqu;
        }
    }
    
    //Hacer la Rotacion simple hacia la Izq
    public NodosArbol RotacionIzq(NodosArbol T){
        NodosArbol Aux = T.HJIzqu;
        T.HJIzqu = Aux.HJDer;
        Aux.HJDer = T;
        T.facEqu = Math.max(ObtenerFE(T.HJIzqu), ObtenerFE(T.HJDer))+1;
        Aux.facEqu = Math.max(ObtenerFE(Aux.HJIzqu), ObtenerFE(Aux.HJDer))+1;
        return Aux;
    }
    
    //Hacer la Rotacion Simple hacia la Der
    public NodosArbol RotacionDer(NodosArbol T){
        NodosArbol Aux = T.HJDer;
        T.HJDer = Aux.HJIzqu;
        Aux.HJIzqu = T;
        T.facEqu = Math.max(ObtenerFE(T.HJIzqu), ObtenerFE(T.HJDer))+1;
        Aux.facEqu = Math.max(ObtenerFE(Aux.HJIzqu), ObtenerFE(Aux.HJDer))+1;
        return Aux;
    }
    
    //rotacion doble Hacia la Izquierda
    public NodosArbol RotacionDobleIzqu(NodosArbol T){
        NodosArbol temp;
        T.HJIzqu = RotacionDer(T.HJIzqu);
        temp = RotacionIzq(T);
        return temp;
    }
    
    //rotacion doble Hacia la Derecha
    public NodosArbol RotacionDobleDere(NodosArbol T){
        NodosArbol temp;
        T.HJDer = RotacionIzq(T.HJDer);
        temp = RotacionDer(T);
        return temp;
    }
    
    //Metodo para ingresar de forma balanceada
    public NodosArbol InsertarAVL(NodosArbol Nuevo, NodosArbol SubArbol){
        NodosArbol NuevoPadre = SubArbol;
        if(Nuevo.dato<SubArbol.dato){
            if (SubArbol.HJIzqu==null){
                SubArbol.HJIzqu=Nuevo;
            }else{
                SubArbol.HJIzqu=InsertarAVL(Nuevo, SubArbol.HJIzqu);
                if ((ObtenerFE(SubArbol.HJIzqu) - ObtenerFE(SubArbol.HJDer)==2)) {
                    if (Nuevo.dato<SubArbol.HJIzqu.dato){
                        NuevoPadre=RotacionIzq(SubArbol);
                    }else{
                        NuevoPadre=RotacionDer(SubArbol);
                    } 
                }
            } 
        }else if(Nuevo.dato>SubArbol.dato){
            if (SubArbol.HJDer==null) {
                SubArbol.HJDer=Nuevo;
            }else{
                SubArbol.HJDer=InsertarAVL(Nuevo, SubArbol.HJDer);
                if ((ObtenerFE(SubArbol.HJDer) - ObtenerFE(SubArbol.HJIzqu)==2)) {
                    if(Nuevo.dato>SubArbol.HJIzqu.dato){
                        NuevoPadre=RotacionDer(SubArbol);
                        
                    }else{
                        NuevoPadre=RotacionDobleDere(SubArbol);
                    }
                }
            }
        }else {
            System.out.println("EL nodo es Duplicado");
        }
        //Actualizando Altura
        if ((SubArbol.HJIzqu==null)&& (SubArbol.HJDer!=null)){
           SubArbol.facEqu=SubArbol.HJDer.facEqu+1;
        }else if((SubArbol.HJDer== null) && (SubArbol.HJIzqu!= null)) {
            SubArbol.facEqu=SubArbol.HJIzqu.facEqu+1;
        }else{
            SubArbol.facEqu=Math.max(ObtenerFE(SubArbol.HJIzqu), ObtenerFE(SubArbol.HJDer)) +1;
        }
            return NuevoPadre;
    }
    
    //Metodo para insertar
    public void insertar(int d){
        NodosArbol nuevo=new NodosArbol(d);
        if(raiz==null){
            raiz=nuevo;
        }else{
            raiz=InsertarAVL(nuevo, raiz);
        }
    }
    
    //recorridos
    //Método para recorrer el árbol InOrden
    public void inOrden(NodosArbol r){
        if(r!=null){
            inOrden(r.HJIzqu);
            System.out.println(r.dato + ", ");
            inOrden(r.HJDer);
        }
    }

    //Método para recorrer el árbol PreOrden
    public void preOrden(NodosArbol r){
        if(r!=null){
            System.out.println(r.dato + ", ");
            preOrden(r.HJIzqu);
            preOrden(r.HJDer);
        }
    }

    //Método para recorrer el árbol PostOrden
    public void postOrden(NodosArbol r){
        if(r!=null){
            postOrden(r.HJIzqu);
            postOrden(r.HJDer);
            System.out.println(r.dato + ", ");
        }
    }
       
}
     
