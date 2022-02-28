package by.petrovich.storage.validator.impl;

import by.petrovich.storage.entity.CncMachine;
import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.entity.Detail;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;

public class CncProgramValidatorTest {
	CncProgramValidator cncProgramValidator = CncProgramValidator.getInstance();
	private int cncProgramId = 154;
	private String cncProgramNumber = "7558411";
	private int cncProgramOperationNumber = 030;
	private String cncProgramText = "%\n" + "N001G00G99M73\n" + "N131G50X87000Z34000S22T02\n"
			+ "N132G00X0Z20000H02M03\n" + "N133Z15500M08\n" + "N134G01Z12490F30\n" + "N135G00Z20000M09\n"
			+ "N136X87000Z34000H00M05\n" + "N002G50X81000Z45000T01S22\n" + "N003G00X33930Z18000H01M04\n"
			+ "N004Z12000M08\n" + "N005G01Z9250F30\n" + "N006G00X34100Z11000\n" + "N007G01X33300Z11400\n"
			+ "N008X28650\n" + "N009G00X28800Z14200\n" + "N010G01X28050" + "N206X68000Z44000H00M05C30000\n"
			+ "N207M63\n" + "N208M06\n" + "N209M73\n" + "N210G00X90000Z49000\n" + "N204M30\n" + "%";
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	private Timestamp cncProgramCreationDate = timestamp;
	private String cncProgramComment = "add drill D=8,5";
	private String cncProgramComment1 = "";
	private boolean isActive = true;
	private int loginPersonnelNumber = 42655;

	private int detailId = 565454;
	private String detailName = "7555-1712212-10";

	private int cncMachineId = 18675;
	private String cncMachineModel = "NBH-6";
	private int codeEquipment = 115;

	Detail detailActual = new Detail(detailId, detailName);
	CncMachine cncMachineActual = new CncMachine(cncMachineId, cncMachineModel, codeEquipment);
	CncProgram cncProgramActual = new CncProgram(cncProgramId, cncProgramNumber, cncProgramOperationNumber,
			cncProgramText, cncProgramCreationDate, cncProgramComment, isActive, detailActual, cncMachineActual,
			loginPersonnelNumber);

	@Test
	public void isCncProgramValidWhenCncProgramValidReturnTrue() {
		boolean actualCncProgarmm = cncProgramValidator.isCncProgramValid(cncProgramActual);
		Assert.assertTrue("isCncProgramValid", actualCncProgarmm);
	}

	@Test
	public void isDetailValid() {
		boolean actualDetail = cncProgramValidator.isDetailValid(detailActual);
		Assert.assertTrue("isDetailValid", actualDetail);
	}

	@Test
	public void isCncMachineValid() {
		boolean actualCncMachine = cncProgramValidator.isCncMachineValid(cncMachineActual);
		Assert.assertTrue("isCncMachineValid", actualCncMachine);
	}

	@Test
	public void isNumberValid() {
		boolean actualNumber = cncProgramValidator.isNumberValid(cncProgramNumber);
		Assert.assertTrue("isNumberValid", actualNumber);
	}

	@Test
	public void isOperationNumberValid() {
		boolean actualOperatinNumber = cncProgramValidator
				.isOperationNumberValid(Integer.toString(cncProgramOperationNumber));
		Assert.assertTrue("isOperationNumberValid", actualOperatinNumber);
	}

	@Test
	public void isCommentValid() {
		boolean actualComment = cncProgramValidator.isCommentValid(cncProgramComment);
		boolean actualComment1 = cncProgramValidator.isCommentValid(cncProgramComment1);
		Assert.assertTrue("isCommentValid", actualComment);
		Assert.assertTrue("isCommentValid", actualComment1);
	}

	@Test
	public void isDatailNameValid() {
		boolean actualDatailName = cncProgramValidator.isDatailNameValid(detailName);
		Assert.assertTrue("isDatailNameValid", actualDatailName);
	}

	@Test
	public void isCncMachineModelValid() {
		boolean actualCncMachineModel = cncProgramValidator.isCncMachineModelValid(cncMachineModel);
		Assert.assertTrue("isCncMachineModelValid", actualCncMachineModel);
	}

	@Test
	public void isCncMachineCodeEquipmentValid() {
		boolean actualCncMachineCodeEquipment = cncProgramValidator
				.isCncMachineCodeEquipmentValid(Integer.toString(codeEquipment));
		Assert.assertTrue("isCncMachineCodeEquipmentValid", actualCncMachineCodeEquipment);
	}
}