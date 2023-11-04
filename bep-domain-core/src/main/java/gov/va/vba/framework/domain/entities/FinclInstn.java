package gov.va.vba.framework.domain.entities;
import gov.va.vba.framework.common.DateAdapter;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the FINCL_INSTN database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="FINCL_INSTN")
public class FinclInstn implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long ptcpntId;
	private String finclInstnTypeNm;
	private Date inactvDt;
	private Date lastChangeDt;
	private String routngTrnsitNbr;
	private CntryType cntryType;
	private Org org;
//	private Lender lender;
	private java.util.Set<PtcpntDpositAcnt> ptcpntDpositAcnts;
//	private SvcrHolder svcrHolder;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public FinclInstn() {
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
	@Column(name="PTCPNT_ID", unique=true, nullable=false, precision=15)
	public Long getPtcpntId() {
		return this.ptcpntId;
	}
	public void setPtcpntId(Long ptcpntId) {
		this.ptcpntId = ptcpntId;
	}

	@Basic()
	@Column(name="FINCL_INSTN_TYPE_NM", nullable=false, length=50)
	public String getFinclInstnTypeNm() {
		return this.finclInstnTypeNm;
	}
	public void setFinclInstnTypeNm(String finclInstnTypeNm) {
		this.finclInstnTypeNm = finclInstnTypeNm;
	}

	@Basic()
	@Column(name="INACTV_DT", length=7)
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getInactvDt() {
		return this.inactvDt;
	}
	public void setInactvDt(Date inactvDt) {
		this.inactvDt = inactvDt;
	}

	@Basic()
	@Column(name="LAST_CHANGE_DT", length=7)
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getLastChangeDt() {
		return this.lastChangeDt;
	}
	public void setLastChangeDt(Date lastChangeDt) {
		this.lastChangeDt = lastChangeDt;
	}

	@Basic()
	@Column(name="ROUTNG_TRNSIT_NBR", length=9)
	public String getRoutngTrnsitNbr() {
		return this.routngTrnsitNbr;
	}
	public void setRoutngTrnsitNbr(String routngTrnsitNbr) {
		this.routngTrnsitNbr = routngTrnsitNbr;
	}

	//bi-directional many-to-one association to CntryType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CNTRY_TYPE_NM", referencedColumnName="CNTRY_TYPE_NM")
	public CntryType getCntryType() {
		return this.cntryType;
	}
	public void setCntryType(CntryType cntryType) {
		this.cntryType = cntryType;
	}

	//bi-directional one-to-one association to Org
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PTCPNT_ID", referencedColumnName="PTCPNT_ID", nullable=false, insertable=false, updatable=false)
	public Org getOrg() {
		return this.org;
	}
	public void setOrg(Org org) {
		this.org = org;
	}

//	//bi-directional one-to-one association to Lender
//	@OneToOne(mappedBy="finclInstn", fetch=FetchType.LAZY)
//	public Lender getLender() {
//		return this.lender;
//	}
//	public void setLender(Lender lender) {
//		this.lender = lender;
//	}

	//bi-directional many-to-one association to PtcpntDpositAcnt
	@OneToMany(mappedBy="finclInstn", fetch=FetchType.LAZY)
	public java.util.Set<PtcpntDpositAcnt> getPtcpntDpositAcnts() {
		return this.ptcpntDpositAcnts;
	}
	public void setPtcpntDpositAcnts(java.util.Set<PtcpntDpositAcnt> ptcpntDpositAcnts) {
		this.ptcpntDpositAcnts = ptcpntDpositAcnts;
	}

//	//bi-directional one-to-one association to SvcrHolder
//	@OneToOne(mappedBy="finclInstn", fetch=FetchType.LAZY)
//	public SvcrHolder getSvcrHolder() {
//		return this.svcrHolder;
//	}
//	public void setSvcrHolder(SvcrHolder svcrHolder) {
//		this.svcrHolder = svcrHolder;
//	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FinclInstn)) {
			return false;
		}
		FinclInstn castOther = (FinclInstn)other;
		return new EqualsBuilder()
			.append(this.getPtcpntId(), castOther.getPtcpntId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getPtcpntId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("ptcpntId", getPtcpntId())
			.toString();
	}
}