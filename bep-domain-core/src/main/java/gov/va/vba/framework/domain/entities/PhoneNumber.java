package gov.va.vba.framework.domain.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class PhoneNumber {

	private Integer phoneNumber;
	private Integer areaCode;
	private Integer extension;
	private Date endDate;
	private String displayNumber;
	private String numberWithExt;
	private String prefix;
	private String number;
	/*private Long participantId;
	private String phoneType;*/
	public abstract PhonePK getId();
	
	public abstract void setId(PhonePK id);

	@Transient
	public Long getParticipantId() {
		if(getId() != null) {
			return getId().getPtcpntId();
		}
		return null;
	}

	public void setParticipantId(Long participantId) {
		if(getId() == null) {
			setId(new PhonePK());
		}
		getId().setPtcpntId(participantId);
	}

	@Transient
	public String getPhoneType() {
		if(getId() != null) {
			return getId().getPhnType();
		}
		return null;
	}

	public void setPhoneType(String phoneType) {
		if(getId() == null) {
			setId(new PhonePK());
		}
		getId().setPhnType(phoneType);
	}
	
	@Transient
	public String getDisplayNumber() {
		if(null == getAreaCode() || null == getPhoneNumber()) {
			return null;
		}
		StringBuilder phone = new StringBuilder(getPhoneNumber());
		String displayNumber = "(" + getAreaCode() + ") " + phone.insert(3, "-").toString();
		if(this.getExtension() != null && !"".equals(this.getExtension())) {
			displayNumber += " Ext. " + this.getExtension();
		}
		return displayNumber;
	}
	
	@Transient
	public String getNumberWithExt() {
		String number = getDisplayNumber();
		if(this.getExtension() != null && !"".equals(this.getExtension())) {
			number += " Ext. " + this.getExtension();
		}
		return number;
	}
	
	@Transient
	public String getPrefix() {
		if(null == getPhoneNumber()) {
			return null;
		}
		return getPhoneNumber().toString().substring(0, 3);
	}

	@Transient
	public String getNumber() {
		if(null == getPhoneNumber()) {
			return null;
		}
		return getPhoneNumber().toString().substring(3);
	}
	
	@Column(name="PHONE_NBR")
	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name="AREA_NBR")
	public Integer getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(Integer areaCode) {
		this.areaCode = areaCode;
	}

	@Column(name="EXTNSN_NBR")
	public Integer getExtension() {
		return extension;
	}

	public void setExtension(Integer extension) {
		this.extension = extension;
	}

	public Date getEffectiveDate() {
		return getId().getEffectiveDate();
	}

	public void setEffectiveDate(Date effectiveDate) {
		if(getId() == null) {
			setId(new PhonePK());
		}
		this.getId().setEffectiveDate(effectiveDate);
	}

	@Column(name="END_DT")
	public Date getEndDate() {
		return endDate;
	}

	public void setDisplayNumber(String displayNumber) {
		this.displayNumber = displayNumber;
	}

	public void setNumberWithExt(String numberWithExt) {
		this.numberWithExt = numberWithExt;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
