package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import java.util.Date;

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
 * The persistent class for the PTCPNT_POA_RLNSHP database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="PTCPNT_POA_RLNSHP")
public class PtcpntPoaRlnshp implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long ptcpntPoaRlnshpId;
	private Long bnftClaimId;
	private Long cntntnId;
	private Date dactvtDt;
	private String directPayInd;
	private String exclsvCntctInd;
	private String lmtdRpsntnInd;
	private String prptnlPhraseTypeCd;
	private PtcpntRlnshp ptcpntRlnshp;
	private Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public PtcpntPoaRlnshp() {
    }
    
	@Basic()
	@Column(name="JRN_DT", nullable=false, length=7)
	public Date getJrnDt() {
		return this.jrnDt;
	}
	public void setJrnDt(Date jrnDt) {
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
	@Column(name="PTCPNT_POA_RLNSHP_ID", unique=true, nullable=false, precision=15)
	public Long getPtcpntPoaRlnshpId() {
		return this.ptcpntPoaRlnshpId;
	}
	public void setPtcpntPoaRlnshpId(Long ptcpntPoaRlnshpId) {
		this.ptcpntPoaRlnshpId = ptcpntPoaRlnshpId;
	}

	@Basic()
	@Column(name="BNFT_CLAIM_ID", precision=15)
	public Long getBnftClaimId() {
		return this.bnftClaimId;
	}
	public void setBnftClaimId(Long bnftClaimId) {
		this.bnftClaimId = bnftClaimId;
	}

	@Basic()
	@Column(name="CNTNTN_ID", precision=15)
	public Long getCntntnId() {
		return this.cntntnId;
	}
	public void setCntntnId(Long cntntnId) {
		this.cntntnId = cntntnId;
	}

	@Basic()
	@Column(name="DACTVT_DT", length=7)
	public Date getDactvtDt() {
		return this.dactvtDt;
	}
	public void setDactvtDt(Date dactvtDt) {
		this.dactvtDt = dactvtDt;
	}

	@Basic()
	@Column(name="DIRECT_PAY_IND", length=1)
	public String getDirectPayInd() {
		return this.directPayInd;
	}
	public void setDirectPayInd(String directPayInd) {
		this.directPayInd = directPayInd;
	}

	@Basic()
	@Column(name="EXCLSV_CNTCT_IND", length=1)
	public String getExclsvCntctInd() {
		return this.exclsvCntctInd;
	}
	public void setExclsvCntctInd(String exclsvCntctInd) {
		this.exclsvCntctInd = exclsvCntctInd;
	}

	@Basic()
	@Column(name="LMTD_RPSNTN_IND", length=1)
	public String getLmtdRpsntnInd() {
		return this.lmtdRpsntnInd;
	}
	public void setLmtdRpsntnInd(String lmtdRpsntnInd) {
		this.lmtdRpsntnInd = lmtdRpsntnInd;
	}

	@Basic()
	@Column(name="PRPTNL_PHRASE_TYPE_CD", length=12)
	public String getPrptnlPhraseTypeCd() {
		return this.prptnlPhraseTypeCd;
	}
	public void setPrptnlPhraseTypeCd(String prptnlPhraseTypeCd) {
		this.prptnlPhraseTypeCd = prptnlPhraseTypeCd;
	}

	//bi-directional many-to-one association to PtcpntRlnshp
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name="PTCPNT_ID_A", referencedColumnName="PTCPNT_ID_A", nullable=false),
		@JoinColumn(name="PTCPNT_ID_B", referencedColumnName="PTCPNT_ID_B", nullable=false),
		@JoinColumn(name="PTCPNT_RLNSHP_TYPE_NM", referencedColumnName="PTCPNT_RLNSHP_TYPE_NM", nullable=false)})
	public PtcpntRlnshp getPtcpntRlnshp() {
		return this.ptcpntRlnshp;
	}
	public void setPtcpntRlnshp(PtcpntRlnshp ptcpntRlnshp) {
		this.ptcpntRlnshp = ptcpntRlnshp;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PtcpntPoaRlnshp)) {
			return false;
		}
		PtcpntPoaRlnshp castOther = (PtcpntPoaRlnshp)other;
		return new EqualsBuilder()
			.append(this.getPtcpntPoaRlnshpId(), castOther.getPtcpntPoaRlnshpId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getPtcpntPoaRlnshpId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("ptcpntPoaRlnshpId", getPtcpntPoaRlnshpId())
			.toString();
	}
}