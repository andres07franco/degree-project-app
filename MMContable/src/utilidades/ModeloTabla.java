/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import javax.swing.table.DefaultTableModel;

public class ModeloTabla extends DefaultTableModel {

    public ModeloTabla() { 
    }

    ModeloTabla(Object[][] data, Object[] columns) {
        super(data, columns);
    }

    ModeloTabla(Object[][] data, Object[] columns, boolean[] canEdit) {
        super(data, columns);
        this.canEdit = canEdit;
    }

    public ModeloTabla(boolean[] canEdit) {

        this.canEdit = canEdit;
    }

    boolean[] canEdit = new boolean[]{
        false, true, false
    };

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
    }
}
