package product.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import product.helpers.ProductHelper;
import product.models.Product;
import product.repositories.mongo.ProductRepo;
/**
* Esta clase contiene la capa service del ProducController
* search-product  
*
* @author  Manuel Hernández
* @version 1.0
* @since   2020-10-08 
*/

@Service
public class ProductService {
	final static double DISCOUNT = 0.5; 
	@Autowired
	ProductRepo productRepo;
	
	/**
	* Método que realiza la búsqueda de productos. 
	*/
	public ResponseEntity<List<Product>> searchProducts(String term){
		try {
		      List<Product> products = new ArrayList<Product>();
		      List<Product> response = new ArrayList<Product>();
		       
		      if (ProductHelper.isNumeric(term)) {
		    	  /* si el término de búsqueda es numérico, se ejecutará una búsqueda exacta por el 
		    	   * identificador numérico del producto */
		    	  Integer identifier = Integer.parseInt(term);
		    	  productRepo.findByIdentifier(identifier).forEach(products::add);	    	  
		      } else if (term.length()>2){
		    	  /* si el parámetro de búsqueda no es numérico y el largo de dicha cadena es superior a 2 
		    	   * se realizará la búsqueda del termino dentro de los campos brand y description (no exacta) */
		    	  productRepo.findByDescriptionContaining(term).forEach(products::add);
		    	  productRepo.findByBrandContaining(term).forEach(products::add);
		      }
	    	  /* si el objeto products está vacío será retornado un 404, ya que la búsqueda no arrojó resultados*/
		      if (products.isEmpty()) {
		        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		      } else {
		    	  /* si el objecto products tiene datos, será recorrido */
		    	  for(Product product : products) {
		    		  /* evalua si existe palindromo dentro de los campos identifier o brand o description */
		    		   if(ProductHelper.isPalindrome(product.getIdentifier().toString()) || 
		    				   ProductHelper.isPalindrome(product.getDescription()) ||
		    				   ProductHelper.isPalindrome(product.getBrand())   ) {
		    			   /* si hay palíndromo procede a realizar los cálculos de descuento del 50% 
		    			    * reemplazando el price por el valor con descuento aplicado, rellenado otro 
		    			    * campo con el valor sin descuento aplicado */
		    			   Integer discountValue = (int) (product.getPrice()*DISCOUNT);
		    			   Integer priceAfterDiscount = product.getPrice() - discountValue;
		    			   product.setPriceOld(product.getPrice());
		    			   product.setPrice(priceAfterDiscount);
		    			   product.setDiscountApplied(DISCOUNT);
		    			   
		    		   }
		    		   response.add(product);
		    	   } 
		      } 
		      return new ResponseEntity<>(response, HttpStatus.OK);
		      
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
}
