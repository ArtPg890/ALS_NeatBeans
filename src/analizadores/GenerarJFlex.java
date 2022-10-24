package analizadores;

import java.io.File;

/**
 * Clase usada para generar la clase "Lexer.java" en caso de que se le haga una modificaciòn
 * @author Juan José Silva López
 * @author Arturo Guzmán Ávila
 * @version 1.0
 */
public class GenerarJFlex {
    public static void main(String[] args) {
        JFlex.Main.generate(new File("src/analizadores/Lexer.flex"));
    }
}
