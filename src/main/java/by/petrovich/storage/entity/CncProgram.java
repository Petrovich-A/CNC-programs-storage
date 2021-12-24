package by.petrovich.storage.entity;

import java.util.Date;
import java.util.StringJoiner;

public class CncProgram {
    private int id;
    private String programText;
    private String name;
    private int operationNumber;
    private String fileExtension;
    private String comment;
    private boolean isActive;
    private Date date;

    public CncProgram(int id, String programText, String name, int operationNumber, String fileExtension,
                      String comment, boolean isActive, Date date) {
        this.id = id;
        this.programText = programText;
        this.name = name;
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
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        if (id != that.id) return false;
        if (operationNumber != that.operationNumber) return false;
        if (isActive != that.isActive) return false;
        if (programText != null ? !programText.equals(that.programText) : that.programText != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (fileExtension != null ? !fileExtension.equals(that.fileExtension) : that.fileExtension != null)
            return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        return date != null ? date.equals(that.date) : that.date == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (programText != null ? programText.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + operationNumber;
        result = 31 * result + (fileExtension != null ? fileExtension.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (isActive ? 1 : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CncProgram.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("programText='" + programText + "'")
                .add("name='" + name + "'")
                .add("operationNumber=" + operationNumber)
                .add("fileExtension='" + fileExtension + "'")
                .add("comment='" + comment + "'")
                .add("isActive=" + isActive)
                .add("date=" + date)
                .toString();
    }
}
