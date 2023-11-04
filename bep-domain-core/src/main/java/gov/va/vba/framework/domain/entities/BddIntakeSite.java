package gov.va.vba.framework.domain.entities;
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the BDD_INTAKE_SITE database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="BDD_INTAKE_SITE")
public class BddIntakeSite implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long lctnId;
	private String nm;
	private Lctn lctn;
	private java.util.Set<BnftClaim> bnftClaims;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;
	
    public BddIntakeSite() {
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
	@Column(name="LCTN_ID", unique=true, nullable=false, precision=15)
	public Long getLctnId() {
		return this.lctnId;
	}
	public void setLctnId(Long lctnId) {
		this.lctnId = lctnId;
	}

	@Basic()
	@Column(name="NM", unique=true, nullable=false, length=50)
	public String getNm() {
		return this.nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}

	//bi-directional one-to-one association to Lctn
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="LCTN_ID", referencedColumnName="LCTN_ID", nullable=false, insertable=false, updatable=false)
	public Lctn getLctn() {
		return this.lctn;
	}
	public void setLctn(Lctn lctn) {
		this.lctn = lctn;
	}

	//bi-directional many-to-one association to BnftClaim
	@OneToMany(mappedBy="bddIntakeSite", fetch=FetchType.LAZY)
	public java.util.Set<BnftClaim> getBnftClaims() {
		return this.bnftClaims;
	}
	public void setBnftClaims(java.util.Set<BnftClaim> bnftClaims) {
		this.bnftClaims = bnftClaims;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BddIntakeSite)) {
			return false;
		}
		BddIntakeSite castOther = (BddIntakeSite)other;
		return new EqualsBuilder()
			.append(this.getLctnId(), castOther.getLctnId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getLctnId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("lctnId", getLctnId())
			.toString();
	}

}