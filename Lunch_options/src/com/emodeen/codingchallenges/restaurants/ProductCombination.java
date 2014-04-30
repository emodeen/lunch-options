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
	
	// The total of the prices for the products in the combination.
	private int totalPrice;
	
	private String restaurantID;
	
	
	/**
	 * @return the restaurantID
	 */
	public String getRestaurantID() {
		return restaurantID;
	}

	/**
	 * @param restaurantID the restaurantID to set
	 */
	public void setRestaurantID(String restaurantID) {
		this.restaurantID = restaurantID;
	}

	public ProductCombination() {

		products = new ArrayList<Product>();
	}
	
	public ProductCombination( List<Product> prods, int price, String id) {

		products = prods;
		totalPrice = price;
		restaurantID = id;
	}
	
	/**
	 * @return the products
	 */
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
	}



	/**
	 * @return the totalPrice
	 */
	public int getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

}
