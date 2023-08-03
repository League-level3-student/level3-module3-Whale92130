package _00_Intro_To_String_Methods;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Base64;

/*
 * Visit the JavaDocs for the String class to view everything you can do with a String.
 * https://docs.oracle.com/javase/7/docs/api/java/lang/String.html
 * https://docs.oracle.com/javase/7/docs/api/java/lang/Character.html
 *
 * HINT:  Here are some String methods you might find useful 
 * contains
 * replace
 * trim
 * length
 * getBytes
 * endsWith
 * split 
 * indexOf
 * lastIndexOf
 * compareTo(IgnoreCase)
 * substring
 * toUpperCase/toLowerCase
 * valueOf
 *
 * Here are some Character methods you might find useful:
 * Character.toLowerCase(char c);
 * Character.toUpperCase(char c);
 * Character.isLetter(char c);
 * Character.isDigit(char c);
 * Character.getNumericValue(char c);
 */

public class _01_StringMethods {

	// Given Strings s1 and s2, return the longer String
	public static String longerString(String s1, String s2) {
		if (s1.length() > s2.length()) {
			return s1;
		} else {
			return s2;
		}
	}

	// If String s contains the word "underscores", change all of the spaces
	// to underscores
	public static String formatSpaces(String s) {
		if (s.contains("underscores")) {
			s = s.replace(' ', '_');
		}
		return s;
	}
	
//	char[] chars = s.toCharArray();
//	if (s.contains("underscores")) {
//		System.out.println("has underscores");
//		
//		for (int i = 0; i < chars.length; i++) {
//			if (chars[i] == ' ') {
//				chars[i] = '_';
//			}
//		}
//	}
//	String k = "";
//	for (int i = 0; i < chars.length; i++) {
//		k = k+chars[i];
//	}
	// Return the name of the person whose LAST name would appear first if they
	// were in alphabetical order.
	// You cannot assume there are no extra spaces around the name, but you can
	// assume there is only one space between the first and last name
	public static String lineLeader(String s1, String s2, String s3) {
		String total = "";
		char temp;
		String a = s1.trim();
		String b = s2.trim();
		String c = s3.trim();
		char[] name1 = a.toCharArray();
		char[] name2 = b.toCharArray();
		char[] name3 = c.toCharArray();
		System.out.println();

		if (name1[name1.length - 1] < name2[name2.length - 1]) {
			temp = name1[name1.length - 1];
			total = a;
			if (name3[name3.length - 1] < temp) {
				temp = name3[name3.length - 1];
				total = c;
			}
		} else {
			temp = name2[name2.length - 1];
			total = b;
			if (name3[name3.length - 1] < temp) {
				temp = name3[name3.length - 1];
				total = c;
			}
		}
		return total;
	}

	// Return the sum of all numerical digits in the String
	public static int numeralSum(String s) {
		int total = 0;
		char[] nums = s.toCharArray();
		for (int i = 0; i < nums.length; i++) {
			if (Character.isDigit(nums[i])) {
				total = total + Character.getNumericValue(nums[i]);
			}
		}
		return total;
	}

	// Return the number of times String substring appears in String s
	public static int substringCount(String s, String substring) {
		String removedSubstring = s.replace(substring, "");
		int numOccurances = (s.length() - removedSubstring.length()) / substring.length();
		return numOccurances;
	}

	// Call Utilities.encrypt at the bottom of this file to encrypt String s
	public static String encrypt(String s, char key) {
		Utilities util = new Utilities();
		Charset charset = Charset.forName("UTF-8");
		String k = Character.toString(key);
		byte[] bytes = k.getBytes();
		byte by = bytes[0];
		return util.encrypt(s.getBytes(), by);
	}

	// Call Utilities.decrypt at the bottom of this file to decrypt the
	// cyphertext (encrypted text)
	public static String decrypt(String s, char key) {
		Utilities util = new Utilities();
		Charset charset = Charset.forName("UTF-8");
		String k = Character.toString(key);
		byte[] bytes = k.getBytes();
		byte by = bytes[0];
		return util.decrypt(s, by);
	}

	// Return the number of words in String s that end with String substring
	// You can assume there are no punctuation marks between words
	public static int wordsEndsWithSubstring(String s, String substring) {
		String[] words = s.split(" ");
		int total = 0;
		for (int i = 0; i < words.length; i++) {
			if (words[i].endsWith(substring)) {
				total++;
			}
		}
		return total;
	}

	// Given String s, return the number of characters between the first
	// occurrence of String substring and the final occurrence
	// You can assume that substring will appear at least twice
	public static int distance(String s, String substring) {
		int a = s.indexOf(substring);
		int b = s.lastIndexOf(substring);
		int total1 = a + substring.length();
		int total2 = b;
		return total2 - total1;
	}

	// Return true if String s is a palindrome
	// palindromes are words or phrases are read the same forward as backward.
	// HINT: ignore/remove all punctuation and spaces in the String
	public static boolean palindrome(String s) {

		String a = "";
		String b = "";
		s = s.replace('.', ' ');
		s = s.replace(',', ' ');
		s = s.replace(':', ' ');
		s = s.replace('?', ' ');
		s = s.replace('!', ' ');
		s = s.replace('-', ' ');
		s = s.trim();
		char[] words = s.toCharArray();
		char[] wordsRev = new char[words.length];
		for (int i = 0; i < words.length; i++) {
			wordsRev[i] = words[words.length - 1 - i];
		}
		for (int o = 0; o < words.length; o++) {
			a = a + words[o];
		}
		for (int p = 0; p < wordsRev.length; p++) {
			b = b + wordsRev[p];
		}
		a=a.replaceAll(" ", "");
		b=b.replaceAll(" ", "");
		System.out.println(a);
		System.out.println(b);
		if (a.equalsIgnoreCase(b)) {
			return true;
		} else {
			return false;
		}
	}
}

class Utilities {
	// This basic encryption scheme is called single-byte xor. It takes a
	// single byte and uses exclusive-or on every character in the String.
	public static String encrypt(byte[] plaintext, byte key) {
		for (int i = 0; i < plaintext.length; i++) {
			plaintext[i] = (byte) (plaintext[i] ^ key);
		}
		return Base64.getEncoder().encodeToString(plaintext);
	}

	public static String decrypt(String cyphertext, byte key) {
		byte[] b = Base64.getDecoder().decode(cyphertext);
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) (b[i] ^ key);
		}
		return new String(b);
	}
}
