package by.petrovich.storage.validator;

import by.petrovich.storage.entity.CncMachine;
import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.entity.Detail;

public interface CncProgramValidate {
	boolean isCncProgramValid(CncProgram cncProgram);

	boolean isDetailValid(Detail detail);

	boolean isCncMachineValid(CncMachine cncMachine);

	boolean isNumberValid(String number);

	boolean isOperationNumberValid(String operationNumber);
	
	boolean isProgramTextValid(String programText);

	boolean isCommentValid(String comment);

	boolean isDatailNameValid(String name);

	boolean isCncMachineModelValid(String model);

	boolean isCncMachineCodeEquipmentValid(String codeEquipment);

}
