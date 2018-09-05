package org.example.test.domain;

public class Device implements DeviceMBean {

	private String id = "Default Device";
	
	public void run() {
		System.out.println("Run: " + id);
	}

	@Override
	public void setIdentification(String id) {
		this.id = id;
	}

	@Override
	public String getIdentification() {
		return this.id;
	}

}
