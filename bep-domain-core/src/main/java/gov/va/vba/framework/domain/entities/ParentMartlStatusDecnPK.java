package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * The primary key class for the PARENT_MARTL_STATUS_DECN database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class ParentMartlStatusDecnPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Timestamp awardEfctvDt;
	private String awardTypeCd;
	private Timestamp decnDt;
	private Long ptcpntIdA;
	private Long ptcpntIdB;
	private Long ptcpntIdC;

    public ParentMartlStatusDecnPK() {
    }
    public ParentMartlStatusDecnPK(String pkValues){
    	Pattern eq = Pattern.compile("=");
		String[] values = eq.split(pkValues);
		Date tempAwardEfctvDt = new Date(values[1].substring(0, values[1].indexOf(",")));
		Timestamp tempAwardEfctvTs = new Timestamp(tempAwardEfctvDt.getTime());
		Date tempDecnDt = new Date(values[2].substring(0, values[1].indexOf(",")));
		Timestamp tempDecnTs = new Timestamp(tempDecnDt.getTime());

		this.awardEfctvDt = tempAwardEfctvTs;
		this.awardTypeCd = values[3].substring(0, values[3].indexOf(","));
		this.decnDt = tempDecnTs;
		this.ptcpntIdA = new Long(values[4].substring(0, values[4].indexOf(",")));
		this.ptcpntIdB = new Long(values[5].substring(0, values[5].indexOf(",")));

    }
    public ParentMartlStatusDecnPK(Long ptcpntIda, Long ptcpntIdb, 
    		Long ptcpntIdc, Date decnDt, Date awardEfctvDt, String awardTypeCd){
    	Timestamp tempDecnDt = new Timestamp(decnDt.getTime());
    	Timestamp tempAwardEfctvDt = new Timestamp(awardEfctvDt.getTime());
    	this.ptcpntIdA = ptcpntIda;
    	this.ptcpntIdB = ptcpntIdb;
    	this.ptcpntIdC = ptcpntIdc;
    	this.decnDt = tempDecnDt;
    	this.awardEfctvDt = tempAwardEfctvDt;
    	this.awardTypeCd = awardTypeCd;
    }

	@Column(name="AWARD_EFCTV_DT", nullable=false, length=7)
	public Timestamp getAwardEfctvDt() {
		return this.awardEfctvDt;
	}
	public void setAwardEfctvDt(Timestamp awardEfctvDt) {
		this.awardEfctvDt = awardEfctvDt;
	}

	@Column(name="AWARD_TYPE_CD", nullable=false, length=12)
	public String getAwardTypeCd() {
		return this.awardTypeCd;
	}
	public void setAwardTypeCd(String awardTypeCd) {
		this.awardTypeCd = awardTypeCd;
	}

	@Column(name="DECN_DT", nullable=false, length=7)
	public Timestamp getDecnDt() {
		return this.decnDt;
	}
	public void setDecnDt(Timestamp decnDt) {
		this.decnDt = decnDt;
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

	@Column(name="PTCPNT_ID_C", nullable=false, precision=15)
	public Long getPtcpntIdC() {
		return this.ptcpntIdC;
	}
	public void setPtcpntIdC(Long ptcpntIdC) {
		this.ptcpntIdC = ptcpntIdC;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ParentMartlStatusDecnPK)) {
			return false;
		}
		ParentMartlStatusDecnPK castOther = (ParentMartlStatusDecnPK)other;
		return new EqualsBuilder()
			.append(this.getAwardEfctvDt(), castOther.getAwardEfctvDt())
			.append(this.getAwardTypeCd(), castOther.getAwardTypeCd())
			.append(this.getDecnDt(), castOther.getDecnDt())
			.append(this.getPtcpntIdA(), castOther.getPtcpntIdA())
			.append(this.getPtcpntIdB(), castOther.getPtcpntIdB())
			.append(this.getPtcpntIdC(), castOther.getPtcpntIdC())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getAwardEfctvDt())
			.append(getAwardTypeCd())
			.append(getDecnDt())
			.append(getPtcpntIdA())
			.append(getPtcpntIdB())
			.append(getPtcpntIdC())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("awardEfctvDt", getAwardEfctvDt())
			.append("awardTypeCd", getAwardTypeCd())
			.append("decnDt", getDecnDt())
			.append("ptcpntIdA", getPtcpntIdA())
			.append("ptcpntIdB", getPtcpntIdB())
			.append("ptcpntIdC", getPtcpntIdC())
			.toString();
	}
}