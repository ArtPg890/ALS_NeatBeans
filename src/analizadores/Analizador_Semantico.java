/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analizadores;

/**
 *
 * @author Arturo
 */
public class Analizador_Semantico {

    private TablaDeTokens tablaSimbolos;

    private int tipoDato;
    
    private String error;

    public Analizador_Semantico() {
        tablaSimbolos = new TablaDeTokens();
        tipoDato = -1;
    }

    public void analizadorSemantico(Token t) {
        tipoDato = obtenerTipoDato(t.obtenerToken());
        
        if (tipoDato != -1 && t.obtenerToken().equals("id")){
            if (!tablaSimbolos.addToken(t))
                error = "Error semántico en la linea " + t.obtenerLinea() + ". El id " + t.obtenerNombre() + " fue declarado antes.";
            return;
        }
        
        if (t.obtenerToken().equals("id")){
            if (!tablaSimbolos.tokenDeclarado(t)){
                error = "Error semántico en la linea " + t.obtenerLinea() + ". El id " + t.obtenerNombre() + " no fue declarado antes.";
            }
            return;
        }
    }

    private int obtenerTipoDato(String token) {
        switch (token) {
            case "int":
                return 0;
            case "float":
                return 1;
            case "char":
                return 2;
            case ",": case "id":
                return tipoDato;        
        }
        return -1;
    }

    public String getError() {
        return error;
    }

}
