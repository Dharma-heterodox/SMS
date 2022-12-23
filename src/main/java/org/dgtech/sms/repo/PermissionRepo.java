package org.dgtech.sms.repo;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly=true)
public class PermissionRepo implements PermissionRepoCustom{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Map<String, List<String>> getURLRoleAccess() {
		Map<String, List<String>> permissionMap=new HashMap<String,List<String>>();
		TypedQuery<String[]> queryStr=em.createQuery("SELECT uri,roleAccess FROM permission",String[].class);
		List<String[]> result=queryStr.getResultList();
		result.forEach(h -> {
			String uri=h[0];
			String role=h[1];
			String[] roles=null;
			if(role!=null && role.contains(",")) {
				roles=role.split(",");
			}else {
				roles=new String[] {role};
			}
			
			permissionMap.put(uri,Arrays.asList(roles));
		});
		return permissionMap;
	}

}
