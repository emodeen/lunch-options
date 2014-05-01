package com.emodeen.codingchallenges.restaurants;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Eric
 * This is the main class for a program that determines the best combination of products to purchase from a set of restaurants.
 * The combination of products must contain the desired set of items at the lowest cost.
 * To run the program, run this class, passing the absolute path of the input file as args[0], and the absolute path of the output file as args[1].
 */
public class LunchOptions {
	
	// The restaurants to choose from.
	private List<Restaurant> restaurants;

	// The items that the customer would like to have.
	private List<Item> desiredItems;
	
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
	 * Read all rows from the file, storing the data in the Restaurant objects.
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
	
	/**
	 * Adds the data in a row to a temporary list of restaurants.
	 * @param rest The temporary list of restaurants.
	 * @param price The price of the product.
	 * @param restaurantID The ID for the restaurant.
	 * @param items The items included in the product.
	 */
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
	 * @return The cheapest product combination at a single restaurant that contains all desired items.
	 * @param validDeals. A list of the deals that contain all desired items.
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
	 * @param allCombinations All possible product combinations within each restaurant.
	 * @return The product combinations that contain all desired foods.
	 */
	private List<ProductCombination> getValidDeals( List<ProductCombination> allCombinations) {
		
		List<ProductCombination> validDeals = new ArrayList<ProductCombination>();

		Product prod = null;
		ProductCombination tempCombo = null;
		Item item = null;
		Item desiredItem = null;
		
		// An array of boolean flags to show if a desired food item has been found.
		boolean [] itemFound = new boolean[desiredItems.size()];
		boolean containsDesiredFoods = true;
		
		// For each product combination
		for (int h=0; h < allCombinations.size(); h++) {
		
			containsDesiredFoods = true;
			tempCombo = allCombinations.get(h);

			// Reset array elements before each pass
			Arrays.fill(itemFound, false);
			
			// For each product in the combination.
			for (int i=0; i < tempCombo.getProducts().size(); i++) {

				// A single product
				prod = tempCombo.getProducts().get(i);
				
				// For each of the desired items
				for (int j=0; j < desiredItems.size(); j++) {
					
					// Check to see that the product contains all desired foods.
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
			
			// If at least one desired food is not present, flag the product combination as invalid.
			for (int l=0; l < itemFound.length; l++) {
				
				if (!itemFound[l]) {
					containsDesiredFoods = false;
					break;
				}
			}
			
			// If the product combination contains all desired foods, add it to the list of valid deals.
			if (containsDesiredFoods) {
				validDeals.add( tempCombo);
			}
		}
			
		return validDeals;
	}
	
	/**
	 * 
	 * @return All product combinations within each restaurant.
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
	 * @return the restaurants
	 */
	List<Restaurant> getRestaurants() {
		return restaurants;
	}

	/**
	 * @param restaurants the restaurants to set
	 */
	void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	/**
	 * @return the desiredItems
	 */
	List<Item> getDesiredItems() {
		return desiredItems;
	}

	/**
	 * @param desiredItems the desiredItems to set
	 */
	void setDesiredItems(List<Item> desiredItems) {
		this.desiredItems = desiredItems;
	}
}
