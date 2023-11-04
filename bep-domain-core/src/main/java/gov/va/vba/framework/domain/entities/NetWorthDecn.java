package gov.va.vba.framework.domain.entities;

import java.io.Serializable;

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
 * The persistent class for the NET_WORTH_DECN database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="NET_WORTH_DECN")
public class NetWorthDecn implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private NetWorthDecnPK compId;
	private java.sql.Timestamp awardEfctvDt;
	private Long decnId;
	private Double familyNetWorthAmt;
	private java.sql.Timestamp invldDecnDt;
	private String netWorthDecnTypeCd;
	private Award award;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public NetWorthDecn() {
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
	public NetWorthDecnPK getCompId() {
		return this.compId;
	}
	public void setCompId(NetWorthDecnPK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="AWARD_EFCTV_DT", length=7)
	public java.sql.Timestamp getAwardEfctvDt() {
		return this.awardEfctvDt;
	}
	public void setAwardEfctvDt(java.sql.Timestamp awardEfctvDt) {
		this.awardEfctvDt = awardEfctvDt;
	}

	@Basic()
	@Column(name="DECN_ID", unique=true, precision=15)
	public Long getDecnId() {
		return this.decnId;
	}
	public void setDecnId(Long decnId) {
		this.decnId = decnId;
	}

	@Basic()
	@Column(name="FAMILY_NET_WORTH_AMT", precision=12, scale=2)
	public Double getFamilyNetWorthAmt() {
		return this.familyNetWorthAmt;
	}
	public void setFamilyNetWorthAmt(Double familyNetWorthAmt) {
		this.familyNetWorthAmt = familyNetWorthAmt;
	}

	@Basic()
	@Column(name="INVLD_DECN_DT", length=7)
	public java.sql.Timestamp getInvldDecnDt() {
		return this.invldDecnDt;
	}
	public void setInvldDecnDt(java.sql.Timestamp invldDecnDt) {
		this.invldDecnDt = invldDecnDt;
	}

	@Basic()
	@Column(name="NET_WORTH_DECN_TYPE_CD", nullable=false, length=12)
	public String getNetWorthDecnTypeCd() {
		return this.netWorthDecnTypeCd;
	}
	public void setNetWorthDecnTypeCd(String netWorthDecnTypeCd) {
		this.netWorthDecnTypeCd = netWorthDecnTypeCd;
	}

	//bi-directional many-to-one association to Award
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="PTCPNT_ID_A", referencedColumnName="PTCPNT_ID_A", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="PTCPNT_ID_B", referencedColumnName="PTCPNT_ID_B", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="AWARD_TYPE_CD", referencedColumnName="AWARD_TYPE_CD", nullable=false, insertable=false, updatable=false)})
	public Award getAward() {
		return this.award;
	}
	public void setAward(Award award) {
		this.award = award;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof NetWorthDecn)) {
			return false;
		}
		NetWorthDecn castOther = (NetWorthDecn)other;
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