/**
 * 
 */
package com.emodeen.codingchallenges.restaurants;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author Eric
 *
 */
public class Restaurant {
	
	// All products on the menu.
	private ProductCombination menu;
	private String id;
	private ProductCombination bestCombination;
	private List<ProductCombination> validCombinations;


	// List of subsets containing all desired foods
	private List<List<Product>> validSubsets;
	
	/**
	 * @param name
	 */
	Restaurant(String id) {
		
		this.id = id;
		menu = new ProductCombination();
		validCombinations = new ArrayList<ProductCombination>();
	}


	/**
	 * 
	 * @return The cheapest product combination.
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
	 * 
	 * @param s The string to find subsets for.
	 * @param size The number of characters that the subsets should be.
	 * @return Returns all combinations that contain the desired foods.
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

	/**
	 * Examine each subset to see if they contain the desired combination of foods.
	 * @param numProducts
	 * @return A list of the subsets that contain the desired foods.
	 */
	private List<List<Product>> findValidSubsets( int numProducts) {
		
		List<List<Product>> validSubsets = new ArrayList<List<Product>>();
		List<Product> subset = null;
		Product prod = null;
		
		// for each subset, see if it contains all desired foods.
		for (int i=0; i < validSubsets.size(); i++) {
			
			subset = validSubsets.get(i);
			
			for (int j=0; j < subset.size(); j++) {
				prod = subset.get(j);
				
				//for (int k=0; k < prod)
			}
		}
		
		return validSubsets;
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
