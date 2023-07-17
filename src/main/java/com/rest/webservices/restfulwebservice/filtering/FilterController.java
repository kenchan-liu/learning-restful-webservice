package com.rest.webservices.restfulwebservice.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.ser.FilterProvider;

@RestController
public class FilterController {
	@GetMapping("/filtering")
	public MappingJacksonValue filtering() {
		SomeBean someBean = new SomeBean("value1","value2","value3");
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFIlter",filter);
		
		mappingJacksonValue.setFilters(filters);
		return mappingJacksonValue;
	}
	
	
	@GetMapping("/filtering-list")
	public MappingJacksonValue filteringlist() {
		List<SomeBean> list = Arrays.asList(new SomeBean("value1","value2","value3"),new SomeBean("value4","value5","value6"));
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFIlter",filter);
		
		mappingJacksonValue.setFilters(filters);
		return mappingJacksonValue;
	}
}
