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
	private static final String NUMBER_PATTERN = "^[\\d,?=.*-|_]{3,20}+$";
	private static final String OPERATION_NUMBER_PATTERN = "^\\p{Digit}{2,3}+$";
	private static final String DETAIL_NAME_PATTERN = "^[0-9,A-Z,-]{3,20}+$";
	private static final String PROGRAM_TEXT_PATTERN = "([\\s\\S]*){1,25000}";
	private static final String COMMENT_PATTERN = "^[\\w,\\s,à-ÿ,À-ß,!?@#$%^&+=.,;:_<>*()]{0,40}+$";
	private static final String CNC_MACHINE_MODEL_PATTERN = "^[a-z,A-Z,à-ÿ,À-ß,0-9,-]{2,20}+$";
	private static final String CNC_MACHINE_CODE_EQUIPMENT_PATTERN = "^[0-9]{2,5}+$";

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
		return isNumberValid(cncProgram.getNumber())
				&& isOperationNumberValid(String.valueOf(cncProgram.getOperationNumber()))
				&& isProgramTextValid(cncProgram.getProgramText()) && isCommentValid(cncProgram.getComment())
				&& isDetailValid(cncProgram.getDetail()) && isCncMachineValid(cncProgram.getCncMachine());
	}

	@Override
	public boolean isDetailValid(Detail detail) {
		return isNumberValid(detail.getName());
	}

	@Override
	public boolean isCncMachineValid(CncMachine cncMachine) {
		return isCncMachineModelValid(cncMachine.getModel())
				&& isCncMachineCodeEquipmentValid(String.valueOf(cncMachine.getCodeEquipment()));
	}

	@Override
	public boolean isNumberValid(String number) {
		boolean isValid = number.matches(NUMBER_PATTERN);
		if (!isValid) {
			logger.log(Level.ERROR, "isn't valid number: {}", number);
		}
		return isValid;
	}

	@Override
	public boolean isOperationNumberValid(String operationNumber) {
		boolean isValid = operationNumber.matches(OPERATION_NUMBER_PATTERN);
		if (!isValid) {
			logger.log(Level.ERROR, "isn't valid number: {}", operationNumber);
		}
		return isValid;
	}

	@Override
	public boolean isProgramTextValid(String programText) {
		boolean isValid = programText.matches(PROGRAM_TEXT_PATTERN);
		if (!isValid) {
			logger.log(Level.ERROR, "isn't valid program text: {}", programText);
		}
		return isValid;
	}

	@Override
	public boolean isCommentValid(String comment) {
		boolean isValid = comment.matches(COMMENT_PATTERN);
		if (!isValid) {
			logger.log(Level.ERROR, "isn't valid comment: {}", comment);
		}
		return isValid;
	}

	@Override
	public boolean isDatailNameValid(String name) {
		boolean isValid = name.matches(DETAIL_NAME_PATTERN);
		if (!isValid) {
			logger.log(Level.ERROR, "isn't valid name: {}", name);
		}
		return isValid;
	}

	@Override
	public boolean isCncMachineModelValid(String model) {
		boolean isValid = model.matches(CNC_MACHINE_MODEL_PATTERN);
		if (!isValid) {
			logger.log(Level.ERROR, "isn't valid model: {}", model);
		}
		return isValid;
	}

	@Override
	public boolean isCncMachineCodeEquipmentValid(String codeEquipment) {
		boolean isValid = codeEquipment.matches(CNC_MACHINE_CODE_EQUIPMENT_PATTERN);
		if (!isValid) {
			logger.log(Level.ERROR, "isn't valid codeEquipment: {}", codeEquipment);
		}
		return isValid;
	}

}
