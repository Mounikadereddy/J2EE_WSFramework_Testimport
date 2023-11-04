package gov.va.vba.framework.domain.entities;
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the BNFT_CHART database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="BNFT_CHART")
public class BnftChart implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private BnftChartPK compId;
	private String fundAcntTypeCd;
	private String prtctnInd;
	private java.util.Set<BnftClaim> bnftClaims;
//	private java.util.Set<CntrlShareListValue> cntrlShareListValues;
//	private java.util.Set<OutgngDcmnt> outgngDcmnts;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;
	
    public BnftChart() {
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

	@EmbeddedId
	public BnftChartPK getCompId() {
		return this.compId;
	}
	public void setCompId(BnftChartPK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="FUND_ACNT_TYPE_CD", nullable=false, length=12)
	public String getFundAcntTypeCd() {
		return this.fundAcntTypeCd;
	}
	public void setFundAcntTypeCd(String fundAcntTypeCd) {
		this.fundAcntTypeCd = fundAcntTypeCd;
	}

	@Basic()
	@Column(name="PRTCTN_IND", nullable=false, length=1)
	public String getPrtctnInd() {
		return this.prtctnInd;
	}
	public void setPrtctnInd(String prtctnInd) {
		this.prtctnInd = prtctnInd;
	}

	//bi-directional many-to-one association to BnftClaim
	@OneToMany(mappedBy="bnftChart", fetch=FetchType.LAZY)
	public java.util.Set<BnftClaim> getBnftClaims() {
		return this.bnftClaims;
	}
	public void setBnftClaims(java.util.Set<BnftClaim> bnftClaims) {
		this.bnftClaims = bnftClaims;
	}

//	//bi-directional many-to-one association to CntrlShareListValue
//	@OneToMany(mappedBy="bnftChart", fetch=FetchType.LAZY)
//	public java.util.Set<CntrlShareListValue> getCntrlShareListValues() {
//		return this.cntrlShareListValues;
//	}
//	public void setCntrlShareListValues(java.util.Set<CntrlShareListValue> cntrlShareListValues) {
//		this.cntrlShareListValues = cntrlShareListValues;
//	}
//
//	//bi-directional many-to-one association to OutgngDcmnt
//	@OneToMany(mappedBy="bnftChart", fetch=FetchType.LAZY)
//	public java.util.Set<OutgngDcmnt> getOutgngDcmnts() {
//		return this.outgngDcmnts;
//	}
//	public void setOutgngDcmnts(java.util.Set<OutgngDcmnt> outgngDcmnts) {
//		this.outgngDcmnts = outgngDcmnts;
//	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BnftChart)) {
			return false;
		}
		BnftChart castOther = (BnftChart)other;
		return new EqualsBuilder()
			.append(this.getCompId(), castOther.getCompId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCompId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("compId", getCompId())
			.toString();
	}

}