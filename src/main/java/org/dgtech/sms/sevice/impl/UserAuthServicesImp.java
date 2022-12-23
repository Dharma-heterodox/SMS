package org.dgtech.sms.sevice.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.dgtech.sms.Exception.SMSException;
import org.dgtech.sms.entity.Parent;
import org.dgtech.sms.entity.Role;
import org.dgtech.sms.entity.User;
import org.dgtech.sms.entity.student.Student;
import org.dgtech.sms.model.LoginDto;
import org.dgtech.sms.model.PCAuthObjects;
import org.dgtech.sms.model.PCAuthResponse;
import org.dgtech.sms.model.StudentAuthResponse;
import org.dgtech.sms.repo.ParentRepo;
import org.dgtech.sms.repo.RoleRepository;
import org.dgtech.sms.repo.UserRepository;
import org.dgtech.sms.sevice.UserAuthServices;
import org.dgtech.sms.util.Constant;
import org.dgtech.sms.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    @Autowired
    private ParentRepo parentRepo;
    @Autowired
	private JwtTokenUtil jwtTokenUtil;
    
    private static Map<Long,PCAuthObjects> pcAuthUsers=new ConcurrentHashMap<Long,PCAuthObjects>();
    
    private static Map<String,List<String>> rolePermissions= new HashMap<String,List<String>>();
    
    
    @Override
    public Object[] loginPCUser(LoginDto dto)throws SMSException,Exception {
    	User user=null;
    	Parent parent=null;
    	Object[] resp=new Object[2];
    	PCAuthResponse mblResp=new PCAuthResponse();
    	PCAuthObjects respObj=new PCAuthObjects();
    	String validate=validateDto(dto);
    	if(validate==null) {
    		user=userRepository.authUser(dto.getUserId(), dto.getPassword());
    		if(user==null) {
    			throw new SMSException("User Id or Password is incorrect");
    		}
    		parent=parentRepo.findByEmail(dto.getUserId());
    		if(parent==null) {
    			throw new SMSException("User is not a parent");
    		}
    		List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
    		UserDetails userDetails = new org.springframework.security.core.userdetails.User(String.valueOf(user.getUserId()), user.getPassword(),
                    user.getActive(), true, true, true, authorities);
    		authenticate(userDetails);
    		String token = jwtTokenUtil.generateToken(userDetails);
    		setAuthResp(mblResp, respObj, user, parent,token);
    		resp[0]=mblResp;
    		resp[1]=respObj;
    	}else {
    		throw new SMSException(validate);
    	}
    	return resp;
    }
    
    private void setAuthResp(PCAuthResponse mblResp,PCAuthObjects respObj,User user,Parent parent,String token)throws Exception{
    	mblResp.setEmail(user.getEmail());
    	mblResp.setToken(token);
    	mblResp.setStudents(getChilderns(parent.getChildList()));
    	mblResp.setFathersName(parent.getFatherName());
    	mblResp.setMothersName(parent.getMotherName());
    	mblResp.setUserId(user.getUserId());
    	
    	List<Role> role=new ArrayList<Role>(user.getRoles());
    	List<String> roleStg=new ArrayList<String>(role.size());
//    	List<String> permissions=new ArrayList<String>();
    	role.forEach(h -> {
    		roleStg.add(h.getRole());
//    		h.getPermissions().forEach(i ->{
//    			permissions.add(i.getPermissionName());
//    		});
    	});
    	
    	respObj.setEmail(user.getEmail());
    	respObj.setFathersName(parent.getFatherName());
    	respObj.setMothersName(parent.getMotherName());
    	respObj.setMobile(user.getMobile());
    	respObj.setRole(roleStg);
    	respObj.setToken(token);
//    	respObj.setPermissions(permissions);
    	respObj.setUserId(user.getUserId());
    }
    
    private Set<StudentAuthResponse> getChilderns(List<Student> students){
    	Set<StudentAuthResponse> studentResp=new HashSet<StudentAuthResponse>();
    	students.forEach(h -> {
    		StudentAuthResponse authResp=new StudentAuthResponse();
    		authResp.setSec(h.getSection());
    		authResp.setStd(h.getGrade());
    		authResp.setStudId(h.getId().toString());
    		authResp.setStudName(h.getFirstName()+" "+h.getLastName());
    		studentResp.add(authResp);
    	});
    	
    	return studentResp;
    }
    
    
    
    private String validateDto(LoginDto dto)throws Exception{
    	String response=null;
    	if((dto.getUserId()==null || dto.getUserId().isEmpty())||
    			(dto.getPassword()==null || dto.getPassword().isEmpty())) {
    		response="User Id or Password should not be empty";
    	}else if(!dto.getUserId().matches(Constant.EMAIL_REGEX)) {
    		response="Enter proper user Id";
    	}else if(!dto.getPassword().matches(Constant.ALPHA_NUMERIC_REGEX)) {
    		response="Enter proper password";
    	}
    	return response;
    	
    }
    
    @Override
    public void putUserInMap(Long userId,PCAuthObjects userObj)throws Exception{
    	pcAuthUsers.put(userId, userObj);
    }
    
    public static PCAuthObjects getPCAuthObj(Long userId) throws SMSException,Exception{
    	if(pcAuthUsers.containsKey(userId)){
    		return pcAuthUsers.get(userId);
    	}else {
    		throw new SMSException("Token not valid");
    	}
    }
    
    @Override
    public void deletePCAuthUser(Long userId)throws Exception{
    	if(pcAuthUsers.containsKey(userId)){
    		 pcAuthUsers.remove(userId);
    	}
    }
    
    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
	    Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
	    for (Role role : userRoles) {
	        roles.add(new SimpleGrantedAuthority(role.getRole()));
	    }
	    List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
	    return grantedAuthorities;
	}

	private void authenticate(UserDetails userDetails) throws Exception {
		try {
			UsernamePasswordAuthenticationToken auth =new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
			auth.setDetails(userDetails);
			SecurityContextHolder.getContext().setAuthentication(auth);
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	public boolean hasRole(Long userId,String role)throws Exception{
		return false;
	}


}
