package org.example.test;

public class Main {

	public static void main(String[] args) {
		TunnelingService t=new TunnelingService();
        try{
            t.start();
        } catch(Exception ex){
            ex.printStackTrace();
        }
	}

}
