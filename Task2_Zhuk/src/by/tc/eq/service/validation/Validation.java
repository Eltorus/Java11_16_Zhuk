package by.tc.eq.service.validation;

public class Validation {
	public static boolean lineisProper(String line) {
		boolean result = false;
		if ((line == null) || (line.isEmpty())) {
			result = false;
		} else {
			result = true;
		}
		return result;
	}

	public static boolean intNumisProper(int number) {
		boolean result = false;
		if (number == 0 || number < 0) {
			result = false;
		} else {
			result = true;
		}
		return result;
	}
}
