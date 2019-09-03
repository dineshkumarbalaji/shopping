package com.shopping.Hendryshop;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.shopping.pojo.Product;

public interface InputCart {
	
	boolean addProduct(Product p);
	boolean removeProduct(String pid) throws ProductNotFoundException;
	Collection getCartDetails();
	public Map<String,Product> getProductName();
	

}
