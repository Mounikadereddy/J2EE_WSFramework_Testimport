package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The primary key class for the PTCPNT_ALIAS database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class PtcpntAliaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Date efctvDt;
	private String ptcpntAliasTypeNm;
	private Long ptcpntId;

    public PtcpntAliaPK() {
    }

	@Column(name="EFCTV_DT", nullable=false, length=7)
	public Date getEfctvDt() {
		return this.efctvDt;
	}
	public void setEfctvDt(Date efctvDt) {
		this.efctvDt = efctvDt;
	}

	@Column(name="PTCPNT_ALIAS_TYPE_NM", nullable=false, length=50)
	public String getPtcpntAliasTypeNm() {
		return this.ptcpntAliasTypeNm;
	}
	public void setPtcpntAliasTypeNm(String ptcpntAliasTypeNm) {
		this.ptcpntAliasTypeNm = ptcpntAliasTypeNm;
	}

	@Column(name="PTCPNT_ID", nullable=false, precision=15)
	public Long getPtcpntId() {
		return this.ptcpntId;
	}
	public void setPtcpntId(Long ptcpntId) {
		this.ptcpntId = ptcpntId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PtcpntAliaPK)) {
			return false;
		}
		PtcpntAliaPK castOther = (PtcpntAliaPK)other;
		return new EqualsBuilder()
			.append(this.getEfctvDt(), castOther.getEfctvDt())
			.append(this.getPtcpntAliasTypeNm(), castOther.getPtcpntAliasTypeNm())
			.append(this.getPtcpntId(), castOther.getPtcpntId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getEfctvDt())
			.append(getPtcpntAliasTypeNm())
			.append(getPtcpntId())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("efctvDt", getEfctvDt())
			.append("ptcpntAliasTypeNm", getPtcpntAliasTypeNm())
			.append("ptcpntId", getPtcpntId())
			.toString();
	}
}