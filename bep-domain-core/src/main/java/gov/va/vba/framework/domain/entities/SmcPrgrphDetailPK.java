package gov.va.vba.framework.domain.entities;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The primary key class for the SMC_PRGRPH_DETAIL database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class SmcPrgrphDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long ratingDecnId;
	private String smcPrgrphCntrlNm;
	private Long smcPrgrphDataId;
	private String smcPrgrphKeyCd;

    public SmcPrgrphDetailPK() {
    }

	@Column(name="RATING_DECN_ID", nullable=false, precision=15)
	public Long getRatingDecnId() {
		return this.ratingDecnId;
	}
	public void setRatingDecnId(Long ratingDecnId) {
		this.ratingDecnId = ratingDecnId;
	}

	@Column(name="SMC_PRGRPH_CNTRL_NM", nullable=false, length=30)
	public String getSmcPrgrphCntrlNm() {
		return this.smcPrgrphCntrlNm;
	}
	public void setSmcPrgrphCntrlNm(String smcPrgrphCntrlNm) {
		this.smcPrgrphCntrlNm = smcPrgrphCntrlNm;
	}

	@Column(name="SMC_PRGRPH_DATA_ID", nullable=false, precision=15)
	public Long getSmcPrgrphDataId() {
		return this.smcPrgrphDataId;
	}
	public void setSmcPrgrphDataId(Long smcPrgrphDataId) {
		this.smcPrgrphDataId = smcPrgrphDataId;
	}

	@Column(name="SMC_PRGRPH_KEY_CD", nullable=false, length=10)
	public String getSmcPrgrphKeyCd() {
		return this.smcPrgrphKeyCd;
	}
	public void setSmcPrgrphKeyCd(String smcPrgrphKeyCd) {
		this.smcPrgrphKeyCd = smcPrgrphKeyCd;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SmcPrgrphDetailPK)) {
			return false;
		}
		SmcPrgrphDetailPK castOther = (SmcPrgrphDetailPK)other;
		return new EqualsBuilder()
			.append(this.getRatingDecnId(), castOther.getRatingDecnId())
			.append(this.getSmcPrgrphCntrlNm(), castOther.getSmcPrgrphCntrlNm())
			.append(this.getSmcPrgrphDataId(), castOther.getSmcPrgrphDataId())
			.append(this.getSmcPrgrphKeyCd(), castOther.getSmcPrgrphKeyCd())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getRatingDecnId())
			.append(getSmcPrgrphCntrlNm())
			.append(getSmcPrgrphDataId())
			.append(getSmcPrgrphKeyCd())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("ratingDecnId", getRatingDecnId())
			.append("smcPrgrphCntrlNm", getSmcPrgrphCntrlNm())
			.append("smcPrgrphDataId", getSmcPrgrphDataId())
			.append("smcPrgrphKeyCd", getSmcPrgrphKeyCd())
			.toString();
	}
}