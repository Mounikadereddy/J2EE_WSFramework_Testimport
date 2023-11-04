package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import java.util.regex.Pattern;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The primary key class for the MLTY_PERSON_TOUR database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class MltyPersonTourPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long mltyPersonTourNbr;
	private Long ptcpntId;

    public MltyPersonTourPK() {
    }
    
    public MltyPersonTourPK(String pkData){
		Pattern eq = Pattern.compile("=");
		String[] values = eq.split(pkData);
		this.mltyPersonTourNbr = new Long(values[1].substring(0, values[1].indexOf(",")));
		this.ptcpntId = new Long(values[2].substring(0, values[2].indexOf(",")));

    }

	@Column(name="MLTY_PERSON_TOUR_NBR", nullable=false, precision=15)
	public Long getMltyPersonTourNbr() {
		return this.mltyPersonTourNbr;
	}
	public void setMltyPersonTourNbr(Long mltyPersonTourNbr) {
		this.mltyPersonTourNbr = mltyPersonTourNbr;
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
		if (!(other instanceof MltyPersonTourPK)) {
			return false;
		}
		MltyPersonTourPK castOther = (MltyPersonTourPK)other;
		return new EqualsBuilder()
			.append(this.getMltyPersonTourNbr(), castOther.getMltyPersonTourNbr())
			.append(this.getPtcpntId(), castOther.getPtcpntId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getMltyPersonTourNbr())
			.append(getPtcpntId())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("mltyPersonTourNbr", getMltyPersonTourNbr())
			.append("ptcpntId", getPtcpntId())
			.toString();
	}
}