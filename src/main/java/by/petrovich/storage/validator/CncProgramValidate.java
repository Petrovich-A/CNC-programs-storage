package by.petrovich.storage.validator;

import by.petrovich.storage.entity.CncProgram;

public interface CncProgramValidate {
	boolean isCncProgramValid(CncProgram cncProgram);

	boolean isNumber(String number);

	boolean isProgramText(String programText);

	boolean isOperationNumber(String operationNumber);

	boolean isFileExtension(String fileExtension);

	boolean isComment(String comment);

}
