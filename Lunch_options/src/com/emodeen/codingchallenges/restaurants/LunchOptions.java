/**
 * 
 */
package com.emodeen.codingchallenges.restaurants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author Eric
 *
 */
public class LunchOptions {
	
	private List<Restaurant> restaurants;
	private List<Item> desiredItems;
	
	
	/**
	 * @param restaurants
	 * @param desiredItems
	 */
	LunchOptions() {
		
		super();
		this.restaurants = new ArrayList<Restaurant>();
		this.desiredItems = new ArrayList<Item>();
	}



	/**
	 * @param args
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
					
					productItems.add( new Item(rowTokens[j]));
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
	 * @param productOptions
	 * @param desiredItems
	 * @return
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
	 * Check if the combo contains the desired foods.
	 */
	private void validateCombination() {
		
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
