package edu.ssafy.dto;

public class MemDTO {
	String id;
	String pw;
	String name;
	String addr;
	String age;
	public MemDTO() {}
	public MemDTO(String id, String pw, String name, String addr, String age) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.addr = addr;
		this.age = age;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "MemDTO [id=" + id + ", pw=" + pw + ", name=" + name + ", addr=" + addr + ", age=" + age + "]";
	}
	
	
}
