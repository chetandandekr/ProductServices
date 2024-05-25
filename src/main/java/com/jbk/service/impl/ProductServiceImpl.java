package com.jbk.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.jbk.dao.ProductDao;
import com.jbk.dao.impl.ProductDaoImpl;
import com.jbk.entity.ProductEntity;
import com.jbk.exception.ResourceNotExistsException;
import com.jbk.model.CategoryModel;
import com.jbk.model.ProductModel;
import com.jbk.model.Product_Catagory_Supplier;
import com.jbk.model.SupplierModel;
import com.jbk.service.ProductService;

//ProductService service=new ProductServiceImpl();
//@Component

@Service
//@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//@Scope("prototype")
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao dao;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public boolean addProduct(ProductModel productModel) {
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
		productModel.setProductId(Long.parseLong(timeStamp));

		ProductEntity productEntity = mapper.map(productModel, ProductEntity.class);

		return dao.addProduct(productEntity);
	}

	@Override
	public ProductModel getProductById(long productId) {

		ProductEntity productEntity = dao.getProductById(productId);

		if (productEntity != null) {
			ProductModel productModel = mapper.map(productEntity, ProductModel.class);
			return productModel;
		} else {
			throw new ResourceNotExistsException("Product Not Exists ID = " + productId);
		}

	}

	@Override
	public boolean deleteProductById(long productId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateProduct(ProductModel product) {

		return dao.updateProduct(mapper.map(product, ProductEntity.class));
	}

	@Override
	public List<ProductModel> getAllProducts() {
		List<ProductEntity> entityList = dao.getAllProducts();
		List<ProductModel> modelList = new ArrayList<ProductModel>();
		if (!entityList.isEmpty()) {

			for (ProductEntity productEntity : entityList) {

				ProductModel productModel = mapper.map(productEntity, ProductModel.class);
				modelList.add(productModel);

			}

			return modelList;
		} else {
			throw new ResourceNotExistsException("Product Not Exists");
		}
	}

	@Override
	public List<ProductModel> sortProduct(String orderType, String property) {
		List<ProductEntity> entityList = dao.sortProduct(orderType, property);
		List<ProductModel> modelList = new ArrayList<ProductModel>();
		if (!entityList.isEmpty()) {

			for (ProductEntity productEntity : entityList) {

				ProductModel productModel = mapper.map(productEntity, ProductModel.class);
				modelList.add(productModel);

			}

			return modelList;
		} else {
			throw new ResourceNotExistsException("Product Not Exists");
		}
	}

	@Override
	public double getMaxProductPrice() {
		double maxProductPrice = dao.getMaxProductPrice();
		if (maxProductPrice > 0) {
			return maxProductPrice;
		} else {
			throw new ResourceNotExistsException("Product Not Exists");
		}

	}

	@Override
	public List<ProductModel> getMaxPriceProduct() {
		List<ProductEntity> list = dao.getMaxPriceProduct();

		List<ProductModel> modelList = new ArrayList<ProductModel>();
//		
//		for (ProductEntity productEntity : list) {
//			ProductModel productModel = mapper.map(productEntity, ProductModel.class);
//			modelList.add(productModel);
//		}

		modelList = list.stream().map(productEntity -> mapper.map(productEntity, ProductModel.class))
				.collect(Collectors.toList());

		return modelList;
	}

	@Override
	public ProductModel getProductByName(String productName) {

		return mapper.map(dao.getProductByName(productName), ProductModel.class);
	}

	@Override
	public List<ProductModel> getAllProducts(double lowPrice, double highPrice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductModel> getProductStartWith(String expression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double productPriceAverage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double countOfTotalProducts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ProductModel> getAllProducts(long category, long supplier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductModel> getAllProducts(String supplier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product_Catagory_Supplier getProductInfo(long productId) {
		ProductModel productModel = null;
		Product_Catagory_Supplier psc = null;

		if (productId >= 0) {
			productModel = getProductById(productId);
			psc.setProductModel(productModel);
		}
		try {
			CategoryModel catagoryModel = restTemplate.getForObject(
					"http://localhost:8091/Category/get-Category-by-id/" + productModel.getCategoryId(),
					CategoryModel.class);
			psc.setCatagoryModel(catagoryModel);

		} catch (RestClientException e) {
			psc.setCatagoryModel(null);
		}
		try {
			SupplierModel supplierModel = restTemplate.getForObject(
					"http://localhost:8093/Supplier/get-Supplier-by-id/" + productModel.getSupplierId(),
					SupplierModel.class);
			psc.setSuppliertModel(supplierModel);

		} catch (RestClientException e) {
			psc.setSuppliertModel(null);
		}

		return psc;
	}

}
