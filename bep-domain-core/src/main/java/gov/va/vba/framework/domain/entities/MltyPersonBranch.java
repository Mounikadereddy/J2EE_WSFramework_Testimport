package gov.va.vba.framework.domain.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the MLTY_PERSON_BRANCH database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="MLTY_PERSON_BRANCH")
public class MltyPersonBranch implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long mltyPersonBranchId;
	private String mltySvcBranchTypeNm;
	private String svcNbr;
	private MltyPerson mltyPerson;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public MltyPersonBranch() {
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
	@Column(name="MLTY_PERSON_BRANCH_ID", unique=true, nullable=false, precision=15)
	public Long getMltyPersonBranchId() {
		return this.mltyPersonBranchId;
	}
	public void setMltyPersonBranchId(Long mltyPersonBranchId) {
		this.mltyPersonBranchId = mltyPersonBranchId;
	}

	@Basic()
	@Column(name="MLTY_SVC_BRANCH_TYPE_NM", nullable=false, length=50)
	public String getMltySvcBranchTypeNm() {
		return this.mltySvcBranchTypeNm;
	}
	public void setMltySvcBranchTypeNm(String mltySvcBranchTypeNm) {
		this.mltySvcBranchTypeNm = mltySvcBranchTypeNm;
	}

	@Basic()
	@Column(name="SVC_NBR", length=9)
	public String getSvcNbr() {
		return this.svcNbr;
	}
	public void setSvcNbr(String svcNbr) {
		this.svcNbr = svcNbr;
	}

	//bi-directional many-to-one association to MltyPerson
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PTCPNT_ID", referencedColumnName="PTCPNT_ID", nullable=false)
	public MltyPerson getMltyPerson() {
		return this.mltyPerson;
	}
	public void setMltyPerson(MltyPerson mltyPerson) {
		this.mltyPerson = mltyPerson;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MltyPersonBranch)) {
			return false;
		}
		MltyPersonBranch castOther = (MltyPersonBranch)other;
		return new EqualsBuilder()
			.append(this.getMltyPersonBranchId(), castOther.getMltyPersonBranchId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getMltyPersonBranchId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("mltyPersonBranchId", getMltyPersonBranchId())
			.toString();
	}
}