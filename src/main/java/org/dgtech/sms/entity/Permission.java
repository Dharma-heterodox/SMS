package org.dgtech.sms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "permission")
public class Permission extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private Long id;
    @Column(name = "permission_name",length=100)
    private String permissionName;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	
	@Override
	public int hashCode() {
		int prime = 31;
		
		return prime*id.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		
		Permission per = (Permission)obj;
		
		if (per == null ) return false;
		
		if((per.id == this.id) || (this.id.equals(per.id)) ) return true;
		
		if((this.permissionName.equals(per.permissionName))) return true;
		
		return false;
	}
    
    

}
