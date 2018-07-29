package org.example.test.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ConnectionData {

	private String driverName;
	
	private String url;
	
	private String username;
	
	private String password;
	
	public ConnectionData() {
		super();
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
