package cp213;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Stores a HashMap of MenuItem objects and the quantity of each MenuItem
 * ordered. Each MenuItem may appear only once in the HashMap.
 *
 * @author Cassel Robson
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2022-11-20
 */
public class Order implements Printable {

    /**
     * The current tax rate on menu items.
     */
    public static final BigDecimal TAX_RATE = new BigDecimal(0.13);

    private Map<MenuItem, Integer> items = new HashMap<MenuItem, Integer>();

    /**
     * Increments the quantity of a particular MenuItem in an Order with a new
     * quantity. If the MenuItem is not in the order, it is added.
     *
     * @param item     The MenuItem to purchase - the HashMap key.
     * @param quantity The number of the MenuItem to purchase - the HashMap value.
     */
    public void add(final MenuItem item, final int quantity) {

	int num = 0;
	if (this.items.containsKey(item)) {
	    num = this.items.get(item);

	}
	this.items.put(item, quantity + num);

    }

    /**
     * Calculates the total value of all MenuItems and their quantities in the
     * HashMap.
     *
     * @return the total price for the MenuItems ordered.
     */
    public BigDecimal getSubTotal() {

	BigDecimal subTot = BigDecimal.valueOf(0);
	for (Map.Entry<MenuItem, Integer> entry : items.entrySet()) {
	    MenuItem i = entry.getKey();
	    Integer q = entry.getValue();
	    BigDecimal itemPrice = i.getPrice().multiply(BigDecimal.valueOf(q));
	    subTot.add(itemPrice);
	}

	return subTot;
    }

    /**
     * Calculates and returns the total taxes to apply to the subtotal of all
     * MenuItems in the order. Tax rate is TAX_RATE.
     *
     * @return total taxes on all MenuItems
     */
    public BigDecimal getTaxes() {

	BigDecimal subTot = this.getSubTotal();

	return subTot.multiply(TAX_RATE);
    }

    /**
     * Calculates and returns the total price of all MenuItems order, including tax.
     *
     * @return total price
     */
    public BigDecimal getTotal() {

	BigDecimal subTot = this.getSubTotal();
	BigDecimal taxes = this.getTaxes();

	return subTot.add(taxes);
    }

    /*
     * Implements the Printable interface print method. Prints lines to a Graphics2D
     * object using the drawString method. Prints the current contents of the Order.
     */
    @Override
    public int print(final Graphics graphics, final PageFormat pageFormat, final int pageIndex)
	    throws PrinterException {
	int result = PAGE_EXISTS;

	if (pageIndex == 0) {
	    final Graphics2D g2d = (Graphics2D) graphics;
	    g2d.setFont(new Font("MONOSPACED", Font.PLAIN, 12));
	    g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
	    // Now we perform our rendering
	    final String[] lines = this.toString().split("\n");
	    int y = 100;
	    final int inc = 12;

	    for (final String line : lines) {
		g2d.drawString(line, 100, y);
		y += inc;
	    }
	} else {
	    result = NO_SUCH_PAGE;
	}
	return result;
    }

    /**
     * Returns a String version of a receipt for all the MenuItems in the order.
     */
    @Override
    public String toString() {

	String str = "Receipt\n";
	for (Map.Entry<MenuItem, Integer> entry : items.entrySet()) {
	    MenuItem i = entry.getKey();
	    Integer q = entry.getValue();
	    BigDecimal itemPrice = i.getPrice().multiply(BigDecimal.valueOf(q));
	    str += i.getName();
	    int spaces = 15 - i.getName().length();
	    for (int j = 0; j < spaces; j++) {
		str = str + " ";
	    }
	    str += String.format("%-14s %d @ $5.2f = $6.2f\n", i.getName(), q, i.getPrice().floatValue(),
		    itemPrice.floatValue());
	}
	str += String.format("-26s$6.2f\n", "Subtotal:", this.getSubTotal().floatValue());
	str += String.format("-26s$6.2f\n", "Taxes:", this.getTaxes().floatValue());
	str += String.format("-26s$6.2f\n", "Total:", this.getTotal().floatValue());
	return str;
    }

    /**
     * Replaces the quantity of a particular MenuItem in an Order with a new
     * quantity. If the MenuItem is not in the order, it is added. If quantity is 0
     * or negative, the MenuItem is removed from the Order.
     *
     * @param item     The MenuItem to update
     * @param quantity The quantity to apply to item
     */
    public void update(final MenuItem item, final int quantity) {

	if (items.containsKey(item)) {
	    items.replace(item, quantity);

	} else {
	    items.put(item, quantity);
	}

    }
