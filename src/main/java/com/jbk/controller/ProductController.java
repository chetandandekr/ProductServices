package com.jbk.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.jbk.exception.ResourceAlreadyExistsException;
import com.jbk.exception.ResourceNotExistsException;
import com.jbk.model.ProductModel;
import com.jbk.model.Product_Catagory_Supplier;
import com.jbk.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	// ProductServiceImpl service=new ProductServiceImpl();
	// ProductService service = new ProductServiceImpl();

	@Autowired
	ProductService service;

	@PostMapping("/add-product")
	public ResponseEntity<String> addProduct(@RequestBody @Valid ProductModel product) {
		service.addProduct(product);

		return ResponseEntity.ok("Product Added !!");

	}

	@GetMapping("/get-product-by-id/{productId}")
	public ResponseEntity<ProductModel> getProductById(@PathVariable long productId) {
		ProductModel productModel = service.getProductById(productId);

		return ResponseEntity.ok(productModel);

	}

	@DeleteMapping("/delete-product-by-id")
	public String deleteProductById(@RequestParam("productId") long productId) {

		return null;

	}

	@PutMapping("/update-product")
	public String updateProduct(@RequestBody ProductModel product) {

		return null;

	}
	
	
	// *************************
	
	@GetMapping("get-all-products")
	public ResponseEntity<List<ProductModel>> getAllProducts(){
		return ResponseEntity.ok(service.getAllProducts());	
	}
	
	@GetMapping("sort-products")
	public ResponseEntity<List<ProductModel>> sortProducts(@RequestParam String orderType,@RequestParam String propertyName){
		return ResponseEntity.ok(service.sortProduct(orderType,propertyName));	
	}
	
	@GetMapping("max-price")
	public ResponseEntity<Double> maxPrice(){
		return ResponseEntity.ok(service.getMaxProductPrice());	
	}
	
	

	@GetMapping("max-price-product")
	public Object getMaxPriceProduct() {
		return null;

	}
	
	@GetMapping("get-product-by-name/{productName}")
	public Object getMaxPriceProduct(@PathVariable String productName) {
		
		
		return ResponseEntity.ok(service.getProductByName(productName));	

	}
	@GetMapping("get-product-Info/{productId}")
	public ResponseEntity<Product_Catagory_Supplier> getAllInfo(@PathVariable long productId) {
		Product_Catagory_Supplier psc = service.getProductInfo(productId);

		return ResponseEntity.ok(psc);

	}
}
