package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The primary key class for the PTCPNT_LCTN database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class PtcpntLctnPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long lctnId;
	private Long ptcpntId;
	private String ptcpntLctnTypeNm;

    public PtcpntLctnPK() {
    }

	@Column(name="LCTN_ID", nullable=false, precision=15)
	public Long getLctnId() {
		return this.lctnId;
	}
	public void setLctnId(Long lctnId) {
		this.lctnId = lctnId;
	}

	@Column(name="PTCPNT_ID", nullable=false, precision=15)
	public Long getPtcpntId() {
		return this.ptcpntId;
	}
	public void setPtcpntId(Long ptcpntId) {
		this.ptcpntId = ptcpntId;
	}

	@Column(name="PTCPNT_LCTN_TYPE_NM", nullable=false, length=50)
	public String getPtcpntLctnTypeNm() {
		return this.ptcpntLctnTypeNm;
	}
	public void setPtcpntLctnTypeNm(String ptcpntLctnTypeNm) {
		this.ptcpntLctnTypeNm = ptcpntLctnTypeNm;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PtcpntLctnPK)) {
			return false;
		}
		PtcpntLctnPK castOther = (PtcpntLctnPK)other;
		return new EqualsBuilder()
			.append(this.getLctnId(), castOther.getLctnId())
			.append(this.getPtcpntId(), castOther.getPtcpntId())
			.append(this.getPtcpntLctnTypeNm(), castOther.getPtcpntLctnTypeNm())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getLctnId())
			.append(getPtcpntId())
			.append(getPtcpntLctnTypeNm())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("lctnId", getLctnId())
			.append("ptcpntId", getPtcpntId())
			.append("ptcpntLctnTypeNm", getPtcpntLctnTypeNm())
			.toString();
	}
}