package com.shopping.HendrshopImp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.shopping.Hendryshop.ProductNotFoundException;
import com.shopping.Hendryshop.ShoppingCart;
import com.shopping.pojo.Product;

public class ShoppingCartImpl implements ShoppingCart {

	private static Map<String, Integer> cartmap;

	static Date todayDate, yesterdayDate, tillNextSevenDays, fromThreeDays, lastDayOfTheMonth;

	public static void dates() {

		Date today = new Date();
		Calendar calender = Calendar.getInstance();
		todayDate = calender.getTime();

		Calendar calender1 = Calendar.getInstance();
		calender1.add(Calendar.DATE, -1);
		yesterdayDate = calender1.getTime();

		Calendar calender2 = Calendar.getInstance();
		calender2.add(Calendar.DATE, +7);
		tillNextSevenDays = calender2.getTime();

		Calendar calender3 = Calendar.getInstance();
		calender3.add(Calendar.DATE, +3);
		fromThreeDays = calender3.getTime();

		Calendar calender4 = Calendar.getInstance();
		calender4.setTime(today);
		calender4.add(Calendar.MONTH, 1);
		calender4.set(Calendar.DAY_OF_MONTH, 1);
		calender4.add(Calendar.DATE, -1);
		lastDayOfTheMonth = calender4.getTime();

		/*
		 * System.out.println("todayDate " + todayDate);
		 * System.out.println("yesterdayDate " + yesterdayDate);
		 * System.out.println("tillNextSevenDays " + tillNextSevenDays);
		 * System.out.println("fromThreeDays " + fromThreeDays);
		 * System.out.println("lastDayOfTheMonth " + lastDayOfTheMonth);
		 */

	} // dates

	public ShoppingCartImpl() {

		cartmap = new HashMap<String, Integer>();
	}

	@Override
	public Map addPurchaseItem(Product sp, Set set) throws ProductNotFoundException {

		if (set.contains(sp.getPname())) {
			if (cartmap.containsKey(sp.getPname())) {
				cartmap.put(sp.getPname(), sp.getQuantity() + sp.getQuantity());
			} else {
				cartmap.put(sp.getPname(), sp.getQuantity());

			}

			return cartmap;
		} else
			throw new ProductNotFoundException("Product with Name " + sp.getPname() + " is not Found.");
	}

	@Override
	public Map RemovePurchaseItem(String pname) {
		if (cartmap.containsKey(pname)) {
			cartmap.remove(pname);
		}

		return cartmap;
	}

	@Override
	public Double calaculatePrice(Map<String, Integer> map, Map<String, Product> cartmap) {

		Double totalprice = 0.0;
		Integer offerbread = 0;

		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
			for (Map.Entry<String, Product> cart : cartmap.entrySet()) {
				Product prd = (Product) cartmap.values();

				if (entry.getKey().endsWith(prd.getPname())) {
					Product p1 = (Product) cartmap.values();
					if (p1.getPname().equals("Milk")) {
						totalprice = entry.getValue() * p1.getCost();
					} else if (p1.getPname().equals("apples")) {
						totalprice = ((entry.getValue() * p1.getCost()) * 10) / 100;
					} else {
						totalprice = entry.getValue() * p1.getCost();
					}
				}
			}
		}

		return totalprice;
	}

	@Override
	public Map getShoppingCartDetails() {
		// TODO Auto-generated method stub
		return cartmap;
	}

	@Override
	public Double getBillamount(Map<String, Product> map, String purchasedate) throws ParseException {
		// TODO Auto-generated method stub
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(purchasedate);
		dates();
		
		
		Double totalPrice = 0.00;
		Double soupPrice = 0.00;
		Double breadPrice = 0.00;
		Double milkPrice = 0.00;
		Double applePrice = 0.00;
		if(cartmap.containsKey("soup") && cartmap.get("soup")> 0) {
			 System.out.println("soup count ..: "+cartmap.get("soup")+" map.get(\"soup\").getCost() " +map.get("soup").getCost()); 
			soupPrice = cartmap.get("soup")*map.get("soup").getCost();
			 System.out.println("soupPrice ..: "+soupPrice); 
			/*
			 * for(int i=0; i<cartmap.get("soup"); i++) { totalPrice +=
			 * map.get("soup").getCost(); System.out.println("cartmap.get(\"soup\") "
			 * +cartmap.get("soup")); System.out.println("Total Price..: "+totalPrice); }
			 */// for
		}// if - noOfSoupTins
		if(cartmap.containsKey("bread") && cartmap.get("bread") > 0) {
			
			 System.out.println("bread count ..: "+cartmap.get("bread")+" map.get(\"bread\").getCost() " +map.get("bread").getCost()); 
				
				if(date.after(yesterdayDate) && date.before(tillNextSevenDays)) {
					int offerAvailableForNoOFBreads = cartmap.get("soup")/2;
					int NoofferBreads = cartmap.get("bread") - offerAvailableForNoOFBreads ;
					System.out.println("offerAvailableForNoOFBreads "+offerAvailableForNoOFBreads);
					System.out.println("NoofferBreads "+NoofferBreads);
					/*
					 * for(int j=0; j<offerAvailableForNoOFBreads; j++) { breadLoafPrice =
					 * map.get("bread").getCost()/2;
					 * System.out.println("itemAndPriceMap.get(\"bread\")/2 :"+breadLoafPrice);
					 * offerAvailableForNoOFBreads--; }
					 */// for
					System.out.println("map.get(\"bread\").getCost()/2 "+map.get("bread").getCost()/2);
					if(offerAvailableForNoOFBreads>0) {
						double discount= 0.0;
						discount = 100 - 50;
						breadPrice = ((discount* (offerAvailableForNoOFBreads*map.get("bread").getCost()))/100);
						System.out.println("breadPrice disscount "+breadPrice);
					}
					breadPrice += NoofferBreads*map.get("bread").getCost();
					System.out.println("breadPrice  "+breadPrice);
				}else{
					 System.out.println("bread count ..: "+cartmap.get("bread")+" map.get(\"bread\").getCost() " +map.get("bread").getCost()); 
					breadPrice = cartmap.get("bread")*map.get("bread").getCost() ;		
				}
				System.out.println("breadPrice "+breadPrice);
			// for
		} // if - noOfBreadLoafs
		
		if(cartmap.containsKey("milk") && cartmap.get("milk")>0) {
			Double milkCost = map.get("milk").getCost();
			System.out.println("milkCost..: "+milkCost);
			for(int i=0; i<cartmap.get("milk"); i++) {
				totalPrice+=milkCost;
			}//for
		}// if noOfMilkBottles
		
		if(cartmap.containsKey("apples") && cartmap.get("apples")>0) {
			Double appleCost = map.get("apples").getCost();
			if(date.after(fromThreeDays)) {
				appleCost = appleCost-(appleCost/10);
			}//Date Checking
			for(int i=0; i<cartmap.get("apples"); i++) {
				totalPrice+=appleCost;
				//System.out.println("totalPrice...!");
			}
			//for
		} //if noOfApples
		
		//System.out.println(float(format(totalPrice,'.2f')));
		System.out.printf("%.2f", totalPrice);
		System.out.println();

		return totalPrice;
	}
	
	

}
