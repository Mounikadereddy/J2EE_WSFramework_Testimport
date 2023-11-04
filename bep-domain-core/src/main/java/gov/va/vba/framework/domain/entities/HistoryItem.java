package gov.va.vba.framework.domain.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class HistoryItem implements Historical {

	@Id
	@Column(name = "ROWID", insertable=false, updatable = false)
	private String rowId;
	
	@Transient
	private String itemType;
	
	@Transient
	private String transactionType;
	
	public abstract History getHistory();

	public abstract void setHistory(History history);

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
}
