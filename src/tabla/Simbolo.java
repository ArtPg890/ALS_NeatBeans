package tabla;

/**
 * Clase que guarda los datos de los símbolos en la tabla de símbolos
 *
 * @author Juan José Silva López
 * @author Arturo Guzmán Avila
 * @version 1.0
 */
public class Simbolo {

    private String tipo, nombre, id;
    private Object valor;
    private TablaDeSimbolos tabla;

    /**
     * Crea un nuevo símbolo
     *
     * @param tipo Tipo de token
     * @param nombre Nombre
     * @param id Identificador
     * @param valor Valor
     * @param tabla Tabla
     */
    public Simbolo(String tipo, String nombre, String id, Object valor, TablaDeSimbolos tabla) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.id = id;
        this.valor = valor;
        this.tabla = tabla;
    }

    /**
     * Retorna el tipo de símbolo
     *
     * @return String que contienen el tipo de símbolo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Retorna el nombre de símbolo
     *
     * @return String que contienen el nombre de símbolo
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna el id de símbolo
     *
     * @return String que contienen el id de símbolo
     */
    public String getId() {
        return id;
    }

    /**
     * Retorna la tabla de símbolo
     *
     * @return String que contienen la tabla de símbolo
     */
    public TablaDeSimbolos getTabla() {
        return tabla;
    }

    /**
     * Define la tabla de símbolos de un símbolo
     * 
     * @param tabla Tabla de símbolos a definir
     */
    public void setTabla(TablaDeSimbolos tabla) {
        this.tabla = tabla;
    }

}
