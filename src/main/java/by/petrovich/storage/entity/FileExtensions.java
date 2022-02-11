package by.petrovich.storage.entity;

import java.util.StringJoiner;

public class FileExtensions {
    private int id;
    private String fileExtensionName;

    public FileExtensions(int id, String fileExtensionName) {
        this.id = id;
        this.fileExtensionName = fileExtensionName;
    }

    public FileExtensions(String fileExtensionName) {
        this.fileExtensionName = fileExtensionName;
    }

    public FileExtensions() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileExtensionName() {
        return fileExtensionName;
    }

    public void setFileExtensionName(String fileExtensionName) {
        this.fileExtensionName = fileExtensionName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileExtensions that = (FileExtensions) o;

        if (getId() != that.getId()) return false;
        return getFileExtensionName() != null ? getFileExtensionName().equals(that.getFileExtensionName()) : that.getFileExtensionName() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getFileExtensionName() != null ? getFileExtensionName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", FileExtensions.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("fileExtensionName='" + fileExtensionName + "'")
                .toString();
    }
}
