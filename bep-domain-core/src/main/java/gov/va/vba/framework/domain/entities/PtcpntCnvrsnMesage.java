package gov.va.vba.framework.domain.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the PTCPNT_CNVRSN_MESAGE database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="PTCPNT_CNVRSN_MESAGE")
public class PtcpntCnvrsnMesage implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long ptcpntCnvrsnMesageId;
	private String cnvrsnMesageProcsgTypeCd;
	private String cnvrsnMesageTypeCd;
	private String mesageTxt;
	private Person person;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public PtcpntCnvrsnMesage() {
    }
    
	@Basic()
	@Column(name="JRN_DT", nullable=false, length=7)
	public java.util.Date getJrnDt() {
		return this.jrnDt;
	}
	public void setJrnDt(java.util.Date jrnDt) {
		this.jrnDt = jrnDt;
	}

	@Basic()
	@Column(name="JRN_LCTN_ID", nullable=false, length=4)
	public String getJrnLctnId() {
		return this.jrnLctnId;
	}
	public void setJrnLctnId(String jrnLctnId) {
		this.jrnLctnId = jrnLctnId;
	}

	@Basic()
	@Column(name="JRN_OBJ_ID", nullable=false, length=32)
	public String getJrnObjId() {
		return this.jrnObjId;
	}
	public void setJrnObjId(String jrnObjId) {
		this.jrnObjId = jrnObjId;
	}

	@Basic()
	@Column(name="JRN_STATUS_TYPE_CD", nullable=false, length=12)
	public String getJrnStatusTypeCd() {
		return this.jrnStatusTypeCd;
	}
	public void setJrnStatusTypeCd(String jrnStatusTypeCd) {
		this.jrnStatusTypeCd = jrnStatusTypeCd;
	}

	@Basic()
	@Column(name="JRN_USER_ID", nullable=false, length=50)
	public String getJrnUserId() {
		return this.jrnUserId;
	}
	public void setJrnUserId(String jrnUserId) {
		this.jrnUserId = jrnUserId;
	}
	
	@Id()
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PTCPNT_CNVRSN_MESAGE_ID", unique=true, nullable=false, precision=15)
	public Long getPtcpntCnvrsnMesageId() {
		return this.ptcpntCnvrsnMesageId;
	}
	public void setPtcpntCnvrsnMesageId(Long ptcpntCnvrsnMesageId) {
		this.ptcpntCnvrsnMesageId = ptcpntCnvrsnMesageId;
	}

	@Basic()
	@Column(name="CNVRSN_MESAGE_PROCSG_TYPE_CD", nullable=false, length=12)
	public String getCnvrsnMesageProcsgTypeCd() {
		return this.cnvrsnMesageProcsgTypeCd;
	}
	public void setCnvrsnMesageProcsgTypeCd(String cnvrsnMesageProcsgTypeCd) {
		this.cnvrsnMesageProcsgTypeCd = cnvrsnMesageProcsgTypeCd;
	}

	@Basic()
	@Column(name="CNVRSN_MESAGE_TYPE_CD", nullable=false, length=12)
	public String getCnvrsnMesageTypeCd() {
		return this.cnvrsnMesageTypeCd;
	}
	public void setCnvrsnMesageTypeCd(String cnvrsnMesageTypeCd) {
		this.cnvrsnMesageTypeCd = cnvrsnMesageTypeCd;
	}

	@Basic()
	@Column(name="MESAGE_TXT", length=80)
	public String getMesageTxt() {
		return this.mesageTxt;
	}
	public void setMesageTxt(String mesageTxt) {
		this.mesageTxt = mesageTxt;
	}

	//bi-directional many-to-one association to Person
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PTCPNT_ID", referencedColumnName="PTCPNT_ID", nullable=false)
	public Person getPerson() {
		return this.person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PtcpntCnvrsnMesage)) {
			return false;
		}
		PtcpntCnvrsnMesage castOther = (PtcpntCnvrsnMesage)other;
		return new EqualsBuilder()
			.append(this.getPtcpntCnvrsnMesageId(), castOther.getPtcpntCnvrsnMesageId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getPtcpntCnvrsnMesageId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("ptcpntCnvrsnMesageId", getPtcpntCnvrsnMesageId())
			.toString();
	}
}