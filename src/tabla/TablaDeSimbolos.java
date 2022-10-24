package tabla;

import java.util.ArrayList;

public class TablaDeSimbolos {
    
    private int numeroDeTabla;
    private ArrayList<Simbolo> tablaDeSimbolos;
    private TablaDeSimbolos tablaPadre;

    public TablaDeSimbolos(TablaDeSimbolos tablaPadre, int numeroDeTabla) {
        this.tablaPadre = tablaPadre;
        this.numeroDeTabla = numeroDeTabla;
        tablaDeSimbolos = new ArrayList<>();
    }
    
    public void addSimbolo(Simbolo s){
        tablaDeSimbolos.add(s);
    }
    
    public TablaDeSimbolos obtenerTablaPadre(){
        return tablaPadre;
    }
    
    public void setTabla(TablaDeSimbolos tabla){
        tablaDeSimbolos.get(tablaDeSimbolos.size() - 1).setTabla(tabla);
    }
    
    public String obtenerTabla (TablaDeSimbolos tabla){
        String cont = "";
        for (Simbolo s : tabla.obtenerContenido()) {
            if (s.getTabla() == null)
                cont += tabs(tabla.getNumeroDeTabla()) + s.getTipo() + ", " + s.getId() + ", "  + s.getNombre() + "\n";
            else{
                cont += tabs(tabla.getNumeroDeTabla()) + s.getTipo() + ", " + s.getId() + ", "  + s.getNombre() + "\n";
                cont+= obtenerTabla(s.getTabla());
            }
        }
        return cont;
    }
    
    public String tabs(int num){
        String tabs = "";
        for (int i = num; i > 0; tabs += "\t", i--);
        return tabs;
    }
    
    public ArrayList<Simbolo> obtenerContenido(){
        return tablaDeSimbolos;
    }

    public int getNumeroDeTabla() {
        return numeroDeTabla;
    }
    
    public String getTipo(){
        return tablaDeSimbolos.get(tablaDeSimbolos.size() -1).getTipo();
    }
    
    public static void main(String[] args) {
        TablaDeSimbolos t1 = new TablaDeSimbolos(null, 0), t2 = new TablaDeSimbolos(null, 1);
        t1.addSimbolo(new Simbolo("String", "cad", "id", null, null));
        t2.addSimbolo(new Simbolo("int", "num", "id", null, null));
        t1.addSimbolo(new Simbolo("if", "if", "if", null, t2));
        System.out.println(t2.obtenerTabla(t1));
    }
}
