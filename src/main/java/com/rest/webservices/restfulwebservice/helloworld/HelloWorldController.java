package com.rest.webservices.restfulwebservice.helloworld;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
//Rest API
@RestController
public class HelloWorldController {
	// /hello-world
	// text return
	private MessageSource messageSource;
	public HelloWorldController(MessageSource ms) {
		this.messageSource = ms;
	}
	@GetMapping(path="/helloworld")
	public String helloworld() {
		return "hello world";
	}
	
	@GetMapping(path="/helloworld-bean/path-variable/{name}")
	public HelloworldBean helloworldPathVariable(@PathVariable String name) {
		return new HelloworldBean("hello world"+name);
	}
	@GetMapping(path="/helloworld-i18n")
	public String helloworldInter() {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null,"Default Message",locale);
	}

}
