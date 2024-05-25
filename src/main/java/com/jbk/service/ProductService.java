package com.jbk.service;

import java.util.List;
import com.jbk.model.ProductModel;
import com.jbk.model.Product_Catagory_Supplier;

public interface ProductService {

	public boolean addProduct(ProductModel product);
	public ProductModel getProductById(long productId);
	public boolean deleteProductById(long productId);
	public boolean updateProduct(ProductModel product); 
	
	public List<ProductModel> getAllProducts();
	public List<ProductModel> sortProduct(String orderType, String property);
	
	public double getMaxProductPrice();

	public Product_Catagory_Supplier getProductInfo(long productId);
	public List<ProductModel> getMaxPriceProduct();
	public ProductModel getProductByName(String productName);
	public List<ProductModel> getAllProducts(double low, double high);
	public List<ProductModel> getProductStartWith(String expression);
	public double productPriceAverage();
	public double countOfTotalProducts();
	public List<ProductModel> getAllProducts(long category,long supplier ); // all products whose  supplier=? & category=?
	public List<ProductModel> getAllProducts(String supplier); // all products whose  supplier=? & category=?

}
