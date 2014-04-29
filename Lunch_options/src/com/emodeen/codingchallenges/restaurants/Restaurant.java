/**
 * 
 */
package com.emodeen.codingchallenges.restaurants;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Eric
 * This class represents a restaurant where the products can be purchased.
 */
public class Restaurant {
	
	// All products on the menu.
	private ProductCombination menu;
	private String id;
	private ProductCombination bestCombination;
	private List<ProductCombination> validCombinations;

	/**
	 * @param id. The restaurant id.
	 */
	Restaurant(String id) {
		
		this.id = id;
		menu = new ProductCombination();
		validCombinations = new ArrayList<ProductCombination>();
	}


	/**
	 * 
	 * @return The cheapest product combination.
	 * @param desiredItems. The items that the customer wants to purchase.
	 */
	private ProductCombination findCheapestCombination( List<Item> desiredItems) {
		
		ProductCombination cheapestCombination = null;
		ProductCombination tempMenu = menu;
		int lowestPrice = -1;
		
		findValidCombinations( tempMenu, menu.getProducts().size(), desiredItems);
	
		for (int i=0; i < validCombinations.size(); i++) {

			// Initialize lowest price
			if (lowestPrice < 1) {
				lowestPrice = validCombinations.get(i).getTotalPrice();
			}
			
			if (validCombinations.get(i).getTotalPrice() < lowestPrice) {
				lowestPrice = validCombinations.get(i).getTotalPrice();
				cheapestCombination = validCombinations.get(i);
			}
		}
		
		return cheapestCombination;
	}
	
	/**
	 * This method finds the product combinations that contain the desired foods.
	 * @param subset A subset to find combinations of.
	 * @param size The size of the subset to find combinations of.
	 * @param desiredItems. The items that the customer wants to purchase.
	 */
	private void findValidCombinations( ProductCombination subset, int size, List<Item> desiredItems) {
		
		ProductCombination tempCombo = subset;
		
		if (subset.isValid(desiredItems)) {
			
			validCombinations.add( subset);
		}
		
		// Base case: if the subset has only only product
		if (subset.getProducts().size() == 1) {
			return;
		}
		
		for (int i=0; i < subset.getProducts().size(); i++) {
		
			tempCombo.getProducts().remove(i);
			
			findValidCombinations( tempCombo, size-1, desiredItems);
		}
	}

	public void addProduct( BigDecimal price, List<Item> items) {
		
		Product p = new Product( price, items);
		
		menu.getProducts().add( p);
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return the bestCombination
	 */
	public ProductCombination getBestCombination( List<Item> desiredItems) {
		
		if (bestCombination == null) {
			
			bestCombination = findCheapestCombination( desiredItems);
		}
		
		return bestCombination;
	}


	/**
	 * @param bestCombination the bestCombination to set
	 */
	public void setBestCombination(ProductCombination bestCombination) {
		this.bestCombination = bestCombination;
	}

}
