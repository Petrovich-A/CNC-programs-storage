package by.petrovich.storage.entity;

import java.util.Objects;

/**
 * @author Petrovich A.V.
 *
 */
public class CncMachine {
	private int id;
	private String model;
	private int codeEquipment;

	/**
	 * @param id
	 * @param model
	 * @param codeEquipment
	 */
	public CncMachine(int id, String model, int codeEquipment) {
		super();
		this.id = id;
		this.model = model;
		this.codeEquipment = codeEquipment;
	}

	/**
	 * @param model
	 * @param codeEquipment
	 */
	public CncMachine(String model, int codeEquipment) {
		super();
		this.model = model;
		this.codeEquipment = codeEquipment;
	}

	/**
	 * 
	 */
	public CncMachine() {
		super();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the codeEquipment
	 */
	public int getCodeEquipment() {
		return codeEquipment;
	}

	/**
	 * @param codeEquipment the codeEquipment to set
	 */
	public void setCodeEquipment(int codeEquipment) {
		this.codeEquipment = codeEquipment;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codeEquipment, id, model);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CncMachine other = (CncMachine) obj;
		return codeEquipment == other.codeEquipment && id == other.id && Objects.equals(model, other.model);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CncMachine [id=").append(id).append(", model=").append(model).append(", codeEquipment=")
				.append(codeEquipment).append("]");
		return builder.toString();
	}

}
