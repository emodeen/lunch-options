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
	private List<Product> menu;
	private String id;
	
	// A list of all combinations of products on the menu.
	private List<ProductCombination> combinations;

	/**
	 * @param id. The restaurant id.
	 */
	Restaurant(String id) {
		
		this.id = id;
		this.menu = new ArrayList<Product>();
	}

	/**
	 * Set the product combinations for this restaurant's menu.
	 */
	public void setCombinations2() {
		
		combinations = new ArrayList<ProductCombination>();
		
		List<Product> prods = new ArrayList<Product>(menu);
		
		getSubsets( prods, 1);
	}
	
	/**
	 * 
	 * @return A list of all product combinations for the menu.
	 */
	public List<ProductCombination> getCombinations2() {
		
		if ( combinations == null) {
			setCombinations2();
		}
		
		return combinations;
	}
	
	/**
	 * 
	 * @param prods The product set to find subsets for.
	 * NOTE: This method is recursive and it uses side effects to accomplish its goal of adding all product combinations to the combinations field.
	 */
	private void getSubsets( List<Product> prods, int size) {
		
		combinations.add( new ProductCombination( prods, this.id));
		
		// Base case: if a subset has only one product.
		if (prods.size() == 1) {
			return;
		}
		
		else {
		
			for (int i=0; i < prods.size(); i++) {

				// Create a new instance to pass into the next call of the method. This new copy will be stored in the next ProductCombination created.
				List<Product> nextProds = new ArrayList<Product>(prods);				
				nextProds.remove(i);
				
				getSubsets( nextProds, size-1);
			}
		}
	}
	

	/**
	 * This method adds a product to the menu.
	 * @param price The price of the product.
	 * @param items The items included in the product.
	 */
	public void addProduct( BigDecimal price, List<Item> items) {
		
		Product p = new Product( price, items);
		
		menu.add( p);
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
	
}
