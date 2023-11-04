package gov.va.vba.framework.domain.entities;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the RATING_AWARD_SUMRY database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="RATING_AWARD_SUMRY")
public class RatingAwardSumry implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long ratingAwardSumryId;
	private Date inactvDt;
	private Date ratingDt;
	private String rbaUserId;
//	private java.util.Set<AwardEvent> awardEvents;
	private java.util.Set<RatingAwardDetail> ratingAwardDetails;
	private RbaPrfil rbaPrfil;
	private Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public RatingAwardSumry() {
    }
    
	@Basic()
	@Column(name="JRN_DT", nullable=false, length=7)
	public Date getJrnDt() {
		return this.jrnDt;
	}
	public void setJrnDt(Date jrnDt) {
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
	@Column(name="RATING_AWARD_SUMRY_ID", unique=true, nullable=false, precision=15)
	public Long getRatingAwardSumryId() {
		return this.ratingAwardSumryId;
	}
	public void setRatingAwardSumryId(Long ratingAwardSumryId) {
		this.ratingAwardSumryId = ratingAwardSumryId;
	}

	@Basic()
	@Column(name="INACTV_DT", length=7)
	public Date getInactvDt() {
		return this.inactvDt;
	}
	public void setInactvDt(Date inactvDt) {
		this.inactvDt = inactvDt;
	}

	@Basic()
	@Column(name="RATING_DT", length=7)
	public Date getRatingDt() {
		return this.ratingDt;
	}
	public void setRatingDt(Date ratingDt) {
		this.ratingDt = ratingDt;
	}

	@Basic()
	@Column(name="RBA_USER_ID", length=15)
	public String getRbaUserId() {
		return this.rbaUserId;
	}
	public void setRbaUserId(String rbaUserId) {
		this.rbaUserId = rbaUserId;
	}

	//bi-directional many-to-one association to AwardEvent
//	@OneToMany(mappedBy="ratingAwardSumry", fetch=FetchType.LAZY)
//	public java.util.Set<AwardEvent> getAwardEvents() {
//		return this.awardEvents;
//	}
//	public void setAwardEvents(java.util.Set<AwardEvent> awardEvents) {
//		this.awardEvents = awardEvents;
//	}

	//bi-directional many-to-one association to RatingAwardDetail
	@OneToMany(mappedBy="ratingAwardSumry", fetch=FetchType.LAZY)
	public java.util.Set<RatingAwardDetail> getRatingAwardDetails() {
		return this.ratingAwardDetails;
	}
	public void setRatingAwardDetails(java.util.Set<RatingAwardDetail> ratingAwardDetails) {
		this.ratingAwardDetails = ratingAwardDetails;
	}

	//bi-directional many-to-one association to RbaPrfil
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="PTCPNT_VET_ID", referencedColumnName="PTCPNT_VET_ID", nullable=false),
		@JoinColumn(name="PRFIL_DT", referencedColumnName="PRFIL_DT")})
	public RbaPrfil getRbaPrfil() {
		return this.rbaPrfil;
	}
	public void setRbaPrfil(RbaPrfil rbaPrfil) {
		this.rbaPrfil = rbaPrfil;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RatingAwardSumry)) {
			return false;
		}
		RatingAwardSumry castOther = (RatingAwardSumry)other;
		return new EqualsBuilder()
			.append(this.getRatingAwardSumryId(), castOther.getRatingAwardSumryId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getRatingAwardSumryId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("ratingAwardSumryId", getRatingAwardSumryId())
			.toString();
	}
}