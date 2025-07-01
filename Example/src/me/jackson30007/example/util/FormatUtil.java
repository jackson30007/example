package me.jackson30007.example.util;

public class FormatUtil {
	public static String cleanNumber(double number) {
		return cleanNumber(number, 2);
	}
	
	public static String cleanNumber(double number, int decimals) {
		if (number == (int) number) {
			return String.format("%,d", (int) number);
		}else{
			double cropped = cropDouble(number, decimals);
			if (cropped == (int) number) return cleanNumber(cropped);
			String s = String.format("%,." + decimals + "f", number);
			while (s.endsWith("0")) {
				s = s.substring(0, s.length() - 1);
			}
			return s;
		}
	}
	
	public static double cropDouble(double d, int maxDecimals) {
		if (maxDecimals <= 0) {
			return (int) d;
		}else if (maxDecimals > 8) {
			maxDecimals = 8;
		}
		int divisor = (int) Math.pow(10, maxDecimals);
		int dInt = (int) (d * divisor);
		return ((double) dInt / divisor);
	}
}
