package datos;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 * @author Arturo Guzmán Avila
 */
public class DataTable extends DefaultTableModel {

    public DataTable(Vector<Vector<String>> dataTable) {
        this.addColumn("Pila");
        this.addColumn("Entrada");
        this.addColumn("Acción");
        
        try {
          for (int i = 0; i < dataTable.get(0).size(); i++) {
            Object fila[] = new Object[3];
            fila[0] = dataTable.get(0).get(i);
            fila[1] = dataTable.get(1).get(i);
            fila[2] = dataTable.get(2).get(i);
            this.addRow(fila);
        }  
        } catch (ArrayIndexOutOfBoundsException e) {
            
        }
        
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

}
