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
		
		List<ProductCombination> allCombinations = lo.getAllProductCombinations();
		List<ProductCombination> validDeals = lo.getValidDeals( allCombinations);
		ProductCombination bestDeal = lo.getBestDeal( validDeals);
		
		String outputStr = new StringBuffer( bestDeal.getRestaurantID()).append(",").append( bestDeal.getTotalPrice()).toString();
		
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
		List<Restaurant> tempRestaurants = new ArrayList<Restaurant>();
		
		// For each row in the file
		for (int i=0; i < rows.size(); i++) {
			
			row = rows.get(i);
			
			// If it is not the last row, add a product to a restaurant.
			if (i != (rows.size()-1)) {
				
				rowTokens = row.split(",");
				restaurantID = rowTokens[0].replace("\"", "");
				price = new BigDecimal(rowTokens[1]);
				productItems = new ArrayList<Item>();
				
				// Store remaining tokens in list of items for a product.
				for (int j=2; j < rowTokens.length; j++) {
					
					productItems.add( new Item(rowTokens[j].replace("\"", "")));
				}
				
				addToTempRestaurants( tempRestaurants, price, restaurantID, productItems);
				
				//storeRowData( price, restaurantID, productItems);
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
		
		// Once all rows have been traversed, add the Restaurants to the main restaurant list.
		for (int z=0; z < tempRestaurants.size(); z++) {
			
			restaurants.add( tempRestaurants.get(z));
		}
	}
	
	private void addToTempRestaurants( List<Restaurant> rest, BigDecimal price, String restaurantID, List<Item> items) {
		
		boolean restaurantFound = false;

		// See if the restaurant has already been added to temp restaurant list.
		for (int i=0; i < rest.size(); i++) {
			
			if (rest.get(i).getId().equals( restaurantID)) {

				restaurantFound = true;
				rest.get(i).addProduct( price, items);
				break;
			}
		}
		
		if (!restaurantFound) {
			
			Restaurant newRest = new Restaurant( restaurantID);
			newRest.addProduct( price, items);
			rest.add( newRest);
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
			r.addProduct( price, items);
			restaurants.add( r);
		}
		
		else {
			r.addProduct( price, items);
		}
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
				r = null;
				break;
			}
		}
		
		return r;
	}

	/**
	 * 
	 * @return The cheapest product combination among all restaurants.
	 * @param desiredItems. The items that the customer wants to purchase.
	 */
	private ProductCombination getBestDeal( List<ProductCombination> validDeals) {
		
		ProductCombination bestDeal = null;
		
		int lowestPrice = -1;
			
		for (int i=0; i < validDeals.size(); i++) {

			// Initialize lowest price
			if (lowestPrice < 1) {
				lowestPrice = validDeals.get(i).getTotalPrice();
				bestDeal = validDeals.get(i);
			}
				
			if (validDeals.get(i).getTotalPrice() < lowestPrice) {
				lowestPrice = validDeals.get(i).getTotalPrice();
				bestDeal = validDeals.get(i);
			}
		}
		
		return bestDeal;
	}
	
	/**
	 * Reduce the list of all combinations down to the combinations that contain all desired foods.
	 */
	private List<ProductCombination> getValidDeals( List<ProductCombination> allCombinations) {
		
		List<ProductCombination> validDeals = new ArrayList<ProductCombination>();

		Product prod = null;
		ProductCombination tempCombo = null;
		Item item = null;
		Item desiredItem = null;
		boolean [] itemFound = new boolean[desiredItems.size()];
		boolean containsDesiredFoods = true;
		
		// For each product combination
		for (int h=0; h < allCombinations.size(); h++) {
		
			containsDesiredFoods = true;
			tempCombo = allCombinations.get(h);
			
			// For each product in the combination.
			for (int i=0; i < tempCombo.getProducts().size(); i++) {

				// A single product
				prod = tempCombo.getProducts().get(i);
				
				// For each of the desired items
				for (int j=0; j < desiredItems.size(); j++) {
					
					// For each item in the product
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
			
			// If all items found, add combination to validDeals.
			for (int l=0; l < itemFound.length; l++) {
				
				if (!itemFound[l]) containsDesiredFoods = false;
				break;
			}
			
			if (containsDesiredFoods) {
				validDeals.add( tempCombo);
			}
		}
			
		return validDeals;
	}
	
	/**
	 * 
	 * @return All product combinations among all restaurants.
	 * @param desiredItems. The items that the customer wants to purchase.
	 */
	private List<ProductCombination> getAllProductCombinations() {
		
		List<ProductCombination> allCombinations = new ArrayList<ProductCombination>();
		
		// For each restaurant, get all product combinations.
		for (int i=0; i < restaurants.size(); i++) {
			
			List<ProductCombination> combinations = restaurants.get(i).getCombinations2();
			
			// Copy all combinations from the restaurant into allCombinations.
			for (int j=0; j < combinations.size(); j++) {
				
				allCombinations.add( combinations.get(j));
			}
		}
		
		return allCombinations;
	}	
	
	/**
	 * TODO: Change this method to return a List of valid combinations.
	 * 
	 * 
	 * This method finds the product combinations that contain the desired foods.
	 * @param combination A combination from which to find smaller sets of combinations recursively.
	 * @param size The size of the subset to find combinations of.
	 * @param desiredItems. The items that the customer wants to purchase.
	private List<ProductCombination> findValidCombinations( ProductCombination combination) {
		
		ProductCombination tempCombo = combination;
		ProductCombination validCombinations = null;
		
		if (combination.isValid(desiredItems)) {
			
			validCombinations.add( combination);
		}
		
		// Base case: if the subset has only only product
		if (combination.getProducts().size() == 1) {
			return;
		}
		
		for (int i=0; i < combination.getProducts().size(); i++) {
		
			tempCombo.getProducts().remove(i);
			
			findValidCombinations( tempCombo, desiredItems);
		}
		
		return validCombinations;
	}
	*/
	
	/**
	 * Calculate which restaurant offers the lowest price for the list of desired items.
	 * @return A Restaurant object.
	 
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
