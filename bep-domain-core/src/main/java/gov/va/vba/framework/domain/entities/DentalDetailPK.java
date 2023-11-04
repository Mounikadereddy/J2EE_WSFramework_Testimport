package gov.va.vba.framework.domain.entities;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The primary key class for the DENTAL_DETAIL database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class DentalDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long ratingDecnId;
	private Integer toothNbr;

    public DentalDetailPK() {
    }

	@Column(name="RATING_DECN_ID", nullable=false, precision=15)
	public Long getRatingDecnId() {
		return this.ratingDecnId;
	}
	public void setRatingDecnId(Long ratingDecnId) {
		this.ratingDecnId = ratingDecnId;
	}

	@Column(name="TOOTH_NBR", nullable=false, precision=2)
	public Integer getToothNbr() {
		return this.toothNbr;
	}
	public void setToothNbr(Integer toothNbr) {
		this.toothNbr = toothNbr;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DentalDetailPK)) {
			return false;
		}
		DentalDetailPK castOther = (DentalDetailPK)other;
		return new EqualsBuilder()
			.append(this.getRatingDecnId(), castOther.getRatingDecnId())
			.append(this.getToothNbr(), castOther.getToothNbr())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getRatingDecnId())
			.append(getToothNbr())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("ratingDecnId", getRatingDecnId())
			.append("toothNbr", getToothNbr())
			.toString();
	}
}