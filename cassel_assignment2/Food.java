package cp213;

import java.io.PrintStream;

/**
 * Food class definition.
 *
 * @author Cassel Robson, 210507000, robs7000@mylaurier.ca
 * @version 2022-10-12
 */
public class Food implements Comparable<Food> {

	// Constants
	public static final String ORIGINS[] = { "Canadian", "Chinese", "Indian", "Ethiopian", "Mexican", "Greek",
			"Japanese", "Italian", "Moroccan", "Scottish", "Columbian", "English" };

	/**
	 * Creates a string of food origins in the format:
	 *
	 * <pre>
	Origins
	0 Canadian
	1 Chinese
	...
	11 English
	 * </pre>
	 *
	 * @return A formatted numbered string of valid food origins.
	 */
	public static String originsMenu() {

		int length = ORIGINS.length;
		for (int i = 0; i < length; i++) {
			System.out.printf("%2d " + ORIGINS[i] + "\n", i);
		}

		return null;
	}

	// Attributes
	private String name = null;
	private int origin = 0;
	private boolean isVegetarian = false;
	private int calories = 0;

	/**
	 * Food constructor.
	 *
	 * @param name         food name
	 * @param origin       food origin code
	 * @param isVegetarian whether food is vegetarian
	 * @param calories     caloric content of food
	 */
	public Food(final String name, final int origin, final boolean isVegetarian, final int calories) {

		this.name = name;
		this.origin = origin;
		this.isVegetarian = isVegetarian;
		this.calories = calories;

		return;
	}

	/*
	 * (non-Javadoc) Compares this food against another food.
	 *
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	/**
	 * Foods are compared by name, then by origin if the names match. Must ignore
	 * case.
	 */
	@Override
	public int compareTo(final Food target) {
		/**
		 * int indexThis = Arrays.asList(ORIGINS).indexOf(this.origin); int indexTarget
		 * = Arrays.asList(ORIGINS).indexOf(target.origin); int result = 0; result =
		 * name.compareToIgnoreCase(target.name); if (result == 0) { if (indexThis ==
		 * indexTarget) { result = 0; } else if (indexThis > indexTarget) { result = 1;
		 * } else { result = -1; } } return result; }
		 */

		int result = 0;
		if (this.name == target.name) {
			if (this.origin > target.origin) {
				result = 1;
			} else if (this.origin < target.origin) {
				result = -1;
			}
		} else {
			if (this.origin > target.origin) {
				result = 1;
			} else if (this.origin < target.origin) {
				result = -1;
			}
		}
		return result;
	}

	/**
	 * Getter for calories attribute.
	 *
	 * @return calories
	 */
	public int getCalories() {

		return this.calories;
	}

	/**
	 * Getter for name attribute.
	 *
	 * @return name
	 */
	public String getName() {

		return this.name;
	}

	/**
	 * Getter for origin attribute.
	 *
	 * @return origin
	 */
	public int getOrigin() {

		// your code here

		return this.origin;
	}

	/**
	 * Getter for string version of origin attribute.
	 *
	 * @return string version of origin
	 */
	public String getOriginString() {

		String originstring = ORIGINS[origin];
		return originstring;
	}

	/**
	 * Getter for isVegetarian attribute.
	 *
	 * @return isVegetarian
	 */
	public boolean isVegetarian() {

		return this.isVegetarian;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object//toString() Creates a formatted string of food data.
	 */
	/**
	 * Returns a string version of a Food object in the form:
	 *
	 * <pre>
	Name:       name
	Origin:     origin string
	Vegetarian: true/false
	Calories:   calories
	 * </pre>
	 */
	@Override
	public String toString() {

		String string = "";
		string += "Name:        " + getName() + "\r\nOrigin:      " + getOriginString() + "\r\nVegetarian:  "
				+ isVegetarian() + "\r\nCalories:    " + getCalories();
		return string;
	}

	/**
	 * Writes a single line of food data to an open PrintStream. The contents of
	 * food are written as a string in the format name|origin|isVegetarian|calories
	 * to ps.
	 *
	 * @param ps The PrintStream to write to.
	 */
	public void write(final PrintStream ps) {

		String out = getName() + "|" + getOriginString() + "|" + isVegetarian() + "|" + getCalories();
		ps.append(out);

		return;
	}

}
