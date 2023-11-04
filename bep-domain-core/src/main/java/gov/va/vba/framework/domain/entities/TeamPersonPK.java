package gov.va.vba.framework.domain.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The primary key class for the TEAM_PERSON database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class TeamPersonPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long ptcpntId;
	private Long teamId;

    public TeamPersonPK() {
    }

	@Column(name="PTCPNT_ID", nullable=false, precision=15)
	public Long getPtcpntId() {
		return this.ptcpntId;
	}
	public void setPtcpntId(Long ptcpntId) {
		this.ptcpntId = ptcpntId;
	}

	@Column(name="TEAM_ID", nullable=false, precision=15)
	public Long getTeamId() {
		return this.teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TeamPersonPK)) {
			return false;
		}
		TeamPersonPK castOther = (TeamPersonPK)other;
		return new EqualsBuilder()
			.append(this.getPtcpntId(), castOther.getPtcpntId())
			.append(this.getTeamId(), castOther.getTeamId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getPtcpntId())
			.append(getTeamId())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("ptcpntId", getPtcpntId())
			.append("teamId", getTeamId())
			.toString();
	}
}