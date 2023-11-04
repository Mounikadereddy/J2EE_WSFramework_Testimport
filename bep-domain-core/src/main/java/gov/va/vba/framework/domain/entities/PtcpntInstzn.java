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
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the PTCPNT_INSTZN database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="PTCPNT_INSTZN")
public class PtcpntInstzn implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long ptcpntInstznId;
	private java.sql.Timestamp beginDt;
	private java.sql.Timestamp endDt;
	private String instznNm;
	private String instznTypeCd;
	private java.sql.Timestamp invldDt;
	private java.util.Set<InstznAdjsmtDecn> instznAdjsmtDecns;
	private Org org;
	private Person person;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public PtcpntInstzn() {
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
	@Column(name="PTCPNT_INSTZN_ID", unique=true, nullable=false, precision=15)
	public Long getPtcpntInstznId() {
		return this.ptcpntInstznId;
	}
	public void setPtcpntInstznId(Long ptcpntInstznId) {
		this.ptcpntInstznId = ptcpntInstznId;
	}

	@Basic()
	@Column(name="BEGIN_DT", length=7)
	public java.sql.Timestamp getBeginDt() {
		return this.beginDt;
	}
	public void setBeginDt(java.sql.Timestamp beginDt) {
		this.beginDt = beginDt;
	}

	@Basic()
	@Column(name="END_DT", length=7)
	public java.sql.Timestamp getEndDt() {
		return this.endDt;
	}
	public void setEndDt(java.sql.Timestamp endDt) {
		this.endDt = endDt;
	}

	@Basic()
	@Column(name="INSTZN_NM", length=50)
	public String getInstznNm() {
		return this.instznNm;
	}
	public void setInstznNm(String instznNm) {
		this.instznNm = instznNm;
	}

	@Basic()
	@Column(name="INSTZN_TYPE_CD", length=12)
	public String getInstznTypeCd() {
		return this.instznTypeCd;
	}
	public void setInstznTypeCd(String instznTypeCd) {
		this.instznTypeCd = instznTypeCd;
	}

	@Basic()
	@Column(name="INVLD_DT", length=7)
	public java.sql.Timestamp getInvldDt() {
		return this.invldDt;
	}
	public void setInvldDt(java.sql.Timestamp invldDt) {
		this.invldDt = invldDt;
	}

	//bi-directional many-to-one association to InstznAdjsmtDecn
	@OneToMany(mappedBy="ptcpntInstzn", fetch=FetchType.LAZY)
	public java.util.Set<InstznAdjsmtDecn> getInstznAdjsmtDecns() {
		return this.instznAdjsmtDecns;
	}
	public void setInstznAdjsmtDecns(java.util.Set<InstznAdjsmtDecn> instznAdjsmtDecns) {
		this.instznAdjsmtDecns = instznAdjsmtDecns;
	}

	//bi-directional many-to-one association to Org
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PTCPNT_ID_B", referencedColumnName="PTCPNT_ID")
	public Org getOrg() {
		return this.org;
	}
	public void setOrg(Org org) {
		this.org = org;
	}

	//bi-directional many-to-one association to Person
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PTCPNT_ID_A", referencedColumnName="PTCPNT_ID", nullable=false)
	public Person getPerson() {
		return this.person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PtcpntInstzn)) {
			return false;
		}
		PtcpntInstzn castOther = (PtcpntInstzn)other;
		return new EqualsBuilder()
			.append(this.getPtcpntInstznId(), castOther.getPtcpntInstznId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getPtcpntInstznId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("ptcpntInstznId", getPtcpntInstznId())
			.toString();
	}
}