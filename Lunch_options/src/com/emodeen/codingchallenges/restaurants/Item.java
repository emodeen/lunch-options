/**
 * 
 */
package com.emodeen.codingchallenges.restaurants;

/**
 * @author Eric
 * This class represents a food item included in a product that can be purchased.
 */
public class Item {

	private FoodType foodType;
	private String name;
	
	/**
	 * @param name The name of the item.
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
