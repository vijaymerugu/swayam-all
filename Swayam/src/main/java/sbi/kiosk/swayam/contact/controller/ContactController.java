package sbi.kiosk.swayam.contact.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import sbi.kiosk.swayam.common.constants.ExceptionConstants;
import sbi.kiosk.swayam.common.dto.ContactDto;
import sbi.kiosk.swayam.common.entity.ContactEntity;
import sbi.kiosk.swayam.common.entity.SecondaryContacts;
import sbi.kiosk.swayam.contact.repository.ContactRepository;
import sbi.kiosk.swayam.contact.repository.SecondaryContactRepoistory;

@RestController
public class ContactController {
	
	Logger logger =  LoggerFactory.getLogger(ContactController.class);
	
	
	@Autowired
	ContactRepository contactRepo;
	
	@Autowired
	SecondaryContactRepoistory secondRepo;
	
	
	
	@RequestMapping("cs/display")
	public ModelAndView ContatPage(ModelAndView model, HttpSession session) {
		
		try {
			
			
			model.setViewName("ContactUs2");
			
		} catch (Exception e) {
		
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}
	
	
	@RequestMapping("cs/faq")
	public ModelAndView faqPage(ModelAndView model, HttpSession session) {
		
		try {
			
			
			model.setViewName("FAQ");
			
		} catch (Exception e) {
		
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}
	
	
	
	@SuppressWarnings("null")
	@RequestMapping(value = "cs/getContact", method = RequestMethod.GET)
	public ResponseEntity<?> getContacts(){
		logger.info("Inside Get Contacts");
		 
		//circleRepo.findAll();
		Gson gson = new GsonBuilder().disableHtmlEscaping()
				.setPrettyPrinting().create();
		
		//ContactDto dto = new ContactDto();
		
		ContactDto dto;
		List<ContactEntity> list =  (List<ContactEntity>) contactRepo.findAll();
		
		List<ContactDto> list2 = new ArrayList<ContactDto>();
		
		Iterator<ContactEntity> itr =  list.iterator();
		
		while (itr.hasNext()) {
			ContactEntity object = (ContactEntity) itr.next();
			
			dto = new ContactDto();
			
			dto.setContactId(object.getContactId());
			dto.setContactName(object.getContactName());
			dto.setEmail(object.getEmail());
			dto.setEsMatrix(object.getEsMatrix());
			dto.setL1contactNo(object.getL1contactNo());
			//dto.setL2Contact("data:application/pdf;base64,"+Base64.getEncoder().encodeToString(object.getL2Contact())); 
			dto.setType(object.getType());
			
			list2.add(dto);
		}
		
		 String json2 = gson.toJson(list2);
		
		// logger.info("Json "+ json2);
			/*
			 * System.out.println("List " + list2);
			 * 
			 * 
			 * String json = gson.toJson(contactRepo.findAll());
			 */
		//logger.info("circles "+ json);
		 
		
		return ResponseEntity.ok(json2);
		
		
	}
	
	
	
	@RequestMapping(value = "cs/getFirst", method = RequestMethod.GET)
	public ResponseEntity<?> getFirstContacts(){
		logger.info("Inside Secondary Contacts Contacts");
		 
		
		Gson gson = new GsonBuilder().disableHtmlEscaping()
				.setPrettyPrinting().create();
		
	
		String json = gson.toJson( secondRepo.getfirstLevelContact());
		return ResponseEntity.ok(json);
		
		
	}
	
	@RequestMapping(value = "cs/getSecond", method = RequestMethod.GET)
	public ResponseEntity<?> getSecondContacts(){
		logger.info("Inside Secondary Contacts Contacts");
		 
		
		Gson gson = new GsonBuilder().disableHtmlEscaping()
				.setPrettyPrinting().create();
		
	
		String json = gson.toJson( secondRepo.getSecondLevelContact());
		return ResponseEntity.ok(json);
		
		
	}
	
	

}
