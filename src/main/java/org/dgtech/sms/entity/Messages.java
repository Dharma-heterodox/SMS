package org.dgtech.sms.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Message_master")
public class Messages extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;
	@Lob
	private String msgBody;
	private LocalDateTime msgDate;
	private boolean display;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "msg", orphanRemoval = true)
	private List<NotifyGrade> notifyGrades= new ArrayList<NotifyGrade>(15);
	
	private String listedGrades;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public List<NotifyGrade> getNotifyGrades() {
		return notifyGrades;
	}
	public void setNotifyGrades(List<NotifyGrade> notifyGrades) {
		this.notifyGrades = notifyGrades;
	}
	public String getListedGrades() {
		return listedGrades;
	}
	public void setListedGrades(String listedGrades) {
		this.listedGrades = listedGrades;
	}
	public String getMsgBody() {
		return msgBody;
	}
	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}
	public LocalDateTime getMsgDate() {
		return msgDate;
	}
	public void setMsgDate(LocalDateTime msgDate) {
		this.msgDate = msgDate;
	}
	public boolean isDisplay() {
		return display;
	}
	public void setDisplay(boolean display) {
		this.display = display;
	}
	
	public void addNotifyGrade(NotifyGrade ntgr) {
		notifyGrades.add(ntgr);
		ntgr.setMsg(this);
	}
	
	public void removeNotifyGrade(NotifyGrade ntgr) {
		notifyGrades.remove(ntgr);
		ntgr.setMsg(null);
		
	}
	
	
	

}
