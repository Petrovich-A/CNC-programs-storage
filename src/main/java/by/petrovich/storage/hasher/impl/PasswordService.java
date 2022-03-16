package by.petrovich.storage.hasher.impl;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.hasher.Hashable;

public class PasswordService implements Hashable {
	private static final Logger logger = LogManager.getLogger();
	private static final String HASH_ALGORITHM = "PBKDF2WithHmacSHA1";
	private static final int ITERATIONS = 1000;
	private static final int HASH_BYTE_SIZE = 256;
	private static final String CONSTANT_SALT = "ZyOzB3buD3a7PZdNMqVLY5BLR1nhRMm6G+0DqYAFf0M=";
	private static final int SALT_LENGTH = 32;

	private static PasswordService instance;

	private PasswordService() {

	}

	public static PasswordService getInstance() {
		if (instance == null) {
			instance = new PasswordService();
		}
		return instance;
	}

	@Override
	public String generateSalt() {
		SecureRandom secureRandom = new SecureRandom();
		byte[] bytes = new byte[SALT_LENGTH];
		secureRandom.nextBytes(bytes);
		logger.log(Level.INFO, "Salt generate succsesfully.");
		return Base64.getEncoder().encodeToString(bytes);
	}

	@Override
	public String generateHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(HASH_ALGORITHM);
		PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray(), CONSTANT_SALT.getBytes(), ITERATIONS,
				HASH_BYTE_SIZE);
		byte[] hash = secretKeyFactory.generateSecret(pbeKeySpec).getEncoded();
		return Base64.getEncoder().encodeToString(hash);
	}

	@Override
	public String generateHash(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
		SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(HASH_ALGORITHM);
		PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), ITERATIONS, HASH_BYTE_SIZE);
		byte[] hash = secretKeyFactory.generateSecret(pbeKeySpec).getEncoded();
		return Base64.getEncoder().encodeToString(hash);
	}

}
