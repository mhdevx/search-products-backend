package product.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import product.models.Product;
import product.repositories.mongo.ProductRepo;
import product.services.ProductService;


@RestController()
@RequestMapping("/product/v1")
@CrossOrigin(origins="${search.allowed-origins:*}" , methods= {RequestMethod.GET,RequestMethod.PUT,RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.DELETE})
public class ProductoController {

	@Autowired
	ProductService productService;
	
	@GetMapping(value = "/")
	public ResponseEntity<?> hola() {
		return new ResponseEntity<>("{\"saludo\":\"hola\"}", HttpStatus.OK);
	}
	
  @GetMapping("/search/{term}")
  public ResponseEntity<List<Product>> searchProducts(@PathVariable("term") String term) {
	  return  productService.searchProducts(term);
  }
  
  
}
