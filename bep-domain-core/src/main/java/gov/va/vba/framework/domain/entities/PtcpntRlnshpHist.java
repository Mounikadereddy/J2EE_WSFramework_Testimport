package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import java.util.Date;

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
 * The persistent class for the PTCPNT_RLNSHP_HIST database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="PTCPNT_RLNSHP_HIST")
public class PtcpntRlnshpHist  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long ptcpntRlnshpHistId;
	private Date beginDt;
	private Date endDt;
	private Date eventDt;
	private String fidAttntnTxt;
	private String hlthcrPrvdrRlseInd;
	private String proofDepncyInd;
	private String prptnlPhraseTypeNm;
	private String ptcpntRlnshpTypeNm;
	private String rateTypeNm;
	private String statusTypeCd;
	private String tempCustdnInd;
	private Ptcpnt ptcpnt1;
	private Ptcpnt ptcpnt2;
	private Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public PtcpntRlnshpHist() {
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
	@Column(name="PTCPNT_RLNSHP_HIST_ID", unique=true, nullable=false, precision=15)
	public Long getPtcpntRlnshpHistId() {
		return this.ptcpntRlnshpHistId;
	}
	public void setPtcpntRlnshpHistId(Long ptcpntRlnshpHistId) {
		this.ptcpntRlnshpHistId = ptcpntRlnshpHistId;
	}

	@Basic()
	@Column(name="BEGIN_DT", length=7)
	public Date getBeginDt() {
		return this.beginDt;
	}
	public void setBeginDt(Date beginDt) {
		this.beginDt = beginDt;
	}

	@Basic()
	@Column(name="END_DT", length=7)
	public Date getEndDt() {
		return this.endDt;
	}
	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}

	@Basic()
	@Column(name="EVENT_DT", length=7)
	public Date getEventDt() {
		return this.eventDt;
	}
	public void setEventDt(Date eventDt) {
		this.eventDt = eventDt;
	}

	@Basic()
	@Column(name="FID_ATTNTN_TXT", length=254)
	public String getFidAttntnTxt() {
		return this.fidAttntnTxt;
	}
	public void setFidAttntnTxt(String fidAttntnTxt) {
		this.fidAttntnTxt = fidAttntnTxt;
	}

	@Basic()
	@Column(name="HLTHCR_PRVDR_RLSE_IND", length=1)
	public String getHlthcrPrvdrRlseInd() {
		return this.hlthcrPrvdrRlseInd;
	}
	public void setHlthcrPrvdrRlseInd(String hlthcrPrvdrRlseInd) {
		this.hlthcrPrvdrRlseInd = hlthcrPrvdrRlseInd;
	}

	@Basic()
	@Column(name="PROOF_DEPNCY_IND", length=1)
	public String getProofDepncyInd() {
		return this.proofDepncyInd;
	}
	public void setProofDepncyInd(String proofDepncyInd) {
		this.proofDepncyInd = proofDepncyInd;
	}

	@Basic()
	@Column(name="PRPTNL_PHRASE_TYPE_NM", length=50)
	public String getPrptnlPhraseTypeNm() {
		return this.prptnlPhraseTypeNm;
	}
	public void setPrptnlPhraseTypeNm(String prptnlPhraseTypeNm) {
		this.prptnlPhraseTypeNm = prptnlPhraseTypeNm;
	}

	@Basic()
	@Column(name="PTCPNT_RLNSHP_TYPE_NM", nullable=false, length=50)
	public String getPtcpntRlnshpTypeNm() {
		return this.ptcpntRlnshpTypeNm;
	}
	public void setPtcpntRlnshpTypeNm(String ptcpntRlnshpTypeNm) {
		this.ptcpntRlnshpTypeNm = ptcpntRlnshpTypeNm;
	}

	@Basic()
	@Column(name="RATE_TYPE_NM", length=50)
	public String getRateTypeNm() {
		return this.rateTypeNm;
	}
	public void setRateTypeNm(String rateTypeNm) {
		this.rateTypeNm = rateTypeNm;
	}

	@Basic()
	@Column(name="STATUS_TYPE_CD", length=12)
	public String getStatusTypeCd() {
		return this.statusTypeCd;
	}
	public void setStatusTypeCd(String statusTypeCd) {
		this.statusTypeCd = statusTypeCd;
	}

	@Basic()
	@Column(name="TEMP_CUSTDN_IND", length=1)
	public String getTempCustdnInd() {
		return this.tempCustdnInd;
	}
	public void setTempCustdnInd(String tempCustdnInd) {
		this.tempCustdnInd = tempCustdnInd;
	}

	//bi-directional many-to-one association to Ptcpnt
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PTCPNT_ID_B", referencedColumnName="PTCPNT_ID", nullable=false)
	public Ptcpnt getPtcpnt1() {
		return this.ptcpnt1;
	}
	public void setPtcpnt1(Ptcpnt ptcpnt1) {
		this.ptcpnt1 = ptcpnt1;
	}

	//bi-directional many-to-one association to Ptcpnt
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PTCPNT_ID_A", referencedColumnName="PTCPNT_ID", nullable=false)
	public Ptcpnt getPtcpnt2() {
		return this.ptcpnt2;
	}
	public void setPtcpnt2(Ptcpnt ptcpnt2) {
		this.ptcpnt2 = ptcpnt2;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PtcpntRlnshpHist)) {
			return false;
		}
		PtcpntRlnshpHist castOther = (PtcpntRlnshpHist)other;
		return new EqualsBuilder()
			.append(this.getPtcpntRlnshpHistId(), castOther.getPtcpntRlnshpHistId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getPtcpntRlnshpHistId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("ptcpntRlnshpHistId", getPtcpntRlnshpHistId())
			.toString();
	}
}