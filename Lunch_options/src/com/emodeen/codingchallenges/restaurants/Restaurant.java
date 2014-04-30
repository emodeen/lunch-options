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
	private List<Product> menu;
	private String id;

	/**
	 * @param id. The restaurant id.
	 */
	Restaurant(String id) {
		
		this.id = id;
	}

	/**
	 * Get all product combinations from this restaurant's menu.
	 * @return
	 */
	public List<ProductCombination> getCombinations() {
		
		List<ProductCombination> combinations = new ArrayList<ProductCombination>();
		List<Product> tempProds = null;
		List<Item> items = new ArrayList();
		items.add( new Item("eric"));
		Product p = new Product( new BigDecimal(4), items);
		tempProds.add(p);
		
		ProductCombination example = new ProductCombination( tempProds, 10, "arbys");
		
		combinations.add( example);
		
		//getSubsets( menu);
		
		return combinations;
	}
	
	private void getSubsets( List<Product> s) {
		
		System.out.println(s);
		
		// Base case: if the string has one letter, no more subsets
		if (s.size() == 1) {
			return;
		}
		
		/*
		for (int i=0; i < s.length(); i++) {
			
			getSubsets( new StringBuilder(s).deleteCharAt(i).toString());
		}
		*/
	}
	
	/**
	 * Recursive function that returns a subset of products.
	 * @param A list of products to divide into subsets.
	 * @return
	 
	private List<Product> getSubsets( int subsetSize) {
		
		List<Product> tempSet = products;
		
		// Base case: if the subset only has one product.
		if ( products.size() == 1) {
			return products;
		}
		
		else {
		
			// Recursion: Remove products from the subset one at a time.
			for (int i=0; i < products.size(); i++) {
			
				tempSet.remove(i);
				
				return getSubsets();
			}
		}
	}
	**/


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
