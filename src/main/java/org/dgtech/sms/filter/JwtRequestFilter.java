package org.dgtech.sms.filter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dgtech.sms.Exception.SMSException;
import org.dgtech.sms.model.PCAuthObjects;
import org.dgtech.sms.repo.PermissionRepo;
import org.dgtech.sms.sevice.SMSUserDetailsService;
import org.dgtech.sms.sevice.impl.UserAuthServicesImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{
	
	@Autowired
	private SMSUserDetailsService smsuserDetailsService;

	@Autowired
	private org.dgtech.sms.util.JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private PermissionRepo permissionRepo;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");
		String uri = request.getRequestURI();

		String userId = null;
		String jwtToken = null;
		// JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("jwt ")) {
			jwtToken = requestTokenHeader.substring(4);
			try {
				userId = jwtTokenUtil.getUsernameFromToken(jwtToken);
				request.setAttribute("userId", userId);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}
		logger.debug("Logged-in username :::: "+userId);
		//Once we get the token validate it.
		try {
			if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				UserDetails userDetails = smsuserDetailsService.loadUserByUsername(userId);

				// if token is valid configure Spring Security to manually set authentication
				if (jwtTokenUtil.isTokenExpired(jwtToken) && accessPermission(uri,Long.valueOf(userId))) {

					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					// After setting the Authentication in the context, we specify
					// that the current user is authenticated. So it passes the Spring Security Configurations successfully.
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
		}catch(SMSException exp) {
			
		}catch(Exception e) {
			
		}
		
		chain.doFilter(request, response);
	}
	
	private boolean accessPermission(String uri,Long userId)throws Exception, SMSException{
		Map<String, List<String>> accessMap = null;
		PCAuthObjects pcAuthObj=null;
		List<String> roles=null;
		List<String> userRoles=null;
		boolean access=false;
		try{
			pcAuthObj = UserAuthServicesImp.getPCAuthObj(userId);
			accessMap = permissionRepo.getURLRoleAccess();
			if(!accessMap.containsKey(uri)) {
				access=true;
			}else {
				roles=accessMap.get(uri);
				userRoles=pcAuthObj.getRole();
				if(userRoles.contains(roles)) {
					access= true;
				}
			}
			
		}catch(Exception ex) {
			
		}
		return access;
	}


}
