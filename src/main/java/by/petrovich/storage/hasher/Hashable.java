package by.petrovich.storage.hasher;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface Hashable {

	String generateSalt();

	String generateHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException;

	String generateHash(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException;

}
