/**
 * 
 */
package by.petrovich.storage.validator.impl;

import org.junit.Assert;
import org.junit.Test;

import by.petrovich.storage.entity.CncMachine;
import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.entity.Detail;

/**
 * @author Petrovich A.V.
 *
 */
public class CncProgramValidatorTest {
	private CncProgramValidator cncProgramValidator = CncProgramValidator.getInstance();
	private Detail detailValid = new Detail("7555-41201063");
	private Detail detailInvalidMoreSymbols = new Detail("7555-4120106354151515");
	private Detail detailInvalidLessSymbols = new Detail("75");
	private CncMachine cncMachineValid = new CncMachine("ИР500", 113);
	private CncMachine cncMachineInvalidCodeEq = new CncMachine("ИР500", 3);
	private CncProgram cncProgramValid = new CncProgram("150_06105113", 050, """
			%
			N001 G01 Z-250 F150 M53
			N002 B2000 F50
			N003 G00 Z300 M54
			N004 B-2000
			N005 G01 Z-500 F150 M53
			N006 B2000 F50
			N007 G00 Z300 M54
			N008 B-2000
			N009 G01 Z-750 F150 M53
			N010 B2000 F50
			N011 G00 Z300 M54
			N012 B-2000
			N013 G01 Z-1000 F150 M53
			N014 B2000 F50
			N015 G00 Z300 M54
			N016 B-2000
			N017 G01 Z-1250 F150 M53
			N018 B2000 F50
			N019 G00 Z300 M54
			N020 B-2000
			M99
			%
			""", "добавил фаску <30гр.", detailValid, cncMachineValid);
	private CncProgram cncProgramInvalidTextEmpty = new CncProgram("150_06105113", 050, "", "добавил фаску <30гр.",
			detailValid, cncMachineValid);
	private String numberValid = "150-79302067";
	private String numberValidUnderScore = "150_79302067";
	private String numberValidNumbersOnly = "79302067";
	private String numberInvalidMoreSymbols = "793020675454545345646";
	private String numberInvalidLessSymbols = "54";
	private String operationNumberValid = "030";
	private String operationNumberInvalidLess = "3";
	private String operationNumberInvalidMore = "1301";
	private String programTextValid = """
			%
			N001 G50X90000Z40000T01
			N002 G98M74
			N003 G22M19
			N004 G28B0T11S71M25
			N005 G00B0
			N006 M06
			N007 G00X12000Z5000C0H11M13
			N008 Z300M08
			N009 G82Z450C1000P50F50
			N010 C4450
			N011 C6450
			N012 C11000
			N013 C13000
			N014 C16450
			N015 C18450
			N016 C23000
			N017 C25000
			N018 C28450
			N019 C30450
			N020 C35000
			N021 G80M29
			N057 M63
			N058 M06
			N059 M73
			N060 G00X90000Z40000
			N061 M30
			%
						""";
	private String programTextInvalid = "";
	private String commentValid = "добавил радиуса скругления на фасках <45";
	private String commentValidEmpty = "";
	private String commentInvalidMore = "добавил радиуса скругления на фасках <45 градусов с торца";
	private String datailNameValid = "7555D-1303025";
	private String datailNameInvalidEmpty = "";
	private String datailNameInvalidMore = "7555D-130302545458467";
	private String cncMachineModelValid = "BTN-10B";
	private String cncMachineModelInvalidLess = "B";
	private String cncMachineModelInvalidMore = "BTN-10BH5454545414784";
	private String cncMachineCodeEquipmentValid = "117";
	private String cncMachineCodeEquipmentInvalidMore = "117456";
	private String cncMachineCodeEquipmentInvalidLess = "1";

	/**
	 * Test method for
	 * {@link by.petrovich.storage.validator.impl.CncProgramValidator#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.validator.impl.CncProgramValidator#isCncProgramValid(by.petrovich.storage.entity.CncProgram)}.
	 */
	@Test
	public void testIsCncProgramValid() {
		boolean positive = cncProgramValidator.isCncProgramValid(cncProgramValid);
		boolean negative = cncProgramValidator.isCncProgramValid(cncProgramInvalidTextEmpty);
		Assert.assertTrue(positive);
		Assert.assertFalse(negative);
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.validator.impl.CncProgramValidator#isDetailValid(by.petrovich.storage.entity.Detail)}.
	 */
	@Test
	public void testIsDetailValid() {
		boolean positive = cncProgramValidator.isDetailValid(detailValid);
		boolean negativeMoreSymbols = cncProgramValidator.isDetailValid(detailInvalidMoreSymbols);
		boolean negativeLessSymbols = cncProgramValidator.isDetailValid(detailInvalidLessSymbols);
		Assert.assertTrue(positive);
		Assert.assertFalse(negativeMoreSymbols);
		Assert.assertFalse(negativeLessSymbols);
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.validator.impl.CncProgramValidator#isCncMachineValid(by.petrovich.storage.entity.CncMachine)}.
	 */
	@Test
	public void testIsCncMachineValid() {
		boolean positive = cncProgramValidator.isCncMachineValid(cncMachineValid);
		boolean negative = cncProgramValidator.isCncMachineValid(cncMachineInvalidCodeEq);
		Assert.assertTrue(positive);
		Assert.assertFalse(negative);
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.validator.impl.CncProgramValidator#isNumberValid(java.lang.String)}.
	 */
	@Test
	public void testIsNumberValid() {
		boolean positive = cncProgramValidator.isNumberValid(numberValid);
		boolean positiveNumbersOnly = cncProgramValidator.isNumberValid(numberValidNumbersOnly);
		boolean positiveUnderScore = cncProgramValidator.isNumberValid(numberValidUnderScore);
		boolean negativeLess = cncProgramValidator.isNumberValid(numberInvalidLessSymbols);
		boolean negativeMore = cncProgramValidator.isNumberValid(numberInvalidMoreSymbols);
		Assert.assertTrue(positive);
		Assert.assertTrue(positiveNumbersOnly);
		Assert.assertTrue(positiveUnderScore);
		Assert.assertFalse(negativeLess);
		Assert.assertFalse(negativeMore);
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.validator.impl.CncProgramValidator#isOperationNumberValid(java.lang.String)}.
	 */
	@Test
	public void testIsOperationNumberValid() {
		boolean positive = cncProgramValidator.isOperationNumberValid(operationNumberValid);
		boolean negativeLess = cncProgramValidator.isOperationNumberValid(operationNumberInvalidLess);
		boolean negativeMore = cncProgramValidator.isOperationNumberValid(operationNumberInvalidMore);
		Assert.assertTrue(positive);
		Assert.assertFalse(negativeLess);
		Assert.assertFalse(negativeMore);
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.validator.impl.CncProgramValidator#isProgramTextValid(java.lang.String)}.
	 */
	@Test
	public void testIsProgramTextValid() {
		boolean positive = cncProgramValidator.isProgramTextValid(programTextValid);
		boolean negative = cncProgramValidator.isProgramTextValid(programTextInvalid);
		Assert.assertTrue(positive);
		Assert.assertFalse(negative);
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.validator.impl.CncProgramValidator#isCommentValid(java.lang.String)}.
	 */
	@Test
	public void testIsCommentValid() {
		boolean positive = cncProgramValidator.isCommentValid(commentValid);
		boolean positiveEmpty = cncProgramValidator.isCommentValid(commentValidEmpty);
		boolean negative = cncProgramValidator.isCommentValid(commentInvalidMore);
		Assert.assertTrue(positive);
		Assert.assertTrue(positiveEmpty);
		Assert.assertFalse(negative);
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.validator.impl.CncProgramValidator#isDatailNameValid(java.lang.String)}.
	 */
	@Test
	public void testIsDatailNameValid() {
		boolean positive = cncProgramValidator.isDatailNameValid(datailNameValid);
		boolean negativeEmpty = cncProgramValidator.isDatailNameValid(datailNameInvalidEmpty);
		boolean negativeMore = cncProgramValidator.isDatailNameValid(datailNameInvalidMore);
		Assert.assertTrue(positive);
		Assert.assertFalse(negativeEmpty);
		Assert.assertFalse(negativeMore);
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.validator.impl.CncProgramValidator#isCncMachineModelValid(java.lang.String)}.
	 */
	@Test
	public void testIsCncMachineModelValid() {
		boolean positive = cncProgramValidator.isCncMachineModelValid(cncMachineModelValid);
		boolean negativeLess = cncProgramValidator.isCncMachineModelValid(cncMachineModelInvalidLess);
		boolean negativeMore = cncProgramValidator.isCncMachineModelValid(cncMachineModelInvalidMore);
		Assert.assertTrue(positive);
		Assert.assertFalse(negativeLess);
		Assert.assertFalse(negativeMore);
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.validator.impl.CncProgramValidator#isCncMachineCodeEquipmentValid(java.lang.String)}.
	 */
	@Test
	public void testIsCncMachineCodeEquipmentValid() {
		boolean positive = cncProgramValidator.isCncMachineCodeEquipmentValid(cncMachineCodeEquipmentValid);
		boolean negativeLess = cncProgramValidator.isCncMachineCodeEquipmentValid(cncMachineCodeEquipmentInvalidLess);
		boolean negativeMore = cncProgramValidator.isCncMachineCodeEquipmentValid(cncMachineCodeEquipmentInvalidMore);
		Assert.assertTrue(positive);
		Assert.assertFalse(negativeLess);
		Assert.assertFalse(negativeMore);
	}

}
