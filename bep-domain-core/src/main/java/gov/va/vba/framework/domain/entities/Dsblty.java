package gov.va.vba.framework.domain.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the DSBLTY database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="DSBLTY")
public class Dsblty implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private DsbltyPK compId;
	private String combatInd;
	private String dsbltyDecnBasisTypeCd;
	private String dsbltyDecnTypeCd;
	private String dsbltyPayTypeCd;
	private java.sql.Timestamp futureExamDt;
	private String imeditExamInd;
	private java.sql.Timestamp inactvDt;
	private java.sql.Timestamp lastExamDt;
	private String mltySvcPeriodTypeCd;
	private Integer prevSvcPct;
	private String staticInd;
	private java.sql.Timestamp svcEventDt;
	private Dsblty dsblty;
	private java.util.Set<Dsblty> dsblties;
	private RbaPrfil rbaPrfil;
	private java.util.Set<DsbltySpeclIssue> dsbltySpeclIssues;
	private java.util.Set<RatingDecn> ratingDecns;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

	public Dsblty() {
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

	@EmbeddedId
	public DsbltyPK getCompId() {
		return this.compId;
	}
	public void setCompId(DsbltyPK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="COMBAT_IND", length=1)
	public String getCombatInd() {
		return this.combatInd;
	}
	public void setCombatInd(String combatInd) {
		this.combatInd = combatInd;
	}

	@Basic()
	@Column(name="DSBLTY_DECN_BASIS_TYPE_CD", length=12)
	public String getDsbltyDecnBasisTypeCd() {
		return this.dsbltyDecnBasisTypeCd;
	}
	public void setDsbltyDecnBasisTypeCd(String dsbltyDecnBasisTypeCd) {
		this.dsbltyDecnBasisTypeCd = dsbltyDecnBasisTypeCd;
	}

	@Basic()
	@Column(name="DSBLTY_DECN_TYPE_CD", nullable=false, length=12)
	public String getDsbltyDecnTypeCd() {
		return this.dsbltyDecnTypeCd;
	}
	public void setDsbltyDecnTypeCd(String dsbltyDecnTypeCd) {
		this.dsbltyDecnTypeCd = dsbltyDecnTypeCd;
	}

	@Basic()
	@Column(name="DSBLTY_PAY_TYPE_CD", length=12)
	public String getDsbltyPayTypeCd() {
		return this.dsbltyPayTypeCd;
	}
	public void setDsbltyPayTypeCd(String dsbltyPayTypeCd) {
		this.dsbltyPayTypeCd = dsbltyPayTypeCd;
	}

	@Basic()
	@Column(name="FUTURE_EXAM_DT", length=7)
	public java.sql.Timestamp getFutureExamDt() {
		return this.futureExamDt;
	}
	public void setFutureExamDt(java.sql.Timestamp futureExamDt) {
		this.futureExamDt = futureExamDt;
	}

	@Basic()
	@Column(name="IMEDIT_EXAM_IND", length=1)
	public String getImeditExamInd() {
		return this.imeditExamInd;
	}
	public void setImeditExamInd(String imeditExamInd) {
		this.imeditExamInd = imeditExamInd;
	}

	@Basic()
	@Column(name="INACTV_DT", length=7)
	public java.sql.Timestamp getInactvDt() {
		return this.inactvDt;
	}
	public void setInactvDt(java.sql.Timestamp inactvDt) {
		this.inactvDt = inactvDt;
	}

	@Basic()
	@Column(name="LAST_EXAM_DT", length=7)
	public java.sql.Timestamp getLastExamDt() {
		return this.lastExamDt;
	}
	public void setLastExamDt(java.sql.Timestamp lastExamDt) {
		this.lastExamDt = lastExamDt;
	}

	@Basic()
	@Column(name="MLTY_SVC_PERIOD_TYPE_CD", length=12)
	public String getMltySvcPeriodTypeCd() {
		return this.mltySvcPeriodTypeCd;
	}
	public void setMltySvcPeriodTypeCd(String mltySvcPeriodTypeCd) {
		this.mltySvcPeriodTypeCd = mltySvcPeriodTypeCd;
	}

	@Basic()
	@Column(name="PREV_SVC_PCT", precision=3)
	public Integer getPrevSvcPct() {
		return this.prevSvcPct;
	}
	public void setPrevSvcPct(Integer prevSvcPct) {
		this.prevSvcPct = prevSvcPct;
	}

	@Basic()
	@Column(name="STATIC_IND", length=1)
	public String getStaticInd() {
		return this.staticInd;
	}
	public void setStaticInd(String staticInd) {
		this.staticInd = staticInd;
	}

	@Basic()
	@Column(name="SVC_EVENT_DT", length=7)
	public java.sql.Timestamp getSvcEventDt() {
		return this.svcEventDt;
	}
	public void setSvcEventDt(java.sql.Timestamp svcEventDt) {
		this.svcEventDt = svcEventDt;
	}

	//bi-directional many-to-one association to Dsblty
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="RELATD_DSBLTY_ID", referencedColumnName="DSBLTY_ID"),
		@JoinColumn(name="RELATD_DSBLTY_DT", referencedColumnName="DSBLTY_DT")})
	public Dsblty getDsblty() {
		return this.dsblty;
	}
	public void setDsblty(Dsblty dsblty) {
		this.dsblty = dsblty;
	}

	//bi-directional many-to-one association to Dsblty
	@OneToMany(mappedBy="dsblty", fetch=FetchType.LAZY)
	public java.util.Set<Dsblty> getDsblties() {
		return this.dsblties;
	}
	public void setDsblties(java.util.Set<Dsblty> dsblties) {
		this.dsblties = dsblties;
	}

	//bi-directional many-to-one association to RbaPrfil
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="PTCPNT_VET_ID", referencedColumnName="PTCPNT_VET_ID", nullable=false),
		@JoinColumn(name="PRFIL_DT", referencedColumnName="PRFIL_DT", nullable=false)})
	public RbaPrfil getRbaPrfil() {
		return this.rbaPrfil;
	}
	public void setRbaPrfil(RbaPrfil rbaPrfil) {
		this.rbaPrfil = rbaPrfil;
	}

	//bi-directional many-to-one association to DsbltySpeclIssue
	@OneToMany(mappedBy="dsblty", fetch=FetchType.LAZY)
	public java.util.Set<DsbltySpeclIssue> getDsbltySpeclIssues() {
		return this.dsbltySpeclIssues;
	}
	public void setDsbltySpeclIssues(java.util.Set<DsbltySpeclIssue> dsbltySpeclIssues) {
		this.dsbltySpeclIssues = dsbltySpeclIssues;
	}

	//bi-directional many-to-one association to RatingDecn
	@OneToMany(mappedBy="dsblty", fetch=FetchType.LAZY)
	public java.util.Set<RatingDecn> getRatingDecns() {
		return this.ratingDecns;
	}
	public void setRatingDecns(java.util.Set<RatingDecn> ratingDecns) {
		this.ratingDecns = ratingDecns;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Dsblty)) {
			return false;
		}
		Dsblty castOther = (Dsblty)other;
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