package be.vdab.util;

import java.math.BigDecimal;

public class StringUtils {
	public static boolean isLong(String string) {
		try {
			Long.parseLong(string);
			return true;
		} catch (NullPointerException | NumberFormatException ex) {
			return false;
		}
	}
	
	public static boolean isGeldigAantal(String string) {
		try {
			int getal = Integer.parseInt(string);
			if (getal <= 0) {
				return false;
			}
			return true;
		} catch (NullPointerException | NumberFormatException ex) {
			return false;
		}
	}

	public static boolean isBigDecimal(String string) {
		try {
			new BigDecimal(string);
			return true;
		} catch (NullPointerException | NumberFormatException ex) {
			return false;
		}
	}
}
