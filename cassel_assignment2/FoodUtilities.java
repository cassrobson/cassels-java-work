package cp213;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Utilities for working with Food objects.
 *
 * @author Cassel Robson, 210507000, robs7000@mylaurier.ca
 * @version 2022-10-12
 */
public class FoodUtilities {

	/**
	 * Determines the average calories in a list of foods. No rounding necessary.
	 * Foods list parameter may be empty.
	 *
	 * @param foods a list of Food
	 * @return average calories in all Food objects
	 */
	public static int averageCalories(final ArrayList<Food> foods) {

		int calories = 0;
		int counter = 0;

		for (Food f : foods) {
			calories += f.getCalories();
			counter += 1;
		}

		int average = calories / counter;

		return average;
	}

	/**
	 * Determines the average calories in a list of foods for a particular origin.
	 * No rounding necessary. Foods list parameter may be empty.
	 *
	 * @param foods  a list of Food
	 * @param origin the origin of the Food
	 * @return average calories for all Foods of the specified origin
	 */
	public static int averageCaloriesByOrigin(final ArrayList<Food> foods, final int origin) {

		int calories = 0;
		int counter = 0;

		for (Food f : foods) {
			if (f.getOrigin() == origin) {
				calories += f.getCalories();
				counter += 1;
			}
		}

		int average = calories / counter;

		return average;
	}

	/**
	 * Creates a list of foods by origin.
	 *
	 * @param foods  a list of Food
	 * @param origin a food origin
	 * @return a list of Food from origin
	 */
	public static ArrayList<Food> getByOrigin(final ArrayList<Food> foods, final int origin) {

		final ArrayList<Food> oFoods = new ArrayList<>();
		for (Food f : foods) {
			if (f.getOrigin() == origin) {
				oFoods.add(f);
			}
		}

		return oFoods;
	}

	/**
	 * Creates a Food object by requesting data from a user. Uses the format:
	 *
	 * <pre>
	Name: name
	Origins
	 0 Canadian
	 1 Chinese
	...
	11 English
	Origin: origin number
	Vegetarian (Y/N): Y/N
	Calories: calories
	 * </pre>
	 *
	 * @param keyboard a keyboard Scanner
	 * @return a Food object
	 */
	public static Food getFood(final Scanner keyboard) {

		System.out.println("Name: ");
		String name = keyboard.nextLine();
		System.out.println(Food.originsMenu());
		System.out.println("Origin: ");
		int origin = Integer.parseInt(keyboard.nextLine());
		System.out.println("Vegetarian(Y/N): ");
		String veggie = keyboard.nextLine();
		System.out.println("Enter the calories: ");
		int calories = Integer.parseInt(keyboard.nextLine());
		boolean isVegetarian = false;
		if (veggie.equals("Y")) {
			isVegetarian = true;
		} else {
			isVegetarian = false;
		}
		Food newfood = new Food(name, origin, isVegetarian, calories);
		return newfood;
	}

	/**
	 * Creates a list of vegetarian foods.
	 *
	 * @param foods a list of Food
	 * @return a list of vegetarian Food
	 */
	public static ArrayList<Food> getVegetarian(final ArrayList<Food> foods) {

		final ArrayList<Food> oFoods = new ArrayList<>();
		for (Food f : foods) {
			if (f.isVegetarian() == true) {
				oFoods.add(f);
			}
		}

		return oFoods;
	}

	/**
	 * Creates and returns a Food object from a line of string data.
	 *
	 * @param line a vertical bar-delimited line of food data in the format
	 *             name|origin|isVegetarian|calories
	 * @return the data from line as a Food object
	 */
	public static Food readFood(final String line) {

		String[] parts = line.split("\\|");
		String name = parts[0];
		int origin = Integer.parseInt(parts[1]);
		boolean isVegetarian = Boolean.parseBoolean(parts[2]);
		int calories = Integer.parseInt(parts[3]);

		return new Food(name, origin, isVegetarian, calories);
	}

	/**
	 * Reads a file of food strings into a list of Food objects.
	 *
	 * @param fileIn a Scanner of a Food data file in the format
	 *               name|origin|isVegetarian|calories
	 * @return a list of Food
	 */
	public static ArrayList<Food> readFoods(final Scanner fileIn) {

		final ArrayList<Food> oFoods = new ArrayList<>();
		Scanner line = fileIn;
		while (line.hasNextLine()) {
			Food f = readFood(line.nextLine());
			oFoods.add(f);
		}

		return oFoods;
	}

	/**
	 * Searches for foods that fit certain conditions.
	 *
	 * @param foods        a list of Food
	 * @param origin       the origin of the food; if -1, accept any origin
	 * @param maxCalories  the maximum calories for the food; if 0, accept any
	 * @param isVegetarian whether the food is vegetarian or not; if false accept
	 *                     any
	 * @return a list of foods that fit the conditions specified
	 */
	public static ArrayList<Food> foodSearch(final ArrayList<Food> foods, final int origin, final int maxCalories,
			final boolean isVegetarian) {

		final ArrayList<Food> oFoods = new ArrayList<>();
		for (Food f : foods) {
			if (f.getOrigin() == origin) {
				if (f.isVegetarian() == isVegetarian) {
					if (f.getCalories() < maxCalories) {
						oFoods.add(f);
					}
				}
			}
		}

		return oFoods;
	}

	/**
	 * Writes the contents of a list of Food to a PrintStream.
	 *
	 * @param foods a list of Food
	 * @param ps    the PrintStream to write to
	 */
	public static void writeFoods(final ArrayList<Food> foods, PrintStream ps) {

		PrintStream write = new PrintStream(ps);
		for (Food f : foods) {
			f.write(ps);
			ps.append("\n");
		}

	}
}
