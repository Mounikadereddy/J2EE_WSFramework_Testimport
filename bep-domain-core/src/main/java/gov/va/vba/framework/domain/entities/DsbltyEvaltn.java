package gov.va.vba.framework.domain.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the DSBLTY_EVALTN database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="DSBLTY_EVALTN")
public class DsbltyEvaltn implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long ratingDecnId;
	private String blatrlTypeCd;
	private String dgnstcTxt;
	private String dgnstcTypeCd;
	private Integer dsbltyWthldgPct;
	private String hypntdDgnstcTypeCd;
	private String majorInd;
	private String prcntsetCd;
	private Integer prcntNbr;
	private String prevEvaltnInd;
	private String prgrphTypeCd;
	private String shedTypeCd;
	private RatingDecn ratingDecn;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public DsbltyEvaltn() {
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
	@Column(name="RATING_DECN_ID", unique=true, nullable=false, precision=15)
	public Long getRatingDecnId() {
		return this.ratingDecnId;
	}
	public void setRatingDecnId(Long ratingDecnId) {
		this.ratingDecnId = ratingDecnId;
	}

	@Basic()
	@Column(name="BLATRL_TYPE_CD", length=12)
	public String getBlatrlTypeCd() {
		return this.blatrlTypeCd;
	}
	public void setBlatrlTypeCd(String blatrlTypeCd) {
		this.blatrlTypeCd = blatrlTypeCd;
	}

	@Basic()
	@Column(name="DGNSTC_TXT", length=254)
	public String getDgnstcTxt() {
		return this.dgnstcTxt;
	}
	public void setDgnstcTxt(String dgnstcTxt) {
		this.dgnstcTxt = dgnstcTxt;
	}

	@Basic()
	@Column(name="DGNSTC_TYPE_CD", length=12)
	public String getDgnstcTypeCd() {
		return this.dgnstcTypeCd;
	}
	public void setDgnstcTypeCd(String dgnstcTypeCd) {
		this.dgnstcTypeCd = dgnstcTypeCd;
	}

	@Basic()
	@Column(name="DSBLTY_WTHLDG_PCT", precision=3)
	public Integer getDsbltyWthldgPct() {
		return this.dsbltyWthldgPct;
	}
	public void setDsbltyWthldgPct(Integer dsbltyWthldgPct) {
		this.dsbltyWthldgPct = dsbltyWthldgPct;
	}

	@Basic()
	@Column(name="HYPNTD_DGNSTC_TYPE_CD", length=12)
	public String getHypntdDgnstcTypeCd() {
		return this.hypntdDgnstcTypeCd;
	}
	public void setHypntdDgnstcTypeCd(String hypntdDgnstcTypeCd) {
		this.hypntdDgnstcTypeCd = hypntdDgnstcTypeCd;
	}

	@Basic()
	@Column(name="MAJOR_IND", length=1)
	public String getMajorInd() {
		return this.majorInd;
	}
	public void setMajorInd(String majorInd) {
		this.majorInd = majorInd;
	}

	@Basic()
	@Column(name="PRCNTSET_CD", length=4)
	public String getPrcntsetCd() {
		return this.prcntsetCd;
	}
	public void setPrcntsetCd(String prcntsetCd) {
		this.prcntsetCd = prcntsetCd;
	}

	@Basic()
	@Column(name="PRCNT_NBR", precision=3)
	public Integer getPrcntNbr() {
		return this.prcntNbr;
	}
	public void setPrcntNbr(Integer prcntNbr) {
		this.prcntNbr = prcntNbr;
	}

	@Basic()
	@Column(name="PREV_EVALTN_IND", length=1)
	public String getPrevEvaltnInd() {
		return this.prevEvaltnInd;
	}
	public void setPrevEvaltnInd(String prevEvaltnInd) {
		this.prevEvaltnInd = prevEvaltnInd;
	}

	@Basic()
	@Column(name="PRGRPH_TYPE_CD", length=12)
	public String getPrgrphTypeCd() {
		return this.prgrphTypeCd;
	}
	public void setPrgrphTypeCd(String prgrphTypeCd) {
		this.prgrphTypeCd = prgrphTypeCd;
	}

	@Basic()
	@Column(name="SHED_TYPE_CD", length=12)
	public String getShedTypeCd() {
		return this.shedTypeCd;
	}
	public void setShedTypeCd(String shedTypeCd) {
		this.shedTypeCd = shedTypeCd;
	}

	//bi-directional one-to-one association to RatingDecn
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="RATING_DECN_ID", referencedColumnName="RATING_DECN_ID", nullable=false, insertable=false, updatable=false)
	public RatingDecn getRatingDecn() {
		return this.ratingDecn;
	}
	public void setRatingDecn(RatingDecn ratingDecn) {
		this.ratingDecn = ratingDecn;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DsbltyEvaltn)) {
			return false;
		}
		DsbltyEvaltn castOther = (DsbltyEvaltn)other;
		return new EqualsBuilder()
			.append(this.getRatingDecnId(), castOther.getRatingDecnId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getRatingDecnId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("ratingDecnId", getRatingDecnId())
			.toString();
	}
}