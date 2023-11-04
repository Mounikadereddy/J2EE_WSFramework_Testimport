package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The primary key class for the MLTY_SVC_PERIOD_EXCPTN database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class MltySvcPeriodExcptnPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String excptnRuleNm;
	private String mltySvcPeriodTypeCd;
	private java.sql.Date periodBeginDt;

    public MltySvcPeriodExcptnPK() {
    }

	@Column(name="EXCPTN_RULE_NM", nullable=false, length=15)
	public String getExcptnRuleNm() {
		return this.excptnRuleNm;
	}
	public void setExcptnRuleNm(String excptnRuleNm) {
		this.excptnRuleNm = excptnRuleNm;
	}

	@Column(name="MLTY_SVC_PERIOD_TYPE_CD", nullable=false, length=12)
	public String getMltySvcPeriodTypeCd() {
		return this.mltySvcPeriodTypeCd;
	}
	public void setMltySvcPeriodTypeCd(String mltySvcPeriodTypeCd) {
		this.mltySvcPeriodTypeCd = mltySvcPeriodTypeCd;
	}

	@Column(name="PERIOD_BEGIN_DT", nullable=false, length=7)
	public java.sql.Date getPeriodBeginDt() {
		return this.periodBeginDt;
	}
	public void setPeriodBeginDt(java.sql.Date periodBeginDt) {
		this.periodBeginDt = periodBeginDt;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MltySvcPeriodExcptnPK)) {
			return false;
		}
		MltySvcPeriodExcptnPK castOther = (MltySvcPeriodExcptnPK)other;
		return new EqualsBuilder()
			.append(this.getExcptnRuleNm(), castOther.getExcptnRuleNm())
			.append(this.getMltySvcPeriodTypeCd(), castOther.getMltySvcPeriodTypeCd())
			.append(this.getPeriodBeginDt(), castOther.getPeriodBeginDt())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getExcptnRuleNm())
			.append(getMltySvcPeriodTypeCd())
			.append(getPeriodBeginDt())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("excptnRuleNm", getExcptnRuleNm())
			.append("mltySvcPeriodTypeCd", getMltySvcPeriodTypeCd())
			.append("periodBeginDt", getPeriodBeginDt())
			.toString();
	}
}