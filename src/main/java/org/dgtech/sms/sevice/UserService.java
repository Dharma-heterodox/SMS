package org.dgtech.sms.sevice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.dgtech.sms.entity.Role;
import org.dgtech.sms.entity.User;
import org.dgtech.sms.repo.RoleRepository;
import org.dgtech.sms.repo.UserRepository;
import org.dgtech.sms.util.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

	@Autowired
    private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public User findUserByMobileLoginId(String mobile,int loginId) {
//        return userRepository.findByMobile(mobile,loginId);
    	return null;
    }
    
    public User findUserByMobile(String mobile) {
        return userRepository.findByMobile(mobile);
    }

    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
    
    public User findById(Long id) {
    	Optional<User> user = userRepository.findById(id);
    	if(user != null) {
    		return user.get();
    	}
    	return null;
    }

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole(Roles.ADMIN.toString());
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }
    
    public User saveOrgAdminAccount(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole(Roles.COORDINATOR.toString());
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }
    
    public User saveStudentAccount(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole(Roles.STUDENT.toString());
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }
    
    public User saveParentAccount(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole(Roles.PARENT.toString());
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }
    
    public User saveEmployeeAccount(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole(Roles.TEACHER.toString());
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }
    
    public Set<String> getMobileNo()throws Exception{
    	return userRepository.getMobileBySchool();
    }

}
