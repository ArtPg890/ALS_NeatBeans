package analizadores;

import java.util.ArrayList;

/**
 * Clase usada para almacenar los tokens
 *
 * @author Juan José Silva López
 * @author Arturo Guzmán Ávila
 */
public class TablaDeTokens {

    private ArrayList<Token> tablaDeTokens;

    /**
     * Metodo constructor que inicializa la tabla de tokens
     */
    public TablaDeTokens() {
        tablaDeTokens = new ArrayList<>();
    }

    /**
     * Añade un token al final del ArrayList<Token>
     *
     * @param t Objeto instanciado de la clase "Token" a añadir
     */
    public boolean addToken(Token t) {
        if (!tokenDeclarado(t)) {
            tablaDeTokens.add(t);
            return true;
        } else {
            return false;
        }

    }

    public boolean tokenDeclarado(Token t) {
        for (Token token : tablaDeTokens) {
            if (token.obtenerNombre().equals(t.obtenerNombre())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna el contenido del ArrayList<Token> tablaDeTokens
     *
     * @return String que contiene cada elemento de ArrayList<Token>
     * tablaDeTokens, separado por un salto de línea
     */
    public String contenido() {
        String cont = "";
        for (int i = 0; i < tablaDeTokens.size(); i++) {
            if (i == 0) {
                cont += tablaDeTokens.get(i).obtenerToken() + " ";
            } else if (tablaDeTokens.get(i).obtenerLinea() == tablaDeTokens.get(i - 1).obtenerLinea()) {
                cont += tablaDeTokens.get(i).obtenerToken() + " ";
            } else {
                cont += "\n" + tablaDeTokens.get(i).obtenerToken() + " ";
            }

        }
        return cont;
    }
}
