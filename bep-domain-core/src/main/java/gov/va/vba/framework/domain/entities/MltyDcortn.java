package gov.va.vba.framework.domain.entities;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the MLTY_DCORTN database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="MLTY_DCORTN")
public class MltyDcortn implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long mltyDcortnId;
	private Date dactvtDt;
	private String descpTxt;
	private String mltyDcortnCd;
	private String mltyDcortnNm;
	private String strsorInd;
	private java.util.Set<MltyPersonDcortn> mltyPersonDcortns;
	private Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public MltyDcortn() {
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
	
	@Id()
	@Column(name="MLTY_DCORTN_ID", unique=true, nullable=false, precision=15)
	public Long getMltyDcortnId() {
		return this.mltyDcortnId;
	}
	public void setMltyDcortnId(Long mltyDcortnId) {
		this.mltyDcortnId = mltyDcortnId;
	}

	@Basic()
	@Column(name="DACTVT_DT", length=7)
	public Date getDactvtDt() {
		return this.dactvtDt;
	}
	public void setDactvtDt(Date dactvtDt) {
		this.dactvtDt = dactvtDt;
	}

	@Basic()
	@Column(name="DESCP_TXT", length=500)
	public String getDescpTxt() {
		return this.descpTxt;
	}
	public void setDescpTxt(String descpTxt) {
		this.descpTxt = descpTxt;
	}

	@Basic()
	@Column(name="MLTY_DCORTN_CD", length=12)
	public String getMltyDcortnCd() {
		return this.mltyDcortnCd;
	}
	public void setMltyDcortnCd(String mltyDcortnCd) {
		this.mltyDcortnCd = mltyDcortnCd;
	}

	@Basic()
	@Column(name="MLTY_DCORTN_NM", nullable=false, length=50)
	public String getMltyDcortnNm() {
		return this.mltyDcortnNm;
	}
	public void setMltyDcortnNm(String mltyDcortnNm) {
		this.mltyDcortnNm = mltyDcortnNm;
	}

	@Basic()
	@Column(name="STRSOR_IND", length=1)
	public String getStrsorInd() {
		return this.strsorInd;
	}
	public void setStrsorInd(String strsorInd) {
		this.strsorInd = strsorInd;
	}

	//bi-directional many-to-one association to MltyPersonDcortn
	@OneToMany(mappedBy="mltyDcortn", fetch=FetchType.LAZY)
	public java.util.Set<MltyPersonDcortn> getMltyPersonDcortns() {
		return this.mltyPersonDcortns;
	}
	public void setMltyPersonDcortns(java.util.Set<MltyPersonDcortn> mltyPersonDcortns) {
		this.mltyPersonDcortns = mltyPersonDcortns;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MltyDcortn)) {
			return false;
		}
		MltyDcortn castOther = (MltyDcortn)other;
		return new EqualsBuilder()
			.append(this.getMltyDcortnId(), castOther.getMltyDcortnId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getMltyDcortnId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("mltyDcortnId", getMltyDcortnId())
			.toString();
	}
}