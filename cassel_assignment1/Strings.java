package cp213;

/**
 * @author Cassel Robson, 210507000
 * @version 2022-09-27
 */
public class Strings {
	// Constants
	public static final String VOWELS = "aeiouAEIOU";

	/**
	 * Determines if string is a "palindrome": a word, verse, or sentence (such as
	 * "Able was I ere I saw Elba") that reads the same backward or forward. Ignores
	 * case, spaces, digits, and punctuation in the string parameter s.
	 *
	 * @param string a string
	 * @return true if string is a palindrome, false otherwise
	 */
	public static boolean isPalindrome(final String string) {

		if (string.isEmpty())
			return true;

		String str_lower = string.toLowerCase();

		str_lower = str_lower.replaceAll("[^a-zA-Z0-9]", "");

		StringBuilder revstring = new StringBuilder(str_lower);
		revstring.reverse();
		String rstr = revstring.toString();

		if (str_lower.equals(rstr))
			return true;

		return false;

	}

	/**
	 * Determines if name is a valid Java variable name. Variables names must start
	 * with a letter or an underscore, but cannot be an underscore alone. The rest
	 * of the variable name may consist of letters, numbers and underscores.
	 *
	 * @param name a string to test as a Java variable name
	 * @return true if name is a valid Java variable name, false otherwise
	 */
	public static boolean isValid(final String name) {
		boolean valid = false;
		if (name.length() > 0) {
			if (!Character.isDigit(name.charAt(0))) {
				valid = true;
			}
		}

		return valid;
	}

	/**
	 * Converts a word to Pig Latin. The conversion is:
	 * <ul>
	 * <li>if a word begins with a vowel, add "way" to the end of the word.</li>
	 * <li>if the word begins with consonants, move the leading consonants to the
	 * end of the word and add "ay" to the end of that. "y" is treated as a
	 * consonant if it is the first character in the word, and as a vowel for
	 * anywhere else in the word.</li>
	 * </ul>
	 * Preserve the case of the word - i.e. if the first character of word is
	 * upper-case, then the new first character should also be upper case.
	 *
	 * @param word The string to convert to Pig Latin
	 * @return the Pig Latin version of word
	 */
	public static String pigLatin(String word) {
		String newstring = "";
		if (VOWELS.indexOf(word.charAt(0)) > -1) {
			newstring = word + "way";
		} else {
			newstring = word.substring(1) + word.charAt(0) + "ay";
		}
		if (Character.isUpperCase(word.charAt(0))) {
			newstring = newstring.substring(0, 1).toUpperCase() + newstring.substring(1).toLowerCase();
		}
		for (int i = 1; i < newstring.length(); i++) {
			if (Character.isUpperCase(newstring.charAt(i))) {
				Character.toLowerCase(newstring.charAt(i));
			}
		}
		return newstring;
	}

}
