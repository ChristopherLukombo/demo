package com.example.demo;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class Dev implements Condition {

	@Override
	public boolean matches(final ConditionContext arg0, final AnnotatedTypeMetadata arg1) {
		// TODO Auto-generated method stub
		return false;
	}

}
