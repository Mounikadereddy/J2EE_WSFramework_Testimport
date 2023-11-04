package gov.va.vba.framework.domain.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The primary key class for the BNFT_CHART database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class BnftChartPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String bnftClaimTypeCd;
	private String pgmTypeCd;
	private String svcTypeCd;

    public BnftChartPK() {
    }

	@Column(name="BNFT_CLAIM_TYPE_CD", nullable=false, length=12)
	public String getBnftClaimTypeCd() {
		return this.bnftClaimTypeCd;
	}
	public void setBnftClaimTypeCd(String bnftClaimTypeCd) {
		this.bnftClaimTypeCd = bnftClaimTypeCd;
	}

	@Column(name="PGM_TYPE_CD", nullable=false, length=12)
	public String getPgmTypeCd() {
		return this.pgmTypeCd;
	}
	public void setPgmTypeCd(String pgmTypeCd) {
		this.pgmTypeCd = pgmTypeCd;
	}

	@Column(name="SVC_TYPE_CD", nullable=false, length=12)
	public String getSvcTypeCd() {
		return this.svcTypeCd;
	}
	public void setSvcTypeCd(String svcTypeCd) {
		this.svcTypeCd = svcTypeCd;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BnftChartPK)) {
			return false;
		}
		BnftChartPK castOther = (BnftChartPK)other;
		return new EqualsBuilder()
			.append(this.getBnftClaimTypeCd(), castOther.getBnftClaimTypeCd())
			.append(this.getPgmTypeCd(), castOther.getPgmTypeCd())
			.append(this.getSvcTypeCd(), castOther.getSvcTypeCd())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getBnftClaimTypeCd())
			.append(getPgmTypeCd())
			.append(getSvcTypeCd())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("bnftClaimTypeCd", getBnftClaimTypeCd())
			.append("pgmTypeCd", getPgmTypeCd())
			.append("svcTypeCd", getSvcTypeCd())
			.toString();
	}
}