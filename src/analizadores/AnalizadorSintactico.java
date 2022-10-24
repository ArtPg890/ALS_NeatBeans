package analizadores;

import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;
import tabla.Simbolo;
import tabla.TablaDeSimbolos;
import datos.Error;
import java.util.Vector;

/**
 * @author Juan José Silva López
 * @author Arturo Guzmán Ávila
 * @version 1.0
 */
public class AnalizadorSintactico {

    private final String[] terminales = new String[]{"id", "int", "float", "char", ",", ";", "=", "+", "-", "*", "/", "(", ")", "num", "$", "P", "Tipo", "V", "A", "Exp", "E", "Term", "T", "F"};

    private final String[] Producciones = new String[]{"P", "Tipo id V", "A", "int", "float", "char", ", id V", "; P", "id = Exp ;", "Term E", "+ Term E", "- Term E", " ", "F T", "* FT", "/ F T", " ", "id", "( Exp )", "num"};

    private final String[] noTerminales = new String[]{"P'", "P", "P", "Tipo", "Tipo", "Tipo", "V", "V", "A", "Exp", "E", "E", "E", "Term", "T", "T", "T", "F", "F", "F"};

    private final String[] esperas = new String[]{"id, int, float, char", " ", "id", " ", "id", "id", "id", "=", ", ;", "id + - ( num", " ", "id", "id int float char", ";", ";", "id ( num", "id ( num", "= * /", "= * /", "id + - ( num", "; * /", ", ;", " ", " ", ";", ";", ";", ";", "id ( num", "id ( num", ")", ")", "id ( num", "id ( num", "* / )", "* / )", "id + - ( num", "* / )", " ", ";", ";", "; * /", "; * /", "; * /", ")", ")", ")", ")", "id num", "id num", ")", ";", ";", ")", ")", "* / )", "* / )", "* / )", ")", ")"};

    private final String[][] tabla = new funciones.LeerTabla().get();

    private int numeroTabla;

    private String tipo;

    private StringTokenizer st;

    private Vector<String> pila, entrada, accion;

    private Stack<String> pilaPrincipal;

    private TablaDeSimbolos tablaDeSimbolos, tablaActual, aux;

    private Analizador_Semantico semantico;
    
    int fila, columna;

    String tope, celda, error;

    /**
     * Método constructor que inicializa las estructuras de datos necesarias
     * para la ejecución del programa
     */
    public AnalizadorSintactico() {
        pila = new Vector<>();
        accion = new Vector<>();
        entrada = new Vector<>();
        pilaPrincipal = new Stack<>();
        pilaPrincipal.push("$");
        pilaPrincipal.push("0");
        numeroTabla = 0;
        semantico = new Analizador_Semantico();
    }

    /**
     * Método desde el que se realiza el análisis sintáctico
     *
     * @param t Objeto de la clase Token que contiene el token, número de línea
     * y lexema.
     */
    public void analisisSintactico(Token t) {

        do {
            mostrarPila();
            System.out.println(t.obtenerToken());
            fila = obtenerfila();
            columna = obtenercolumna(t.obtenerToken());
            celda = tabla[fila][columna];
            pila.add(obtenerPila());
            entrada.add(t.obtenerToken());
            System.out.println(celda);
            if (celda.equals("")) {
                System.out.println("Esperaba un: " + esperas[fila]);
                error = esperas[fila];
                return;
            }
            if (celda.equals("acc")) {
                accion.add("Se acepta la cadena");
                return;
            }
            if (celda.startsWith("s")) {
                accion.add("desplazar " + t.obtenerToken() + "" + celda.substring(1, celda.length()));
                pilaPrincipal.add(t.obtenerToken());
                pilaPrincipal.add(celda.substring(1, celda.length()));
                semantico.analizadorSemantico(t);
                if (semantico.getError() != null)
                    error = semantico.getError();
                return;
            }
            if (celda.startsWith("r")) {
                int prod, ele, cima;
                prod = Integer.parseInt(celda.substring(1, celda.length()));
                accion.add("reducir " + noTerminales[prod] + " " + Producciones[prod]);
                StringTokenizer numele = new StringTokenizer(Producciones[prod], " ");
                ele = numele.countTokens();
                if (ele == 1 && numele.nextToken() == "") {
                    ele = 0;
                }
                for (int i = 0; i < ele; i++) {
                    pilaPrincipal.pop();
                    pilaPrincipal.pop();
                }
                System.out.println("Cima" + pilaPrincipal.peek());
                cima = Integer.parseInt(pilaPrincipal.peek());
                System.out.println("Correcto");
                System.out.println(cima + ", " + obtenercolumna(noTerminales[prod]));
                pilaPrincipal.add(noTerminales[prod]);
                pilaPrincipal.add(tabla[cima][obtenercolumna(noTerminales[prod])]);
                continue;
            }
        } while (true);

    }

    public String getError() {
        return error;
    }

    private int obtenerfila() {
        return Integer.parseInt(pilaPrincipal.peek());
    }

    private int obtenercolumna(String entrada) {
        for (int i = 0; i < terminales.length; i++) {
            if (terminales[i].equals(entrada)) {
                return i;
            }
        }

        return -1;
    }

    private String obtenerPila() {
        String pila = "";
        for (int i = 0; i < pilaPrincipal.size(); i++) {
            pila += pilaPrincipal.get(i) + " ";
        }
        return pila;
    }

    public Vector<Vector<String>> obtenerTabla() {
        Vector<Vector<String>> tabla = new Vector<>();
        tabla.add(pila);
        tabla.add(entrada);
        tabla.add(accion);
        return tabla;
    }
    private void mostrarPila(){
        for (int i = 0; i < pilaPrincipal.size(); i++) {
            String get = pilaPrincipal.get(i);
            System.out.print (get + " ");
        }
        System.out.println("");
    }
}
