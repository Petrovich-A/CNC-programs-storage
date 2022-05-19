package by.petrovich.storage.entity;

import java.util.Objects;

public class Detail {
	private int id;
	private String name;

	/**
	 * 
	 */
	public Detail() {
		super();
	}

	/**
	 * @param name
	 */
	public Detail(String name) {
		super();
		this.name = name;
	}

	/**
	 * @param id
	 * @param name
	 */
	public Detail(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Detail other = (Detail) obj;
		return id == other.id && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Detail [id=").append(id).append(", name=").append(name).append("]");
		return builder.toString();
	}

}
