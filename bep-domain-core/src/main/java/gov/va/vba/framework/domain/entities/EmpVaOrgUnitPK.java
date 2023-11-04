package gov.va.vba.framework.domain.entities;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The primary key class for the EMP_VA_ORG_UNIT database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class EmpVaOrgUnitPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long ptcpntId;
	private String vaOrgUnitTypeNm;

    public EmpVaOrgUnitPK() {
    }

	@Column(name="PTCPNT_ID", nullable=false, precision=15)
	public Long getPtcpntId() {
		return this.ptcpntId;
	}
	public void setPtcpntId(Long ptcpntId) {
		this.ptcpntId = ptcpntId;
	}

	@Column(name="VA_ORG_UNIT_TYPE_NM", nullable=false, length=50)
	public String getVaOrgUnitTypeNm() {
		return this.vaOrgUnitTypeNm;
	}
	public void setVaOrgUnitTypeNm(String vaOrgUnitTypeNm) {
		this.vaOrgUnitTypeNm = vaOrgUnitTypeNm;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EmpVaOrgUnitPK)) {
			return false;
		}
		EmpVaOrgUnitPK castOther = (EmpVaOrgUnitPK)other;
		return new EqualsBuilder()
			.append(this.getPtcpntId(), castOther.getPtcpntId())
			.append(this.getVaOrgUnitTypeNm(), castOther.getVaOrgUnitTypeNm())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getPtcpntId())
			.append(getVaOrgUnitTypeNm())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("ptcpntId", getPtcpntId())
			.append("vaOrgUnitTypeNm", getVaOrgUnitTypeNm())
			.toString();
	}
}