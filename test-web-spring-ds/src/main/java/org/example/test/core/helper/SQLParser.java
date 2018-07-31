package org.example.test.core.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public final class SQLParser {

	public static List<String> parser(String sqlform) {
		List<String> result;
		StringTokenizer st;
		
		result = new ArrayList<String>();
		st = new StringTokenizer(sqlform, ";");
		
		while (st.hasMoreElements()) {
			String sql = (String) st.nextElement();
			result.add(sql.trim());
		}

		return result;
	}


}
