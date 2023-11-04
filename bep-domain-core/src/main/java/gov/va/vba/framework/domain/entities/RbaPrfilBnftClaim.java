package gov.va.vba.framework.domain.entities;
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the RBA_PRFIL_BNFT_CLAIM database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="RBA_PRFIL_BNFT_CLAIM")
public class RbaPrfilBnftClaim  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long rbaPrfilBnftClaimId;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;
	private BnftClaim bnftClaim;
	private RbaPrfil rbaPrfil;

    public RbaPrfilBnftClaim() {
    }

	@Id()
	@Column(name="RBA_PRFIL_BNFT_CLAIM_ID", unique=true, nullable=false, precision=15)
	public Long getRbaPrfilBnftClaimId() {
		return this.rbaPrfilBnftClaimId;
	}
	public void setRbaPrfilBnftClaimId(Long rbaPrfilBnftClaimId) {
		this.rbaPrfilBnftClaimId = rbaPrfilBnftClaimId;
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

	//bi-directional many-to-one association to BnftClaim
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BNFT_CLAIM_ID", referencedColumnName="BNFT_CLAIM_ID", nullable=false)
	public BnftClaim getBnftClaim() {
		return this.bnftClaim;
	}
	public void setBnftClaim(BnftClaim bnftClaim) {
		this.bnftClaim = bnftClaim;
	}

	//bi-directional many-to-one association to RbaPrfil
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="PTCPNT_VET_ID", referencedColumnName="PTCPNT_VET_ID", nullable=false),
		@JoinColumn(name="PRFIL_DT", referencedColumnName="PRFIL_DT", nullable=false)
		})
	public RbaPrfil getRbaPrfil() {
		return this.rbaPrfil;
	}
	public void setRbaPrfil(RbaPrfil rbaPrfil) {
		this.rbaPrfil = rbaPrfil;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RbaPrfilBnftClaim)) {
			return false;
		}
		RbaPrfilBnftClaim castOther = (RbaPrfilBnftClaim)other;
		return new EqualsBuilder()
			.append(this.getRbaPrfilBnftClaimId(), castOther.getRbaPrfilBnftClaimId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getRbaPrfilBnftClaimId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("rbaPrfilBnftClaimId", getRbaPrfilBnftClaimId())
			.toString();
	}
}