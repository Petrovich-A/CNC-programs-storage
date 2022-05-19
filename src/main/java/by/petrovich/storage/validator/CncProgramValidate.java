package by.petrovich.storage.validator;

import by.petrovich.storage.entity.CncMachine;
import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.entity.Detail;

/**
 * @author Petrovich A.V.
 *
 */
public interface CncProgramValidate {
	boolean isCncProgramValid(CncProgram cncProgram);

	/**
	 * @param detail
	 * @return
	 */
	boolean isDetailValid(Detail detail);

	/**
	 * @param cncMachine
	 * @return
	 */
	boolean isCncMachineValid(CncMachine cncMachine);

	/**
	 * @param number
	 * @return
	 */
	boolean isNumberValid(String number);

	/**
	 * @param operationNumber
	 * @return
	 */
	boolean isOperationNumberValid(String operationNumber);
	
	/**
	 * @param programText
	 * @return
	 */
	boolean isProgramTextValid(String programText);

	/**
	 * @param comment
	 * @return
	 */
	boolean isCommentValid(String comment);

	/**
	 * @param name
	 * @return
	 */
	boolean isDatailNameValid(String name);

	/**
	 * @param model
	 * @return
	 */
	boolean isCncMachineModelValid(String model);

	/**
	 * @param codeEquipment
	 * @return
	 */
	boolean isCncMachineCodeEquipmentValid(String codeEquipment);

}
