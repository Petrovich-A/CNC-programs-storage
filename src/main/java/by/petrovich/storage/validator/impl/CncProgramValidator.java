package by.petrovich.storage.validator.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.validator.CncProgramValidate;

public class CncProgramValidator implements CncProgramValidate {
	private static final Logger logger = LogManager.getLogger();
	private static final String NUMBER_PATTERN = "^[\\p{IsAlphabetic}\\p{IsDigit}\\p{Punct}]{5,12}+$";
	private static final String PROGRAM_TEXT_PATTERN = "^[\\p{IsAlphabetic}\\p{IsDigit}\\p{Punct}]{5,12}+$";
	private static final String OPERATION_NUMBER_PATTERN = "^[\\p{IsAlphabetic}\\p{IsDigit}\\p{Punct}]{5,12}+$";
	private static final String FILE_EXTENSION_PATTERN = "^[\\p{IsAlphabetic}\\p{IsDigit}\\p{Punct}]{5,12}+$";
	private static final String COMMENT_PATTERN = "^[\\p{IsAlphabetic}\\p{IsDigit}\\p{Punct}]{5,12}+$";

	@Override
	public boolean isCncProgramValid(CncProgram cncProgram) {
		return isNumber(cncProgram.getName()) && isProgramText(cncProgram.getProgramText())
				&& isOperationNumber(String.valueOf(cncProgram.getOperationNumber()))
				&& isFileExtension(cncProgram.getFileExtension()) && isComment(cncProgram.getComment());
	}

	@Override
	public boolean isNumber(String number) {
		boolean isValid = number.matches(NUMBER_PATTERN);
		if (!isValid) {
			logger.log(Level.DEBUG, "isn't valid number: {}", number);
		}
		return isValid;
	}

	@Override
	public boolean isProgramText(String programText) {
		boolean isValid = true;
		// to do
		return isValid;
	}

	@Override
	public boolean isOperationNumber(String operationNumber) {
		boolean isValid = operationNumber.matches(OPERATION_NUMBER_PATTERN);
		if (!isValid) {
			logger.log(Level.DEBUG, "isn't valid number: {}", operationNumber);
		}
		return isValid;
	}

	@Override
	public boolean isFileExtension(String fileExtension) {
		boolean isValid = fileExtension.matches(FILE_EXTENSION_PATTERN);
		if (!isValid) {
			logger.log(Level.DEBUG, "isn't valid number: {}", fileExtension);
		}
		return isValid;
	}

	@Override
	public boolean isComment(String comment) {
		boolean isValid = comment.matches(COMMENT_PATTERN);
		if (!isValid) {
			logger.log(Level.DEBUG, "isn't valid number: {}", comment);
		}
		return isValid;
	}

}
