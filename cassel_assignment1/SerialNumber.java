package cp213;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Cassel Robson, 210507000
 * @version 2022-09-27
 */
public class SerialNumber {

	/**
	 * Determines if a string contains all digits.
	 *
	 * @param str The string to test.
	 * @return true if str is all digits, false otherwise.
	 */
	public static boolean allDigits(final String str) {

		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i)))

			{
				return false;
			}

		}

		return true;
	}

	/**
	 * Determines if a string is a good serial number. Good serial numbers are of
	 * the form 'SN/nnnn-nnn', where 'n' is a digit.
	 *
	 * @param sn The serial number to test.
	 * @return true if the serial number is valid in form, false otherwise.
	 */
	public static boolean validSn(final String sn) {
		boolean answer = true;
		if (sn.charAt(7) == '-') {
			answer = true;
		} else {
			answer = false;
		}
		return answer;
	}

	/**
	 * Evaluates serial numbers from a file. Writes valid serial numbers to
	 * good_sns, and invalid serial numbers to bad_sns.
	 *
	 * @param fileIn  a file already open for reading
	 * @param goodSns a file already open for writing
	 * @param badSns  a file already open for writing
	 */
	public static void validSnFile(final Scanner fileIn, final PrintStream goodSns, final PrintStream badSns) {
		PrintStream destination1 = goodSns;
		PrintStream ps = new PrintStream(destination1);
		PrintStream destination2 = badSns;
		PrintStream ps2 = new PrintStream(destination2);
		Scanner scanner = fileIn;
		while (scanner.hasNextLine()) {
			String serial = scanner.nextLine();
			if (serial.charAt(7) == '-') {
				ps.println(serial);
			} else {
				ps2.println(serial);
			}
		}

		return;
	}

}
