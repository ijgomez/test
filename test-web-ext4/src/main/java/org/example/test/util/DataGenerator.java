package org.example.test.util;

public class DataGenerator {

	public static void main(String[] args) {
		
		StringBuffer sb;
		
		String s1 = "insert into CONTACT (CONTACT_NAME, CONTACT_PHONE, CONTACT_EMAIL) values ('Contact";
		String s2 = "', '";
		String s3 = "(000) 000-0000";
		String s4 = "@braziljs.com.br";
		String s5 = "');";
		String s6 = "Contact";
		
		for (int i=101; i<200; i++){
			sb = new StringBuffer();
			sb.append(s1).append(i).append(s2);
			sb.append(s3).append(s2).append(s6);
			sb.append(s4).append(s5);
			System.out.println(sb.toString());
		}
		
	}

}
