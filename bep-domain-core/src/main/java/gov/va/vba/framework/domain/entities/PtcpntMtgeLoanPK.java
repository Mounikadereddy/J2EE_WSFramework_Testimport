package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The primary key class for the PTCPNT_MTGE_LOAN database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class PtcpntMtgeLoanPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long mtgeLoanId;
	private Long ptcpntId;
	private String ptcpntMtgeLoanTypeNm;

    public PtcpntMtgeLoanPK() {
    }

	@Column(name="MTGE_LOAN_ID", nullable=false, precision=15)
	public Long getMtgeLoanId() {
		return this.mtgeLoanId;
	}
	public void setMtgeLoanId(Long mtgeLoanId) {
		this.mtgeLoanId = mtgeLoanId;
	}

	@Column(name="PTCPNT_ID", nullable=false, precision=15)
	public Long getPtcpntId() {
		return this.ptcpntId;
	}
	public void setPtcpntId(Long ptcpntId) {
		this.ptcpntId = ptcpntId;
	}

	@Column(name="PTCPNT_MTGE_LOAN_TYPE_NM", nullable=false, length=50)
	public String getPtcpntMtgeLoanTypeNm() {
		return this.ptcpntMtgeLoanTypeNm;
	}
	public void setPtcpntMtgeLoanTypeNm(String ptcpntMtgeLoanTypeNm) {
		this.ptcpntMtgeLoanTypeNm = ptcpntMtgeLoanTypeNm;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PtcpntMtgeLoanPK)) {
			return false;
		}
		PtcpntMtgeLoanPK castOther = (PtcpntMtgeLoanPK)other;
		return new EqualsBuilder()
			.append(this.getMtgeLoanId(), castOther.getMtgeLoanId())
			.append(this.getPtcpntId(), castOther.getPtcpntId())
			.append(this.getPtcpntMtgeLoanTypeNm(), castOther.getPtcpntMtgeLoanTypeNm())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getMtgeLoanId())
			.append(getPtcpntId())
			.append(getPtcpntMtgeLoanTypeNm())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("mtgeLoanId", getMtgeLoanId())
			.append("ptcpntId", getPtcpntId())
			.append("ptcpntMtgeLoanTypeNm", getPtcpntMtgeLoanTypeNm())
			.toString();
	}
}