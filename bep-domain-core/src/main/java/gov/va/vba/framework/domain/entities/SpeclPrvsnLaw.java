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
 * The persistent class for the SPECL_PRVSN_LAW database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="SPECL_PRVSN_LAW")
public class SpeclPrvsnLaw  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long ratingDecnId;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;
	private String speclPrvsnLawDecnTypeCd;
	private RatingDecn ratingDecn;

    public SpeclPrvsnLaw() {
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
	@Column(name="SPECL_PRVSN_LAW_DECN_TYPE_CD", nullable=false, length=12)
	public String getSpeclPrvsnLawDecnTypeCd() {
		return this.speclPrvsnLawDecnTypeCd;
	}
	public void setSpeclPrvsnLawDecnTypeCd(String speclPrvsnLawDecnTypeCd) {
		this.speclPrvsnLawDecnTypeCd = speclPrvsnLawDecnTypeCd;
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SpeclPrvsnLaw)) {
			return false;
		}
		SpeclPrvsnLaw castOther = (SpeclPrvsnLaw)other;
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