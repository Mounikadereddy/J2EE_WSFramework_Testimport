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

/**
 * The persistent class for the MLTY_PERSON database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="MLTY_PERSON")
public class MltyPerson implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long ptcpntId;
	private String activeDutyStatusInd;
//	private String atpsyInd;
//	private String bodyDonatnInd;
//	private java.sql.Timestamp burialDt;
//	private String crmtnInd;
	private String deathInSvcInd;
	private String dsbltySvcInd;
//	private int fileNbr;
//	private String flagIssuedInd;
	private String gulfWarRgstryInd;
	private String incmptInd;
	private String insFileNbr;
	private String insPolicyNbr;
	private Double lgyEntlmtAmt;
	private String resrvInd;
	private Integer totalActiveSvcDaysNbr;
	private Integer totalActiveSvcMonthsNbr;
	private Integer totalActiveSvcYearsNbr;
//	private java.util.Set<DrillResrvPay> drillResrvPays;
	private Person person;
	private java.util.Set<MltyPersonBranch> mltyPersonBranches;
	private java.util.Set<MltyPersonDcortn> mltyPersonDcortns;
	private java.util.Set<MltyPersonPow> mltyPersonPows;
	private java.util.Set<MltyPersonTour> mltyPersonTours;
	private java.util.Set<MltyRdjsmtPay> mltyRdjsmtPays;
	private java.util.Set<MltyRtrmntPay> mltyRtrmntPays;
	private java.util.Set<MltySeprtnPay> mltySeprtnPays;
	private java.util.Set<MltySevrncPay> mltySevrncPays;
	private java.util.Set<MltySsbPay> mltySsbPays;
	private java.util.Set<MltyTheatr> mltyTheatrs;
	private java.util.Set<MltyVsiPay> mltyVsiPays;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public MltyPerson() {
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
	@Column(name="PTCPNT_ID", nullable=false, precision=15)
	public Long getPtcpntId() {
		return this.ptcpntId;
	}
	public void setPtcpntId(Long ptcpntId) {
		this.ptcpntId = ptcpntId;
	}

	@Basic()
	@Column(name="ACTIVE_DUTY_STATUS_IND", length=1)
	public String getActiveDutyStatusInd() {
		return this.activeDutyStatusInd;
	}
	public void setActiveDutyStatusInd(String activeDutyStatusInd) {
		this.activeDutyStatusInd = activeDutyStatusInd;
	}

//	@Basic()
//	@Column(name="ATPSY_IND", length=1)
//	public String getAtpsyInd() {
//		return this.atpsyInd;
//	}
//	public void setAtpsyInd(String atpsyInd) {
//		this.atpsyInd = atpsyInd;
//	}
//
//	@Basic()
//	@Column(name="BODY_DONATN_IND", length=1)
//	public String getBodyDonatnInd() {
//		return this.bodyDonatnInd;
//	}
//	public void setBodyDonatnInd(String bodyDonatnInd) {
//		this.bodyDonatnInd = bodyDonatnInd;
//	}

//	@Basic()
//	@Column(name="BURIAL_DT", length=7)
//	public java.sql.Timestamp getBurialDt() {
//		return this.burialDt;
//	}
//	public void setBurialDt(java.sql.Timestamp burialDt) {
//		this.burialDt = burialDt;
//	}
//
//	@Basic()
//	@Column(name="CRMTN_IND", length=1)
//	public String getCrmtnInd() {
//		return this.crmtnInd;
//	}
//	public void setCrmtnInd(String crmtnInd) {
//		this.crmtnInd = crmtnInd;
//	}

	@Basic()
	@Column(name="DEATH_IN_SVC_IND", length=1)
	public String getDeathInSvcInd() {
		return this.deathInSvcInd;
	}
	public void setDeathInSvcInd(String deathInSvcInd) {
		this.deathInSvcInd = deathInSvcInd;
	}

	@Basic()
	@Column(name="DSBLTY_SVC_IND", length=1)
	public String getDsbltySvcInd() {
		return this.dsbltySvcInd;
	}
	public void setDsbltySvcInd(String dsbltySvcInd) {
		this.dsbltySvcInd = dsbltySvcInd;
	}

//	@Basic()
//	@Column(name="FILE_NBR", nullable=false, precision=9)
//	public int getFileNbr() {
//		return this.fileNbr;
//	}
//	public void setFileNbr(int fileNbr) {
//		this.fileNbr = fileNbr;
//	}

//	@Basic()
//	@Column(name="FLAG_ISSUED_IND", length=1)
//	public String getFlagIssuedInd() {
//		return this.flagIssuedInd;
//	}
//	public void setFlagIssuedInd(String flagIssuedInd) {
//		this.flagIssuedInd = flagIssuedInd;
//	}

	@Basic()
	@Column(name="GULF_WAR_RGSTRY_IND", length=1)
	public String getGulfWarRgstryInd() {
		return this.gulfWarRgstryInd;
	}
	public void setGulfWarRgstryInd(String gulfWarRgstryInd) {
		this.gulfWarRgstryInd = gulfWarRgstryInd;
	}

	@Basic()
	@Column(name="INCMPT_IND", length=1)
	public String getIncmptInd() {
		return this.incmptInd;
	}
	public void setIncmptInd(String incmptInd) {
		this.incmptInd = incmptInd;
	}

	@Basic()
	@Column(name="INS_FILE_NBR", length=10)
	public String getInsFileNbr() {
		return this.insFileNbr;
	}
	public void setInsFileNbr(String insFileNbr) {
		this.insFileNbr = insFileNbr;
	}

	@Basic()
	@Column(name="INS_POLICY_NBR", length=10)
	public String getInsPolicyNbr() {
		return this.insPolicyNbr;
	}
	public void setInsPolicyNbr(String insPolicyNbr) {
		this.insPolicyNbr = insPolicyNbr;
	}

	@Basic()
	@Column(name="LGY_ENTLMT_AMT", precision=8, scale=2)
	public Double getLgyEntlmtAmt() {
		return this.lgyEntlmtAmt;
	}
	public void setLgyEntlmtAmt(Double lgyEntlmtAmt) {
		this.lgyEntlmtAmt = lgyEntlmtAmt;
	}

	@Basic()
	@Column(name="RESRV_IND", length=1)
	public String getResrvInd() {
		return this.resrvInd;
	}
	public void setResrvInd(String resrvInd) {
		this.resrvInd = resrvInd;
	}

	@Basic()
	@Column(name="TOTAL_ACTIVE_SVC_DAYS_NBR", precision=2)
	public Integer getTotalActiveSvcDaysNbr() {
		return this.totalActiveSvcDaysNbr;
	}
	public void setTotalActiveSvcDaysNbr(Integer totalActiveSvcDaysNbr) {
		this.totalActiveSvcDaysNbr = totalActiveSvcDaysNbr;
	}

	@Basic()
	@Column(name="TOTAL_ACTIVE_SVC_MONTHS_NBR", precision=2)
	public Integer getTotalActiveSvcMonthsNbr() {
		return this.totalActiveSvcMonthsNbr;
	}
	public void setTotalActiveSvcMonthsNbr(Integer totalActiveSvcMonthsNbr) {
		this.totalActiveSvcMonthsNbr = totalActiveSvcMonthsNbr;
	}

	@Basic()
	@Column(name="TOTAL_ACTIVE_SVC_YEARS_NBR", precision=2)
	public Integer getTotalActiveSvcYearsNbr() {
		return this.totalActiveSvcYearsNbr;
	}
	public void setTotalActiveSvcYearsNbr(Integer totalActiveSvcYearsNbr) {
		this.totalActiveSvcYearsNbr = totalActiveSvcYearsNbr;
	}

//	//bi-directional many-to-one association to DrillResrvPay
//	@OneToMany(mappedBy="mltyPerson", fetch=FetchType.LAZY)
//	public java.util.Set<DrillResrvPay> getDrillResrvPays() {
//		return this.drillResrvPays;
//	}
//	public void setDrillResrvPays(java.util.Set<DrillResrvPay> drillResrvPays) {
//		this.drillResrvPays = drillResrvPays;
//	}

	//bi-directional many-to-one association to Person
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PTCPNT_ID", referencedColumnName="PTCPNT_ID", nullable=false, insertable=false, updatable=false)
	public Person getPerson() {
		return this.person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}

	//bi-directional many-to-one association to MltyPersonBranch
	@OneToMany(mappedBy="mltyPerson", fetch=FetchType.LAZY)
	public java.util.Set<MltyPersonBranch> getMltyPersonBranches() {
		return this.mltyPersonBranches;
	}
	public void setMltyPersonBranches(java.util.Set<MltyPersonBranch> mltyPersonBranches) {
		this.mltyPersonBranches = mltyPersonBranches;
	}

	//bi-directional many-to-one association to MltyPersonDcortn
	@OneToMany(mappedBy="mltyPerson", fetch=FetchType.LAZY)
	public java.util.Set<MltyPersonDcortn> getMltyPersonDcortns() {
		return this.mltyPersonDcortns;
	}
	public void setMltyPersonDcortns(java.util.Set<MltyPersonDcortn> mltyPersonDcortns) {
		this.mltyPersonDcortns = mltyPersonDcortns;
	}

	//bi-directional many-to-one association to MltyPersonPow
	@OneToMany(mappedBy="mltyPerson", fetch=FetchType.LAZY)
	public java.util.Set<MltyPersonPow> getMltyPersonPows() {
		return this.mltyPersonPows;
	}
	public void setMltyPersonPows(java.util.Set<MltyPersonPow> mltyPersonPows) {
		this.mltyPersonPows = mltyPersonPows;
	}

	//bi-directional many-to-one association to MltyPersonTour
	@OneToMany(mappedBy="mltyPerson", fetch=FetchType.LAZY)
	public java.util.Set<MltyPersonTour> getMltyPersonTours() {
		return this.mltyPersonTours;
	}
	public void setMltyPersonTours(java.util.Set<MltyPersonTour> mltyPersonTours) {
		this.mltyPersonTours = mltyPersonTours;
	}

	//bi-directional many-to-one association to MltyRdjsmtPay
	@OneToMany(mappedBy="mltyPerson", fetch=FetchType.LAZY)
	public java.util.Set<MltyRdjsmtPay> getMltyRdjsmtPays() {
		return this.mltyRdjsmtPays;
	}
	public void setMltyRdjsmtPays(java.util.Set<MltyRdjsmtPay> mltyRdjsmtPays) {
		this.mltyRdjsmtPays = mltyRdjsmtPays;
	}

	//bi-directional many-to-one association to MltyRtrmntPay
	@OneToMany(mappedBy="mltyPerson", fetch=FetchType.LAZY)
	public java.util.Set<MltyRtrmntPay> getMltyRtrmntPays() {
		return this.mltyRtrmntPays;
	}
	public void setMltyRtrmntPays(java.util.Set<MltyRtrmntPay> mltyRtrmntPays) {
		this.mltyRtrmntPays = mltyRtrmntPays;
	}

	//bi-directional many-to-one association to MltySeprtnPay
	@OneToMany(mappedBy="mltyPerson", fetch=FetchType.LAZY)
	public java.util.Set<MltySeprtnPay> getMltySeprtnPays() {
		return this.mltySeprtnPays;
	}
	public void setMltySeprtnPays(java.util.Set<MltySeprtnPay> mltySeprtnPays) {
		this.mltySeprtnPays = mltySeprtnPays;
	}

	//bi-directional many-to-one association to MltySevrncPay
	@OneToMany(mappedBy="mltyPerson", fetch=FetchType.LAZY)
	public java.util.Set<MltySevrncPay> getMltySevrncPays() {
		return this.mltySevrncPays;
	}
	public void setMltySevrncPays(java.util.Set<MltySevrncPay> mltySevrncPays) {
		this.mltySevrncPays = mltySevrncPays;
	}

	//bi-directional many-to-one association to MltySsbPay
	@OneToMany(mappedBy="mltyPerson", fetch=FetchType.LAZY)
	public java.util.Set<MltySsbPay> getMltySsbPays() {
		return this.mltySsbPays;
	}
	public void setMltySsbPays(java.util.Set<MltySsbPay> mltySsbPays) {
		this.mltySsbPays = mltySsbPays;
	}

	//bi-directional many-to-one association to MltyTheatr
	@OneToMany(mappedBy="mltyPerson", fetch=FetchType.LAZY)
	public java.util.Set<MltyTheatr> getMltyTheatrs() {
		return this.mltyTheatrs;
	}
	public void setMltyTheatrs(java.util.Set<MltyTheatr> mltyTheatrs) {
		this.mltyTheatrs = mltyTheatrs;
	}

	//bi-directional many-to-one association to MltyVsiPay
	@OneToMany(mappedBy="mltyPerson", fetch=FetchType.LAZY)
	public java.util.Set<MltyVsiPay> getMltyVsiPays() {
		return this.mltyVsiPays;
	}
	public void setMltyVsiPays(java.util.Set<MltyVsiPay> mltyVsiPays) {
		this.mltyVsiPays = mltyVsiPays;
	}

}