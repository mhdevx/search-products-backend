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

@Service
public class ProductService {
	final static double DISCOUNT = 0.5; 
	@Autowired
	ProductRepo productRepo;
	
	
	public ResponseEntity<List<Product>> searchProducts(String term){
		try {
		      List<Product> products = new ArrayList<Product>();
		      List<Product> response = new ArrayList<Product>();
		      if (ProductHelper.isNumeric(term)) {
		    	  Integer identifier = Integer.parseInt(term);
		    	  productRepo.findByIdentifier(identifier).forEach(products::add);	    	  
		      } else if (term.length()>2){
		    	  productRepo.findByDescriptionContaining(term).forEach(products::add);
		    	  productRepo.findByBrandContaining(term).forEach(products::add);
		      }
		      
		      if (products.isEmpty()) {
		        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		      } else {
		    	  for(Product product : products) {
		    		   if(ProductHelper.isPalindrome(product.getIdentifier().toString()) || 
		    				   ProductHelper.isPalindrome(product.getDescription()) ||
		    				   ProductHelper.isPalindrome(product.getBrand())   ) {
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
