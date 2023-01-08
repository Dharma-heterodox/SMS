package org.dgtech.sms.controller;

import javax.xml.bind.ValidationException;

import org.dgtech.sms.Exception.SMSException;
import org.dgtech.sms.entity.User;
import org.dgtech.sms.model.EmployeeDto;
import org.dgtech.sms.model.LoginDto;
import org.dgtech.sms.model.PCAuthObjects;
import org.dgtech.sms.model.PCAuthResponse;
import org.dgtech.sms.sevice.EmployeeService;
import org.dgtech.sms.sevice.ParentService;
import org.dgtech.sms.sevice.SMSUserDetailsService;
import org.dgtech.sms.sevice.StudentService;
import org.dgtech.sms.sevice.TeacherMappingService;
import org.dgtech.sms.sevice.UserAuthServices;
import org.dgtech.sms.sevice.UserService;
import org.dgtech.sms.util.Constant;
import org.dgtech.sms.util.JwtTokenUtil;
import org.dgtech.sms.util.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    @Autowired
    private UserAuthServices userAuthServices;
    
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
    
    
    
    @PostMapping(value={"/pcUserLogin"})
    @ResponseStatus(HttpStatus.OK)
    public PCAuthResponse pcUserAuthentication(@RequestBody LoginDto loginDto) throws Exception{
    	System.out.println("--->"+loginDto.getUserId());
    	PCAuthObjects authObj = null;
    	PCAuthResponse respObj = null;
    	Object[] userObjs = null;
    	try {
    		userObjs = userAuthServices.loginPCUser(loginDto);
        	if(userObjs == null) {
        		throw new ValidationException("User not found");
        	}
        	respObj = (PCAuthResponse) userObjs[0];
        	authObj = (PCAuthObjects) userObjs[1];
        	userAuthServices.putUserInMap(authObj.getUserId(),authObj);
        	respObj.setResponseCode(Constant.SUCCESS_REPS);
    	}catch(SMSException |Exception sexp) {
    		respObj=new PCAuthResponse();
    		respObj.setResponseCode(Constant.FAILED_RESP);
    		respObj.setErrorMsg(sexp.getMessage());
    	}
       	return respObj;
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