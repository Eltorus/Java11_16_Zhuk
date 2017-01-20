package by.tc.eq.service.validation;

public class Validation {// если все методы класса статические, то тогда почему ты разрешаешь создать объект этого класса?
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
