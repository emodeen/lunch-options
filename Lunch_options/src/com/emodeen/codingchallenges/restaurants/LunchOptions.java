/**
 * 
 */
package com.emodeen.codingchallenges.restaurants;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Eric
 * This is the main class for a program that determines the best combination of products to purchase from one of the possible restaurants.
 * The combination of products must contain the desired set of foods at the lowest cost.
 */
public class LunchOptions {
	
	private List<Restaurant> restaurants;
	private List<Item> desiredItems;
	
	
	/**
	 */
	LunchOptions() {
		
		super();
		this.restaurants = new ArrayList<Restaurant>();
		this.desiredItems = new ArrayList<Item>();
	}


	/**
	 * @param args args[0] is the input file, and args[1] is the output file.
	 */
	public static void main(String[] args) {

		LunchOptions lo = new LunchOptions();
		CsvFile input = new CsvFile( args[0]);
		List<String> rows = input.getRows();
		
		lo.readRows(rows);
		
		// The restaurant with the best deal.
		Restaurant rest = lo.getBestRestaurant();
		
		int bestPrice = rest.getBestCombination( lo.getDesiredItems()).getTotalPrice();
		String restID = rest.getId();
		String outputStr = new StringBuffer(restID).append(",").append(bestPrice).toString();
		
		CsvFile output = new CsvFile( args[1]);
		output.write( outputStr);
	}
	
	/**
	 * Read all rows from the file, storing the data in the appropriate data structures.
	 * @param rows The rows to read.
	 */
	private void readRows( List<String> rows) {
		
		String row = null;
		String restaurantID = null;
		BigDecimal price = null;
		List<Item> productItems = null;
		String [] rowTokens = null;
		
		// For each row in the file
		for (int i=0; i < rows.size(); i++) {
			
			row = rows.get(i);
			
			// If it is not the last row, add a product to a restaurant.
			if (i != (rows.size()-1)) {
				
				rowTokens = row.split(",");
				restaurantID = rowTokens[0];
				price = new BigDecimal(rowTokens[1]);
				productItems = new ArrayList<Item>();
				
				// Store remaining tokens in list of items for a product.
				for (int j=2; j < rowTokens.length; j++) {
					
					productItems.add( new Item(rowTokens[j].replace("\"", "")));
				}
				
				storeRowData( price, restaurantID, productItems);
			}
			
			// Create a list of the desired food items.
			else {
				
				desiredItems = new ArrayList<Item>();
				rowTokens = row.split(",");
				
				for (int k=0; k < rowTokens.length; k++) {
					
					// remove any double-quote characters in the string before adding to the list.
					desiredItems.add( new Item(rowTokens[k].replace("\"", "")));
				}
			}
		}
	}
	
	/**
	 * 
	 * @param price The price of the product.
	 * @param restaurantID The restaurant where the product is for sale.
	 * @param items The items in the product.
	 */
	private void storeRowData( BigDecimal price, String restaurantID, List<Item> items) {
		
		Restaurant r = getRestaurant(restaurantID);
		
		if ( r == null) {
			r = new Restaurant(restaurantID);
			restaurants.add( r);
		}
		
		r.addProduct( price, items);
	}
	
	/**
	 * 
	 * @param restID
	 * @return Returns the restaurant with the specified id.  Returns null if the restaurant is not found.
	 */
	private Restaurant getRestaurant( String restID) {
		
		boolean restaurantFound = false;
		Restaurant r = null;
		
		// If not already added, add restaurant to the set.
		Iterator<Restaurant> rIter = restaurants.iterator();
		
		while (!restaurantFound && rIter.hasNext()) {
			
			r = (Restaurant)rIter.next();
			
			if (r.getId().equals(restID)) {
				restaurantFound = true;
				break;
			}
		}
		
		return r;
	}

	/**
	 * Calculate which restaurant offers the lowest price for the list of desired items.
	 * @return A Restaurant object.
	 */
	private Restaurant getBestRestaurant() {
		
		Restaurant selectedRestaurant = null;
		Restaurant r = null;
		int lowestPrice = -1;
		int price = 0;
		
		// Iterate over list of restaurants to find each one's lowest price.
		for (int i=0; i < restaurants.size(); i++) {
			
			r = restaurants.get(i);
			
			price = r.getBestCombination( desiredItems).getTotalPrice();
			
			// Initialize lowest price
			if ( lowestPrice < 0) {
				lowestPrice = price;
			}
			
			else if (price < lowestPrice) {
				lowestPrice = price;
				selectedRestaurant = r;
			}
		}

		return selectedRestaurant;
	}
	
	/**
	 * @return the restaurants
	 */
	public List<Restaurant> getRestaurants() {
		return restaurants;
	}

	/**
	 * @param restaurants the restaurants to set
	 */
	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	/**
	 * @return the desiredItems
	 */
	public List<Item> getDesiredItems() {
		return desiredItems;
	}

	/**
	 * @param desiredItems the desiredItems to set
	 */
	public void setDesiredItems(List<Item> desiredItems) {
		this.desiredItems = desiredItems;
	}
}
