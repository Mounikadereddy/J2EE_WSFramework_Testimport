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
 * The persistent class for the PTCPNT_ALIAS database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="PTCPNT_ALIAS")
public class PtcpntAlia implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private PtcpntAliaPK compId;
	private String birlsGenrtdNbr;
	private Date endDt;
	private String firstNm;
	private String lastNm;
	private String middleNm;
	private String orgNm;
	private String slttnTypeNm;
	private String suffixNm;
	private Ptcpnt ptcpnt;
	private Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public PtcpntAlia() {
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
	public PtcpntAliaPK getCompId() {
		return this.compId;
	}
	public void setCompId(PtcpntAliaPK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="BIRLS_GENRTD_NBR", length=8)
	public String getBirlsGenrtdNbr() {
		return this.birlsGenrtdNbr;
	}
	public void setBirlsGenrtdNbr(String birlsGenrtdNbr) {
		this.birlsGenrtdNbr = birlsGenrtdNbr;
	}

	@Basic()
	@Column(name="END_DT", length=7)
	public Date getEndDt() {
		return this.endDt;
	}
	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}

	@Basic()
	@Column(name="FIRST_NM", length=30)
	public String getFirstNm() {
		return this.firstNm;
	}
	public void setFirstNm(String firstNm) {
		this.firstNm = firstNm;
	}

	@Basic()
	@Column(name="LAST_NM", length=30)
	public String getLastNm() {
		return this.lastNm;
	}
	public void setLastNm(String lastNm) {
		this.lastNm = lastNm;
	}

	@Basic()
	@Column(name="MIDDLE_NM", length=30)
	public String getMiddleNm() {
		return this.middleNm;
	}
	public void setMiddleNm(String middleNm) {
		this.middleNm = middleNm;
	}

	@Basic()
	@Column(name="ORG_NM", length=50)
	public String getOrgNm() {
		return this.orgNm;
	}
	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}

	@Basic()
	@Column(name="SLTTN_TYPE_NM", length=50)
	public String getSlttnTypeNm() {
		return this.slttnTypeNm;
	}
	public void setSlttnTypeNm(String slttnTypeNm) {
		this.slttnTypeNm = slttnTypeNm;
	}

	@Basic()
	@Column(name="SUFFIX_NM", length=15)
	public String getSuffixNm() {
		return this.suffixNm;
	}
	public void setSuffixNm(String suffixNm) {
		this.suffixNm = suffixNm;
	}

	//bi-directional many-to-one association to Ptcpnt
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PTCPNT_ID", referencedColumnName="PTCPNT_ID", nullable=false, insertable=false, updatable=false)
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
		if (!(other instanceof PtcpntAlia)) {
			return false;
		}
		PtcpntAlia castOther = (PtcpntAlia)other;
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