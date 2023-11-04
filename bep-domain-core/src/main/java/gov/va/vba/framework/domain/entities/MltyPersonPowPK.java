package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The primary key class for the MLTY_PERSON_POW database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class MltyPersonPowPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long mltyPersonPowSeqNbr;
	private Long ptcpntId;

    public MltyPersonPowPK() {
    }

	@Column(name="MLTY_PERSON_POW_SEQ_NBR", nullable=false, precision=15)
	public Long getMltyPersonPowSeqNbr() {
		return this.mltyPersonPowSeqNbr;
	}
	public void setMltyPersonPowSeqNbr(Long mltyPersonPowSeqNbr) {
		this.mltyPersonPowSeqNbr = mltyPersonPowSeqNbr;
	}

	@Column(name="PTCPNT_ID", nullable=false, precision=15)
	public Long getPtcpntId() {
		return this.ptcpntId;
	}
	public void setPtcpntId(Long ptcpntId) {
		this.ptcpntId = ptcpntId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MltyPersonPowPK)) {
			return false;
		}
		MltyPersonPowPK castOther = (MltyPersonPowPK)other;
		return new EqualsBuilder()
			.append(this.getMltyPersonPowSeqNbr(), castOther.getMltyPersonPowSeqNbr())
			.append(this.getPtcpntId(), castOther.getPtcpntId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getMltyPersonPowSeqNbr())
			.append(getPtcpntId())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("mltyPersonPowSeqNbr", getMltyPersonPowSeqNbr())
			.append("ptcpntId", getPtcpntId())
			.toString();
	}
}