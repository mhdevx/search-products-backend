package product.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("products")
public class Product {

	@Id
	@Field(name="_id")
	private String id;
	
	@Field(name = "id")
	private Integer identifier;
	
	private String brand;
	private String description;
	private String image;
	private Integer price;
	private double discountApplied;
	private Integer priceOld;

	public Product() {
		this.id = null;
		this.identifier = null;
		this.brand = null;
		this.description = null;
		this.image = null;
		this.price = null;
		this.discountApplied = 0;
		this.priceOld = null;
	}
	
	public Object getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getIdentifier() {
		return identifier;
	}
	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public double getDiscountApplied() {
		return discountApplied;
	}

	public void setDiscountApplied(double discountApplied) {
		this.discountApplied = discountApplied;
	}

	public Integer getPriceOld() {
		return priceOld;
	}

	public void setPriceOld(Integer priceOld) {
		this.priceOld = priceOld;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", identifier=" + identifier + ", brand=" + brand + ", description=" + description
				+ ", image=" + image + ", price=" + price + ", discountApplied=" + discountApplied + ", priceOld="
				+ priceOld + "]";
	}
	
	
}
