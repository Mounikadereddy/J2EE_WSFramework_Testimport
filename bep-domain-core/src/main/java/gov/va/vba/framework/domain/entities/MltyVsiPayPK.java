package gov.va.vba.framework.domain.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Embeddable()
public class MltyVsiPayPK implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer lineItemNbr;
	private long ptcpntId;
	
	public MltyVsiPayPK(){}

	@Column(name="LINE_ITEM_NBR", nullable=false, precision=5)
	public Integer getLineItemNbr() {
		return lineItemNbr;
	}

	public void setLineItemNbr(Integer lineItemNbr) {
		this.lineItemNbr = lineItemNbr;
	}

	@Column(name="PTCPNT_ID", nullable=false, precision=15)
	public long getPtcpntId() {
		return ptcpntId;
	}

	public void setPtcpntId(long ptcpntId) {
		this.ptcpntId = ptcpntId;
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MltyVsiPayPK)) {
			return false;
		}
		MltyVsiPayPK castOther = (MltyVsiPayPK)other;
		return new EqualsBuilder()
			.append(this.getLineItemNbr(), castOther.getLineItemNbr())
			.append(this.getPtcpntId(), castOther.getPtcpntId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getLineItemNbr())
			.append(getPtcpntId())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("lineItemNbr", getLineItemNbr())
			.append("ptcpntId", getPtcpntId())
			.toString();
	}

}
