package gov.va.vba.framework.domain.entities;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The persistent class for the SMC_PRGRPH database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="SMC_PRGRPH")
public class SmcPrgrph  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long ratingDecnId;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;
	private java.sql.Timestamp smcPrgrphEfctvDt;
	private String smcPrgrphKeyCd;
	private long smcPrgrphSortSeqNbr;
	private String smcPrgrphTxt;
	private String splmtlDecnTypeCd;
	private RatingDecn ratingDecn;
	private java.util.Set<SmcPrgrphDetail> smcPrgrphDetails;

    public SmcPrgrph() {
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

	@Basic()
	@Column(name="SMC_PRGRPH_EFCTV_DT", nullable=false, length=7)
	public java.sql.Timestamp getSmcPrgrphEfctvDt() {
		return this.smcPrgrphEfctvDt;
	}
	public void setSmcPrgrphEfctvDt(java.sql.Timestamp smcPrgrphEfctvDt) {
		this.smcPrgrphEfctvDt = smcPrgrphEfctvDt;
	}

	@Basic()
	@Column(name="SMC_PRGRPH_KEY_CD", nullable=false, length=10)
	public String getSmcPrgrphKeyCd() {
		return this.smcPrgrphKeyCd;
	}
	public void setSmcPrgrphKeyCd(String smcPrgrphKeyCd) {
		this.smcPrgrphKeyCd = smcPrgrphKeyCd;
	}

	@Basic()
	@Column(name="SMC_PRGRPH_SORT_SEQ_NBR", nullable=false, precision=15)
	public long getSmcPrgrphSortSeqNbr() {
		return this.smcPrgrphSortSeqNbr;
	}
	public void setSmcPrgrphSortSeqNbr(long smcPrgrphSortSeqNbr) {
		this.smcPrgrphSortSeqNbr = smcPrgrphSortSeqNbr;
	}

	@Basic()
	@Column(name="SMC_PRGRPH_TXT", length=2000)
	public String getSmcPrgrphTxt() {
		return this.smcPrgrphTxt;
	}
	public void setSmcPrgrphTxt(String smcPrgrphTxt) {
		this.smcPrgrphTxt = smcPrgrphTxt;
	}

	@Basic()
	@Column(name="SPLMTL_DECN_TYPE_CD", length=12)
	public String getSplmtlDecnTypeCd() {
		return this.splmtlDecnTypeCd;
	}
	public void setSplmtlDecnTypeCd(String splmtlDecnTypeCd) {
		this.splmtlDecnTypeCd = splmtlDecnTypeCd;
	}

	//bi-directional one-to-one association to RatingDecn
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="RATING_DECN_ID", referencedColumnName="RATING_DECN_ID", nullable=false, insertable=false, updatable=false)
	public RatingDecn getRatingDecn() {
		return this.ratingDecn;
	}
	public void setRatingDecn(RatingDecn ratingDecn) {
		this.ratingDecn = ratingDecn;
	}

	//bi-directional many-to-one association to SmcPrgrphDetail
	@OneToMany(mappedBy="smcPrgrph", fetch=FetchType.LAZY)
	public java.util.Set<SmcPrgrphDetail> getSmcPrgrphDetails() {
		return this.smcPrgrphDetails;
	}
	public void setSmcPrgrphDetails(java.util.Set<SmcPrgrphDetail> smcPrgrphDetails) {
		this.smcPrgrphDetails = smcPrgrphDetails;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SmcPrgrph)) {
			return false;
		}
		SmcPrgrph castOther = (SmcPrgrph)other;
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