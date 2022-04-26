package edu.ssafy.dto;

public class FileDTO {
	String no;
	String path;
	String name;
	public FileDTO(String no, String path, String name) {
		super();
		this.no = no;
		this.path = path;
		this.name = name;
	}
	public FileDTO(String path, String name) {
		this.path = path;
		this.name = name;
	}
	public FileDTO() {
		super();
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "FileDTO [no=" + no + ", path=" + path + ", name=" + name + "]";
	}
	
	
}
