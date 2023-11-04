package gov.va.vba.framework.domain.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the EMP database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="EMP")
public class Emp implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long ptcpntId;
	private String divsnTxt;
	private String empVetsPrfrncTypeNm;
	private String gradeTypeNm;
	private String jobTitleTypeNm;
	private String officeAddrsTxt;
	private Long rangeId;
//	private java.util.Set<CrtfdClaimWrkld> crtfdClaimWrklds;
//	private java.util.Set<DspntnCnkrnc> dspntnCnkrncs;
	private Emp emp;
	private java.util.Set<Emp> emps;
	private Person person;
//	private java.util.Set<EmpPymtAuthty> empPymtAuthties;
//	private java.util.Set<MtgeLoanWrkld> mtgeLoanWrklds1;
//	private java.util.Set<MtgeLoanWrkld> mtgeLoanWrklds2;
	private java.util.Set<PtcpntDiary> ptcpntDiaries;
//	private java.util.Set<TempMtgeLoanEvent> tempMtgeLoanEvents;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public Emp() {
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
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PTCPNT_ID", unique=true, nullable=false, precision=15)
	public Long getPtcpntId() {
		return this.ptcpntId;
	}
	public void setPtcpntId(Long ptcpntId) {
		this.ptcpntId = ptcpntId;
	}

	@Basic()
	@Column(name="DIVSN_TXT", length=80)
	public String getDivsnTxt() {
		return this.divsnTxt;
	}
	public void setDivsnTxt(String divsnTxt) {
		this.divsnTxt = divsnTxt;
	}

	@Basic()
	@Column(name="EMP_VETS_PRFRNC_TYPE_NM", length=50)
	public String getEmpVetsPrfrncTypeNm() {
		return this.empVetsPrfrncTypeNm;
	}
	public void setEmpVetsPrfrncTypeNm(String empVetsPrfrncTypeNm) {
		this.empVetsPrfrncTypeNm = empVetsPrfrncTypeNm;
	}

	@Basic()
	@Column(name="GRADE_TYPE_NM", length=50)
	public String getGradeTypeNm() {
		return this.gradeTypeNm;
	}
	public void setGradeTypeNm(String gradeTypeNm) {
		this.gradeTypeNm = gradeTypeNm;
	}

	@Basic()
	@Column(name="JOB_TITLE_TYPE_NM", length=50)
	public String getJobTitleTypeNm() {
		return this.jobTitleTypeNm;
	}
	public void setJobTitleTypeNm(String jobTitleTypeNm) {
		this.jobTitleTypeNm = jobTitleTypeNm;
	}

	@Basic()
	@Column(name="OFFICE_ADDRS_TXT", length=35)
	public String getOfficeAddrsTxt() {
		return this.officeAddrsTxt;
	}
	public void setOfficeAddrsTxt(String officeAddrsTxt) {
		this.officeAddrsTxt = officeAddrsTxt;
	}

	@Basic()
	@Column(name="RANGE_ID", precision=15)
	public Long getRangeId() {
		return this.rangeId;
	}
	public void setRangeId(Long rangeId) {
		this.rangeId = rangeId;
	}
//
//	//bi-directional many-to-one association to CrtfdClaimWrkld
//	@OneToMany(mappedBy="emp", fetch=FetchType.LAZY)
//	public java.util.Set<CrtfdClaimWrkld> getCrtfdClaimWrklds() {
//		return this.crtfdClaimWrklds;
//	}
//	public void setCrtfdClaimWrklds(java.util.Set<CrtfdClaimWrkld> crtfdClaimWrklds) {
//		this.crtfdClaimWrklds = crtfdClaimWrklds;
//	}
//
//	//bi-directional many-to-one association to DspntnCnkrnc
//	@OneToMany(mappedBy="emp", fetch=FetchType.LAZY)
//	public java.util.Set<DspntnCnkrnc> getDspntnCnkrncs() {
//		return this.dspntnCnkrncs;
//	}
//	public void setDspntnCnkrncs(java.util.Set<DspntnCnkrnc> dspntnCnkrncs) {
//		this.dspntnCnkrncs = dspntnCnkrncs;
//	}

	//bi-directional many-to-one association to Emp
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PTCPNT_SUPVSR_ID", referencedColumnName="PTCPNT_ID")
	public Emp getEmp() {
		return this.emp;
	}
	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	//bi-directional many-to-one association to Emp
	@OneToMany(mappedBy="emp", fetch=FetchType.LAZY)
	public java.util.Set<Emp> getEmps() {
		return this.emps;
	}
	public void setEmps(java.util.Set<Emp> emps) {
		this.emps = emps;
	}

	//bi-directional one-to-one association to Person
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PTCPNT_ID", referencedColumnName="PTCPNT_ID", nullable=false, insertable=false, updatable=false)
	public Person getPerson() {
		return this.person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
//
//	//bi-directional many-to-one association to EmpPymtAuthty
//	@OneToMany(mappedBy="emp", fetch=FetchType.LAZY)
//	public java.util.Set<EmpPymtAuthty> getEmpPymtAuthties() {
//		return this.empPymtAuthties;
//	}
//	public void setEmpPymtAuthties(java.util.Set<EmpPymtAuthty> empPymtAuthties) {
//		this.empPymtAuthties = empPymtAuthties;
//	}
//
//	//bi-directional many-to-one association to MtgeLoanWrkld
//	@OneToMany(mappedBy="emp1", fetch=FetchType.LAZY)
//	public java.util.Set<MtgeLoanWrkld> getMtgeLoanWrklds1() {
//		return this.mtgeLoanWrklds1;
//	}
//	public void setMtgeLoanWrklds1(java.util.Set<MtgeLoanWrkld> mtgeLoanWrklds1) {
//		this.mtgeLoanWrklds1 = mtgeLoanWrklds1;
//	}
//
//	//bi-directional many-to-one association to MtgeLoanWrkld
//	@OneToMany(mappedBy="emp2", fetch=FetchType.LAZY)
//	public java.util.Set<MtgeLoanWrkld> getMtgeLoanWrklds2() {
//		return this.mtgeLoanWrklds2;
//	}
//	public void setMtgeLoanWrklds2(java.util.Set<MtgeLoanWrkld> mtgeLoanWrklds2) {
//		this.mtgeLoanWrklds2 = mtgeLoanWrklds2;
//	}

	//bi-directional many-to-one association to PtcpntDiary
	@OneToMany(mappedBy="emp", fetch=FetchType.LAZY)
	public java.util.Set<PtcpntDiary> getPtcpntDiaries() {
		return this.ptcpntDiaries;
	}
	public void setPtcpntDiaries(java.util.Set<PtcpntDiary> ptcpntDiaries) {
		this.ptcpntDiaries = ptcpntDiaries;
	}
//
//	//bi-directional many-to-one association to TempMtgeLoanEvent
//	@OneToMany(mappedBy="emp", fetch=FetchType.LAZY)
//	public java.util.Set<TempMtgeLoanEvent> getTempMtgeLoanEvents() {
//		return this.tempMtgeLoanEvents;
//	}
//	public void setTempMtgeLoanEvents(java.util.Set<TempMtgeLoanEvent> tempMtgeLoanEvents) {
//		this.tempMtgeLoanEvents = tempMtgeLoanEvents;
//	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Emp)) {
			return false;
		}
		Emp castOther = (Emp)other;
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