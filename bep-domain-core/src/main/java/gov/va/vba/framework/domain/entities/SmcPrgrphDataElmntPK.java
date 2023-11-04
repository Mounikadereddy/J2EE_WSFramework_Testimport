package gov.va.vba.framework.domain.entities;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The primary key class for the SMC_PRGRPH_DATA_ELMNTS database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class SmcPrgrphDataElmntPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String smcPrgrphCntrlNm;
	private Long smcPrgrphDataId;
	private java.sql.Timestamp smcPrgrphEfctvDt;
	private String smcPrgrphKeyCd;

    public SmcPrgrphDataElmntPK() {
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

	@Column(name="SMC_PRGRPH_EFCTV_DT", nullable=false, length=7)
	public java.sql.Timestamp getSmcPrgrphEfctvDt() {
		return this.smcPrgrphEfctvDt;
	}
	public void setSmcPrgrphEfctvDt(java.sql.Timestamp smcPrgrphEfctvDt) {
		this.smcPrgrphEfctvDt = smcPrgrphEfctvDt;
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
		if (!(other instanceof SmcPrgrphDataElmntPK)) {
			return false;
		}
		SmcPrgrphDataElmntPK castOther = (SmcPrgrphDataElmntPK)other;
		return new EqualsBuilder()
			.append(this.getSmcPrgrphCntrlNm(), castOther.getSmcPrgrphCntrlNm())
			.append(this.getSmcPrgrphDataId(), castOther.getSmcPrgrphDataId())
			.append(this.getSmcPrgrphEfctvDt(), castOther.getSmcPrgrphEfctvDt())
			.append(this.getSmcPrgrphKeyCd(), castOther.getSmcPrgrphKeyCd())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getSmcPrgrphCntrlNm())
			.append(getSmcPrgrphDataId())
			.append(getSmcPrgrphEfctvDt())
			.append(getSmcPrgrphKeyCd())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("smcPrgrphCntrlNm", getSmcPrgrphCntrlNm())
			.append("smcPrgrphDataId", getSmcPrgrphDataId())
			.append("smcPrgrphEfctvDt", getSmcPrgrphEfctvDt())
			.append("smcPrgrphKeyCd", getSmcPrgrphKeyCd())
			.toString();
	}
}