package product.helpers;


/**
* Esta clase contiene la funciones helper para encapsular o facilitar 
*  tareas repetitivas
*  
* @author  Manuel Hernández
* @version 1.0
* @since   2020-10-08 
*/
public class ProductHelper {
	/*método para evaluar si un string es palíndromo */
	public static boolean isPalindrome(String text) {
	    String clean = text.replaceAll("\\s+", "").toLowerCase();
	    int length = clean.length();
	    int forward = 0;
	    int backward = length - 1;
	    while (backward > forward) {
	        char forwardChar = clean.charAt(forward++);
	        char backwardChar = clean.charAt(backward--);
	        if (forwardChar != backwardChar)
	            return false;
	    }
	    return true;
	}
	
	/* método para evaluar si un string contiene sólo números */
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	private ProductHelper() {
	    throw new IllegalStateException("Utility class");
	  }

}
