package com.shopping.Hendryshop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

import javax.swing.text.html.HTMLDocument.Iterator;

import com.shopping.HendrshopImp.InputCartImp;
import com.shopping.pojo.Product;

/**
 * Hello world!
 *
 */
public class Order 
{  
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader((new InputStreamReader(System.in)));
		int quantity = Integer.parseInt((bufferedReader.readLine().trim()));	
		Map map = new HashMap();
		InputCart inputCart = new InputCartImp();
		
		IntStream.range(0, quantity).forEach(i -> {
			try {
				
				String[] strs = bufferedReader.readLine().trim().split("\\s+");
			//	System.out.println(strs[0]+"\t"+strs[1]+"\t"+strs[2]);
				Product pojo = new Product();
				pojo.setPname(strs[0]);
				pojo.setCost(Double.parseDouble(strs[1]));
				pojo.setQuantity(Integer.parseInt(strs[2]));
				pojo.setPunit(strs[3]);
				inputCart.addProduct(pojo);
				
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});
		
		Product p1 =  (Product) inputCart.getCartDetails();
		
		
		
	
	}
   
}

/*
apple	1.45	50  single	
milk	2.3	56 bottle
soup	6.25	32 tin
bread	1.3	50 loaf

*/
