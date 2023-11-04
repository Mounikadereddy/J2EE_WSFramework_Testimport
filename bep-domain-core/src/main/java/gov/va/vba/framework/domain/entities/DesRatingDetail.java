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
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the DES_RATING_DETAIL database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="DES_RATING_DETAIL")
public class DesRatingDetail implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private DesRatingDetailPK compId;
	private Double desScBlatrlNbr;
	private Integer desScCombndPct;
	private Double pebReferdBlatrlNbr;
	private Integer pebReferdCombndPct;
	private RbaPrfil rbaPrfil;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public DesRatingDetail() {
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
	public DesRatingDetailPK getCompId() {
		return this.compId;
	}
	public void setCompId(DesRatingDetailPK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="DES_SC_BLATRL_NBR", precision=5, scale=2)
	public Double getDesScBlatrlNbr() {
		return this.desScBlatrlNbr;
	}
	public void setDesScBlatrlNbr(Double desScBlatrlNbr) {
		this.desScBlatrlNbr = desScBlatrlNbr;
	}

	@Basic()
	@Column(name="DES_SC_COMBND_PCT", precision=3)
	public Integer getDesScCombndPct() {
		return this.desScCombndPct;
	}
	public void setDesScCombndPct(Integer desScCombndPct) {
		this.desScCombndPct = desScCombndPct;
	}

	@Basic()
	@Column(name="PEB_REFERD_BLATRL_NBR", precision=5, scale=2)
	public Double getPebReferdBlatrlNbr() {
		return this.pebReferdBlatrlNbr;
	}
	public void setPebReferdBlatrlNbr(Double pebReferdBlatrlNbr) {
		this.pebReferdBlatrlNbr = pebReferdBlatrlNbr;
	}

	@Basic()
	@Column(name="PEB_REFERD_COMBND_PCT", precision=3)
	public Integer getPebReferdCombndPct() {
		return this.pebReferdCombndPct;
	}
	public void setPebReferdCombndPct(Integer pebReferdCombndPct) {
		this.pebReferdCombndPct = pebReferdCombndPct;
	}

	//bi-directional many-to-one association to RbaPrfil
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="PTCPNT_VET_ID", referencedColumnName="PTCPNT_VET_ID", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="PRFIL_DT", referencedColumnName="PRFIL_DT", nullable=false, insertable=false, updatable=false)})
	public RbaPrfil getRbaPrfil() {
		return this.rbaPrfil;
	}
	public void setRbaPrfil(RbaPrfil rbaPrfil) {
		this.rbaPrfil = rbaPrfil;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DesRatingDetail)) {
			return false;
		}
		DesRatingDetail castOther = (DesRatingDetail)other;
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