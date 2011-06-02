/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utilidades;

/**
 *
 * @author carlos
 */
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.*;
public class FormatoCantidades implements KeyListener,FocusListener{

    public JTextField vrunit;
    public FormatoCantidades(JTextField vrunit){
        this.vrunit=vrunit;

    }




    public static String formatear(String num){
        Locale.setDefault(Locale.ENGLISH);
       DecimalFormat df = new DecimalFormat("#,###.000");

      return df.format(Double.parseDouble(num.replaceAll(",","")));

    }

    int punto=0;
    public void keyTyped(KeyEvent evt) {

            if(vrunit.getText().indexOf(".")<=-1&&punto>1)
                        punto=0;
            if(evt.getKeyChar()!='.'){
                if(!Character.isDigit(evt.getKeyChar())){

                                    evt.consume();

                }else{
                    if(punto==1){
                        evt.consume();
                        vrunit.setText(vrunit.getText()+"."+evt.getKeyChar());
                        punto++;
                     }

                }
            }else{
                evt.consume();
                punto++;
            }

    }

    public void keyPressed(KeyEvent evt) {

    }

    public void keyReleased(KeyEvent evt) {

            /* Locale.setDefault(Locale.ENGLISH);
             DecimalFormat df = new DecimalFormat("#,###.##");
           // double num=Truncar(Double.parseDouble(vrunit.getText().replaceAll(",","")),2);
            //num=Double.parseDouble(vrunit.getText().replaceAll(",",""));
       //     DecimalFormat df=new DecimalFormat(".##");
           // df.format(num);
                vrunit.setText(""+df.format(Double.parseDouble(vrunit.getText().replaceAll(",",""))));
               // vrunit.setSelectionEnd(vrunit.getText().indexOf(".")-1);*/

    }

public double Truncar(double nD, int nDec)
{
  if(nD > 0)
    nD = Math.floor(nD * Math.pow(10,nDec))/Math.pow(10,nDec);
  else
    nD = Math.ceil(nD * Math.pow(10,nDec))/Math.pow(10,nDec);

  return nD;
}

    public void focusGained(FocusEvent e) {
      vrunit.setText(vrunit.getText().replaceAll(",",""));
     vrunit.setSelectionStart(0);
     vrunit.setSelectionEnd(vrunit.getText().length());

    }

    public void focusLost(FocusEvent e) {
        if(!vrunit.getText().trim().equals(""))
            vrunit.setText(formatear(vrunit.getText()));
        else
            vrunit.setText("0.000");
    }



    public static void main(String arg[]){

    }

}
