package by.petrovich.storage.validator.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.entity.CncMachine;
import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.entity.Detail;
import by.petrovich.storage.validator.CncProgramValidate;

public class CncProgramValidator implements CncProgramValidate {
	private static final Logger logger = LogManager.getLogger();
	private static final String NUMBER_PATTERN = "^[\\p{IsAlphabetic}\\p{IsDigit}\\p{Punct}]{1,12}+$";
	private static final String OPERATION_NUMBER_PATTERN = "^[\\p{IsDigit}]{3}+$";
	private static final String COMMENT_PATTERN = "^[\\p{IsAlphabetic}\\p{IsDigit}\\p{Punct}]{0,100}+$";
	private static final String DETAIL_NAME_PATTERN = "^[\\p{IsAlphabetic}\\p{IsDigit}\\p{Punct}]{3,20}+$";
	private static final String CNC_MACHINE_MODEL_PATTERN = "^[\\p{IsAlphabetic}\\p{IsDigit}\\p{Punct}]{3,20}+$";
	private static final String CNC_MACHINE_CODE_EQUIPMENT_PATTERN = "^[\\p{IsDigit}]{3}+$";

	private static CncProgramValidator instance;

	private CncProgramValidator() {
	}

	public static CncProgramValidator getInstance() {
		if (instance == null) {
			instance = new CncProgramValidator();
		}
		return instance;
	}

	@Override
	public boolean isCncProgramValid(CncProgram cncProgram) {
		return isNumber(cncProgram.getNumber()) && isOperationNumber(String.valueOf(cncProgram.getOperationNumber()))
				&& isComment(cncProgram.getComment()) && isDetailValid(cncProgram.getDetail())
				&& isCncMachineValid(cncProgram.getCncMachine());
	}

	@Override
	public boolean isDetailValid(Detail detail) {
		return isNumber(detail.getName());
	}

	@Override
	public boolean isCncMachineValid(CncMachine cncMachine) {
		return isCncMachineModelValid(cncMachine.getModel())
				&& isCncMachineCodeEquipmentValid(String.valueOf(cncMachine.getCodeEquipment()));
	}

	@Override
	public boolean isNumber(String number) {
		boolean isValid = number.matches(NUMBER_PATTERN);
		if (!isValid) {
			logger.log(Level.INFO, "isn't valid number: {}", number);
		}
		return isValid;
	}

	@Override
	public boolean isOperationNumber(String operationNumber) {
		boolean isValid = operationNumber.matches(OPERATION_NUMBER_PATTERN);
		if (!isValid) {
			logger.log(Level.INFO, "isn't valid number: {}", operationNumber);
		}
		return isValid;
	}

	@Override
	public boolean isComment(String comment) {
		boolean isValid = comment.matches(COMMENT_PATTERN);
		if (!isValid) {
			logger.log(Level.INFO, "isn't valid number: {}", comment);
		}
		return isValid;
	}

	@Override
	public boolean isDatailName(String name) {
		boolean isValid = name.matches(DETAIL_NAME_PATTERN);
		if (!isValid) {
			logger.log(Level.INFO, "isn't valid name: {}", name);
		}
		return isValid;
	}

	@Override
	public boolean isCncMachineModelValid(String model) {
		boolean isValid = model.matches(CNC_MACHINE_MODEL_PATTERN);
		if (!isValid) {
			logger.log(Level.INFO, "isn't valid model: {}", model);
		}
		return isValid;
	}

	@Override
	public boolean isCncMachineCodeEquipmentValid(String codeEquipment) {
		boolean isValid = codeEquipment.matches(CNC_MACHINE_CODE_EQUIPMENT_PATTERN);
		if (!isValid) {
			logger.log(Level.INFO, "isn't valid codeEquipment: {}", codeEquipment);
		}
		return isValid;
	}

}
