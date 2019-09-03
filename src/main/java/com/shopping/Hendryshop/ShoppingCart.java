package com.shopping.Hendryshop;

import java.text.ParseException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.shopping.pojo.Product;

public interface ShoppingCart {
	
	public Map addPurchaseItem(Product shoppingproduct,Set set) throws ProductNotFoundException;
	public Map RemovePurchaseItem(String pname);
	public Double calaculatePrice(Map<String, Integer> map,Map<String, Product> cartmap);
	public Map getShoppingCartDetails();
	public Double getBillamount(Map<String,Product> map,String purchasedate) throws ParseException;
}
