package by.petrovich.storage.validator.impl;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import by.petrovich.storage.entity.CncMachine;
import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.entity.Detail;

public class CncProgramValidatorTest {
	CncProgramValidator cncProgramValidator = CncProgramValidator.getInstance();

	@Test
	public void isNumberValid() {
		String number = "150_79302067";
		boolean result = cncProgramValidator.isNumberValid(number);
		assertTrue(result);
	}

	@Test
	public void isOperationNumberValid() {
		String operationNumber = "030";
		boolean result = cncProgramValidator.isOperationNumberValid(operationNumber);
		assertTrue(result);
	}

	@Test
	public void isDatailNameValid() {
		String detailName = "7555-1712004";
		boolean result = cncProgramValidator.isDatailNameValid(detailName);
		Assert.assertTrue(result);
	}

	@Test
	public void isCommentValid() {
		String comment = "добавил проход на проходной черн.";
		boolean result = cncProgramValidator.isCommentValid(comment);
		Assert.assertTrue(result);
	}

	@Test
	public void isCncMachineModelValid() {
		String model = "NBH-6";
		boolean result = cncProgramValidator.isCncMachineModelValid(model);
		Assert.assertTrue(result);
	}

	@Test
	public void isCncMachineCodeEquipmentValid() {
		String codeEquipment = "067";
		boolean result = cncProgramValidator.isCncMachineCodeEquipmentValid(codeEquipment);
		Assert.assertTrue(result);
	}

	@Test
	public void isCncProgramValid() {
		Detail detail = new Detail("7555-41201063");
		CncMachine cncMachine = new CncMachine("ИР500", 113);
		CncProgram cncProgram = new CncProgram("150_06105113", 050, "добавил фаску <30гр.", detail, cncMachine);
		boolean result = cncProgramValidator.isCncProgramValid(cncProgram);
		Assert.assertTrue(result);
	}

	@Test
	public void isDetailValid() {
		Detail detail = new Detail();
		detail.setName("7555-41201063");
		boolean result = cncProgramValidator.isDetailValid(detail);
		Assert.assertTrue(result);
	}

	@Test
	public void isCncMachineValid() {
		CncMachine cncMachine = new CncMachine();
		cncMachine.setModel("LM70-AT");
		cncMachine.setCodeEquipment(120);
		boolean result = cncProgramValidator.isCncMachineValid(cncMachine);
		assertTrue(result);
	}

}