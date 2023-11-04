package gov.va.vba.framework.domain.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The primary key class for the PERSON_ORG_RLNSHP database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class PersonOrgRlnshpPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long ptcpntIdA;
	private Long ptcpntIdB;
	private String ptcpntRlnshpTypeNm;

    public PersonOrgRlnshpPK() {
    }

	@Column(name="PTCPNT_ID_A", nullable=false, precision=15)
	public Long getPtcpntIdA() {
		return this.ptcpntIdA;
	}
	public void setPtcpntIdA(Long ptcpntIdA) {
		this.ptcpntIdA = ptcpntIdA;
	}

	@Column(name="PTCPNT_ID_B", nullable=false, precision=15)
	public Long getPtcpntIdB() {
		return this.ptcpntIdB;
	}
	public void setPtcpntIdB(Long ptcpntIdB) {
		this.ptcpntIdB = ptcpntIdB;
	}

	@Column(name="PTCPNT_RLNSHP_TYPE_NM", nullable=false, length=50)
	public String getPtcpntRlnshpTypeNm() {
		return this.ptcpntRlnshpTypeNm;
	}
	public void setPtcpntRlnshpTypeNm(String ptcpntRlnshpTypeNm) {
		this.ptcpntRlnshpTypeNm = ptcpntRlnshpTypeNm;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PersonOrgRlnshpPK)) {
			return false;
		}
		PersonOrgRlnshpPK castOther = (PersonOrgRlnshpPK)other;
		return new EqualsBuilder()
			.append(this.getPtcpntIdA(), castOther.getPtcpntIdA())
			.append(this.getPtcpntIdB(), castOther.getPtcpntIdB())
			.append(this.getPtcpntRlnshpTypeNm(), castOther.getPtcpntRlnshpTypeNm())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getPtcpntIdA())
			.append(getPtcpntIdB())
			.append(getPtcpntRlnshpTypeNm())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("ptcpntIdA", getPtcpntIdA())
			.append("ptcpntIdB", getPtcpntIdB())
			.append("ptcpntRlnshpTypeNm", getPtcpntRlnshpTypeNm())
			.toString();
	}
}