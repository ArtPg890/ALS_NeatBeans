package datos;

/**
 * Clase utilizada para guardar la información de un error léxico o sintáctico
 *
 * @author Juan José Silva López
 * @author Arturo Guzmán Ávila
 */
public class Error {

    private int linea;
    private String tipo, descripcion;

    /**
     * Definición de un error
     *
     * @param linea número de línea donde se encontró el error
     * @param tipo tipo de error
     * @param descripcion descripción del error (ya sea léxico o sintáctico)
     */
    public Error(int linea, String tipo, String descripcion) {
        this.linea = linea;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    /**
     * Retorna el número de línea donde se generó el error
     *
     * @return número de línea donde se generó el error
     */
    public int getLinea() {
        return linea;
    }

    /**
     * Retorna el tipo de error que se genero
     *
     * @return cadena que contiene el tipo de error que se genero
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Retorna la descripción del error
     *
     * @return String que contiene la descripción del error
     */
    public String getDescripcion() {
        return descripcion;
    }
}
