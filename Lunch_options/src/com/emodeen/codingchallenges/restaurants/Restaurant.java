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
	private List<ProductCombination> combinations;

	/**
	 * @param id. The restaurant id.
	 */
	Restaurant(String id) {
		
		this.id = id;
		this.menu = new ArrayList<Product>();
	}

	/**
	 * Get all product combinations from this restaurant's menu.
	 * TODO: Remove side effects.
	 * @return
	 */
	public void setCombinations2() {
		
		combinations = new ArrayList<ProductCombination>();
		
		List<Product> prods = menu;
		
		getSubsets( prods, 1);
	}
	
	public List<ProductCombination> getCombinations2() {
		
		if ( combinations == null) {
			setCombinations2();
		}
		
		return combinations;
	}
	
	/**
	 * 
	 * @param s The string to find subsets for.
	 * @param size The number of characters that the subsets should be.
	 * @return Returns a subset of a given size.
	 */
	private void getSubsets( List<Product> prods, int size) {
		
		System.out.println(prods.size());
		
		combinations.add( new ProductCombination( prods, 10, this.id));
		
		// Create a new instance to pass into the next call of the method. This new copy will be stored in the next ProductCombination created.
		List<Product> nextProds = new ArrayList<Product>(prods);
		
		// Base case: if a subset has only one product.
		if (nextProds.size() == 1) {
			return;
		}
		
		else {
		
			for (int i=0; i < nextProds.size(); i++) {
				System.out.println("i=" + i);
				nextProds.remove(i);
				getSubsets( nextProds, size-1);
			}
		}
	}
	
	
	private void getSubsets( String s, int size) {
		
		System.out.println(s);
		
		// Base case: if the string has one letter, no more subsets
		if (s.length() == 1) {
			return;
		}
		
		for (int i=0; i < s.length(); i++) {
			
			getSubsets( new StringBuilder(s).deleteCharAt(i).toString(), size-1);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Get all product combinations from this restaurant's menu.
	 * @return
	 */
	public List<ProductCombination> getCombinations() {
		
		List<ProductCombination> combinations = new ArrayList<ProductCombination>();
		List<Product> tempProds = new ArrayList<Product>();
		List<Item> items = new ArrayList();
		
		items.add( new Item("burger"));
		items.add( new Item("fries"));
		items.add( new Item("salad"));
		
		Product p = new Product( new BigDecimal(4), items);
		tempProds.add(p);
		
		ProductCombination example = new ProductCombination( tempProds, 10, "arbys");
		
		combinations.add( example);
		
		//getSubsets( menu);
		
		return combinations;
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
