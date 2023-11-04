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
 * The persistent class for the RBA_RATING_DETAIL database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="RBA_RATING_DETAIL")
public class RbaRatingDetail  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private RbaRatingDetailPK compId;
	private Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;
	private Double nscBlatrlNbr;
	private Integer nscCombndPctNbr;
	private Double scBlatrlNbr;
	private Integer scCombndPctNbr;
	private RbaPrfil rbaPrfil;

    public RbaRatingDetail() {
    }

	@EmbeddedId
	public RbaRatingDetailPK getCompId() {
		return this.compId;
	}
	public void setCompId(RbaRatingDetailPK compId) {
		this.compId = compId;
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

	//bi-directional many-to-one association to RbaPrfil
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="PTCPNT_VET_ID", referencedColumnName="PTCPNT_VET_ID", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="PRFIL_DT", referencedColumnName="PRFIL_DT", nullable=false, insertable=false, updatable=false)
		})
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
		if (!(other instanceof RbaRatingDetail)) {
			return false;
		}
		RbaRatingDetail castOther = (RbaRatingDetail)other;
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