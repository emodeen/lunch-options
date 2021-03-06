package com.emodeen.codingchallenges.restaurants;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents a combination of Products.
 * @author Eric
 *
 */
public class ProductCombination {

	// The products contained in the combination
	private List<Product> products;
	
	// The total price for the products in the combination.
	private int totalPrice;
	
	private String restaurantID;
	
	
	/**
	 * @return the restaurantID
	 */
	String getRestaurantID() {
		return restaurantID;
	}

	/**
	 * @param restaurantID the restaurantID to set
	 */
	void setRestaurantID(String restaurantID) {
		this.restaurantID = restaurantID;
	}

	ProductCombination() {

		products = new ArrayList<Product>();
	}
	
	/**
	 * 
	 * @param prods The products to include in the combination.
	 * @param id The restaurant id for the product combination.
	 */
	ProductCombination( List<Product> prods, String id) {

		products = prods;
		totalPrice = 0;
		restaurantID = id;
		
		// Calculate the total price of the combination.
		for (int i=0; i < prods.size(); i++) {
			
			totalPrice += prods.get(i).getPrice().intValue();
		}
	}
	
	/**
	 * @return the products
	 */
	List<Product> getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	void setProducts(List<Product> products) {
		this.products = products;
	}

	/**
	 * @return the totalPrice
	 */
	int getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice the totalPrice to set
	 */
	void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

}
