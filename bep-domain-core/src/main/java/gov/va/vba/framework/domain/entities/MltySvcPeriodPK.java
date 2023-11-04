package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The primary key class for the MLTY_SVC_PERIOD database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class MltySvcPeriodPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private java.sql.Date beginDt;
	private String mltySvcPeriodTypeCd;

    public MltySvcPeriodPK() {
    }

	@Column(name="BEGIN_DT", nullable=false, length=7)
	public java.sql.Date getBeginDt() {
		return this.beginDt;
	}
	public void setBeginDt(java.sql.Date beginDt) {
		this.beginDt = beginDt;
	}

	@Column(name="MLTY_SVC_PERIOD_TYPE_CD", nullable=false, length=12)
	public String getMltySvcPeriodTypeCd() {
		return this.mltySvcPeriodTypeCd;
	}
	public void setMltySvcPeriodTypeCd(String mltySvcPeriodTypeCd) {
		this.mltySvcPeriodTypeCd = mltySvcPeriodTypeCd;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MltySvcPeriodPK)) {
			return false;
		}
		MltySvcPeriodPK castOther = (MltySvcPeriodPK)other;
		return new EqualsBuilder()
			.append(this.getBeginDt(), castOther.getBeginDt())
			.append(this.getMltySvcPeriodTypeCd(), castOther.getMltySvcPeriodTypeCd())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getBeginDt())
			.append(getMltySvcPeriodTypeCd())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("beginDt", getBeginDt())
			.append("mltySvcPeriodTypeCd", getMltySvcPeriodTypeCd())
			.toString();
	}
}