package by.petrovich.storage.reader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertyLoader {
	private static final Logger logger = LogManager.getLogger();
	private static final String PROPERT_PATH = "sql-config.properties";
	private static final Properties PROPERTIES = new Properties();

	static {
		InputStream inputStream = PropertyLoader.class.getClassLoader().getResourceAsStream(PROPERT_PATH);
		if (inputStream == null) {
			logger.log(Level.FATAL, "{} file does not exist, initialization failed", PROPERT_PATH);
		}
		readProperties(inputStream);
	}

	private static void readProperties(InputStream inputStream) {

		try {
			PROPERTIES.load(inputStream);
		} catch (FileNotFoundException e) {
			logger.log(Level.FATAL, "Properties file not found: {}", e.getMessage(), e);
		} catch (IOException e) {
			logger.log(Level.FATAL, "Reading database properties failed: {}", e.getMessage(), e);
		}
		logger.log(Level.INFO, "Reading property file successful: {}");
	}

	public String get(String propertyName) {
		return PROPERTIES.getProperty(propertyName);
	}
}
