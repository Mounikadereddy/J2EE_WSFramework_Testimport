package gov.va.vba.framework.domain.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the ORG database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="ORG")
public class Org implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private OrgPK compId;
	private String cnsldtPayeeCd;
	private String extnlId;
	private String fnctnlDescpTxt;
	private String nm;
	private Long nmKey;
	private String organizationTypeNm;
	private String orgTypeNm;
	private String pocTxt;
	private String svcrIdfctnNbr;
	private Integer taxIdfctnNbr;
	private String titleTypeNm;
//	private java.util.Set<Cntrct> cntrcts;
//	private EduInstn eduInstn;
//	private EduPrvdr eduPrvdr;
	private FinclInstn finclInstn;
//	private InsCmpny insCmpny;
	private Ptcpnt ptcpnt;
	private java.util.Set<OrgRmk> orgRmks;
	private java.util.Set<PtcpntInstzn> ptcpntInstzns;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public Org() {
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
	public OrgPK getCompId() {
		return this.compId;
	}
	public void setCompId(OrgPK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="CNSLDT_PAYEE_CD", length=4)
	public String getCnsldtPayeeCd() {
		return this.cnsldtPayeeCd;
	}
	public void setCnsldtPayeeCd(String cnsldtPayeeCd) {
		this.cnsldtPayeeCd = cnsldtPayeeCd;
	}

	@Basic()
	@Column(name="EXTNL_ID", length=15)
	public String getExtnlId() {
		return this.extnlId;
	}
	public void setExtnlId(String extnlId) {
		this.extnlId = extnlId;
	}

	@Basic()
	@Column(name="FNCTNL_DESCP_TXT", length=254)
	public String getFnctnlDescpTxt() {
		return this.fnctnlDescpTxt;
	}
	public void setFnctnlDescpTxt(String fnctnlDescpTxt) {
		this.fnctnlDescpTxt = fnctnlDescpTxt;
	}

	@Basic()
	@Column(name="NM", nullable=false, length=50)
	public String getNm() {
		return this.nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}

	@Basic()
	@Column(name="NM_KEY", precision=22)
	public Long getNmKey() {
		return this.nmKey;
	}
	public void setNmKey(Long nmKey) {
		this.nmKey = nmKey;
	}

	@Basic()
	@Column(name="ORGANIZATION_TYPE_NM", nullable=false, length=50)
	public String getOrganizationTypeNm() {
		return this.organizationTypeNm;
	}
	public void setOrganizationTypeNm(String organizationTypeNm) {
		this.organizationTypeNm = organizationTypeNm;
	}

	@Basic()
	@Column(name="ORG_TYPE_NM", nullable=false, length=50)
	public String getOrgTypeNm() {
		return this.orgTypeNm;
	}
	public void setOrgTypeNm(String orgTypeNm) {
		this.orgTypeNm = orgTypeNm;
	}

	@Basic()
	@Column(name="POC_TXT", length=40)
	public String getPocTxt() {
		return this.pocTxt;
	}
	public void setPocTxt(String pocTxt) {
		this.pocTxt = pocTxt;
	}

	@Basic()
	@Column(name="SVCR_IDFCTN_NBR", length=6)
	public String getSvcrIdfctnNbr() {
		return this.svcrIdfctnNbr;
	}
	public void setSvcrIdfctnNbr(String svcrIdfctnNbr) {
		this.svcrIdfctnNbr = svcrIdfctnNbr;
	}

	@Basic()
	@Column(name="TAX_IDFCTN_NBR", precision=9)
	public Integer getTaxIdfctnNbr() {
		return this.taxIdfctnNbr;
	}
	public void setTaxIdfctnNbr(Integer taxIdfctnNbr) {
		this.taxIdfctnNbr = taxIdfctnNbr;
	}

	@Basic()
	@Column(name="TITLE_TYPE_NM", length=50)
	public String getTitleTypeNm() {
		return this.titleTypeNm;
	}
	public void setTitleTypeNm(String titleTypeNm) {
		this.titleTypeNm = titleTypeNm;
	}

//	//bi-directional many-to-one association to Cntrct
//	@OneToMany(mappedBy="org", fetch=FetchType.LAZY)
//	public java.util.Set<Cntrct> getCntrcts() {
//		return this.cntrcts;
//	}
//	public void setCntrcts(java.util.Set<Cntrct> cntrcts) {
//		this.cntrcts = cntrcts;
//	}
//
//	//bi-directional one-to-one association to EduInstn
//	@OneToOne(mappedBy="org", fetch=FetchType.LAZY)
//	public EduInstn getEduInstn() {
//		return this.eduInstn;
//	}
//	public void setEduInstn(EduInstn eduInstn) {
//		this.eduInstn = eduInstn;
//	}
//
//	//bi-directional one-to-one association to EduPrvdr
//	@OneToOne(mappedBy="org", fetch=FetchType.LAZY)
//	public EduPrvdr getEduPrvdr() {
//		return this.eduPrvdr;
//	}
//	public void setEduPrvdr(EduPrvdr eduPrvdr) {
//		this.eduPrvdr = eduPrvdr;
//	}

	//bi-directional one-to-one association to FinclInstn
	@OneToOne(mappedBy="org", fetch=FetchType.LAZY)
	public FinclInstn getFinclInstn() {
		return this.finclInstn;
	}
	public void setFinclInstn(FinclInstn finclInstn) {
		this.finclInstn = finclInstn;
	}

//	//bi-directional one-to-one association to InsCmpny
//	@OneToOne(mappedBy="org", fetch=FetchType.LAZY)
//	public InsCmpny getInsCmpny() {
//		return this.insCmpny;
//	}
//	public void setInsCmpny(InsCmpny insCmpny) {
//		this.insCmpny = insCmpny;
//	}

	//bi-directional many-to-one association to Ptcpnt
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PTCPNT_ID", referencedColumnName="PTCPNT_ID", nullable=false, insertable=false, updatable=false)
	public Ptcpnt getPtcpnt() {
		return this.ptcpnt;
	}
	public void setPtcpnt(Ptcpnt ptcpnt) {
		this.ptcpnt = ptcpnt;
	}

	//bi-directional many-to-one association to OrgRmk
	@OneToMany(mappedBy="org", fetch=FetchType.LAZY)
	public java.util.Set<OrgRmk> getOrgRmks() {
		return this.orgRmks;
	}
	public void setOrgRmks(java.util.Set<OrgRmk> orgRmks) {
		this.orgRmks = orgRmks;
	}

	//bi-directional many-to-one association to PtcpntInstzn
	@OneToMany(mappedBy="org", fetch=FetchType.LAZY)
	public java.util.Set<PtcpntInstzn> getPtcpntInstzns() {
		return this.ptcpntInstzns;
	}
	public void setPtcpntInstzns(java.util.Set<PtcpntInstzn> ptcpntInstzns) {
		this.ptcpntInstzns = ptcpntInstzns;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Org)) {
			return false;
		}
		Org castOther = (Org)other;
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