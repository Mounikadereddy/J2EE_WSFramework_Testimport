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
 * The persistent class for the BURIAL_SUMRY_DECN database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="BURIAL_SUMRY_DECN")
public class BurialSumryDecn implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private BurialSumryDecnPK compId;
	private Long decnId;
	private String dvlpForTrnspnInd;
	private Date finalDisptnDt;
	private Date invldDecnDt;
	private Date markerPrchsDt;
	private String scDeathDtrmtnDeferlInd;
	private Double totalBurialExpnsAmt;
	private Double trnspnExpnssAmt;
	private Award award;
    private Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public BurialSumryDecn() {
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
	public BurialSumryDecnPK getCompId() {
		return this.compId;
	}
	public void setCompId(BurialSumryDecnPK compId) {
		this.compId = compId;
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
	@Column(name="DVLP_FOR_TRNSPN_IND", nullable=false, length=1)
	public String getDvlpForTrnspnInd() {
		return this.dvlpForTrnspnInd;
	}
	public void setDvlpForTrnspnInd(String dvlpForTrnspnInd) {
		this.dvlpForTrnspnInd = dvlpForTrnspnInd;
	}

	@Basic()
	@Column(name="FINAL_DISPTN_DT", length=7)
	public Date getFinalDisptnDt() {
		return this.finalDisptnDt;
	}
	public void setFinalDisptnDt(Date finalDisptnDt) {
		this.finalDisptnDt = finalDisptnDt;
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
	@Column(name="MARKER_PRCHS_DT", length=7)
	public Date getMarkerPrchsDt() {
		return this.markerPrchsDt;
	}
	public void setMarkerPrchsDt(Date markerPrchsDt) {
		this.markerPrchsDt = markerPrchsDt;
	}

	@Basic()
	@Column(name="SC_DEATH_DTRMTN_DEFERL_IND", nullable=false, length=1)
	public String getScDeathDtrmtnDeferlInd() {
		return this.scDeathDtrmtnDeferlInd;
	}
	public void setScDeathDtrmtnDeferlInd(String scDeathDtrmtnDeferlInd) {
		this.scDeathDtrmtnDeferlInd = scDeathDtrmtnDeferlInd;
	}

	@Basic()
	@Column(name="TOTAL_BURIAL_EXPNS_AMT", precision=15, scale=2)
	public Double getTotalBurialExpnsAmt() {
		return this.totalBurialExpnsAmt;
	}
	public void setTotalBurialExpnsAmt(Double totalBurialExpnsAmt) {
		this.totalBurialExpnsAmt = totalBurialExpnsAmt;
	}

	@Basic()
	@Column(name="TRNSPN_EXPNSS_AMT", precision=15, scale=2)
	public Double getTrnspnExpnssAmt() {
		return this.trnspnExpnssAmt;
	}
	public void setTrnspnExpnssAmt(Double trnspnExpnssAmt) {
		this.trnspnExpnssAmt = trnspnExpnssAmt;
	}

	//bi-directional many-to-one association to Award
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="PTCPNT_ID_A", referencedColumnName="PTCPNT_ID_A", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="PTCPNT_ID_B", referencedColumnName="PTCPNT_ID_B", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="AWARD_TYPE_CD", referencedColumnName="AWARD_TYPE_CD", nullable=false, insertable=false, updatable=false)
		})
	public Award getAward() {
		return this.award;
	}
	public void setAward(Award award) {
		this.award = award;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BurialSumryDecn)) {
			return false;
		}
		BurialSumryDecn castOther = (BurialSumryDecn)other;
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