package com.emodeen.codingchallenges.restaurants;

import java.util.ArrayList;
import java.util.List;

public class ProductCombination {

	// The products contained in the combination
	private List<Product> products;
	
	// Whether the combination contains all of the desired foods.
	private boolean containsDesiredFoods;
	
	// The total of the prices for the products in the combination.
	private int totalPrice;
	
	public ProductCombination() {

		products = new ArrayList<Product>();
		containsDesiredFoods = true;
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
	 * @return Returns true if the product combination contains all of the desired foods.
	 */
	public boolean isValid( List<Item> desiredItems) {
		
		Product prod = null;
		Item item = null;
		Item desiredItem = null;
		boolean [] itemFound = new boolean[desiredItems.size()];
		
		// For each product in the combination.
		for (int i=0; i < products.size(); i++) {

			// A single product
			prod = products.get(i);
			
			// For each of the desired items
			for (int j=0; j < desiredItems.size(); j++) {
				
				// Check if any desired items are contained in the item.
				for (int k=0; k < prod.getItems().size(); k++) {
					
					// Compare the item to the desired food.
					item = prod.getItems().get(k);
					desiredItem = desiredItems.get(j);
					
					if ( item.getFoodType() == desiredItem.getFoodType()) {
						itemFound[j] = true;
						break;
					}
				}
			}
		}
		
		// This boolean was initialized to true in the constructor.
		for (int l=0; l < itemFound.length; l++) {
			
			if (!itemFound[l]) containsDesiredFoods = false;
			break;
		}
		
		return containsDesiredFoods;
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
