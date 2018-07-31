package org.example.test.web.validator;

import java.util.List;

import org.example.test.core.domain.PeticionDS;
import org.example.test.core.helper.SQLParser;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PeticionDSFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PeticionDS.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sql", "sql.required");
  
		PeticionDS p = (PeticionDS) target;

		List<String> sqls = SQLParser.parser(p.getSql());
		
		for (String sql : sqls) {
			if (sql.toUpperCase().startsWith("SELECT")) {
				errors.rejectValue("sql", "sql.query.select");
				break;
			}
		}
		
	}


}
