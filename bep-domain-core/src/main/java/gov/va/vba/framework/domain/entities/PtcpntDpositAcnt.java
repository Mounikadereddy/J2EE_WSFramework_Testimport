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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the PTCPNT_DPOSIT_ACNT database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="PTCPNT_DPOSIT_ACNT")
public class PtcpntDpositAcnt implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long ptcpntDpositAcntId;
	private java.sql.Timestamp beginDt;
	private String dpositAcntNbr;
	private String dpositAcntTypeNm;
	private java.sql.Timestamp endDt;
	private String ptcpntAddrsTypeNm;
	private int trsurySeqNbr;
//	private java.util.Set<AwardBene> awardBenes;
	private java.util.Set<BnftClaim> bnftClaims;
//	private java.util.Set<PriorsRecip> priorsRecips;
	private FinclInstn finclInstn;
	private Ptcpnt ptcpnt;
//	private java.util.Set<Pymt> pymts;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public PtcpntDpositAcnt() {
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
	@Column(name="PTCPNT_DPOSIT_ACNT_ID", unique=true, nullable=false, precision=15)
	public Long getPtcpntDpositAcntId() {
		return this.ptcpntDpositAcntId;
	}
	public void setPtcpntDpositAcntId(Long ptcpntDpositAcntId) {
		this.ptcpntDpositAcntId = ptcpntDpositAcntId;
	}

	@Basic()
	@Column(name="BEGIN_DT", nullable=false, length=7)
	public java.sql.Timestamp getBeginDt() {
		return this.beginDt;
	}
	public void setBeginDt(java.sql.Timestamp beginDt) {
		this.beginDt = beginDt;
	}

	@Basic()
	@Column(name="DPOSIT_ACNT_NBR", nullable=false, length=17)
	public String getDpositAcntNbr() {
		return this.dpositAcntNbr;
	}
	public void setDpositAcntNbr(String dpositAcntNbr) {
		this.dpositAcntNbr = dpositAcntNbr;
	}

	@Basic()
	@Column(name="DPOSIT_ACNT_TYPE_NM", nullable=false, length=50)
	public String getDpositAcntTypeNm() {
		return this.dpositAcntTypeNm;
	}
	public void setDpositAcntTypeNm(String dpositAcntTypeNm) {
		this.dpositAcntTypeNm = dpositAcntTypeNm;
	}

	@Basic()
	@Column(name="END_DT", length=7)
	public java.sql.Timestamp getEndDt() {
		return this.endDt;
	}
	public void setEndDt(java.sql.Timestamp endDt) {
		this.endDt = endDt;
	}

	@Basic()
	@Column(name="PTCPNT_ADDRS_TYPE_NM", nullable=false, length=50)
	public String getPtcpntAddrsTypeNm() {
		return this.ptcpntAddrsTypeNm;
	}
	public void setPtcpntAddrsTypeNm(String ptcpntAddrsTypeNm) {
		this.ptcpntAddrsTypeNm = ptcpntAddrsTypeNm;
	}

	@Basic()
	@Column(name="TRSURY_SEQ_NBR", nullable=false, precision=4)
	public int getTrsurySeqNbr() {
		return this.trsurySeqNbr;
	}
	public void setTrsurySeqNbr(int trsurySeqNbr) {
		this.trsurySeqNbr = trsurySeqNbr;
	}

//	//bi-directional many-to-one association to AwardBene
//	@OneToMany(mappedBy="ptcpntDpositAcnt", fetch=FetchType.LAZY)
//	public java.util.Set<AwardBene> getAwardBenes() {
//		return this.awardBenes;
//	}
//	public void setAwardBenes(java.util.Set<AwardBene> awardBenes) {
//		this.awardBenes = awardBenes;
//	}

	//bi-directional many-to-one association to BnftClaim
	@OneToMany(mappedBy="ptcpntDpositAcnt", fetch=FetchType.LAZY)
	public java.util.Set<BnftClaim> getBnftClaims() {
		return this.bnftClaims;
	}
	public void setBnftClaims(java.util.Set<BnftClaim> bnftClaims) {
		this.bnftClaims = bnftClaims;
	}

//	//bi-directional many-to-one association to PriorsRecip
//	@OneToMany(mappedBy="ptcpntDpositAcnt", fetch=FetchType.LAZY)
//	public java.util.Set<PriorsRecip> getPriorsRecips() {
//		return this.priorsRecips;
//	}
//	public void setPriorsRecips(java.util.Set<PriorsRecip> priorsRecips) {
//		this.priorsRecips = priorsRecips;
//	}

	//bi-directional many-to-one association to FinclInstn
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PTCPNT_FINCL_INSTN_ID", referencedColumnName="PTCPNT_ID", nullable=false)
	public FinclInstn getFinclInstn() {
		return this.finclInstn;
	}
	public void setFinclInstn(FinclInstn finclInstn) {
		this.finclInstn = finclInstn;
	}

	//bi-directional many-to-one association to Ptcpnt
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PTCPNT_ACNT_OWNER_ID", referencedColumnName="PTCPNT_ID", nullable=false)
	public Ptcpnt getPtcpnt() {
		return this.ptcpnt;
	}
	public void setPtcpnt(Ptcpnt ptcpnt) {
		this.ptcpnt = ptcpnt;
	}

//	//bi-directional many-to-one association to Pymt
//	@OneToMany(mappedBy="ptcpntDpositAcnt", fetch=FetchType.LAZY)
//	public java.util.Set<Pymt> getPymts() {
//		return this.pymts;
//	}
//	public void setPymts(java.util.Set<Pymt> pymts) {
//		this.pymts = pymts;
//	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PtcpntDpositAcnt)) {
			return false;
		}
		PtcpntDpositAcnt castOther = (PtcpntDpositAcnt)other;
		return new EqualsBuilder()
			.append(this.getPtcpntDpositAcntId(), castOther.getPtcpntDpositAcntId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getPtcpntDpositAcntId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("ptcpntDpositAcntId", getPtcpntDpositAcntId())
			.toString();
	}
}