package cp213;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 * Stores a List of MenuItems and provides a method return these items in a
 * formatted String. May be constructed from an existing List or from a file
 * with lines in the format:
 *
 * <pre>
1.25 hot dog
10.00 pizza
...
 * </pre>
 *
 * @author Cassel Robson
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2022-11-20
 */
public class Menu {

    // Attributes.

    private ArrayList<MenuItem> items = new ArrayList<MenuItem>();

    /**
     * Creates a new Menu from an existing Collection of MenuItems. MenuItems are
     * copied into the Menu List.
     *
     * @param items an existing Collection of MenuItems.
     */
    public Menu(Collection<MenuItem> items) {
	this.items.addAll(items);

    }

    /**
     * Constructor from a Scanner of MenuItem strings. Each line in the Scanner
     * corresponds to a MenuItem. You have to read the Scanner line by line and add
     * each MenuItem to the List of items.
     *
     * @param fileScanner A Scanner accessing MenuItem String data.
     */
    public Menu(Scanner fileScanner) {

	while (fileScanner.hasNextLine()) {
	    Double price = fileScanner.nextDouble();
	    String name = fileScanner.nextLine();
	    name = name.trim();
	    MenuItem item = new MenuItem(name, price);
	    this.items.add(item);
	}

    }

    /**
     * Returns the List's i-th MenuItem.
     *
     * @param i Index of a MenuItem.
     * @return the MenuItem at index i
     */
    public MenuItem getItem(int i) {

	MenuItem men = items.get(i);

	return men;
    }

    /**
     * Returns the number of MenuItems in the items List.
     *
     * @return Size of the items List.
     */
    public int size() {

	int s = items.size();

	return s;
    }

    /**
     * Returns the Menu items as a String in the format:
     *
     * <pre>
    5) poutine      $ 3.75
    6) pizza        $10.00
     * </pre>
     *
     * where n) is the index + 1 of the MenuItems in the List.
     */
    @Override
    public String toString() {

	String itemls = "";
	String form = "%2d)%-13s $%5.2f";
	for (int i = 0; i < items.size(); i++) {
	    MenuItem item = this.items.get(i);
	    itemls += String.format(form, (i + 1), item.getName(), item.getPrice());
	    itemls += "\n";
	}

	return itemls;
    }
