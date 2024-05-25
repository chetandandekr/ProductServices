package com.jbk.model;

public class Product_Catagory_Supplier {

private	ProductModel productModel;
private CategoryModel catagoryModel;
private SupplierModel suppliertModel;
public Product_Catagory_Supplier() {
	super();
	// TODO Auto-generated constructor stub
}
public Product_Catagory_Supplier(ProductModel productModel, CategoryModel catagoryModel, SupplierModel suppliertModel) {
	super();
	this.productModel = productModel;
	this.catagoryModel = catagoryModel;
	this.suppliertModel = suppliertModel;
}
public ProductModel getProductModel() {
	return productModel;
}
public void setProductModel(ProductModel productModel) {
	this.productModel = productModel;
}
public CategoryModel getCatagoryModel() {
	return catagoryModel;
}
public void setCatagoryModel(CategoryModel catagoryModel) {
	this.catagoryModel = catagoryModel;
}
public SupplierModel getSuppliertModel() {
	return suppliertModel;
}
public void setSuppliertModel(SupplierModel suppliertModel) {
	this.suppliertModel = suppliertModel;
}
@Override
public String toString() {
	return "Product_Catagory_Supplier [productModel=" + productModel + ", catagoryModel=" + catagoryModel
			+ ", suppliertModel=" + suppliertModel + "]";
}

	
}
