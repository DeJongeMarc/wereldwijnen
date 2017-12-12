package be.vdab.util;

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
	
	public static boolean isGeldigString(String string) {
		if (string == null || string.trim().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public static boolean isGeldigBestelwijze(String string) {
		try {
			int getal = Integer.parseInt(string);
			if (getal < 0 | getal > 1) {
				return false;
			}
		} catch (NullPointerException | NumberFormatException ex) {
			return false;
		}
		return true;
	}

}
