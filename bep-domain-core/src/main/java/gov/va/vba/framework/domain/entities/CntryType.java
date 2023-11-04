package gov.va.vba.framework.domain.entities;
import java.io.Serializable;

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
 * The persistent class for the CNTRY_TYPE database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="CNTRY_TYPE")
public class CntryType implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String cntryTypeNm;
	private String cntryTypeCd;
	private String descpTxt;
	private String eftCntryCd;
	private String eftCntryNm;
//	private java.util.Set<EduPersonCntryWrkld> eduPersonCntryWrklds;
	private java.util.Set<FinclInstn> finclInstns;
//	private java.util.Set<FmsIntrfcTran> fmsIntrfcTrans;
	private java.util.Set<PrprtyAddr> prprtyAddrs;
	private java.util.Set<PtcpntAddr> ptcpntAddrs;
//	private java.util.Set<StdDvlpmtCntct> stdDvlpmtCntcts;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public CntryType() {
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
	@Column(name="CNTRY_TYPE_NM", unique=true, nullable=false, length=50)
	public String getCntryTypeNm() {
		return this.cntryTypeNm;
	}
	public void setCntryTypeNm(String cntryTypeNm) {
		this.cntryTypeNm = cntryTypeNm;
	}

	@Basic()
	@Column(name="CNTRY_TYPE_CD", nullable=false, length=12)
	public String getCntryTypeCd() {
		return this.cntryTypeCd;
	}
	public void setCntryTypeCd(String cntryTypeCd) {
		this.cntryTypeCd = cntryTypeCd;
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
	@Column(name="EFT_CNTRY_CD", length=12)
	public String getEftCntryCd() {
		return this.eftCntryCd;
	}
	public void setEftCntryCd(String eftCntryCd) {
		this.eftCntryCd = eftCntryCd;
	}

	@Basic()
	@Column(name="EFT_CNTRY_NM", length=50)
	public String getEftCntryNm() {
		return this.eftCntryNm;
	}
	public void setEftCntryNm(String eftCntryNm) {
		this.eftCntryNm = eftCntryNm;
	}

//	//bi-directional many-to-one association to EduPersonCntryWrkld
//	@OneToMany(mappedBy="cntryType", fetch=FetchType.LAZY)
//	public java.util.Set<EduPersonCntryWrkld> getEduPersonCntryWrklds() {
//		return this.eduPersonCntryWrklds;
//	}
//	public void setEduPersonCntryWrklds(java.util.Set<EduPersonCntryWrkld> eduPersonCntryWrklds) {
//		this.eduPersonCntryWrklds = eduPersonCntryWrklds;
//	}

	//bi-directional many-to-one association to FinclInstn
	@OneToMany(mappedBy="cntryType", fetch=FetchType.LAZY)
	public java.util.Set<FinclInstn> getFinclInstns() {
		return this.finclInstns;
	}
	public void setFinclInstns(java.util.Set<FinclInstn> finclInstns) {
		this.finclInstns = finclInstns;
	}

//	//bi-directional many-to-one association to FmsIntrfcTran
//	@OneToMany(mappedBy="cntryType", fetch=FetchType.LAZY)
//	public java.util.Set<FmsIntrfcTran> getFmsIntrfcTrans() {
//		return this.fmsIntrfcTrans;
//	}
//	public void setFmsIntrfcTrans(java.util.Set<FmsIntrfcTran> fmsIntrfcTrans) {
//		this.fmsIntrfcTrans = fmsIntrfcTrans;
//	}

	//bi-directional many-to-one association to PrprtyAddr
	@OneToMany(mappedBy="cntryType", fetch=FetchType.LAZY)
	public java.util.Set<PrprtyAddr> getPrprtyAddrs() {
		return this.prprtyAddrs;
	}
	public void setPrprtyAddrs(java.util.Set<PrprtyAddr> prprtyAddrs) {
		this.prprtyAddrs = prprtyAddrs;
	}

	//bi-directional many-to-one association to PtcpntAddr
	@OneToMany(mappedBy="cntryType", fetch=FetchType.LAZY)
	public java.util.Set<PtcpntAddr> getPtcpntAddrs() {
		return this.ptcpntAddrs;
	}
	public void setPtcpntAddrs(java.util.Set<PtcpntAddr> ptcpntAddrs) {
		this.ptcpntAddrs = ptcpntAddrs;
	}

//	//bi-directional many-to-one association to StdDvlpmtCntct
//	@OneToMany(mappedBy="cntryType", fetch=FetchType.LAZY)
//	public java.util.Set<StdDvlpmtCntct> getStdDvlpmtCntcts() {
//		return this.stdDvlpmtCntcts;
//	}
//	public void setStdDvlpmtCntcts(java.util.Set<StdDvlpmtCntct> stdDvlpmtCntcts) {
//		this.stdDvlpmtCntcts = stdDvlpmtCntcts;
//	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CntryType)) {
			return false;
		}
		CntryType castOther = (CntryType)other;
		return new EqualsBuilder()
			.append(this.getCntryTypeNm(), castOther.getCntryTypeNm())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCntryTypeNm())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("cntryTypeNm", getCntryTypeNm())
			.toString();
	}

}