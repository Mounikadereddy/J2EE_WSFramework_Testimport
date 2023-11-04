package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The primary key class for the MLTY_PERSON_DCORTN database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class MltyPersonDcortnPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long mltyDcortnId;
	private Long ptcpntId;

    public MltyPersonDcortnPK() {
    }
    public MltyPersonDcortnPK(String pkData){
		Pattern eq = Pattern.compile("=");
		String[] values = eq.split(pkData);
		this.mltyDcortnId = new Long(values[1].substring(0, values[1].indexOf(",")));
		this.ptcpntId = new Long(values[2].substring(0, values[2].indexOf(",")));
    }
    public MltyPersonDcortnPK(Long partId, Long dcrtnId){
    	this.ptcpntId = partId;
    	this.mltyDcortnId = dcrtnId;
    }

	@Column(name="MLTY_DCORTN_ID", nullable=false, precision=15)
	public Long getMltyDcortnId() {
		return this.mltyDcortnId;
	}
	public void setMltyDcortnId(Long mltyDcortnId) {
		this.mltyDcortnId = mltyDcortnId;
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
		if (!(other instanceof MltyPersonDcortnPK)) {
			return false;
		}
		MltyPersonDcortnPK castOther = (MltyPersonDcortnPK)other;
		return new EqualsBuilder()
			.append(this.getMltyDcortnId(), castOther.getMltyDcortnId())
			.append(this.getPtcpntId(), castOther.getPtcpntId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getMltyDcortnId())
			.append(getPtcpntId())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("mltyDcortnId", getMltyDcortnId())
			.append("ptcpntId", getPtcpntId())
			.toString();
	}
}