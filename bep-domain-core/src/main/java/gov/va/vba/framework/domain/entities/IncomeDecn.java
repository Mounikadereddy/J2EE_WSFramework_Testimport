package gov.va.vba.framework.domain.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the INCOME_DECN database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@SqlResultSetMapping(name="incomeDecnMapping", entities={@EntityResult(entityClass=IncomeDecn.class )})
@Table(name="INCOME_DECN")
public class IncomeDecn implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private IncomeDecnPK compId;
	private Double annualAmt;
	private Double calndrYearCntblAmt;
	private Double calndrYearReprtdAmt;
	private Long decnId;
	private java.sql.Timestamp endDt;
	private Double exclsnAmt;
	private String exclsnTypeCd;
	private java.sql.Timestamp invldDecnDt;
	private Double mthlyReprtdAmt;
	private Double reprtdAmt;
	private Double usedAmt;
	private Award award;
	private Ptcpnt ptcpnt;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public IncomeDecn() {
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
	public IncomeDecnPK getCompId() {
		return this.compId;
	}
	public void setCompId(IncomeDecnPK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="ANNUAL_AMT", precision=15, scale=2)
	public Double getAnnualAmt() {
		return this.annualAmt;
	}
	public void setAnnualAmt(Double annualAmt) {
		this.annualAmt = annualAmt;
	}

	@Basic()
	@Column(name="CALNDR_YEAR_CNTBL_AMT", precision=15, scale=2)
	public Double getCalndrYearCntblAmt() {
		return this.calndrYearCntblAmt;
	}
	public void setCalndrYearCntblAmt(Double calndrYearCntblAmt) {
		this.calndrYearCntblAmt = calndrYearCntblAmt;
	}

	@Basic()
	@Column(name="CALNDR_YEAR_REPRTD_AMT", precision=15, scale=2)
	public Double getCalndrYearReprtdAmt() {
		return this.calndrYearReprtdAmt;
	}
	public void setCalndrYearReprtdAmt(Double calndrYearReprtdAmt) {
		this.calndrYearReprtdAmt = calndrYearReprtdAmt;
	}

	@Basic()
	@Column(name="DECN_ID", unique=true, precision=15)
	public Long getDecnId() {
		return this.decnId;
	}
	public void setDecnId(Long decnId) {
		this.decnId = decnId;
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
	@Column(name="EXCLSN_AMT", precision=15, scale=2)
	public Double getExclsnAmt() {
		return this.exclsnAmt;
	}
	public void setExclsnAmt(Double exclsnAmt) {
		this.exclsnAmt = exclsnAmt;
	}

	@Basic()
	@Column(name="EXCLSN_TYPE_CD", length=12)
	public String getExclsnTypeCd() {
		return this.exclsnTypeCd;
	}
	public void setExclsnTypeCd(String exclsnTypeCd) {
		this.exclsnTypeCd = exclsnTypeCd;
	}

	@Basic()
	@Column(name="INVLD_DECN_DT", length=7)
	public java.sql.Timestamp getInvldDecnDt() {
		return this.invldDecnDt;
	}
	public void setInvldDecnDt(java.sql.Timestamp invldDecnDt) {
		this.invldDecnDt = invldDecnDt;
	}

	@Basic()
	@Column(name="MTHLY_REPRTD_AMT", precision=15, scale=2)
	public Double getMthlyReprtdAmt() {
		return this.mthlyReprtdAmt;
	}
	public void setMthlyReprtdAmt(Double mthlyReprtdAmt) {
		this.mthlyReprtdAmt = mthlyReprtdAmt;
	}

	@Basic()
	@Column(name="REPRTD_AMT", precision=15, scale=2)
	public Double getReprtdAmt() {
		return this.reprtdAmt;
	}
	public void setReprtdAmt(Double reprtdAmt) {
		this.reprtdAmt = reprtdAmt;
	}

	@Basic()
	@Column(name="USED_AMT", precision=15, scale=2)
	public Double getUsedAmt() {
		return this.usedAmt;
	}
	public void setUsedAmt(Double usedAmt) {
		this.usedAmt = usedAmt;
	}

	//bi-directional many-to-one association to Award
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="PTCPNT_ID_A", referencedColumnName="PTCPNT_ID_A", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="PTCPNT_ID_B", referencedColumnName="PTCPNT_ID_B", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="AWARD_TYPE_CD", referencedColumnName="AWARD_TYPE_CD", nullable=false, insertable=false, updatable=false)})
	public Award getAward() {
		return this.award;
	}
	public void setAward(Award award) {
		this.award = award;
	}

	//bi-directional many-to-one association to Ptcpnt
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PTCPNT_ID_C", referencedColumnName="PTCPNT_ID", nullable=false, insertable=false, updatable=false)
	public Ptcpnt getPtcpnt() {
		return this.ptcpnt;
	}
	public void setPtcpnt(Ptcpnt ptcpnt) {
		this.ptcpnt = ptcpnt;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof IncomeDecn)) {
			return false;
		}
		IncomeDecn castOther = (IncomeDecn)other;
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