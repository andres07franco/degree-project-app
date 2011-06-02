/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

/**
 *
 * @author carlos3
 */
import javax.swing.text.*;

public class FormatoTextField extends PlainDocument {

    public static final String LOWERCASE = "abcdefghijklmnñopqrstuvwxyzáéíóúü";
    public static final String UPPERCASE = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZÁÉÍÓÚÜ";
    public static final String ALPHA = UPPERCASE + " ";
    public static final String NUMERIC = "0123456789";
    public static final String FLOAT = NUMERIC + ".";
    public static final String ALPHA_NUMERIC = ALPHA + NUMERIC;
    public static final String ALL = ALPHA + NUMERIC + " ¡!¿?.:,;()";
    public static final String ALPHA_ESPC = ALPHA + " ";
    public static final String EMAIL = ALPHA_NUMERIC + "@-_.";
    public static final String ALPHA_NUMERIC_ESPC = ALPHA_ESPC + NUMERIC;
    public static final String DIRE = ALPHA_NUMERIC_ESPC + "#.,-_()";
    public static final String TELF = NUMERIC + " /-()";
    public static final String NOMB = ALPHA_ESPC + ".";
    public static final String IVA = DIRE + "$%&/.:";
    public static final String LOGIN = ALPHA_NUMERIC + "_";
    private int limit;
    protected String acceptedChars = null;
    protected boolean negativeAccepted = false;

    public FormatoTextField(String acceptedchars, int limit) {
        acceptedChars = acceptedchars;
        this.limit = limit;
    }

///////////////////
    public FormatoTextField(int limit) {
        this(ALL, limit);
    }
//////////////////////

    public void setNegativeAccepted(boolean negativeaccepted) {
        if (acceptedChars.equals(NUMERIC) || acceptedChars.equals(FLOAT) || acceptedChars.equals(ALPHA_NUMERIC)) {
            negativeAccepted = negativeaccepted;
            acceptedChars += "-";
        }
    }

    public void insertString(int offset, String str, AttributeSet attr)
            throws BadLocationException {
        if (str == null) {
            return;
        }

        if (acceptedChars.equals(UPPERCASE)) {
            str = str.toUpperCase();
        } else if (acceptedChars.equals(LOWERCASE)) {
            str = str.toLowerCase();
        }

        for (int i = 0; i < str.length(); i++) {
            if (acceptedChars.indexOf(str.valueOf(str.charAt(i))) == -1) {
                return;
            }
        }

        if (acceptedChars.equals(FLOAT) || (acceptedChars.equals(FLOAT + "-") && negativeAccepted)) {
            if (str.indexOf(".") != -1) {
                if (getText(0, getLength()).indexOf(".") != -1) {
                    return;
                }
            }
        }

        if (negativeAccepted && str.indexOf("-") != -1) {
            if (str.indexOf("-") != 0 || offset != 0) {
                return;
            }
        }

        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }
}
