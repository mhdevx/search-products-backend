package product.repositories.mongo;

import java.util.List;
import java.util.Optional;


import org.springframework.data.mongodb.repository.MongoRepository;

import product.models.Product;


public interface ProductRepo extends  MongoRepository<Product, String> {

	List<Product> findByDescriptionContaining(String description);
	List<Product> findByBrandContaining(String brand);
	List<Product> findByIdentifier(Integer identifier);
	
}
