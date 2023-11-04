package gov.va.vba.framework.domain.entities;
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the LCTN database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="LCTN")
public class Lctn implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long lctnId;
	private String lctnTypeNm;
//	private java.util.Set<AgencyLedgerDcmnt> agencyLedgerDcmnts;
//	private java.util.Set<Apntmt> apntmts;
	private BddIntakeSite bddIntakeSite;
//	private java.util.Set<CaseMiscExpn> caseMiscExpns;
//	private java.util.Set<CasePgmCostAuthzn> casePgmCostAuthzns;
//	private java.util.Set<CaseTravel> caseTravels1;
//	private java.util.Set<CaseTravel> caseTravels2;
//	private java.util.Set<CaseTravel> caseTravels3;
//	private java.util.Set<Cntrct> cntrcts;
//	private java.util.Set<CntrctAward> cntrctAwards;
//	private java.util.Set<EduPrvdrAward> eduPrvdrAwards;
//	private java.util.Set<Folder> folders;
//	private GeoArea geoArea;
//	private java.util.Set<GroupApntmt> groupApntmts;
//	private Jrsdtn jrsdtn;
//	private java.util.Set<LctnRlnshp> lctnRlnshps1;
//	private java.util.Set<LctnRlnshp> lctnRlnshps2;
//	private java.util.Set<LocalTmplat> localTmplats;
//	private NaraStorgFclty naraStorgFclty;
//	private OutbsdSite outbsdSite;
//	private java.util.Set<Prprty> prprties;
//	private PrprtyAddr prprtyAddr;
//	private java.util.Set<PtcpntLctn> ptcpntLctns;
//	private java.util.Set<StdStmnt> stdStmnts;
	private Stn stn;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;
	
    public Lctn() {
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
	@Column(name="LCTN_ID", unique=true, nullable=false, precision=15)
	public Long getLctnId() {
		return this.lctnId;
	}
	public void setLctnId(Long lctnId) {
		this.lctnId = lctnId;
	}

	@Basic()
	@Column(name="LCTN_TYPE_NM", nullable=false, length=50)
	public String getLctnTypeNm() {
		return this.lctnTypeNm;
	}
	public void setLctnTypeNm(String lctnTypeNm) {
		this.lctnTypeNm = lctnTypeNm;
	}

//	//bi-directional many-to-one association to AgencyLedgerDcmnt
//	@OneToMany(mappedBy="lctn", fetch=FetchType.LAZY)
//	public java.util.Set<AgencyLedgerDcmnt> getAgencyLedgerDcmnts() {
//		return this.agencyLedgerDcmnts;
//	}
//	public void setAgencyLedgerDcmnts(java.util.Set<AgencyLedgerDcmnt> agencyLedgerDcmnts) {
//		this.agencyLedgerDcmnts = agencyLedgerDcmnts;
//	}
//
//	//bi-directional many-to-one association to Apntmt
//	@OneToMany(mappedBy="lctn", fetch=FetchType.LAZY)
//	public java.util.Set<Apntmt> getApntmts() {
//		return this.apntmts;
//	}
//	public void setApntmts(java.util.Set<Apntmt> apntmts) {
//		this.apntmts = apntmts;
//	}

	//bi-directional one-to-one association to BddIntakeSite
	@OneToOne(mappedBy="lctn", fetch=FetchType.LAZY)
	public BddIntakeSite getBddIntakeSite() {
		return this.bddIntakeSite;
	}
	public void setBddIntakeSite(BddIntakeSite bddIntakeSite) {
		this.bddIntakeSite = bddIntakeSite;
	}

//	//bi-directional many-to-one association to CaseMiscExpn
//	@OneToMany(mappedBy="lctn", fetch=FetchType.LAZY)
//	public java.util.Set<CaseMiscExpn> getCaseMiscExpns() {
//		return this.caseMiscExpns;
//	}
//	public void setCaseMiscExpns(java.util.Set<CaseMiscExpn> caseMiscExpns) {
//		this.caseMiscExpns = caseMiscExpns;
//	}
//
//	//bi-directional many-to-one association to CasePgmCostAuthzn
//	@OneToMany(mappedBy="lctn", fetch=FetchType.LAZY)
//	public java.util.Set<CasePgmCostAuthzn> getCasePgmCostAuthzns() {
//		return this.casePgmCostAuthzns;
//	}
//	public void setCasePgmCostAuthzns(java.util.Set<CasePgmCostAuthzn> casePgmCostAuthzns) {
//		this.casePgmCostAuthzns = casePgmCostAuthzns;
//	}
//
//	//bi-directional many-to-one association to CaseTravel
//	@OneToMany(mappedBy="lctn1", fetch=FetchType.LAZY)
//	public java.util.Set<CaseTravel> getCaseTravels1() {
//		return this.caseTravels1;
//	}
//	public void setCaseTravels1(java.util.Set<CaseTravel> caseTravels1) {
//		this.caseTravels1 = caseTravels1;
//	}
//
//	//bi-directional many-to-one association to CaseTravel
//	@OneToMany(mappedBy="lctn2", fetch=FetchType.LAZY)
//	public java.util.Set<CaseTravel> getCaseTravels2() {
//		return this.caseTravels2;
//	}
//	public void setCaseTravels2(java.util.Set<CaseTravel> caseTravels2) {
//		this.caseTravels2 = caseTravels2;
//	}
//
//	//bi-directional many-to-one association to CaseTravel
//	@OneToMany(mappedBy="lctn3", fetch=FetchType.LAZY)
//	public java.util.Set<CaseTravel> getCaseTravels3() {
//		return this.caseTravels3;
//	}
//	public void setCaseTravels3(java.util.Set<CaseTravel> caseTravels3) {
//		this.caseTravels3 = caseTravels3;
//	}
//
//	//bi-directional many-to-one association to Cntrct
//	@OneToMany(mappedBy="lctn", fetch=FetchType.LAZY)
//	public java.util.Set<Cntrct> getCntrcts() {
//		return this.cntrcts;
//	}
//	public void setCntrcts(java.util.Set<Cntrct> cntrcts) {
//		this.cntrcts = cntrcts;
//	}
//
//	//bi-directional many-to-one association to CntrctAward
//	@OneToMany(mappedBy="lctn", fetch=FetchType.LAZY)
//	public java.util.Set<CntrctAward> getCntrctAwards() {
//		return this.cntrctAwards;
//	}
//	public void setCntrctAwards(java.util.Set<CntrctAward> cntrctAwards) {
//		this.cntrctAwards = cntrctAwards;
//	}
//
//	//bi-directional many-to-one association to EduPrvdrAward
//	@OneToMany(mappedBy="lctn", fetch=FetchType.LAZY)
//	public java.util.Set<EduPrvdrAward> getEduPrvdrAwards() {
//		return this.eduPrvdrAwards;
//	}
//	public void setEduPrvdrAwards(java.util.Set<EduPrvdrAward> eduPrvdrAwards) {
//		this.eduPrvdrAwards = eduPrvdrAwards;
//	}
//
//	//bi-directional many-to-one association to Folder
//	@OneToMany(mappedBy="lctn", fetch=FetchType.LAZY)
//	public java.util.Set<Folder> getFolders() {
//		return this.folders;
//	}
//	public void setFolders(java.util.Set<Folder> folders) {
//		this.folders = folders;
//	}
//
//	//bi-directional one-to-one association to GeoArea
//	@OneToOne(mappedBy="lctn", fetch=FetchType.LAZY)
//	public GeoArea getGeoArea() {
//		return this.geoArea;
//	}
//	public void setGeoArea(GeoArea geoArea) {
//		this.geoArea = geoArea;
//	}
//
//	//bi-directional many-to-one association to GroupApntmt
//	@OneToMany(mappedBy="lctn", fetch=FetchType.LAZY)
//	public java.util.Set<GroupApntmt> getGroupApntmts() {
//		return this.groupApntmts;
//	}
//	public void setGroupApntmts(java.util.Set<GroupApntmt> groupApntmts) {
//		this.groupApntmts = groupApntmts;
//	}
//
//	//bi-directional one-to-one association to Jrsdtn
//	@OneToOne(mappedBy="lctn", fetch=FetchType.LAZY)
//	public Jrsdtn getJrsdtn() {
//		return this.jrsdtn;
//	}
//	public void setJrsdtn(Jrsdtn jrsdtn) {
//		this.jrsdtn = jrsdtn;
//	}
//
//	//bi-directional many-to-one association to LctnRlnshp
//	@OneToMany(mappedBy="lctn1", fetch=FetchType.LAZY)
//	public java.util.Set<LctnRlnshp> getLctnRlnshps1() {
//		return this.lctnRlnshps1;
//	}
//	public void setLctnRlnshps1(java.util.Set<LctnRlnshp> lctnRlnshps1) {
//		this.lctnRlnshps1 = lctnRlnshps1;
//	}
//
//	//bi-directional many-to-one association to LctnRlnshp
//	@OneToMany(mappedBy="lctn2", fetch=FetchType.LAZY)
//	public java.util.Set<LctnRlnshp> getLctnRlnshps2() {
//		return this.lctnRlnshps2;
//	}
//	public void setLctnRlnshps2(java.util.Set<LctnRlnshp> lctnRlnshps2) {
//		this.lctnRlnshps2 = lctnRlnshps2;
//	}
//
//	//bi-directional many-to-one association to LocalTmplat
//	@OneToMany(mappedBy="lctn", fetch=FetchType.LAZY)
//	public java.util.Set<LocalTmplat> getLocalTmplats() {
//		return this.localTmplats;
//	}
//	public void setLocalTmplats(java.util.Set<LocalTmplat> localTmplats) {
//		this.localTmplats = localTmplats;
//	}
//
//	//bi-directional one-to-one association to NaraStorgFclty
//	@OneToOne(mappedBy="lctn", fetch=FetchType.LAZY)
//	public NaraStorgFclty getNaraStorgFclty() {
//		return this.naraStorgFclty;
//	}
//	public void setNaraStorgFclty(NaraStorgFclty naraStorgFclty) {
//		this.naraStorgFclty = naraStorgFclty;
//	}
//
//	//bi-directional one-to-one association to OutbsdSite
//	@OneToOne(mappedBy="lctn", fetch=FetchType.LAZY)
//	public OutbsdSite getOutbsdSite() {
//		return this.outbsdSite;
//	}
//	public void setOutbsdSite(OutbsdSite outbsdSite) {
//		this.outbsdSite = outbsdSite;
//	}
//
//	//bi-directional many-to-one association to Prprty
//	@OneToMany(mappedBy="lctn", fetch=FetchType.LAZY)
//	public java.util.Set<Prprty> getPrprties() {
//		return this.prprties;
//	}
//	public void setPrprties(java.util.Set<Prprty> prprties) {
//		this.prprties = prprties;
//	}
//
//	//bi-directional one-to-one association to PrprtyAddr
//	@OneToOne(mappedBy="lctn", fetch=FetchType.LAZY)
//	public PrprtyAddr getPrprtyAddr() {
//		return this.prprtyAddr;
//	}
//	public void setPrprtyAddr(PrprtyAddr prprtyAddr) {
//		this.prprtyAddr = prprtyAddr;
//	}
//
//	//bi-directional many-to-one association to PtcpntLctn
//	@OneToMany(mappedBy="lctn", fetch=FetchType.LAZY)
//	public java.util.Set<PtcpntLctn> getPtcpntLctns() {
//		return this.ptcpntLctns;
//	}
//	public void setPtcpntLctns(java.util.Set<PtcpntLctn> ptcpntLctns) {
//		this.ptcpntLctns = ptcpntLctns;
//	}
//
//	//bi-directional many-to-one association to StdStmnt
//	@OneToMany(mappedBy="lctn", fetch=FetchType.LAZY)
//	public java.util.Set<StdStmnt> getStdStmnts() {
//		return this.stdStmnts;
//	}
//	public void setStdStmnts(java.util.Set<StdStmnt> stdStmnts) {
//		this.stdStmnts = stdStmnts;
//	}

	//bi-directional one-to-one association to Stn
	@OneToOne(mappedBy="lctn", fetch=FetchType.LAZY)
	public Stn getStn() {
		return this.stn;
	}
	public void setStn(Stn stn) {
		this.stn = stn;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Lctn)) {
			return false;
		}
		Lctn castOther = (Lctn)other;
		return new EqualsBuilder()
			.append(this.getLctnId(), castOther.getLctnId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getLctnId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("lctnId", getLctnId())
			.toString();
	}

}