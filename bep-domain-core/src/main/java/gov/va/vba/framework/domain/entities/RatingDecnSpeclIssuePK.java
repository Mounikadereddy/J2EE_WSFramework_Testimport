package gov.va.vba.framework.domain.entities;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The primary key class for the RATING_DECN_SPECL_ISSUE database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class RatingDecnSpeclIssuePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long ratingDecnId;
	private String speclIssueBasisTypeCd;
	private String speclIssueTypeCd;

    public RatingDecnSpeclIssuePK() {
    }

	@Column(name="RATING_DECN_ID", nullable=false, precision=15)
	public Long getRatingDecnId() {
		return this.ratingDecnId;
	}
	public void setRatingDecnId(Long ratingDecnId) {
		this.ratingDecnId = ratingDecnId;
	}

	@Column(name="SPECL_ISSUE_BASIS_TYPE_CD", nullable=false, length=12)
	public String getSpeclIssueBasisTypeCd() {
		return this.speclIssueBasisTypeCd;
	}
	public void setSpeclIssueBasisTypeCd(String speclIssueBasisTypeCd) {
		this.speclIssueBasisTypeCd = speclIssueBasisTypeCd;
	}

	@Column(name="SPECL_ISSUE_TYPE_CD", nullable=false, length=12)
	public String getSpeclIssueTypeCd() {
		return this.speclIssueTypeCd;
	}
	public void setSpeclIssueTypeCd(String speclIssueTypeCd) {
		this.speclIssueTypeCd = speclIssueTypeCd;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RatingDecnSpeclIssuePK)) {
			return false;
		}
		RatingDecnSpeclIssuePK castOther = (RatingDecnSpeclIssuePK)other;
		return new EqualsBuilder()
			.append(this.getRatingDecnId(), castOther.getRatingDecnId())
			.append(this.getSpeclIssueBasisTypeCd(), castOther.getSpeclIssueBasisTypeCd())
			.append(this.getSpeclIssueTypeCd(), castOther.getSpeclIssueTypeCd())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getRatingDecnId())
			.append(getSpeclIssueBasisTypeCd())
			.append(getSpeclIssueTypeCd())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("ratingDecnId", getRatingDecnId())
			.append("speclIssueBasisTypeCd", getSpeclIssueBasisTypeCd())
			.append("speclIssueTypeCd", getSpeclIssueTypeCd())
			.toString();
	}
}