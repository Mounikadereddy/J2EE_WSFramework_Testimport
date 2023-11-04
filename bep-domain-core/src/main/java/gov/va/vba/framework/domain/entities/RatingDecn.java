package gov.va.vba.framework.domain.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the RATING_DECN database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@SqlResultSetMapping(name="ratingDecnMapping", entities={@EntityResult(entityClass=RatingDecn.class )})
@Table(name="RATING_DECN")
public class RatingDecn implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long ratingDecnId;
	private java.sql.Timestamp beginDt;
	private java.sql.Timestamp cnvrtdBeginDt;
	private java.sql.Timestamp cnvrtdEndDt;
	private java.sql.Timestamp endDt;
	private java.sql.Timestamp futureExamDt;
	private java.sql.Timestamp inactvDt;
	private java.sql.Timestamp lastExamDt;
	private Long prevRatingDecnId;
	private String ratingDecnTypeCd;
	private String splmtlDecnTypeCd;
	private AncilyRating ancilyRating;
	private Cmptny cmptny;
	private Dental dental;
	private DsbltyEvaltn dsbltyEvaltn;
	private IndvdlUnempl indvdlUnempl;
	private PermntTotalDsblty permntTotalDsblty;
	private Dsblty dsblty;
	private Person person;
	private RbaPrfil rbaPrfil;
	private RbaPrfilIssue rbaPrfilIssue;
	private java.util.Set<RatingDecnSpeclIssue> ratingDecnSpeclIssues;
	private SmcPrgrph smcPrgrph;
	private SpeclMthlyComp speclMthlyComp;
	private SpeclMthlyPensn speclMthlyPensn;
	private SpeclPrvsnLaw speclPrvsnLaw;
	private SvcCnectdDeath svcCnectdDeath;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public RatingDecn() {
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
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="RATING_DECN_ID", unique=true, nullable=false, precision=15)
	public Long getRatingDecnId() {
		return this.ratingDecnId;
	}
	public void setRatingDecnId(Long ratingDecnId) {
		this.ratingDecnId = ratingDecnId;
	}

	@Basic()
	@Column(name="BEGIN_DT", length=7)
	public java.sql.Timestamp getBeginDt() {
		return this.beginDt;
	}
	public void setBeginDt(java.sql.Timestamp beginDt) {
		this.beginDt = beginDt;
	}

	@Basic()
	@Column(name="CNVRTD_BEGIN_DT", length=7)
	public java.sql.Timestamp getCnvrtdBeginDt() {
		return this.cnvrtdBeginDt;
	}
	public void setCnvrtdBeginDt(java.sql.Timestamp cnvrtdBeginDt) {
		this.cnvrtdBeginDt = cnvrtdBeginDt;
	}

	@Basic()
	@Column(name="CNVRTD_END_DT", length=7)
	public java.sql.Timestamp getCnvrtdEndDt() {
		return this.cnvrtdEndDt;
	}
	public void setCnvrtdEndDt(java.sql.Timestamp cnvrtdEndDt) {
		this.cnvrtdEndDt = cnvrtdEndDt;
	}

	@Basic()
	@Column(name="END_DT", length=7)
	public java.sql.Timestamp getEndDt() {
		return this.endDt;
	}
	public void setEndDt(java.sql.Timestamp endDt) {
		this.endDt = endDt;
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
	@Column(name="PREV_RATING_DECN_ID", precision=15)
	public Long getPrevRatingDecnId() {
		return this.prevRatingDecnId;
	}
	public void setPrevRatingDecnId(Long prevRatingDecnId) {
		this.prevRatingDecnId = prevRatingDecnId;
	}

	@Basic()
	@Column(name="RATING_DECN_TYPE_CD", nullable=false, length=12)
	public String getRatingDecnTypeCd() {
		return this.ratingDecnTypeCd;
	}
	public void setRatingDecnTypeCd(String ratingDecnTypeCd) {
		this.ratingDecnTypeCd = ratingDecnTypeCd;
	}

	@Basic()
	@Column(name="SPLMTL_DECN_TYPE_CD", length=12)
	public String getSplmtlDecnTypeCd() {
		return this.splmtlDecnTypeCd;
	}
	public void setSplmtlDecnTypeCd(String splmtlDecnTypeCd) {
		this.splmtlDecnTypeCd = splmtlDecnTypeCd;
	}

	//bi-directional one-to-one association to AncilyRating
	@OneToOne(mappedBy="ratingDecn", fetch=FetchType.LAZY)
	public AncilyRating getAncilyRating() {
		return this.ancilyRating;
	}
	public void setAncilyRating(AncilyRating ancilyRating) {
		this.ancilyRating = ancilyRating;
	}

	//bi-directional one-to-one association to Cmptny
	@OneToOne(mappedBy="ratingDecn", fetch=FetchType.LAZY)
	public Cmptny getCmptny() {
		return this.cmptny;
	}
	public void setCmptny(Cmptny cmptny) {
		this.cmptny = cmptny;
	}

	//bi-directional one-to-one association to Dental
	@OneToOne(mappedBy="ratingDecn", fetch=FetchType.LAZY)
	public Dental getDental() {
		return this.dental;
	}
	public void setDental(Dental dental) {
		this.dental = dental;
	}

	//bi-directional one-to-one association to DsbltyEvaltn
	@OneToOne(mappedBy="ratingDecn", fetch=FetchType.LAZY)
	public DsbltyEvaltn getDsbltyEvaltn() {
		return this.dsbltyEvaltn;
	}
	public void setDsbltyEvaltn(DsbltyEvaltn dsbltyEvaltn) {
		this.dsbltyEvaltn = dsbltyEvaltn;
	}

	//bi-directional one-to-one association to IndvdlUnempl
	@OneToOne(mappedBy="ratingDecn", fetch=FetchType.LAZY)
	public IndvdlUnempl getIndvdlUnempl() {
		return this.indvdlUnempl;
	}
	public void setIndvdlUnempl(IndvdlUnempl indvdlUnempl) {
		this.indvdlUnempl = indvdlUnempl;
	}

	//bi-directional one-to-one association to PermntTotalDsblty
	@OneToOne(mappedBy="ratingDecn", fetch=FetchType.LAZY)
	public PermntTotalDsblty getPermntTotalDsblty() {
		return this.permntTotalDsblty;
	}
	public void setPermntTotalDsblty(PermntTotalDsblty permntTotalDsblty) {
		this.permntTotalDsblty = permntTotalDsblty;
	}

	//bi-directional many-to-one association to Dsblty
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="DSBLTY_ID", referencedColumnName="DSBLTY_ID"),
		@JoinColumn(name="DSBLTY_DT", referencedColumnName="DSBLTY_DT")})
	public Dsblty getDsblty() {
		return this.dsblty;
	}
	public void setDsblty(Dsblty dsblty) {
		this.dsblty = dsblty;
	}

	//bi-directional many-to-one association to Person
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PTCPNT_DECN_ID", referencedColumnName="PTCPNT_ID", nullable=false)
	public Person getPerson() {
		return this.person;
	}
	public void setPerson(Person person) {
		this.person = person;
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

	//bi-directional many-to-one association to RbaPrfilIssue
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="RBA_PRFIL_ISSUE_ID", referencedColumnName="RBA_PRFIL_ISSUE_ID")
	public RbaPrfilIssue getRbaPrfilIssue() {
		return this.rbaPrfilIssue;
	}
	public void setRbaPrfilIssue(RbaPrfilIssue rbaPrfilIssue) {
		this.rbaPrfilIssue = rbaPrfilIssue;
	}

	//bi-directional many-to-one association to RatingDecnSpeclIssue
	@OneToMany(mappedBy="ratingDecn", fetch=FetchType.LAZY)
	public java.util.Set<RatingDecnSpeclIssue> getRatingDecnSpeclIssues() {
		return this.ratingDecnSpeclIssues;
	}
	public void setRatingDecnSpeclIssues(java.util.Set<RatingDecnSpeclIssue> ratingDecnSpeclIssues) {
		this.ratingDecnSpeclIssues = ratingDecnSpeclIssues;
	}

	//bi-directional one-to-one association to SmcPrgrph
	@OneToOne(mappedBy="ratingDecn", fetch=FetchType.LAZY)
	public SmcPrgrph getSmcPrgrph() {
		return this.smcPrgrph;
	}
	public void setSmcPrgrph(SmcPrgrph smcPrgrph) {
		this.smcPrgrph = smcPrgrph;
	}

	//bi-directional one-to-one association to SpeclMthlyComp
	@OneToOne(mappedBy="ratingDecn", fetch=FetchType.LAZY)
	public SpeclMthlyComp getSpeclMthlyComp() {
		return this.speclMthlyComp;
	}
	public void setSpeclMthlyComp(SpeclMthlyComp speclMthlyComp) {
		this.speclMthlyComp = speclMthlyComp;
	}

	//bi-directional one-to-one association to SpeclMthlyPensn
	@OneToOne(mappedBy="ratingDecn", fetch=FetchType.LAZY)
	public SpeclMthlyPensn getSpeclMthlyPensn() {
		return this.speclMthlyPensn;
	}
	public void setSpeclMthlyPensn(SpeclMthlyPensn speclMthlyPensn) {
		this.speclMthlyPensn = speclMthlyPensn;
	}

	//bi-directional one-to-one association to SpeclPrvsnLaw
	@OneToOne(mappedBy="ratingDecn", fetch=FetchType.LAZY)
	public SpeclPrvsnLaw getSpeclPrvsnLaw() {
		return this.speclPrvsnLaw;
	}
	public void setSpeclPrvsnLaw(SpeclPrvsnLaw speclPrvsnLaw) {
		this.speclPrvsnLaw = speclPrvsnLaw;
	}

	//bi-directional one-to-one association to SvcCnectdDeath
	@OneToOne(mappedBy="ratingDecn", fetch=FetchType.LAZY)
	public SvcCnectdDeath getSvcCnectdDeath() {
		return this.svcCnectdDeath;
	}
	public void setSvcCnectdDeath(SvcCnectdDeath svcCnectdDeath) {
		this.svcCnectdDeath = svcCnectdDeath;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RatingDecn)) {
			return false;
		}
		RatingDecn castOther = (RatingDecn)other;
		return new EqualsBuilder()
			.append(this.getRatingDecnId(), castOther.getRatingDecnId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getRatingDecnId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("ratingDecnId", getRatingDecnId())
			.toString();
	}
}