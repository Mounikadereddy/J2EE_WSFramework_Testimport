package gov.va.vba.framework.domain.entities;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The primary key class for the RATING_AWARD_DETAIL database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class RatingAwardDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Date efctvDt;
	private Long ptcpntBeneId;
	private Long ptcpntVetId;
	private Long ratingAwardSumryId;

    public RatingAwardDetailPK() {
    }

	@Column(name="EFCTV_DT", nullable=false, length=7)
	public Date getEfctvDt() {
		return this.efctvDt;
	}
	public void setEfctvDt(Date efctvDt) {
		this.efctvDt = efctvDt;
	}

	@Column(name="PTCPNT_BENE_ID", nullable=false, precision=15)
	public Long getPtcpntBeneId() {
		return this.ptcpntBeneId;
	}
	public void setPtcpntBeneId(Long ptcpntBeneId) {
		this.ptcpntBeneId = ptcpntBeneId;
	}

	@Column(name="PTCPNT_VET_ID", nullable=false, precision=15)
	public Long getPtcpntVetId() {
		return this.ptcpntVetId;
	}
	public void setPtcpntVetId(Long ptcpntVetId) {
		this.ptcpntVetId = ptcpntVetId;
	}

	@Column(name="RATING_AWARD_SUMRY_ID", nullable=false, precision=15)
	public Long getRatingAwardSumryId() {
		return this.ratingAwardSumryId;
	}
	public void setRatingAwardSumryId(Long ratingAwardSumryId) {
		this.ratingAwardSumryId = ratingAwardSumryId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RatingAwardDetailPK)) {
			return false;
		}
		RatingAwardDetailPK castOther = (RatingAwardDetailPK)other;
		return new EqualsBuilder()
			.append(this.getEfctvDt(), castOther.getEfctvDt())
			.append(this.getPtcpntBeneId(), castOther.getPtcpntBeneId())
			.append(this.getPtcpntVetId(), castOther.getPtcpntVetId())
			.append(this.getRatingAwardSumryId(), castOther.getRatingAwardSumryId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getEfctvDt())
			.append(getPtcpntBeneId())
			.append(getPtcpntVetId())
			.append(getRatingAwardSumryId())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("efctvDt", getEfctvDt())
			.append("ptcpntBeneId", getPtcpntBeneId())
			.append("ptcpntVetId", getPtcpntVetId())
			.append("ratingAwardSumryId", getRatingAwardSumryId())
			.toString();
	}
}