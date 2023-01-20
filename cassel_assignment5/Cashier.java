package cp213;

import java.util.Scanner;

/**
 * Wraps around an Order object to ask for MenuItems and quantities.
 *
 * @author Cassel Robson
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2022-11-20
 */
public class Cashier {

    // Attributes
    private Menu menu = null;

    /**
     * Constructor.
     *
     * @param menu A Menu.
     */
    public Cashier(Menu menu) {
	this.menu = menu;
    }

    /**
     * Prints the menu.
     */
    private void printCommands() {
	System.out.println("\nMenu:");
	System.out.println(menu.toString());
	System.out.println("Press 0 when done.");
	System.out.println("Press any other key to see the menu again.\n");
    }

    /**
     * Asks for commands and quantities. Prints a receipt when all orders have been
     * placed.
     *
     * @return the completed Order.
     */
    public Order takeOrder() {
	System.out.println("Welcome to WLU Foodorama!");
	this.printCommands();
	Scanner keyboard = new Scanner(System.in);
	int size = this.menu != null ? this.menu.size() : 0;
	int comm;
	Order order = new Order();

	do {
	    System.out.print("Command: ");
	    comm = this.getCommand(keyboard);
	    if ((1 <= comm) && (comm <= size)) {
		MenuItem item = menu.getItem(comm - 1);
		System.out.print("How many do you want? ");
		int quantity = this.getCommand(keyboard);

		if (quantity > 0) {
		    order.add(item, quantity);

		}

	    } else if (comm != 0) {
		this.printCommands();
	    }
	} while (comm != 0);
	System.out.println("----------------------------------------");

	System.out.println("Receipt");

	System.out.println(order.toString());

	return order;
    }

    private int getCommand(Scanner in) {
	int num = 0;
	String command = in.next();
	in.close();
	if (command == "") {
	    return 0;

	}
	if (isNumeric(command)) {
	    num = Integer.parseInt(command);
	    if (num >= 1 && num <= menu.size()) {
		return num;
	    }
	} else {
	    return -99;
	}
	if (num == 0) {
	    return 0;
	}
	return -1;
    }

    private static boolean isNumeric(String str) {
	if (str == null) {
	    return false;

	}
	try {
	    double dub = Double.parseDouble(str);
	} catch (NumberFormatException e) {
	    return false;
	}
	return true;
    }

    private int getQuantity() {
	Scanner in = new Scanner(System.in);
	int num = 0;
	String command = in.next();
	in.close();
	if (isNumeric(command)) {
	    num = Integer.parseInt(command);
	    if (num >= 0) {
		return num;
	    }

	} else {
	    return -1;
	}
	return -1;
    }

