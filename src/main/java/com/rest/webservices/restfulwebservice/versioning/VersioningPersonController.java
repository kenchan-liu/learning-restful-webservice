package com.rest.webservices.restfulwebservice.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
	@GetMapping("/v1/person")
	public PersonV1 getFirstVersionOfPerson() {
		return new PersonV1("ranga noir");
		
	}
	@GetMapping("/v2/person")
	public PersonV2 getSecondVersionOfPerson() {
		return new PersonV2(new Name("alice","bob"));
	}
	@GetMapping(path = "/person",params = "version =1")
	public PersonV1 getPersonV1byparam(){
		return new PersonV1("nn 1");
	}
	
	@GetMapping(path = "/person/header",headers = "X-API-VERSION=1")
	public PersonV1 getFirstVersionOfPersonRequestHeader(){
		return new PersonV1("nn 1");
	}

}
