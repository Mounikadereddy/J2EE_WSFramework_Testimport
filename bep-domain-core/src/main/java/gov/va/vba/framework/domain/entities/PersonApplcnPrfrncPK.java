package gov.va.vba.framework.domain.entities;
import java.io.Serializable;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The primary key class for the PERSON_APPLCN_PRFRNC database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class PersonApplcnPrfrncPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long applcnId;
	private String prfrncTypeCd;
	private Long ptcpntId;

    public PersonApplcnPrfrncPK() {
    }

    public PersonApplcnPrfrncPK(String pkValues){
    	Pattern eq = Pattern.compile("=");
		String[] info = eq.split(pkValues);
		this.applcnId = Long.parseLong(info[1].substring(0, info[1].indexOf(",")));
		this.prfrncTypeCd = info[2].substring(0, info[2].indexOf(","));
		this.ptcpntId = Long.parseLong(info[3].substring(0, info[3].indexOf("]")));
    }
	@Column(name="APPLCN_ID", nullable=false, precision=15)
	public Long getApplcnId() {
		return this.applcnId;
	}
	public void setApplcnId(Long applcnId) {
		this.applcnId = applcnId;
	}

	@Column(name="PRFRNC_TYPE_CD", nullable=false, length=12)
	public String getPrfrncTypeCd() {
		return this.prfrncTypeCd;
	}
	public void setPrfrncTypeCd(String prfrncTypeCd) {
		this.prfrncTypeCd = prfrncTypeCd;
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
		if (!(other instanceof PersonApplcnPrfrncPK)) {
			return false;
		}
		PersonApplcnPrfrncPK castOther = (PersonApplcnPrfrncPK)other;
		return new EqualsBuilder()
			.append(this.getApplcnId(), castOther.getApplcnId())
			.append(this.getPrfrncTypeCd(), castOther.getPrfrncTypeCd())
			.append(this.getPtcpntId(), castOther.getPtcpntId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getApplcnId())
			.append(getPrfrncTypeCd())
			.append(getPtcpntId())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("applcnId", getApplcnId())
			.append("prfrncTypeCd", getPrfrncTypeCd())
			.append("ptcpntId", getPtcpntId())
			.toString();
	}
}