package com.example.memberproject;

public class MemberVO {
	private String id;
	private String pwd;
	private String name;
	private int age;
	private String addr;
	private String tel;
	private String regDate;

	public MemberVO(){}
	
	public MemberVO(String id, String pwd, String name, int age, String addr, String tel) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.age = age;
		this.addr = addr;
		this.tel = tel;
	}	
	
	public MemberVO(String id, String pwd, String name, int age, String addr, String tel, String regDate) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.age = age;
		this.addr = addr;
		this.tel = tel;
		this.regDate = regDate;
	}	
	
	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}	

	
}
