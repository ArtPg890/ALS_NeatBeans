package datos;

import java.util.ArrayList;

/**
 * Clase usada para almacenar los errores léxicos y sintácticos que se vayan
 * identificando
 *
 * @author Juan José Silva López
 * @author Arturo Guzmán Ávila
 */
public class TablaErrores {

    private ArrayList<Error> tablaErrores;

    /**
     * Se inicializa la tabla de errores
     */
    public TablaErrores() {
        tablaErrores = new ArrayList<>();
    }

    /**
     * Retorna la cantidad de errores encontrados
     *
     * @return cantidad de errores encontrados
     */
    public int size() {
        return tablaErrores.size();
    }

    /**
     * Añade un error al final de la tabla de errores
     *
     * @param e objeto instanciado de la clase "Error" que contiene la
     * información del error;
     */
    public void addError(Error e) {
        tablaErrores.add(e);
    }

    /**
     * Retorna el contenido de la tabla de errores
     * @return String que contiene cada elemento de la tabla errores, separados por un salto de línea
     */
    public String contenido() {
        String content = "";
        for (Error e : tablaErrores) {
            content += "*Error " + e.getTipo() + " en la línea " + e.getLinea() + ": " + e.getDescripcion() + "\n";
        }
        return content;
    }

}
