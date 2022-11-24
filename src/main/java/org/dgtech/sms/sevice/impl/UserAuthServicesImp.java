package org.dgtech.sms.sevice.impl;

import org.apache.logging.log4j.Logger;
import org.dgtech.sms.entity.User;
import org.dgtech.sms.model.LoginDto;
import org.dgtech.sms.repo.RoleRepository;
import org.dgtech.sms.repo.UserRepository;
import org.dgtech.sms.sevice.UserAuthServices;
import org.dgtech.sms.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthServicesImp implements UserAuthServices{
	
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    
    
    public String loginUser(LoginDto dto)throws Exception{
    	User user=null;
    	if(validateDto(dto)==null) {
    		user=userRepository.authUser(dto.getUserId(), dto.getPassword());
    		if(user==null) {
    			throw new Exception("User Id or Password is incorrect");
    		}
    	}
    }
    
    
    private String validateDto(LoginDto dto)throws Exception{
    	String response=null;
    	if((dto.getUserId()==null || dto.getUserId().isEmpty())||
    			(dto.getPassword()==null || dto.getPassword().isEmpty())) {
    		response="User Id or Password should not be empty";
    	}else if(!dto.getUserId().matches(Constant.EMAIL_REGEX)) {
    		response="Enter proper user Id";
    	}else if(!dto.getPassword().matches(Constant.NAME_REGEX)) {
    		response="Enter proper password";
    	}
    	return response;
    	
    }

}
