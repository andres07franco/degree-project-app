/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utilidades;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

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


