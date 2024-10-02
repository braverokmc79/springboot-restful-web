package net.macaronics.springboot.webapp.api.controller;


import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
@Slf4j
public class ApiHelloWorldController {
	
	private final MessageSource messageSource;
		
	@GetMapping
	public String index() {		
		return "Hello Test";
	}
		
	/**
	 * 다국어 테스트
	 * @return
	 */
	//  http://localhost:8080/api/test/multi-lang
	@GetMapping(path = "/multi-lang")
	public String multiLingualTest() {
		Locale  locale=LocaleContextHolder.getLocale();			
		log.info(" locale   :" + locale);	
		//locale  로컬값이 ko_KR 이면
		return messageSource.getMessage("good.morning.message", null, "다국어 기본 설정값이야!"  , locale);	
	}

	
	
}




