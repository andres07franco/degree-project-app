/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utilidades;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author carlos2
 */

 public class TablaRender extends DefaultTableCellRenderer
{

     int toteados[];

     public void setToteados(int toteados[]){
         this.toteados=toteados;
     }     
    
    public static void setAnchoColumnas(JScrollPane scroll,JTable tbLista,int ... arg){

       // JViewport scroll =  (JViewport) tbLista.getParent();
        int ancho = 1000;//0 (int) scroll.getViewport().getSize().getWidth();

        int anchoColumna;
        TableColumnModel modeloColumna = tbLista.getColumnModel();
        TableColumn columnaTabla;
            System.out.println(ancho);
        for (int i = 0; i < tbLista.getColumnCount(); i++) {
            columnaTabla = modeloColumna.getColumn(i);
            anchoColumna = (arg[i]*ancho)/100;
            columnaTabla.setMaxWidth(anchoColumna);
        }

    }
     public boolean esta(int index){
       //  if(toteados!=null)
         for(int i=0;i<toteados.length;i++){
             
             if(toteados[i]==index){
                
                 return true;
             }
         }
         return false;
     }

public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column)
{

    setEnabled(table == null || table.isEnabled()); // see question above

        if (esta(row))
         setBackground(Color.RED);
        else
          setBackground(null);

    super.getTableCellRendererComponent(table, value, selected, focused, row, column);

    return this;
}



}


