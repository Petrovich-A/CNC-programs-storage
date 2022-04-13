package by.petrovich.storage.entity;

import java.sql.Timestamp;
import java.util.StringJoiner;

public class CncProgram {
	private int id;
	private String number;
	private int operationNumber;
	private String programText;
	private Timestamp creationDate;
	private String comment;
	private boolean isActive;
	private Detail detail;
	private CncMachine cncMachine;
	private int loginPersonnelNumber;

	public CncProgram() {
	}

	public CncProgram(int id, String number, int operationNumber, String programText, Timestamp creationDate,
			String comment, boolean isActive, Detail detail, CncMachine cncMachine, int loginPersonnelNumber) {
		this.id = id;
		this.number = number;
		this.operationNumber = operationNumber;
		this.programText = programText;
		this.creationDate = creationDate;
		this.comment = comment;
		this.isActive = isActive;
		this.detail = detail;
		this.cncMachine = cncMachine;
		this.loginPersonnelNumber = loginPersonnelNumber;
	}

	public CncProgram(String number, int operationNumber, String comment, Detail detail, CncMachine cncMachine) {
		this.number = number;
		this.operationNumber = operationNumber;
		this.comment = comment;
		this.detail = detail;
		this.cncMachine = cncMachine;
	}

	public CncProgram(int id, String number, int operationNumber, String programText, Timestamp creationDate,
			String comment, boolean isActive) {
		this.id = id;
		this.number = number;
		this.operationNumber = operationNumber;
		this.programText = programText;
		this.creationDate = creationDate;
		this.comment = comment;
		this.isActive = isActive;
	}

	public CncProgram(String number, int operationNumber, String programText, Timestamp creationDate, String comment,
			boolean isActive, Detail detail, CncMachine cncMachine, int loginPersonnelNumber) {
		this.number = number;
		this.operationNumber = operationNumber;
		this.programText = programText;
		this.creationDate = creationDate;
		this.comment = comment;
		this.isActive = isActive;
		this.detail = detail;
		this.cncMachine = cncMachine;
		this.loginPersonnelNumber = loginPersonnelNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getOperationNumber() {
		return operationNumber;
	}

	public void setOperationNumber(int operationNumber) {
		this.operationNumber = operationNumber;
	}

	public String getProgramText() {
		return programText;
	}

	public void setProgramText(String programText) {
		this.programText = programText;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public Detail getDetail() {
		return detail;
	}

	public void setDetail(Detail detail) {
		this.detail = detail;
	}

	public CncMachine getCncMachine() {
		return cncMachine;
	}

	public void setCncMachine(CncMachine cncMachine) {
		this.cncMachine = cncMachine;
	}

	public int getLoginPersonnelNumber() {
		return loginPersonnelNumber;
	}

	public void setLoginPersonnelNumber(int loginPersonnelNumber) {
		this.loginPersonnelNumber = loginPersonnelNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		CncProgram that = (CncProgram) o;

		if (getId() != that.getId())
			return false;
		if (getOperationNumber() != that.getOperationNumber())
			return false;
		if (isActive() != that.isActive())
			return false;
		if (getLoginPersonnelNumber() != that.getLoginPersonnelNumber())
			return false;
		if (getNumber() != null ? !getNumber().equals(that.getNumber()) : that.getNumber() != null)
			return false;
		if (getProgramText() != null ? !getProgramText().equals(that.getProgramText()) : that.getProgramText() != null)
			return false;
		if (getCreationDate() != null ? !getCreationDate().equals(that.getCreationDate())
				: that.getCreationDate() != null)
			return false;
		if (getComment() != null ? !getComment().equals(that.getComment()) : that.getComment() != null)
			return false;
		if (getDetail() != null ? !getDetail().equals(that.getDetail()) : that.getDetail() != null)
			return false;
		return getCncMachine() != null ? getCncMachine().equals(that.getCncMachine()) : that.getCncMachine() == null;
	}

	@Override
	public int hashCode() {
		int result = getId();
		result = 31 * result + (getNumber() != null ? getNumber().hashCode() : 0);
		result = 31 * result + getOperationNumber();
		result = 31 * result + (getProgramText() != null ? getProgramText().hashCode() : 0);
		result = 31 * result + (getCreationDate() != null ? getCreationDate().hashCode() : 0);
		result = 31 * result + (getComment() != null ? getComment().hashCode() : 0);
		result = 31 * result + (isActive() ? 1 : 0);
		result = 31 * result + (getDetail() != null ? getDetail().hashCode() : 0);
		result = 31 * result + (getCncMachine() != null ? getCncMachine().hashCode() : 0);
		result = 31 * result + getLoginPersonnelNumber();
		return result;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", CncProgram.class.getSimpleName() + "[", "]").add("id=" + id)
				.add("number='" + number + "'").add("operationNumber=" + operationNumber)
				.add("programText='" + programText + "'").add("creationDate=" + creationDate)
				.add("comment='" + comment + "'").add("isActive=" + isActive).add("detail=" + detail)
				.add("cncMachine=" + cncMachine).add("loginPersonnelNumber=" + loginPersonnelNumber).toString();
	}
}
