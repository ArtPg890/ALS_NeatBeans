package funciones;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Clase que se encarga de leer el contenido de un archivo de Excel y
 * convertirlo en un arreglo bidimensional
 */
public class LeerTabla {

    public String[][] get() {
        String[][] dataTable = null;
        File file = new File("src/tabla_sintactica/Tablagram.xls");
        try {
            // Create a file input stream to read Excel workbook and worksheet
            FileInputStream xlfile = new FileInputStream(file);
            HSSFWorkbook xlwb = new HSSFWorkbook(xlfile);
            HSSFSheet xlSheet = xlwb.getSheetAt(0);
            String cad;

            // Get the number of rows and columns
            int numRows = xlSheet.getLastRowNum() + 1;
            int numCols = xlSheet.getRow(0).getLastCellNum();

            // Create double array data table - rows x cols
            // We will return this data table
            dataTable = new String[numRows][numCols];

            // For each row, create a HSSFRow, then iterate through the "columns"
            // For each "column" create an HSSFCell to grab the value at the specified cell (i,j)
            for (int i = 0; i < numRows; i++) {
                HSSFRow xlRow = xlSheet.getRow(i);
                for (int j = 0; j < numCols; j++) {
                    HSSFCell xlCell = xlRow.getCell(j);
                    cad = xlCell.toString();
                    if (cad.equals("#")) {
                        dataTable[i][j] = "";
                    }
                    else{
                        if (cad.contains(".")){
                            cad = cad.substring(0, cad.indexOf("."));
                        }
                        dataTable[i][j] = cad;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR FILE HANDLING " + e.toString());
        }
        return dataTable;
    }

    /**
     * Retorna un arreglo bidimencional con el contenido del archivo de Excel
     *
     * @param fileName Nombre del arhivo
     * @param sheetName Nombre de la hola
     * @return String[][] con el contenido del archivo de Excel
     */
    /**
     * Da el formato adecuado a la tabla
     *
     * @param dt Tabla de Excel
     * @return String[][] con el contenido dado formato
     */
    private String[][] arrayFormat(String dt[][]) {
        int index;
        String cell;
        for (int i = 0; i < dt.length; i++) {
            for (int j = 0; j < dt[i].length; j++) {
                cell = dt[i][j];
                if (cell != null) {
                    if (cell.equals("")) {
                        dt[i][j] = null;
                    } else if ((index = cell.indexOf("->")) != -1) {
                        cell = cell.substring(index + 3);
                        if (cell.equals("''")) {
                            dt[i][j] = "";
                        } else {
                            dt[i][j] = cell;
                        }
                    }
                }

            }
        }
        /*for (String[] strings : dt) {
			for (String strings2 : strings) {
				System.out.print((strings2 == null ? "null" : strings2.equals("") ? "\"\"" : strings2) + "|*|");
			}
			System.out.println();
		}
        System.out.println("Orden: "+ dt[10][3]);*/
        return dt;
    }

    public static void main(String[] args) {
        for (String[] strings : new LeerTabla().get()) {
            for (String string : strings) {
                System.out.print(string + "\t");
            }
            System.out.println("");
        }
    }
}
