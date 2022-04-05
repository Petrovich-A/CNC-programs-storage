package by.petrovich.storage;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class main {

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
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
//		User user = new User();
//		user.setUserRole(UserRole.ADMINISTRATOR);
//		
//		if (user.getUserRole().equals(UserRole.ADMINISTRATOR)) {
//			System.out.println(user.getUserRole());
//		} else {
//			System.out.println("non");
//		}
		String HASH_ALGORITHM = "PBKDF2WithHmacSHA1";
		int ITERATIONS = 1000;
		int HASH_BYTE_SIZE = 256;
		String CONSTANT_SALT = "ZyOzB3buD3a7PZdNMqVLY5BLR1nhRMm6G+0DqYAFf0M=";
		int SALT_LENGTH = 32;
		String password = "wdf123wef*";
		String passwordHashed = null;

		SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(HASH_ALGORITHM);
		PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray(), CONSTANT_SALT.getBytes(), ITERATIONS, HASH_BYTE_SIZE);
		byte[] hash = secretKeyFactory.generateSecret(pbeKeySpec).getEncoded();
		passwordHashed = Base64.getEncoder().encodeToString(hash);
		System.out.println(passwordHashed);
 
	}

}
