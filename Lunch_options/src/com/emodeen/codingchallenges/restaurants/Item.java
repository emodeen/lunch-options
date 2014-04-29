/**
 * 
 */
package com.emodeen.codingchallenges.restaurants;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Eric
 *
 */
public class Item {

	private FoodType foodType;
	private String name;
	
	/**
	 * @param price
	 * @param restaurantID
	 * @param items
	 * @param name
	 */
	Item( String name) {

		this.name = name;
		
		// Set the food type based on the food's name.
		switch (name) {
		case "burger": foodType = FoodType.BURGER;
			break;
		case "fries": foodType = FoodType.FRIES;
			break;
		case "salad": foodType = FoodType.FRIES;
			break;
		default: foodType = null;
			break;
		}
	}

	/**
	 * @return the foodType
	 */
	public FoodType getFoodType() {
		return foodType;
	}

	/**
	 * @param foodType the foodType to set
	 */
	public void setFoodType(FoodType foodType) {
		this.foodType = foodType;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
