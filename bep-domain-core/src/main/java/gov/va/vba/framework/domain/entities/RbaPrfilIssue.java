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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the RBA_PRFIL_ISSUE database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="RBA_PRFIL_ISSUE")
public class RbaPrfilIssue  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long rbaPrfilIssueId;
	private Integer bestNewsWeightNbr;
	private String decnTxt;
	private String issueCategyTypeCd;
	private String issueDescpTxt;
	private Long issueSortSeqNbr;
	private String issueSubjctTypeCd;
	private String issueTxt;
	private Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;
	private String subjctTxt;
	private java.util.Set<RatingDecn> ratingDecns;
	private CntntDetail cntntDetail1;
	private CntntDetail cntntDetail2;
	private CntntDetail cntntDetail3;
	private CntntDetail cntntDetail4;
//	private RbaAnly rbaAnly;
	private RbaPrfil rbaPrfil;

    public RbaPrfilIssue() {
    }

	@Id()
	@Column(name="RBA_PRFIL_ISSUE_ID", unique=true, nullable=false, precision=15)
	public Long getRbaPrfilIssueId() {
		return this.rbaPrfilIssueId;
	}
	public void setRbaPrfilIssueId(Long rbaPrfilIssueId) {
		this.rbaPrfilIssueId = rbaPrfilIssueId;
	}

	@Basic()
	@Column(name="BEST_NEWS_WEIGHT_NBR", precision=5)
	public Integer getBestNewsWeightNbr() {
		return this.bestNewsWeightNbr;
	}
	public void setBestNewsWeightNbr(Integer bestNewsWeightNbr) {
		this.bestNewsWeightNbr = bestNewsWeightNbr;
	}

	@Basic()
	@Column(name="DECN_TXT", length=2000)
	public String getDecnTxt() {
		return this.decnTxt;
	}
	public void setDecnTxt(String decnTxt) {
		this.decnTxt = decnTxt;
	}

	@Basic()
	@Column(name="ISSUE_CATEGY_TYPE_CD", length=12)
	public String getIssueCategyTypeCd() {
		return this.issueCategyTypeCd;
	}
	public void setIssueCategyTypeCd(String issueCategyTypeCd) {
		this.issueCategyTypeCd = issueCategyTypeCd;
	}

	@Basic()
	@Column(name="ISSUE_DESCP_TXT", length=2000)
	public String getIssueDescpTxt() {
		return this.issueDescpTxt;
	}
	public void setIssueDescpTxt(String issueDescpTxt) {
		this.issueDescpTxt = issueDescpTxt;
	}

	@Basic()
	@Column(name="ISSUE_SORT_SEQ_NBR", precision=15)
	public Long getIssueSortSeqNbr() {
		return this.issueSortSeqNbr;
	}
	public void setIssueSortSeqNbr(Long issueSortSeqNbr) {
		this.issueSortSeqNbr = issueSortSeqNbr;
	}

	@Basic()
	@Column(name="ISSUE_SUBJCT_TYPE_CD", length=12)
	public String getIssueSubjctTypeCd() {
		return this.issueSubjctTypeCd;
	}
	public void setIssueSubjctTypeCd(String issueSubjctTypeCd) {
		this.issueSubjctTypeCd = issueSubjctTypeCd;
	}

	@Basic()
	@Column(name="ISSUE_TXT", length=2000)
	public String getIssueTxt() {
		return this.issueTxt;
	}
	public void setIssueTxt(String issueTxt) {
		this.issueTxt = issueTxt;
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

	@Basic()
	@Column(name="SUBJCT_TXT", length=256)
	public String getSubjctTxt() {
		return this.subjctTxt;
	}
	public void setSubjctTxt(String subjctTxt) {
		this.subjctTxt = subjctTxt;
	}

	//bi-directional many-to-one association to RatingDecn
	@OneToMany(mappedBy="rbaPrfilIssue", fetch=FetchType.LAZY)
	public java.util.Set<RatingDecn> getRatingDecns() {
		return this.ratingDecns;
	}
	public void setRatingDecns(java.util.Set<RatingDecn> ratingDecns) {
		this.ratingDecns = ratingDecns;
	}

	//bi-directional many-to-one association to CntntDetail
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FACTUL_GENRTD_TEXT_ID", referencedColumnName="CNTNT_DETAIL_ID")
	public CntntDetail getCntntDetail1() {
		return this.cntntDetail1;
	}
	public void setCntntDetail1(CntntDetail cntntDetail1) {
		this.cntntDetail1 = cntntDetail1;
	}

	//bi-directional many-to-one association to CntntDetail
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ANLYS_GENRTD_TEXT_ID", referencedColumnName="CNTNT_DETAIL_ID")
	public CntntDetail getCntntDetail2() {
		return this.cntntDetail2;
	}
	public void setCntntDetail2(CntntDetail cntntDetail2) {
		this.cntntDetail2 = cntntDetail2;
	}

	//bi-directional many-to-one association to CntntDetail
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ANLYS_USER_TEXT_ID", referencedColumnName="CNTNT_DETAIL_ID")
	public CntntDetail getCntntDetail3() {
		return this.cntntDetail3;
	}
	public void setCntntDetail3(CntntDetail cntntDetail3) {
		this.cntntDetail3 = cntntDetail3;
	}

	//bi-directional many-to-one association to CntntDetail
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FACTUL_USER_TEXT_ID", referencedColumnName="CNTNT_DETAIL_ID")
	public CntntDetail getCntntDetail4() {
		return this.cntntDetail4;
	}
	public void setCntntDetail4(CntntDetail cntntDetail4) {
		this.cntntDetail4 = cntntDetail4;
	}

	//bi-directional many-to-one association to RbaAnly
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="RBA_ANLYS_ID", referencedColumnName="RBA_ANLYS_ID")
//	public RbaAnly getRbaAnly() {
//		return this.rbaAnly;
//	}
//	public void setRbaAnly(RbaAnly rbaAnly) {
//		this.rbaAnly = rbaAnly;
//	}

	//bi-directional many-to-one association to RbaPrfil
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="PTCPNT_VET_ID", referencedColumnName="PTCPNT_VET_ID", nullable=false),
		@JoinColumn(name="PRFIL_DT", referencedColumnName="PRFIL_DT", nullable=false)})
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
		if (!(other instanceof RbaPrfilIssue)) {
			return false;
		}
		RbaPrfilIssue castOther = (RbaPrfilIssue)other;
		return new EqualsBuilder()
			.append(this.getRbaPrfilIssueId(), castOther.getRbaPrfilIssueId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getRbaPrfilIssueId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("rbaPrfilIssueId", getRbaPrfilIssueId())
			.toString();
	}
}