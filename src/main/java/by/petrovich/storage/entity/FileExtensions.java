package by.petrovich.storage.entity;

public class FileExtensions {
	private String fileExtensionName;

	public FileExtensions(String fileExtensionName) {
		super();
		this.fileExtensionName = fileExtensionName;
	}

	public FileExtensions() {
		super();
	}

	public String getFileExtensionName() {
		return fileExtensionName;
	}

	public void setFileExtensionName(String fileExtensionName) {
		this.fileExtensionName = fileExtensionName;
	}

}
