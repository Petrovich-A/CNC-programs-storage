package by.petrovich.storage.hasher;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @author Petrovich A.V.
 *
 */
public interface Hashable {

	/**
	 * @return
	 */
	String generateSalt();

	/**
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	String generateHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException;

	/**
	 * @param password
	 * @param salt
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	String generateHash(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException;

}
