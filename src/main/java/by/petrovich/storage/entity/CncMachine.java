package by.petrovich.storage.entity;

import java.util.StringJoiner;

public class CncMachine {
    private int id;
    private String model;
    private int codeEquipment;

    public CncMachine(int id, String model, int codeEquipment) {
        this.id = id;
        this.model = model;
        this.codeEquipment = codeEquipment;
    }

    public CncMachine(String model) {
        this.model = model;
    }

    public CncMachine(String model, int codeEquipment) {
        this.model = model;
        this.codeEquipment = codeEquipment;
    }

    public CncMachine() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCodeEquipment() {
        return codeEquipment;
    }

    public void setCodeEquipment(int codeEquipment) {
        this.codeEquipment = codeEquipment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CncMachine that = (CncMachine) o;

        if (getId() != that.getId()) return false;
        if (getCodeEquipment() != that.getCodeEquipment()) return false;
        return getModel() != null ? getModel().equals(that.getModel()) : that.getModel() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getModel() != null ? getModel().hashCode() : 0);
        result = 31 * result + getCodeEquipment();
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CncMachine.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("model='" + model + "'")
                .add("codeEquipment=" + codeEquipment)
                .toString();
    }
}
