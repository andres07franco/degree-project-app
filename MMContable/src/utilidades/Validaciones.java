/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utilidades;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Usuario
 */
public class Validaciones {


 public static  boolean esEmail(String email) {

       Pattern p = Pattern.compile("[a-zA-Z0-9]+[.[a-zA-Z0-9_-]+]*@[a-z0-9][\\w\\.-]*[a-z0-9]\\.[a-z][a-z\\.]*[a-z]$");//me gusta esta

       Matcher m = p.matcher(email);
       return m.matches();
   }

  public static  boolean esEntero(String email) {

       Pattern p = Pattern.compile("^(?:\\+|-)?[1-9]+\\d*$");//me gusta esta

       Matcher m = p.matcher(email);
       return m.matches();
   }

   public boolean validateLeftEmail(String emailLeft) {
       Pattern p = Pattern.compile("[a-zA-Z0-9]+[.[a-zA-Z0-9_-]+]*");
       Matcher m = p.matcher(emailLeft);
       return m.matches();
   }

   public boolean validateRightEmail(String emailRight) {
       Pattern p = Pattern.compile("[a-z0-9][\\w\\.-]*[a-z0-9]\\.[a-z][a-z\\.]*[a-z]$");
       Matcher m = p.matcher(emailRight);
       return m.matches();

   }


public static void main(String ar[]){
    System.out.println(Validaciones.esEntero("10"));
}
}
