package gov.va.vba.framework.domain.entities;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the SCRTY_LEVEL_LC_STATUS database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="SCRTY_LEVEL_LC_STATUS")
public class ScrtyLevelLcStatus implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private ScrtyLevelLcStatusPK compId;
	private Date dt;
	private String statusTypeNm;
	private PersonScrtyLevel personScrtyLevel;
	private Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public ScrtyLevelLcStatus() {
    }
    
	@Basic()
	@Column(name="JRN_DT", nullable=false, length=7)
	public Date getJrnDt() {
		return this.jrnDt;
	}
	public void setJrnDt(Date jrnDt) {
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
	
	@EmbeddedId
	public ScrtyLevelLcStatusPK getCompId() {
		return this.compId;
	}
	public void setCompId(ScrtyLevelLcStatusPK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="DT", length=7)
	public Date getDt() {
		return this.dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}

	@Basic()
	@Column(name="STATUS_TYPE_NM", nullable=false, length=50)
	public String getStatusTypeNm() {
		return this.statusTypeNm;
	}
	public void setStatusTypeNm(String statusTypeNm) {
		this.statusTypeNm = statusTypeNm;
	}

	//bi-directional many-to-one association to PersonScrtyLevel
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="PTCPNT_ID", referencedColumnName="PTCPNT_ID", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="ACCESS_SCRTY_PURPOS_IND", referencedColumnName="ACCESS_SCRTY_PURPOS_IND", nullable=false, insertable=false, updatable=false)
		})
	public PersonScrtyLevel getPersonScrtyLevel() {
		return this.personScrtyLevel;
	}
	public void setPersonScrtyLevel(PersonScrtyLevel personScrtyLevel) {
		this.personScrtyLevel = personScrtyLevel;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ScrtyLevelLcStatus)) {
			return false;
		}
		ScrtyLevelLcStatus castOther = (ScrtyLevelLcStatus)other;
		return new EqualsBuilder()
			.append(this.getCompId(), castOther.getCompId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCompId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("compId", getCompId())
			.toString();
	}
}