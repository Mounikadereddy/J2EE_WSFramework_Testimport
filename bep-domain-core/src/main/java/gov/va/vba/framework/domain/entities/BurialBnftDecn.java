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
 * The persistent class for the BURIAL_BNFT_DECN database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="BURIAL_BNFT_DECN")
public class BurialBnftDecn implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private BurialBnftDecnPK compId;
	private String burialDecnTypeCd;
	private Long decnId;
	private java.sql.Timestamp invldDecnDt;
	private Award award;
    private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;


    public BurialBnftDecn() {
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
	public BurialBnftDecnPK getCompId() {
		return this.compId;
	}
	public void setCompId(BurialBnftDecnPK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="BURIAL_DECN_TYPE_CD", nullable=false, length=12)
	public String getBurialDecnTypeCd() {
		return this.burialDecnTypeCd;
	}
	public void setBurialDecnTypeCd(String burialDecnTypeCd) {
		this.burialDecnTypeCd = burialDecnTypeCd;
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
	@Column(name="INVLD_DECN_DT", length=7)
	public java.sql.Timestamp getInvldDecnDt() {
		return this.invldDecnDt;
	}
	public void setInvldDecnDt(java.sql.Timestamp invldDecnDt) {
		this.invldDecnDt = invldDecnDt;
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
		if (!(other instanceof BurialBnftDecn)) {
			return false;
		}
		BurialBnftDecn castOther = (BurialBnftDecn)other;
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