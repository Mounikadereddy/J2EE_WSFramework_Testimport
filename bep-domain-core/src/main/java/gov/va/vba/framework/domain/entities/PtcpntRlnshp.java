package gov.va.vba.framework.domain.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the PTCPNT_RLNSHP database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="PTCPNT_RLNSHP")
public class PtcpntRlnshp implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private PtcpntRlnshpPK compId;
//	private String alegdRlnshpInd;
	private java.sql.Timestamp beginDt;
//	private Date efctvDt;
	private java.sql.Timestamp endDt;
	private java.sql.Timestamp eventDt;
	private String fidAttntnTxt;
	private String hlthcrPrvdrRlseInd;
	private String proofDepncyInd;
	private String prptnlPhraseTypeNm;
	private String rateTypeNm;
	private java.sql.Timestamp reviewDt;
	private String statusTypeCd;
	private String tempCustdnInd;
	private java.util.Set<PtcpntPoaRlnshp> ptcpntPoaRlnshps;
	private Ptcpnt ptcpnt1;
	private Ptcpnt ptcpnt2;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public PtcpntRlnshp() {
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
	public PtcpntRlnshpPK getCompId() {
		return this.compId;
	}
	public void setCompId(PtcpntRlnshpPK compId) {
		this.compId = compId;
	}

//	@Basic()
//	@Column(name="ALEGD_RLNSHP_IND", nullable=false, length=1)
//	public String getAlegdRlnshpInd() {
//		return this.alegdRlnshpInd;
//	}
//	public void setAlegdRlnshpInd(String alegdRlnshpInd) {
//		this.alegdRlnshpInd = alegdRlnshpInd;
//	}

	@Basic()
	@Column(name="BEGIN_DT", length=7)
	public java.sql.Timestamp getBeginDt() {
		return this.beginDt;
	}
	public void setBeginDt( java.sql.Timestamp beginDt) {
		this.beginDt = beginDt;
	}

//	@Basic()
//	@Column(name="EFCTV_DT", nullable=false, length=7)
//	public Date getEfctvDt() {
//		return this.efctvDt;
//	}
//	public void setEfctvDt(Date efctvDt) {
//		this.efctvDt = efctvDt;
//	}

	@Basic()
	@Column(name="END_DT", length=7)
	public java.sql.Timestamp getEndDt() {
		return this.endDt;
	}
	public void setEndDt(java.sql.Timestamp endDt) {
		this.endDt = endDt;
	}

	@Basic()
	@Column(name="EVENT_DT", length=7)
	public java.sql.Timestamp getEventDt() {
		return this.eventDt;
	}
	public void setEventDt(java.sql.Timestamp eventDt) {
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
	@Column(name="RATE_TYPE_NM", length=50)
	public String getRateTypeNm() {
		return this.rateTypeNm;
	}
	public void setRateTypeNm(String rateTypeNm) {
		this.rateTypeNm = rateTypeNm;
	}

	@Basic()
	@Column(name="REVIEW_DT", length=7)
	public java.sql.Timestamp getReviewDt() {
		return this.reviewDt;
	}
	public void setReviewDt(java.sql.Timestamp reviewDt) {
		this.reviewDt = reviewDt;
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

	//bi-directional many-to-one association to PtcpntPoaRlnshp
	@OneToMany(mappedBy="ptcpntRlnshp", fetch=FetchType.EAGER)
	public java.util.Set<PtcpntPoaRlnshp> getPtcpntPoaRlnshps() {
		return this.ptcpntPoaRlnshps;
	}
	public void setPtcpntPoaRlnshps(java.util.Set<PtcpntPoaRlnshp> ptcpntPoaRlnshps) {
		this.ptcpntPoaRlnshps = ptcpntPoaRlnshps;
	}

	//bi-directional many-to-one association to Ptcpnt
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PTCPNT_ID_B", referencedColumnName="PTCPNT_ID", nullable=false, insertable=false, updatable=false)
	public Ptcpnt getPtcpnt1() {
		return this.ptcpnt1;
	}
	public void setPtcpnt1(Ptcpnt ptcpnt1) {
		this.ptcpnt1 = ptcpnt1;
	}

	//bi-directional many-to-one association to Ptcpnt
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PTCPNT_ID_A", referencedColumnName="PTCPNT_ID", nullable=false, insertable=false, updatable=false)
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
		if (!(other instanceof PtcpntRlnshp)) {
			return false;
		}
		PtcpntRlnshp castOther = (PtcpntRlnshp)other;
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