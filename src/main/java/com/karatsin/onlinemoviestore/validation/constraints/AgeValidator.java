package com.karatsin.onlinemoviestore.validation.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AgeValidator implements ConstraintValidator<AgeConstraint, Integer> {
	 
	protected long minAge;
	    
	@Override
	public void initialize(AgeConstraint ageValue) {
		
		this.minAge = ageValue.minAge();
	}
	
	@Override
	public boolean isValid(Integer age, ConstraintValidatorContext constraintValidatorContext) {
		
		if(age == null || age>100 || age<0) {
			return false;
		}

		return age>=minAge;
	 }
}
