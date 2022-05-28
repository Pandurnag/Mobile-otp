package com.app.controller;

import java.net.http.WebSocket;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.SmsPojo;
import com.app.service.SmsService;
import com.twilio.rest.accounts.v1.credential.PublicKey;
import com.twilio.rest.api.v2010.account.message.Media;
import com.twilio.twiml.fax.Receive.MediaType;

@RestController
public class SMSController {
	
	@Autowired
	SmsService service;
	
	@Autowired
	private SimpMessagingTemplate webSocket;
	
	
	private final String TOPIC_DESTINATION = "/lesson/sms";
	//You can send SMS in verified number
	@PostMapping("/mobileNo")
	public ResponseEntity<Boolean> smsSubmit(@RequestBody SmsPojo sms){
		
		try {
			System.out.println("hello");
			   service.send(sms);
			System.out.println("hello");  
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ResponseEntity<Boolean>(false,null, HttpStatus.SC_INTERNAL_SERVER_ERROR);
	}
	WebSocket.convertAndSend(TOPIC_DESTINATION, getTimeStamp() + ";SMS has been sent!:" +sms.getPhoneNo());
	return new ResponseEntity<Boolean>(true,HttpStatus.SC_OK);
	
	@RequestMapping(value = "/smscallback", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_PDF,produces = MediaType.APPLICATION_PDF,produces = MediaType.APPLICATION_PDF)
	Public void smsCallback(@RequestBody MultivalueMap<String, String> map) {
		service.receive(map);
		webSocket.convertAndSend(TOPIC_DESTINATION, getTimeStamp() + ": Twilio has made a collback request! Here are the contents:
	}
    private String getTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()); 
		
	}	

}
