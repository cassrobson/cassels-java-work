package cp213;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.print.PrinterJob;
import java.math.BigDecimal;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The GUI for the Order class.
 *
 * @author Cassel Robson
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2022-11-20
 */
@SuppressWarnings("serial")
public class OrderPanel extends JPanel {

    // Attributes
    private Menu menu = null; // Menu attached to panel.
    private final Order order = new Order(); // Order to be printed by panel.
    private HashMap<JTextField, MenuItem> store = new HashMap<JTextField, MenuItem>();
    // GUI Widgets
    private final JButton printButton = new JButton("Print");
    private final JLabel subtotalLabel = new JLabel("0");
    private final JLabel taxLabel = new JLabel("0");
    private final JLabel totalLabel = new JLabel("0");

    private JLabel nameLabels[] = null;
    private JLabel priceLabels[] = null;
    // TextFields for menu item quantities.
    private JTextField quantityFields[] = null;
    private Object f;

    /**
     * Displays the menu in a GUI.
     *
     * @param menu The menu to display.
     */
    public OrderPanel(final Menu menu) {
	this.menu = menu;
	this.nameLabels = new JLabel[this.menu.size()];
	this.priceLabels = new JLabel[this.menu.size()];
	this.quantityFields = new JTextField[this.menu.size()];
	this.layoutView();
	this.registerListeners();
    }

    /**
     * Implements an ActionListener for the 'Print' button. Prints the current
     * contents of the Order to a system printer or PDF.
     */
    private class PrintListener implements ActionListener {

	@Override
	public void actionPerformed(final ActionEvent e) {

	    final PrinterJob printj = PrinterJob.getPrinterJob();
	    printj.setPrintable(order);

	    if (printj.printDialog()) {
		try {
		    printj.print();
		} catch (final Exception printException) {
		    System.err.println(printException);
		}
	    }

	}
    }

    /**
     * Implements a FocusListener on a quantityField. Requires appropriate
     * FocusListener methods.
     */
    private class QuantityListener implements FocusListener {

	@Override
	public void focusGained(FocusEvent e) {
	    // TODO auto-generated method stub
	}

	@Override
	public void focusLost(FocusEvent e) {
	    // TODO Auto-generated method stub

	    JTextField source = (JTextField) e.getSource();

	    String quantity = source.getText();

	    int quant;

	    try {
		quant = Integer.parseInt(quantity);
	    } catch (NumberFormatException exception) {
		System.out.println("Please enter a number!");
		quant = 0;
	    }

	    MenuItem item = OrderPanel.this.store.get(source);

	    OrderPanel.this.order.update(item, quant);

	    BigDecimal subtotal = OrderPanel.this.order.getSubTotal();

	    BigDecimal taxes = OrderPanel.this.order.getTaxes();

	    BigDecimal total = OrderPanel.this.order.getTotal();

	    String format = "%6.2f";

	    OrderPanel.this.subtotalLabel.setText(String.format(format, subtotal));

	    OrderPanel.this.taxLabel.setText(String.format(format, taxes));

	    OrderPanel.this.totalLabel.setText(String.format(format, total));
	}
    }

    /**
     * Layout the panel.
     */
    private void layoutView() {
	this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	int size = this.menu.size() + 5;
	this.setLayout(new GridLayout(size, 3));
	this.add(new JLabel("Item"));
	this.add(new JLabel("Price"));
	this.add(new JLabel("Quantity"));

	JLabel priceLabel;

	JTextField quantity;

	for (int i = 0; i < menu.size(); i++) {
	    MenuItem item = menu.getItem(i);

	    this.add(new JLabel(item.getName()));

	    priceLabel = new JLabel(String.format("$%.2f", item.getPrice()));

	    priceLabel.setHorizontalAlignment(JLabel.TRAILING);

	    this.add(priceLabel);

	    quantity = new JTextField("0");

	    quantity.setName(item.getName());

	    quantity.addFocusListener(new QuantityListener());

	    this.store.put(quantity, item);

	    this.add(quantity);
	}
    }

    /**
     * Register the widget listeners.
     */
    private void registerListeners() {

	this.add(new JLabel("Subtotal:"));
	this.add(new JLabel(""));

	this.subtotalLabel.setHorizontalAlignment(JLabel.TRAILING);
	this.add(this.subtotalLabel);

	this.add(new JLabel("Tax:"));
	this.add(new JLabel(""));

	this.taxLabel.setHorizontalAlignment(JLabel.TRAILING);
	this.add(this.taxLabel);

	this.add(new JLabel("Total:"));
	this.add(new JLabel(""));

	this.totalLabel.setHorizontalAlignment(JLabel.TRAILING);
	this.add(this.totalLabel);

	this.add(new JLabel(""));

	this.printButton.addActionListener(new PrintListener());
	this.add(this.printButton);

	this.add(new JLabel(""));
	return;
    }

