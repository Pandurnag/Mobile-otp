package com.app.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.TempOTP;

@RestController
public class VerifyOTPController {
	
	@PostMapping("/otp")
	public String verifyOTP(@RequestBody TempOTP  sms) {
		
		if(sms.getOtp()==12345)
		
		return "Correct OTP";
		
		else
			
			return "Not correct OTP";
		
	}

}
