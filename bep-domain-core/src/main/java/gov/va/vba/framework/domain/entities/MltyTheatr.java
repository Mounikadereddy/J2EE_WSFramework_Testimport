package gov.va.vba.framework.domain.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the MLTY_THEATR database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="MLTY_THEATR")
public class MltyTheatr implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long mltyTheatrId;
	private java.sql.Date beginDt;
	private Integer daysQty;
	private java.sql.Date endDt;
	private String mltyTheatrTypeNm;
	private String verifdInd;
	private MltyPerson mltyPerson;
	private MltyPersonTour mltyPersonTour;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public MltyTheatr() {
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
	@Column(name="MLTY_THEATR_ID", unique=true, nullable=false, precision=15)
	public Long getMltyTheatrId() {
		return this.mltyTheatrId;
	}
	public void setMltyTheatrId(Long mltyTheatrId) {
		this.mltyTheatrId = mltyTheatrId;
	}

	@Basic()
	@Column(name="BEGIN_DT", length=7)
	public java.sql.Date getBeginDt() {
		return this.beginDt;
	}
	public void setBeginDt(java.sql.Date beginDt) {
		this.beginDt = beginDt;
	}

	@Basic()
	@Column(name="DAYS_QTY", precision=6)
	public Integer getDaysQty() {
		return this.daysQty;
	}
	public void setDaysQty(Integer daysQty) {
		this.daysQty = daysQty;
	}

	@Basic()
	@Column(name="END_DT", length=7)
	public java.sql.Date getEndDt() {
		return this.endDt;
	}
	public void setEndDt(java.sql.Date endDt) {
		this.endDt = endDt;
	}

	@Basic()
	@Column(name="MLTY_THEATR_TYPE_NM", nullable=false, length=50)
	public String getMltyTheatrTypeNm() {
		return this.mltyTheatrTypeNm;
	}
	public void setMltyTheatrTypeNm(String mltyTheatrTypeNm) {
		this.mltyTheatrTypeNm = mltyTheatrTypeNm;
	}

	@Basic()
	@Column(name="VERIFD_IND", nullable=false, length=1)
	public String getVerifdInd() {
		return this.verifdInd;
	}
	public void setVerifdInd(String verifdInd) {
		this.verifdInd = verifdInd;
	}

	//bi-directional many-to-one association to MltyPerson
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PTCPNT_ID", referencedColumnName="PTCPNT_ID", nullable=false)
	public MltyPerson getMltyPerson() {
		return this.mltyPerson;
	}
	public void setMltyPerson(MltyPerson mltyPerson) {
		this.mltyPerson = mltyPerson;
	}

	//bi-directional many-to-one association to MltyPersonTour
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name="PTCPNT_ID", referencedColumnName="PTCPNT_ID", nullable=false),
		@JoinColumn(name="MLTY_PERSON_TOUR_NBR", referencedColumnName="MLTY_PERSON_TOUR_NBR")})
	public MltyPersonTour getMltyPersonTour() {
		return this.mltyPersonTour;
	}
	public void setMltyPersonTour(MltyPersonTour mltyPersonTour) {
		this.mltyPersonTour = mltyPersonTour;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MltyTheatr)) {
			return false;
		}
		MltyTheatr castOther = (MltyTheatr)other;
		return new EqualsBuilder()
			.append(this.getMltyTheatrId(), castOther.getMltyTheatrId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getMltyTheatrId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("mltyTheatrId", getMltyTheatrId())
			.toString();
	}
}