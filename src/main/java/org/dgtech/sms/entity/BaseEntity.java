package org.dgtech.sms.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.UpdateTimestamp;


//@FilterDef(name = BaseEntity.TENANT_FILTER, parameters = @ParamDef(name = BaseEntity.TENANT_ID_PARAM, type = "int"))
//@Filters(
//        @Filter(name = BaseEntity.TENANT_FILTER, condition = BaseEntity.TENANT_ID+" = :"+BaseEntity.TENANT_ID_PARAM)
//)
@MappedSuperclass
//@EntityListeners(TenantEntityListener.class)
public class BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
//	public static final String TENANT_FILTER = "tenantFilter";
//	public static final String TENANT_ID_PARAM = "tenantId";
//    public static final String TENANT_ID = "TENANT_ID";
//    @Column(name = TENANT_ID, columnDefinition = "INT")
//    private Integer tenantId;
    private String source;
    @CreationTimestamp
    private LocalDateTime createdTime;
    @UpdateTimestamp
    private LocalDateTime updatedTime;
    private String createdBy;
    private String updatedBy;

//    public Integer getTenantId() {
//        return tenantId;
//    }
//
//    public void setTenantId(Integer tenantId) {
//        this.tenantId = tenantId;
//    }

	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}

	public LocalDateTime getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(LocalDateTime updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}
