package gov.va.vba.framework.domain.entities;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The primary key class for the DSBLTY_SPECL_ISSUE database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class DsbltySpeclIssuePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private java.sql.Timestamp dsbltyDt;
	private Long dsbltyId;
	private String speclIssueBasisTypeCd;
	private String speclIssueTypeCd;

    public DsbltySpeclIssuePK() {
    }

	@Column(name="DSBLTY_DT", nullable=false, length=7)
	public java.sql.Timestamp getDsbltyDt() {
		return this.dsbltyDt;
	}
	public void setDsbltyDt(java.sql.Timestamp dsbltyDt) {
		this.dsbltyDt = dsbltyDt;
	}

	@Column(name="DSBLTY_ID", nullable=false, precision=15)
	public Long getDsbltyId() {
		return this.dsbltyId;
	}
	public void setDsbltyId(Long dsbltyId) {
		this.dsbltyId = dsbltyId;
	}

	@Column(name="SPECL_ISSUE_BASIS_TYPE_CD", nullable=false, length=12)
	public String getSpeclIssueBasisTypeCd() {
		return this.speclIssueBasisTypeCd;
	}
	public void setSpeclIssueBasisTypeCd(String speclIssueBasisTypeCd) {
		this.speclIssueBasisTypeCd = speclIssueBasisTypeCd;
	}

	@Column(name="SPECL_ISSUE_TYPE_CD", nullable=false, length=12)
	public String getSpeclIssueTypeCd() {
		return this.speclIssueTypeCd;
	}
	public void setSpeclIssueTypeCd(String speclIssueTypeCd) {
		this.speclIssueTypeCd = speclIssueTypeCd;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DsbltySpeclIssuePK)) {
			return false;
		}
		DsbltySpeclIssuePK castOther = (DsbltySpeclIssuePK)other;
		return new EqualsBuilder()
			.append(this.getDsbltyDt(), castOther.getDsbltyDt())
			.append(this.getDsbltyId(), castOther.getDsbltyId())
			.append(this.getSpeclIssueBasisTypeCd(), castOther.getSpeclIssueBasisTypeCd())
			.append(this.getSpeclIssueTypeCd(), castOther.getSpeclIssueTypeCd())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getDsbltyDt())
			.append(getDsbltyId())
			.append(getSpeclIssueBasisTypeCd())
			.append(getSpeclIssueTypeCd())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("dsbltyDt", getDsbltyDt())
			.append("dsbltyId", getDsbltyId())
			.append("speclIssueBasisTypeCd", getSpeclIssueBasisTypeCd())
			.append("speclIssueTypeCd", getSpeclIssueTypeCd())
			.toString();
	}
}