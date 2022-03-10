package by.petrovich.storage;

import java.util.regex.Pattern;

import by.petrovich.storage.entity.EmployeePosition;
import by.petrovich.storage.entity.User;
import by.petrovich.storage.entity.UserRole;

public class main {

	public static void main(String[] args) {
//	int ordinalNum = EmployeePosition.ENGINEERING_TECHNICIAN.ordinal();
//	int ordinalNum1 = EmployeePosition.CNC_PROGRAMMER.ordinal()+1;
//	ordinalNum++;
//	ordinalNum1++;
//			System.out.println("ordinalNum: " + ordinalNum + " ordinalNum1: " + ordinalNum1);

//		String PASSWORD_PATTERN = "^\\p{Alpha}{1,}\\p{Digit}{1,}\\p{Punct}{1,}{8,40}+$";
//		String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z,A-Z])(?=.*[!?@#$%^&+=,;:_*()]).{8,40}$";
//		String passwordWithoutPunct = "efweferg2324";
//		String passwordWithoutDigit = "!!dgergergw";
//		String passwordWithoutAlpha = "2325%42123!";
//		String passwordLess = "rg15!%f";
//		String passwordMore = "145waasdf!wwf2efwef%#414sdfjkhsad987asfsafasf";
//		String passwordValid = "jhwdk234!@";
//
//		System.out.println("passwordWithoutPunct: " + Pattern.matches(PASSWORD_PATTERN, passwordWithoutPunct));
//		System.out.println("passwordWithoutDigit: " + Pattern.matches(PASSWORD_PATTERN, passwordWithoutDigit));
//		System.out.println("passwordWithoutAlpha: " + Pattern.matches(PASSWORD_PATTERN, passwordWithoutAlpha));
//		System.out.println(Pattern.matches(PASSWORD_PATTERN, passwordLess));
//		System.out.println(Pattern.matches(PASSWORD_PATTERN, passwordMore));
//		System.out.println(Pattern.matches(PASSWORD_PATTERN, passwordValid));
//		user.getUserRole().equals(UserRole.USER);
//		user.getUserRole() != UserRole.ADMINISTRATOR
		User user = new User();
		user.setUserRole(UserRole.ADMINISTRATOR);
		
		if (user.getUserRole().equals(UserRole.ADMINISTRATOR)) {
			System.out.println(user.getUserRole());
		} else {
			System.out.println("non");
		}

	}

}
