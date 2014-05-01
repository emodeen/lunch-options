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
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the productID
	 */
	public int getProductID() {
		return productID;
	}

	/**
	 * @param productID the productID to set
	 */
	public void setProductID(int productID) {
		this.productID = productID;
	}

	/**
	 * @return the items
	 */
	public List<Item> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}

}
