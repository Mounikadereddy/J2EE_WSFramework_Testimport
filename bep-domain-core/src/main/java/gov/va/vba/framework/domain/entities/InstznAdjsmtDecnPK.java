package gov.va.vba.framework.domain.entities;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The primary key class for the INSTZN_ADJSMT_DECN database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class InstznAdjsmtDecnPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String awardTypeCd;
	private Date beginDt;
	private Date decnDt;
	private String instznEfctvTypeCd;
	private Long ptcpntIdA;
	private Long ptcpntIdB;
	private Long ptcpntInstznId;

    public InstznAdjsmtDecnPK() {
    }

	@Column(name="AWARD_TYPE_CD", nullable=false, length=12)
	public String getAwardTypeCd() {
		return this.awardTypeCd;
	}
	public void setAwardTypeCd(String awardTypeCd) {
		this.awardTypeCd = awardTypeCd;
	}

	@Column(name="BEGIN_DT", nullable=false, length=7)
	public Date getBeginDt() {
		return this.beginDt;
	}
	public void setBeginDt(Date beginDt) {
		this.beginDt = beginDt;
	}

	@Column(name="DECN_DT", nullable=false, length=7)
	public Date getDecnDt() {
		return this.decnDt;
	}
	public void setDecnDt(Date decnDt) {
		this.decnDt = decnDt;
	}

	@Column(name="INSTZN_EFCTV_TYPE_CD", nullable=false, length=12)
	public String getInstznEfctvTypeCd() {
		return this.instznEfctvTypeCd;
	}
	public void setInstznEfctvTypeCd(String instznEfctvTypeCd) {
		this.instznEfctvTypeCd = instznEfctvTypeCd;
	}

	@Column(name="PTCPNT_ID_A", nullable=false, precision=15)
	public Long getPtcpntIdA() {
		return this.ptcpntIdA;
	}
	public void setPtcpntIdA(Long ptcpntIdA) {
		this.ptcpntIdA = ptcpntIdA;
	}

	@Column(name="PTCPNT_ID_B", nullable=false, precision=15)
	public Long getPtcpntIdB() {
		return this.ptcpntIdB;
	}
	public void setPtcpntIdB(Long ptcpntIdB) {
		this.ptcpntIdB = ptcpntIdB;
	}

	@Column(name="PTCPNT_INSTZN_ID", nullable=false, precision=15)
	public Long getPtcpntInstznId() {
		return this.ptcpntInstznId;
	}
	public void setPtcpntInstznId(Long ptcpntInstznId) {
		this.ptcpntInstznId = ptcpntInstznId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof InstznAdjsmtDecnPK)) {
			return false;
		}
		InstznAdjsmtDecnPK castOther = (InstznAdjsmtDecnPK)other;
		return new EqualsBuilder()
			.append(this.getAwardTypeCd(), castOther.getAwardTypeCd())
			.append(this.getBeginDt(), castOther.getBeginDt())
			.append(this.getDecnDt(), castOther.getDecnDt())
			.append(this.getInstznEfctvTypeCd(), castOther.getInstznEfctvTypeCd())
			.append(this.getPtcpntIdA(), castOther.getPtcpntIdA())
			.append(this.getPtcpntIdB(), castOther.getPtcpntIdB())
			.append(this.getPtcpntInstznId(), castOther.getPtcpntInstznId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getAwardTypeCd())
			.append(getBeginDt())
			.append(getDecnDt())
			.append(getInstznEfctvTypeCd())
			.append(getPtcpntIdA())
			.append(getPtcpntIdB())
			.append(getPtcpntInstznId())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("awardTypeCd", getAwardTypeCd())
			.append("beginDt", getBeginDt())
			.append("decnDt", getDecnDt())
			.append("instznEfctvTypeCd", getInstznEfctvTypeCd())
			.append("ptcpntIdA", getPtcpntIdA())
			.append("ptcpntIdB", getPtcpntIdB())
			.append("ptcpntInstznId", getPtcpntInstznId())
			.toString();
	}
}