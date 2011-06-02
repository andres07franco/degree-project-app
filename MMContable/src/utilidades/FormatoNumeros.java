package utilidades;

import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.Locale;
import javax.swing.*;

public class FormatoNumeros implements KeyListener, FocusListener {

    public JTextField vrunit;

    public FormatoNumeros(JTextField vrunit) {
        this.vrunit = vrunit;
    }

    public static String formatear(String num) {
        Locale.setDefault(Locale.ENGLISH);
        DecimalFormat df = new DecimalFormat("#,###.00");

        return df.format(Double.parseDouble(num.replaceAll(",", "")));

    }

    public static String formatearCantidades(String num) {
        Locale.setDefault(Locale.ENGLISH);
        DecimalFormat df = new DecimalFormat("#,###.00");

        return df.format(Double.parseDouble(num.replaceAll(",", "")));

    }
    int punto = 0;

    public void keyTyped(KeyEvent evt) {

        if (vrunit.getText().indexOf(".") <= -1 && punto > 1) {
            punto = 0;
        }
        if (evt.getKeyChar() != '.') {
            if (!Character.isDigit(evt.getKeyChar())) {

                evt.consume();

            } else {
                if (punto == 1) {
                    evt.consume();
                    vrunit.setText(vrunit.getText() + "." + evt.getKeyChar());
                    punto++;
                }
            }
        } else {
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

    public double Truncar(double nD, int nDec) {
        if (nD > 0) {
            nD = Math.floor(nD * Math.pow(10, nDec)) / Math.pow(10, nDec);
        } else {
            nD = Math.ceil(nD * Math.pow(10, nDec)) / Math.pow(10, nDec);
        }

        return nD;
    }

    public void focusGained(FocusEvent e) {
        vrunit.setText(vrunit.getText().replaceAll(",", ""));
        vrunit.setSelectionStart(0);
        vrunit.setSelectionEnd(vrunit.getText().length());

    }

    public void focusLost(FocusEvent e) {
        if (!vrunit.getText().trim().equals("")) {
            vrunit.setText(formatear(vrunit.getText()));
        } else {
            vrunit.setText("0.00");
        }
    }

    public static String ValidarDigitoVerificacion(String unNit) {
        String miTemp;
        int miContador;
        int miResiduo;
        int miChequeo;
        int[] miArregloPA = new int[15];
        miArregloPA[0] = 3;
        miArregloPA[1] = 7;
        miArregloPA[2] = 13;
        miArregloPA[3] = 17;
        miArregloPA[4] = 19;
        miArregloPA[5] = 23;
        miArregloPA[6] = 29;
        miArregloPA[7] = 37;
        miArregloPA[8] = 41;
        miArregloPA[9] = 43;
        miArregloPA[10] = 47;
        miArregloPA[11] = 53;
        miArregloPA[12] = 59;
        miArregloPA[13] = 67;
        miArregloPA[14] = 71;
        miChequeo = 0;
        miResiduo = 0;
        for (miContador = 0; miContador < unNit.length(); miContador++) {
            miTemp = unNit.charAt((unNit.length() - 1) - miContador) + "";
            miChequeo = miChequeo + (Integer.parseInt(miTemp) * miArregloPA[miContador]);
        }
        miResiduo = miChequeo % 11;
        if (miResiduo > 1) {
            return "" + (11 - miResiduo);
        }
        return miResiduo + "";
    }
}
