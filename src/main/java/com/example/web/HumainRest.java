package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.HumainService;

@RestController
public class HumainRest {
	@Autowired
	private HumainService humainService;
	
}
