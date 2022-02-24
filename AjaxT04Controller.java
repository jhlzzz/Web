package soo.md.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import soo.md.domain.Address;
import soo.md.service.AddressAjaxService;

@Log4j
@RestController
@RequestMapping("ajax04")
@AllArgsConstructor
public class AjaxT04Controller {
	private AddressAjaxService service;
	
	@GetMapping(value="search01", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Address search01(long seq) {
		Address address = service.selectBySeqS(seq);
		
		return address;
	}
	@PostMapping(value="search02", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<Address> search02(String name) {
		List<Address> list = service.selectByNameS(name);
		
		return list;
	}
	
	//Tip
	@GetMapping("txt")
    public String getText() {
    	return "Good morning";
    }
}





