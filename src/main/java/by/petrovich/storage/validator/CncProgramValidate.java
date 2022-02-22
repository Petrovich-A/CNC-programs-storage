package by.petrovich.storage.validator;

import by.petrovich.storage.entity.CncMachine;
import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.entity.Detail;

public interface CncProgramValidate {
	boolean isCncProgramValid(CncProgram cncProgram);

	boolean isDetailValid(Detail detail);

	boolean isCncMachineValid(CncMachine cncMachine);

	boolean isNumber(String number);

	boolean isOperationNumber(String operationNumber);

	boolean isComment(String comment);

	boolean isDatailName(String name);

	boolean isCncMachineModelValid(String model);

	boolean isCncMachineCodeEquipmentValid(String codeEquipment);

}
