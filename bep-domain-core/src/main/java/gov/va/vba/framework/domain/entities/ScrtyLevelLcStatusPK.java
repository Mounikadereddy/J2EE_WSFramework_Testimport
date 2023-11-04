package gov.va.vba.framework.domain.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The primary key class for the SCRTY_LEVEL_LC_STATUS database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class ScrtyLevelLcStatusPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String accessScrtyPurposInd;
	private Long ptcpntId;
	private Long scrtyLevelLcStatusSeqNbr;

    public ScrtyLevelLcStatusPK() {
    }

	@Column(name="ACCESS_SCRTY_PURPOS_IND", nullable=false, length=1)
	public String getAccessScrtyPurposInd() {
		return this.accessScrtyPurposInd;
	}
	public void setAccessScrtyPurposInd(String accessScrtyPurposInd) {
		this.accessScrtyPurposInd = accessScrtyPurposInd;
	}

	@Column(name="PTCPNT_ID", nullable=false, precision=15)
	public Long getPtcpntId() {
		return this.ptcpntId;
	}
	public void setPtcpntId(Long ptcpntId) {
		this.ptcpntId = ptcpntId;
	}

	@Column(name="SCRTY_LEVEL_LC_STATUS_SEQ_NBR", nullable=false, precision=15)
	public Long getScrtyLevelLcStatusSeqNbr() {
		return this.scrtyLevelLcStatusSeqNbr;
	}
	public void setScrtyLevelLcStatusSeqNbr(Long scrtyLevelLcStatusSeqNbr) {
		this.scrtyLevelLcStatusSeqNbr = scrtyLevelLcStatusSeqNbr;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ScrtyLevelLcStatusPK)) {
			return false;
		}
		ScrtyLevelLcStatusPK castOther = (ScrtyLevelLcStatusPK)other;
		return new EqualsBuilder()
			.append(this.getAccessScrtyPurposInd(), castOther.getAccessScrtyPurposInd())
			.append(this.getPtcpntId(), castOther.getPtcpntId())
			.append(this.getScrtyLevelLcStatusSeqNbr(), castOther.getScrtyLevelLcStatusSeqNbr())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getAccessScrtyPurposInd())
			.append(getPtcpntId())
			.append(getScrtyLevelLcStatusSeqNbr())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("accessScrtyPurposInd", getAccessScrtyPurposInd())
			.append("ptcpntId", getPtcpntId())
			.append("scrtyLevelLcStatusSeqNbr", getScrtyLevelLcStatusSeqNbr())
			.toString();
	}
}