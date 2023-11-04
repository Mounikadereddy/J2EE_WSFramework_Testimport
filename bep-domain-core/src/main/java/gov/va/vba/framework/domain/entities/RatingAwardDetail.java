package gov.va.vba.framework.domain.entities;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the RATING_AWARD_DETAIL database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="RATING_AWARD_DETAIL")
public class RatingAwardDetail implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private RatingAwardDetailPK compId;
	private String compSvcPeriodTypeCd;
	private String hsptlInd;
	private String indvdlUnemplInd;
	private Date invldDecnDt;
	private Double nscBlatrlNbr;
	private Integer nscCombndPctNbr;
	private String paySmcTypeCd;
	private String pensnSvcPeriodTypeCd;
	private String permntTotalInd;
	private Integer prcntWthoutRecaNbr;
	private Integer prcntWthoutSevrncNbr;
	private Integer prcntWthoutTortNbr;
	private Integer prcntWthoutWkcompNbr;
	private Integer recaWthldgPctNbr;
	private Double scBlatrlNbr;
	private Integer scCombndPctNbr;
	private Integer sevrncWthldgPctNbr;
	private String smcTypeCd;
	private String smpTxt;
	private Integer tortWthldgPctNbr;
	private Integer wkcompWthldgPctNbr;
	private Person person1;
	private Person person2;
	private RatingAwardSumry ratingAwardSumry;
	private Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public RatingAwardDetail() {
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
	public RatingAwardDetailPK getCompId() {
		return this.compId;
	}
	public void setCompId(RatingAwardDetailPK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="COMP_SVC_PERIOD_TYPE_CD", length=12)
	public String getCompSvcPeriodTypeCd() {
		return this.compSvcPeriodTypeCd;
	}
	public void setCompSvcPeriodTypeCd(String compSvcPeriodTypeCd) {
		this.compSvcPeriodTypeCd = compSvcPeriodTypeCd;
	}

	@Basic()
	@Column(name="HSPTL_IND", length=1)
	public String getHsptlInd() {
		return this.hsptlInd;
	}
	public void setHsptlInd(String hsptlInd) {
		this.hsptlInd = hsptlInd;
	}

	@Basic()
	@Column(name="INDVDL_UNEMPL_IND", length=1)
	public String getIndvdlUnemplInd() {
		return this.indvdlUnemplInd;
	}
	public void setIndvdlUnemplInd(String indvdlUnemplInd) {
		this.indvdlUnemplInd = indvdlUnemplInd;
	}

	@Basic()
	@Column(name="INVLD_DECN_DT", length=7)
	public Date getInvldDecnDt() {
		return this.invldDecnDt;
	}
	public void setInvldDecnDt(Date invldDecnDt) {
		this.invldDecnDt = invldDecnDt;
	}

	@Basic()
	@Column(name="NSC_BLATRL_NBR", precision=5, scale=2)
	public Double getNscBlatrlNbr() {
		return this.nscBlatrlNbr;
	}
	public void setNscBlatrlNbr(Double nscBlatrlNbr) {
		this.nscBlatrlNbr = nscBlatrlNbr;
	}

	@Basic()
	@Column(name="NSC_COMBND_PCT_NBR", precision=3)
	public Integer getNscCombndPctNbr() {
		return this.nscCombndPctNbr;
	}
	public void setNscCombndPctNbr(Integer nscCombndPctNbr) {
		this.nscCombndPctNbr = nscCombndPctNbr;
	}

	@Basic()
	@Column(name="PAY_SMC_TYPE_CD", length=12)
	public String getPaySmcTypeCd() {
		return this.paySmcTypeCd;
	}
	public void setPaySmcTypeCd(String paySmcTypeCd) {
		this.paySmcTypeCd = paySmcTypeCd;
	}

	@Basic()
	@Column(name="PENSN_SVC_PERIOD_TYPE_CD", length=12)
	public String getPensnSvcPeriodTypeCd() {
		return this.pensnSvcPeriodTypeCd;
	}
	public void setPensnSvcPeriodTypeCd(String pensnSvcPeriodTypeCd) {
		this.pensnSvcPeriodTypeCd = pensnSvcPeriodTypeCd;
	}

	@Basic()
	@Column(name="PERMNT_TOTAL_IND", length=1)
	public String getPermntTotalInd() {
		return this.permntTotalInd;
	}
	public void setPermntTotalInd(String permntTotalInd) {
		this.permntTotalInd = permntTotalInd;
	}

	@Basic()
	@Column(name="PRCNT_WTHOUT_RECA_NBR", precision=3)
	public Integer getPrcntWthoutRecaNbr() {
		return this.prcntWthoutRecaNbr;
	}
	public void setPrcntWthoutRecaNbr(Integer prcntWthoutRecaNbr) {
		this.prcntWthoutRecaNbr = prcntWthoutRecaNbr;
	}

	@Basic()
	@Column(name="PRCNT_WTHOUT_SEVRNC_NBR", precision=3)
	public Integer getPrcntWthoutSevrncNbr() {
		return this.prcntWthoutSevrncNbr;
	}
	public void setPrcntWthoutSevrncNbr(Integer prcntWthoutSevrncNbr) {
		this.prcntWthoutSevrncNbr = prcntWthoutSevrncNbr;
	}

	@Basic()
	@Column(name="PRCNT_WTHOUT_TORT_NBR", precision=3)
	public Integer getPrcntWthoutTortNbr() {
		return this.prcntWthoutTortNbr;
	}
	public void setPrcntWthoutTortNbr(Integer prcntWthoutTortNbr) {
		this.prcntWthoutTortNbr = prcntWthoutTortNbr;
	}

	@Basic()
	@Column(name="PRCNT_WTHOUT_WKCOMP_NBR", precision=3)
	public Integer getPrcntWthoutWkcompNbr() {
		return this.prcntWthoutWkcompNbr;
	}
	public void setPrcntWthoutWkcompNbr(Integer prcntWthoutWkcompNbr) {
		this.prcntWthoutWkcompNbr = prcntWthoutWkcompNbr;
	}

	@Basic()
	@Column(name="RECA_WTHLDG_PCT_NBR", precision=3)
	public Integer getRecaWthldgPctNbr() {
		return this.recaWthldgPctNbr;
	}
	public void setRecaWthldgPctNbr(Integer recaWthldgPctNbr) {
		this.recaWthldgPctNbr = recaWthldgPctNbr;
	}

	@Basic()
	@Column(name="SC_BLATRL_NBR", precision=5, scale=2)
	public Double getScBlatrlNbr() {
		return this.scBlatrlNbr;
	}
	public void setScBlatrlNbr(Double scBlatrlNbr) {
		this.scBlatrlNbr = scBlatrlNbr;
	}

	@Basic()
	@Column(name="SC_COMBND_PCT_NBR", precision=3)
	public Integer getScCombndPctNbr() {
		return this.scCombndPctNbr;
	}
	public void setScCombndPctNbr(Integer scCombndPctNbr) {
		this.scCombndPctNbr = scCombndPctNbr;
	}

	@Basic()
	@Column(name="SEVRNC_WTHLDG_PCT_NBR", precision=3)
	public Integer getSevrncWthldgPctNbr() {
		return this.sevrncWthldgPctNbr;
	}
	public void setSevrncWthldgPctNbr(Integer sevrncWthldgPctNbr) {
		this.sevrncWthldgPctNbr = sevrncWthldgPctNbr;
	}

	@Basic()
	@Column(name="SMC_TYPE_CD", length=12)
	public String getSmcTypeCd() {
		return this.smcTypeCd;
	}
	public void setSmcTypeCd(String smcTypeCd) {
		this.smcTypeCd = smcTypeCd;
	}

	@Basic()
	@Column(name="SMP_TXT", length=4)
	public String getSmpTxt() {
		return this.smpTxt;
	}
	public void setSmpTxt(String smpTxt) {
		this.smpTxt = smpTxt;
	}

	@Basic()
	@Column(name="TORT_WTHLDG_PCT_NBR", precision=3)
	public Integer getTortWthldgPctNbr() {
		return this.tortWthldgPctNbr;
	}
	public void setTortWthldgPctNbr(Integer tortWthldgPctNbr) {
		this.tortWthldgPctNbr = tortWthldgPctNbr;
	}

	@Basic()
	@Column(name="WKCOMP_WTHLDG_PCT_NBR", precision=3)
	public Integer getWkcompWthldgPctNbr() {
		return this.wkcompWthldgPctNbr;
	}
	public void setWkcompWthldgPctNbr(Integer wkcompWthldgPctNbr) {
		this.wkcompWthldgPctNbr = wkcompWthldgPctNbr;
	}

	//bi-directional many-to-one association to Person
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PTCPNT_BENE_ID", referencedColumnName="PTCPNT_ID", nullable=false, insertable=false, updatable=false)
	public Person getPerson1() {
		return this.person1;
	}
	public void setPerson1(Person person1) {
		this.person1 = person1;
	}

	//bi-directional many-to-one association to Person
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PTCPNT_VET_ID", referencedColumnName="PTCPNT_ID", nullable=false, insertable=false, updatable=false)
	public Person getPerson2() {
		return this.person2;
	}
	public void setPerson2(Person person2) {
		this.person2 = person2;
	}

	//bi-directional many-to-one association to RatingAwardSumry
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="RATING_AWARD_SUMRY_ID", referencedColumnName="RATING_AWARD_SUMRY_ID", nullable=false, insertable=false, updatable=false)
	public RatingAwardSumry getRatingAwardSumry() {
		return this.ratingAwardSumry;
	}
	public void setRatingAwardSumry(RatingAwardSumry ratingAwardSumry) {
		this.ratingAwardSumry = ratingAwardSumry;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RatingAwardDetail)) {
			return false;
		}
		RatingAwardDetail castOther = (RatingAwardDetail)other;
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