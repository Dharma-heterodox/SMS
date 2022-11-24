package org.dgtech.sms.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.ValidationException;

import org.dgtech.sms.entity.Role;
import org.dgtech.sms.entity.User;
import org.dgtech.sms.model.AuthResponseDto;
import org.dgtech.sms.model.EmployeeDto;
import org.dgtech.sms.model.LoginDto;
import org.dgtech.sms.model.ParentDto;
import org.dgtech.sms.model.StudentDto;
import org.dgtech.sms.model.TeacherMappingDto;
import org.dgtech.sms.model.VerifyOtpDto;
import org.dgtech.sms.sevice.EmployeeService;
import org.dgtech.sms.sevice.ParentService;
import org.dgtech.sms.sevice.SMSUserDetailsService;
import org.dgtech.sms.sevice.StudentService;
import org.dgtech.sms.sevice.TeacherMappingService;
import org.dgtech.sms.sevice.UserService;
import org.dgtech.sms.util.JwtTokenUtil;
import org.dgtech.sms.util.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class LoginController {

    @Autowired
    private UserService userService;
    
//    @Autowired
//    private MessageService messageService;
    
    @Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private SMSUserDetailsService userDetailsService;
	
	@Autowired
	private ParentService parentService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private TeacherMappingService teacherMappingService;
	@Autowired
	private EmployeeService employeeService;
    

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView signin(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
    
    @RequestMapping(value={"/login"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody LoginDto login) throws Exception{
    	User user = userService.findUserByMobile(login.getMobilenumber());
    	if(user == null) {
    		throw new ValidationException("User not found");
    	}
//        messageService.sendOTPV2(login.getMobilenumber());
        return "success";
    }
    
    @RequestMapping(value={"/verifyOTP"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public AuthResponseDto verifyOTP(@RequestBody VerifyOtpDto verifyOtpDto) throws Exception{
    	User user = userService.findUserByMobile(verifyOtpDto.getMobilenumber());
    	System.out.println("--->"+verifyOtpDto.getMobilenumber());
    	if(user == null) {
    		throw new ValidationException("User not found");
    	}
//        boolean isValid = messageService.verifyOTP(verifyOtpDto.getMobilenumber(), verifyOtpDto.getOtp());
//    	boolean isValid = messageService.verifyOTPV2(verifyOtpDto.getMobilenumber(), verifyOtpDto.getOtp());
    	boolean isValid = true;
        if(!isValid) {
        	throw new ValidationException("Code not valid");
        }
        List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
        AuthResponseDto authResponse = new AuthResponseDto();
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getMobile(), user.getPassword(),
                user.getActive(), true, true, true, authorities);
		authenticate(userDetails);
		String token = jwtTokenUtil.generateToken(userDetails);
		authResponse.setFirstName(user.getFirstName());
		authResponse.setLastName(user.getLastName());
		authResponse.setDisplayName(user.getFirstName());
		authResponse.setRoles(user.getRoles());
		for(Role role: user.getRoles()) {
			if(role.getRole().equalsIgnoreCase(Roles.PARENT.toString())) {
				ParentDto parent = parentService.getParentByMobile(user.getMobile());
//				List<StudentDto> childs = studentService.getStudentsByParentId(parent.getId());
				List<StudentDto> childs = parent.getChilds();
				if(!CollectionUtils.isEmpty(childs)) {
					Set<StudentDto> students = new HashSet<StudentDto>();
					for(StudentDto student: childs) {
//						TeacherMappingDto teacher = teacherMappingService.getClassTeacher(student.getSchoolId(), student.getGradeId(), student.getSectionId(), null);
//						if(teacher != null) {
//							student.setClassTeacher(teacher);
//							students.add(student);
//						}
						students.add(student);
					}
					authResponse.setStudents(students);
				}
			}
		}
		authResponse.setUserId(user.getId());
		authResponse.setToken(token);
		return authResponse;
    }

	public List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
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


	/*
	 * @RequestMapping(value="/registration", method = RequestMethod.GET) public
	 * ModelAndView registration(){ ModelAndView modelAndView = new ModelAndView();
	 * User user = new User(); modelAndView.addObject("user", user);
	 * modelAndView.setViewName("registration"); return modelAndView; }
	 * 
	 * @RequestMapping(value = "/registration", method = RequestMethod.POST) public
	 * ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
	 * ModelAndView modelAndView = new ModelAndView(); User userExists =
	 * userService.findUserByUserName(user.getUserName()); if (userExists != null) {
	 * bindingResult .rejectValue("userName", "error.user",
	 * "There is already a user registered with the user name provided"); } if
	 * (bindingResult.hasErrors()) { modelAndView.setViewName("registration"); }
	 * else { userService.saveUser(user); modelAndView.addObject("successMessage",
	 * "User has been registered successfully"); modelAndView.addObject("user", new
	 * User()); modelAndView.setViewName("registration");
	 * 
	 * } return modelAndView; }
	 */

    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserName() + "/" + user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }
//	Created By : Dharma
//	Date:10-10-2020
//	Purpose: To retrieve the Employee Details , if the signed person was employee .
    @GetMapping(value="/login/employee/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto getEmployeeDetails(@PathVariable("userId")Long userId) throws Exception{
    	EmployeeDto employee=new EmployeeDto();
    	employee= employeeService.getEmployeeByUserId(userId);
    	if(employee.getCategory().equals(Roles.TEACHER.toString())){
    		employee.setTeacherMapping(teacherMappingService.getTechMapByTeacher(employee.getId()));
    	}
    	
    	return employee;
    }


}