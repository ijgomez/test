package org.example.test.views.components;

public interface ApplicationLogComponent {

	public void info(String message);
	
	public void warn(String message);
	
	public void error(String message);
	
	public void error(Throwable th);
	
}
