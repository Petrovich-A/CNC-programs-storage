package by.petrovich.storage.entity;

import java.util.Date;
import java.util.StringJoiner;

public class CncProgram {
    private int id;
    private String number;
    private String programText;
    private int operationNumber;
    private String fileExtension;
    private String comment;
    private boolean isActive;
    private Date date;

    public CncProgram() {
    }

    public CncProgram(int id, String programText, String name, int operationNumber, String fileExtension,
                      String comment, boolean isActive, Date date) {
        this.id = id;
        this.programText = programText;
        this.number = name;
        this.operationNumber = operationNumber;
        this.fileExtension = fileExtension;
        this.comment = comment;
        this.isActive = isActive;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProgramText() {
        return programText;
    }

    public void setProgramText(String programText) {
        this.programText = programText;
    }

    public String getName() {
        return number;
    }

    public void setName(String name) {
        this.number = name;
    }

    public int getOperationNumber() {
        return operationNumber;
    }

    public void setOperationNumber(int operationNumber) {
        this.operationNumber = operationNumber;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CncProgram that = (CncProgram) o;

        if (getId() != that.getId()) return false;
        if (getOperationNumber() != that.getOperationNumber()) return false;
        if (isActive() != that.isActive()) return false;
        if (getProgramText() != null ? !getProgramText().equals(that.getProgramText()) : that.getProgramText() != null)
            return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getFileExtension() != null ? !getFileExtension().equals(that.getFileExtension()) : that.getFileExtension() != null)
            return false;
        if (getComment() != null ? !getComment().equals(that.getComment()) : that.getComment() != null) return false;
        return getDate() != null ? getDate().equals(that.getDate()) : that.getDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getProgramText() != null ? getProgramText().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + getOperationNumber();
        result = 31 * result + (getFileExtension() != null ? getFileExtension().hashCode() : 0);
        result = 31 * result + (getComment() != null ? getComment().hashCode() : 0);
        result = 31 * result + (isActive() ? 1 : 0);
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CncProgram.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("programText='" + programText + "'")
                .add("name='" + number + "'")
                .add("operationNumber=" + operationNumber)
                .add("fileExtension='" + fileExtension + "'")
                .add("comment='" + comment + "'")
                .add("isActive=" + isActive)
                .add("date=" + date)
                .toString();
    }
}
