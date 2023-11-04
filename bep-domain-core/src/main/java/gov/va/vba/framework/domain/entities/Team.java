package gov.va.vba.framework.domain.entities;
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the TEAM database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="TEAM")
public class Team implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long teamId;
	private String descpTxt;
	private String nm;
	private java.util.Set<ApplcnTeam> applcnTeams;
	private Stn stn;
	private java.util.Set<TeamPerson> teamPersons;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public Team() {
    }
    
	@Basic()
	@Column(name="JRN_DT", nullable=false, length=7)
	public java.util.Date getJrnDt() {
		return this.jrnDt;
	}
	public void setJrnDt(java.util.Date jrnDt) {
		this.jrnDt = jrnDt;
	}

	@Basic()
	@Column(name="JRN_LCTN_ID", nullable=false, length=4)
	public String getJrnLctnId() {
		return this.jrnLctnId;
	}
	public void setJrnLctnId(String jrnLctnId) {
		this.jrnLctnId = jrnLctnId;
	}

	@Basic()
	@Column(name="JRN_OBJ_ID", nullable=false, length=32)
	public String getJrnObjId() {
		return this.jrnObjId;
	}
	public void setJrnObjId(String jrnObjId) {
		this.jrnObjId = jrnObjId;
	}

	@Basic()
	@Column(name="JRN_STATUS_TYPE_CD", nullable=false, length=12)
	public String getJrnStatusTypeCd() {
		return this.jrnStatusTypeCd;
	}
	public void setJrnStatusTypeCd(String jrnStatusTypeCd) {
		this.jrnStatusTypeCd = jrnStatusTypeCd;
	}

	@Basic()
	@Column(name="JRN_USER_ID", nullable=false, length=50)
	public String getJrnUserId() {
		return this.jrnUserId;
	}
	public void setJrnUserId(String jrnUserId) {
		this.jrnUserId = jrnUserId;
	}
	
	@Id()
	@Column(name="TEAM_ID", unique=true, nullable=false, precision=15)
	public Long getTeamId() {
		return this.teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	@Basic()
	@Column(name="DESCP_TXT", length=254)
	public String getDescpTxt() {
		return this.descpTxt;
	}
	public void setDescpTxt(String descpTxt) {
		this.descpTxt = descpTxt;
	}

	@Basic()
	@Column(name="NM", nullable=false, length=40)
	public String getNm() {
		return this.nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}

	//bi-directional many-to-one association to ApplcnTeam
	@OneToMany(mappedBy="team", fetch=FetchType.LAZY)
	public java.util.Set<ApplcnTeam> getApplcnTeams() {
		return this.applcnTeams;
	}
	public void setApplcnTeams(java.util.Set<ApplcnTeam> applcnTeams) {
		this.applcnTeams = applcnTeams;
	}

	//bi-directional many-to-one association to Stn
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="LCTN_ID", referencedColumnName="LCTN_ID", nullable=false)
	public Stn getStn() {
		return this.stn;
	}
	public void setStn(Stn stn) {
		this.stn = stn;
	}

	//bi-directional many-to-one association to TeamPerson
	@OneToMany(mappedBy="team", fetch=FetchType.LAZY)
	public java.util.Set<TeamPerson> getTeamPersons() {
		return this.teamPersons;
	}
	public void setTeamPersons(java.util.Set<TeamPerson> teamPersons) {
		this.teamPersons = teamPersons;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Team)) {
			return false;
		}
		Team castOther = (Team)other;
		return new EqualsBuilder()
			.append(this.getTeamId(), castOther.getTeamId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getTeamId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("teamId", getTeamId())
			.toString();
	}
}