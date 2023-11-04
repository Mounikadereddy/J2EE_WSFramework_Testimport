package gov.va.vba.framework.domain.entities;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The primary key class for the REQST_LC_STATUS database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class ReqstLcStatusPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long reqstId;
	private Date statusDt;

    public ReqstLcStatusPK() {
    }

	@Column(name="REQST_ID", nullable=false, precision=15)
	public Long getReqstId() {
		return this.reqstId;
	}
	public void setReqstId(Long reqstId) {
		this.reqstId = reqstId;
	}

	@Column(name="STATUS_DT", nullable=false, length=7)
	public Date getStatusDt() {
		return this.statusDt;
	}
	public void setStatusDt(Date statusDt) {
		this.statusDt = statusDt;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ReqstLcStatusPK)) {
			return false;
		}
		ReqstLcStatusPK castOther = (ReqstLcStatusPK)other;
		return new EqualsBuilder()
			.append(this.getReqstId(), castOther.getReqstId())
			.append(this.getStatusDt(), castOther.getStatusDt())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getReqstId())
			.append(getStatusDt())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("reqstId", getReqstId())
			.append("statusDt", getStatusDt())
			.toString();
	}
}