package com.emodeen.codingchallenges.restaurants;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Eric
 * This class represents a product for sale in a restaurant.
 */
public class Product {
	
	private BigDecimal price;
	private int productID;
	private List<Item> items;
	
	/**
	 * @param price
	 * @param items
	 */
	Product(BigDecimal price, List<Item> items) {
	
		super();
		this.price = price;
		this.items = items;
	}

	/**
	 * @return the price
	 */
	BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the productID
	 */
	int getProductID() {
		return productID;
	}

	/**
	 * @param productID the productID to set
	 */
	void setProductID(int productID) {
		this.productID = productID;
	}

	/**
	 * @return the items
	 */
	List<Item> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	void setItems(List<Item> items) {
		this.items = items;
	}

}
