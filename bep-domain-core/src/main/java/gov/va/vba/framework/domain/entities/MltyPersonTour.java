package gov.va.vba.framework.domain.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the MLTY_PERSON_TOUR database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="MLTY_PERSON_TOUR")
public class MltyPersonTour implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private MltyPersonTourPK compId;
	private Integer daysActiveQty;
	private String dschrgPayGradeNm;
	private java.sql.Date eodDt;
	private Integer lostTimeDaysNbr;
	private String mltyBranchInd;
	private String mltyDutyVaPurposTypeCd;
	private String mltySeprtnNarrtvTypeCd;
	private String mltySeprtnReasonTypeNm;
	private String mltySvcBranchTypeNm;
	private String mltySvcOtherBranchTypeNm;
	private String mltyTourSvcStatusTypeNm;
	private String mpDschrgAuthtyTypeNm;
	private String mpDschrgCharTypeNm;
	private String payGradeTypeNm;
	private java.sql.Date radDt;
	private java.sql.Date resrvOblgtnTrmntnDt;
	private java.sql.Date sixYearOblgtnDt;
	private String svcNbr;
	private String travelTimeDaysNbr;
	private String travelTimeVerifdInd;
	private String vadsCd;
	private String varInd;
	private String verifdInd;
	private String warTimeSvcCntryNm;
	private String warTimeSvcInd;
	private MltyPerson mltyPerson;
	private java.util.Set<MltyTheatr> mltyTheatrs;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public MltyPersonTour() {
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
	public MltyPersonTourPK getCompId() {
		return this.compId;
	}
	public void setCompId(MltyPersonTourPK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="DAYS_ACTIVE_QTY", precision=5)
	public Integer getDaysActiveQty() {
		return this.daysActiveQty;
	}
	public void setDaysActiveQty(Integer daysActiveQty) {
		this.daysActiveQty = daysActiveQty;
	}

	@Basic()
	@Column(name="DSCHRG_PAY_GRADE_NM", length=4)
	public String getDschrgPayGradeNm() {
		return this.dschrgPayGradeNm;
	}
	public void setDschrgPayGradeNm(String dschrgPayGradeNm) {
		this.dschrgPayGradeNm = dschrgPayGradeNm;
	}

	@Basic()
	@Column(name="EOD_DT", length=7)
	public java.sql.Date getEodDt() {
		return this.eodDt;
	}
	public void setEodDt(java.sql.Date eodDt) {
		this.eodDt = eodDt;
	}

	@Basic()
	@Column(name="LOST_TIME_DAYS_NBR", precision=6)
	public Integer getLostTimeDaysNbr() {
		return this.lostTimeDaysNbr;
	}
	public void setLostTimeDaysNbr(Integer lostTimeDaysNbr) {
		this.lostTimeDaysNbr = lostTimeDaysNbr;
	}

	@Basic()
	@Column(name="MLTY_BRANCH_IND", length=1)
	public String getMltyBranchInd() {
		return this.mltyBranchInd;
	}
	public void setMltyBranchInd(String mltyBranchInd) {
		this.mltyBranchInd = mltyBranchInd;
	}

	@Basic()
	@Column(name="MLTY_DUTY_VA_PURPOS_TYPE_CD", length=12)
	public String getMltyDutyVaPurposTypeCd() {
		return this.mltyDutyVaPurposTypeCd;
	}
	public void setMltyDutyVaPurposTypeCd(String mltyDutyVaPurposTypeCd) {
		this.mltyDutyVaPurposTypeCd = mltyDutyVaPurposTypeCd;
	}

	@Basic()
	@Column(name="MLTY_SEPRTN_NARRTV_TYPE_CD", length=12)
	public String getMltySeprtnNarrtvTypeCd() {
		return this.mltySeprtnNarrtvTypeCd;
	}
	public void setMltySeprtnNarrtvTypeCd(String mltySeprtnNarrtvTypeCd) {
		this.mltySeprtnNarrtvTypeCd = mltySeprtnNarrtvTypeCd;
	}

	@Basic()
	@Column(name="MLTY_SEPRTN_REASON_TYPE_NM", length=50)
	public String getMltySeprtnReasonTypeNm() {
		return this.mltySeprtnReasonTypeNm;
	}
	public void setMltySeprtnReasonTypeNm(String mltySeprtnReasonTypeNm) {
		this.mltySeprtnReasonTypeNm = mltySeprtnReasonTypeNm;
	}

	@Basic()
	@Column(name="MLTY_SVC_BRANCH_TYPE_NM", length=50)
	public String getMltySvcBranchTypeNm() {
		return this.mltySvcBranchTypeNm;
	}
	public void setMltySvcBranchTypeNm(String mltySvcBranchTypeNm) {
		this.mltySvcBranchTypeNm = mltySvcBranchTypeNm;
	}

	@Basic()
	@Column(name="MLTY_SVC_OTHER_BRANCH_TYPE_NM", length=50)
	public String getMltySvcOtherBranchTypeNm() {
		return this.mltySvcOtherBranchTypeNm;
	}
	public void setMltySvcOtherBranchTypeNm(String mltySvcOtherBranchTypeNm) {
		this.mltySvcOtherBranchTypeNm = mltySvcOtherBranchTypeNm;
	}

	@Basic()
	@Column(name="MLTY_TOUR_SVC_STATUS_TYPE_NM", length=50)
	public String getMltyTourSvcStatusTypeNm() {
		return this.mltyTourSvcStatusTypeNm;
	}
	public void setMltyTourSvcStatusTypeNm(String mltyTourSvcStatusTypeNm) {
		this.mltyTourSvcStatusTypeNm = mltyTourSvcStatusTypeNm;
	}

	@Basic()
	@Column(name="MP_DSCHRG_AUTHTY_TYPE_NM", length=50)
	public String getMpDschrgAuthtyTypeNm() {
		return this.mpDschrgAuthtyTypeNm;
	}
	public void setMpDschrgAuthtyTypeNm(String mpDschrgAuthtyTypeNm) {
		this.mpDschrgAuthtyTypeNm = mpDschrgAuthtyTypeNm;
	}

	@Basic()
	@Column(name="MP_DSCHRG_CHAR_TYPE_NM", length=50)
	public String getMpDschrgCharTypeNm() {
		return this.mpDschrgCharTypeNm;
	}
	public void setMpDschrgCharTypeNm(String mpDschrgCharTypeNm) {
		this.mpDschrgCharTypeNm = mpDschrgCharTypeNm;
	}

	@Basic()
	@Column(name="PAY_GRADE_TYPE_NM", length=50)
	public String getPayGradeTypeNm() {
		return this.payGradeTypeNm;
	}
	public void setPayGradeTypeNm(String payGradeTypeNm) {
		this.payGradeTypeNm = payGradeTypeNm;
	}

	@Basic()
	@Column(name="RAD_DT", length=7)
	public java.sql.Date getRadDt() {
		return this.radDt;
	}
	public void setRadDt(java.sql.Date radDt) {
		this.radDt = radDt;
	}

	@Basic()
	@Column(name="RESRV_OBLGTN_TRMNTN_DT", length=7)
	public java.sql.Date getResrvOblgtnTrmntnDt() {
		return this.resrvOblgtnTrmntnDt;
	}
	public void setResrvOblgtnTrmntnDt(java.sql.Date resrvOblgtnTrmntnDt) {
		this.resrvOblgtnTrmntnDt = resrvOblgtnTrmntnDt;
	}

	@Basic()
	@Column(name="SIX_YEAR_OBLGTN_DT", length=7)
	public java.sql.Date getSixYearOblgtnDt() {
		return this.sixYearOblgtnDt;
	}
	public void setSixYearOblgtnDt(java.sql.Date sixYearOblgtnDt) {
		this.sixYearOblgtnDt = sixYearOblgtnDt;
	}

	@Basic()
	@Column(name="SVC_NBR", length=9)
	public String getSvcNbr() {
		return this.svcNbr;
	}
	public void setSvcNbr(String svcNbr) {
		this.svcNbr = svcNbr;
	}

	@Basic()
	@Column(name="TRAVEL_TIME_DAYS_NBR", length=5)
	public String getTravelTimeDaysNbr() {
		return this.travelTimeDaysNbr;
	}
	public void setTravelTimeDaysNbr(String travelTimeDaysNbr) {
		this.travelTimeDaysNbr = travelTimeDaysNbr;
	}

	@Basic()
	@Column(name="TRAVEL_TIME_VERIFD_IND", length=1)
	public String getTravelTimeVerifdInd() {
		return this.travelTimeVerifdInd;
	}
	public void setTravelTimeVerifdInd(String travelTimeVerifdInd) {
		this.travelTimeVerifdInd = travelTimeVerifdInd;
	}

	@Basic()
	@Column(name="VADS_CD", length=1)
	public String getVadsCd() {
		return this.vadsCd;
	}
	public void setVadsCd(String vadsCd) {
		this.vadsCd = vadsCd;
	}

	@Basic()
	@Column(name="VAR_IND", length=1)
	public String getVarInd() {
		return this.varInd;
	}
	public void setVarInd(String varInd) {
		this.varInd = varInd;
	}

	@Basic()
	@Column(name="VERIFD_IND", length=1)
	public String getVerifdInd() {
		return this.verifdInd;
	}
	public void setVerifdInd(String verifdInd) {
		this.verifdInd = verifdInd;
	}

	@Basic()
	@Column(name="WAR_TIME_SVC_CNTRY_NM", length=50)
	public String getWarTimeSvcCntryNm() {
		return this.warTimeSvcCntryNm;
	}
	public void setWarTimeSvcCntryNm(String warTimeSvcCntryNm) {
		this.warTimeSvcCntryNm = warTimeSvcCntryNm;
	}

	@Basic()
	@Column(name="WAR_TIME_SVC_IND", length=1)
	public String getWarTimeSvcInd() {
		return this.warTimeSvcInd;
	}
	public void setWarTimeSvcInd(String warTimeSvcInd) {
		this.warTimeSvcInd = warTimeSvcInd;
	}

	//bi-directional many-to-one association to MltyPerson
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PTCPNT_ID", referencedColumnName="PTCPNT_ID", nullable=false, insertable=false, updatable=false)
	public MltyPerson getMltyPerson() {
		return this.mltyPerson;
	}
	public void setMltyPerson(MltyPerson mltyPerson) {
		this.mltyPerson = mltyPerson;
	}

	//bi-directional many-to-one association to MltyTheatr
	@OneToMany(mappedBy="mltyPersonTour", fetch=FetchType.EAGER)
	public java.util.Set<MltyTheatr> getMltyTheatrs() {
		return this.mltyTheatrs;
	}
	public void setMltyTheatrs(java.util.Set<MltyTheatr> mltyTheatrs) {
		this.mltyTheatrs = mltyTheatrs;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MltyPersonTour)) {
			return false;
		}
		MltyPersonTour castOther = (MltyPersonTour)other;
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