package com.qa.trcrm.pojo;

public class Credentials {

	String emailId;
	String password;
	
	public Credentials(String emailId,String password)
	{
		this.emailId=emailId;
		this.password=password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
}
