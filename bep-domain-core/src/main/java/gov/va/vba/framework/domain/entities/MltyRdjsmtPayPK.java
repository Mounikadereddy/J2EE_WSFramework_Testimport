package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The primary key class for the MLTY_RDJSMT_PAY database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class MltyRdjsmtPayPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Integer lineItemNbr;
	private Long ptcpntId;

    public MltyRdjsmtPayPK() {
    }

	@Column(name="LINE_ITEM_NBR", nullable=false, precision=5)
	public Integer getLineItemNbr() {
		return this.lineItemNbr;
	}
	public void setLineItemNbr(Integer lineItemNbr) {
		this.lineItemNbr = lineItemNbr;
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
		if (!(other instanceof MltyRdjsmtPayPK)) {
			return false;
		}
		MltyRdjsmtPayPK castOther = (MltyRdjsmtPayPK)other;
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