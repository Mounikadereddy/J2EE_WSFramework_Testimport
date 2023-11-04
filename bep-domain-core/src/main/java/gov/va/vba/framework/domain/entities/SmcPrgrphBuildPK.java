package gov.va.vba.framework.domain.entities;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The primary key class for the SMC_PRGRPH_BUILD database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class SmcPrgrphBuildPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private java.sql.Timestamp smcPrgrphEfctvDt;
	private String smcPrgrphKeyCd;

    public SmcPrgrphBuildPK() {
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
		if (!(other instanceof SmcPrgrphBuildPK)) {
			return false;
		}
		SmcPrgrphBuildPK castOther = (SmcPrgrphBuildPK)other;
		return new EqualsBuilder()
			.append(this.getSmcPrgrphEfctvDt(), castOther.getSmcPrgrphEfctvDt())
			.append(this.getSmcPrgrphKeyCd(), castOther.getSmcPrgrphKeyCd())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getSmcPrgrphEfctvDt())
			.append(getSmcPrgrphKeyCd())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("smcPrgrphEfctvDt", getSmcPrgrphEfctvDt())
			.append("smcPrgrphKeyCd", getSmcPrgrphKeyCd())
			.toString();
	}
}