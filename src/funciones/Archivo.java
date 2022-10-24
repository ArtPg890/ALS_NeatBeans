package funciones;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Clase utilizada para obtener información de un archivo, guardarlo, guardarlo
 * como...
 *
 * @author Juan José Silva López
 * @author Arturo Guzmán Ávila
 * @version 1.0
 */
public class Archivo {

    private File archivo;
    private JFrame ventanaPrincipal;
    private JFileChooser selectorArchivos;

    /**
     * Inicialización de los objetos necesarios para la ejecución
     * @param ventanaPrincipal 
     */
    public Archivo(JFrame ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        selectorArchivos = new JFileChooser();
        selectorArchivos.setFileFilter(new FileNameExtensionFilter("SG Files", "sg"));
    }

    /**
     * Abre el archivo seleccionado y retorna su contenido en forma de String
     * @return String que contienen la información del archivo
     */
    public String abrir() {
        if (selectorArchivos.showOpenDialog(ventanaPrincipal) == JFileChooser.APPROVE_OPTION) {
            archivo = selectorArchivos.getSelectedFile();
            return leerArchivo();
        } else {
            return null;
        }
    }

    /**
     * Determina si el archivo se guarda o no
     * @param txt Texto a escribir en el archivo
     * @return true: si el archivo fue guardado | false: si el archivo no fue guardado
     */
    public boolean guardar(String txt) {
        if (archivoSelecionado()) {
            return escribirArchivo(txt);
        } else {
            if (selectorArchivos.showSaveDialog(ventanaPrincipal) == JFileChooser.APPROVE_OPTION) {
                archivo = extencionValida();
                return escribirArchivo(txt);
            } else {
                return false;
            }
        }
    }

    /**
     * Determina si el archivo se guardó como...
     * @param txt Texto a escribir en el archivo
     * @return true: si el archivo fue guardado | false: si el archivo no fue guardado
     */
    public boolean guardarComo(String txt) {
        if (selectorArchivos.showSaveDialog(ventanaPrincipal) == JFileChooser.APPROVE_OPTION) {
            archivo = extencionValida();
            return escribirArchivo(txt);
        } else {
            return false;
        }
    }

    /**
     * Retorna el nombre del archivo actualmente abierto
     * @return String que contienen el nombre del archivo actualmente abierto
     */
    public String nombreDelArchivo() {
        return archivo.getName();
    }

    /**
     * Cierra el archivo actual
     */
    public void cerrarArrchivo() {
        archivo = null;
    }

    /**
     * Determina si hay un archivo abierto
     * @return true: si hay un archivo abierto | false: si no hay un archivo abierto
     */
    public boolean archivoSelecionado() {
        return archivo != null;
    }

    /**
     * Obteniene la referencia del archivo
     * @return Objeto instanciado de la clase "File" con la información del archivo
     */
    public File obtenerArchivo() {
        return archivo;
    }

    /**
     * Retorna el contenido del archivo
     * @return String que contien el contenido del archivo
     */
    private String leerArchivo() {
        try ( BufferedReader lector = new BufferedReader(new FileReader(archivo, StandardCharsets.UTF_8))) {
            String txt = "", linea;
            while ((linea = lector.readLine()) != null) {
                txt += linea + "\n";
            }
            if (txt.equals("")) {
                return txt;
            }
            return txt.substring(0, txt.length() - 1);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(selectorArchivos,
                    "Error al abir el archivo.\nCódigo de error: " + e.getMessage(), "IOError",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }

    /**
     * Determina si se pudo escirbir o sobrescribir el archivo
     * @param txt Texto a escribir o sobrescribir
     * @return true: si se pudo escribir o sobreescribir el archivo | false: si no se pudo escribir o sobreescribir el archivo
     */
    private boolean escribirArchivo(String txt) {
        try ( BufferedWriter w = new BufferedWriter(new FileWriter(archivo, StandardCharsets.UTF_8))) {
            w.write(txt);
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(selectorArchivos,
                    "Error al guardar el archivo.\nCódigo de error: " + e.getMessage(), "IOError",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Asegura que el archivo se guarde con la extención ".sg"
     * @return true: si el nombre del archivo termina en ".sg" | false: si el nombre del archivo termina no en ".sg"
     */
    private File extencionValida() {
        if (!selectorArchivos.getSelectedFile().getAbsolutePath().endsWith(".sg")) {
            return new File(selectorArchivos.getSelectedFile().getAbsolutePath() + ".sg");
        }
        return selectorArchivos.getSelectedFile();
    }
}
