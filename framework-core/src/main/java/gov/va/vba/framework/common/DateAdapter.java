package gov.va.vba.framework.common;

import java.text.Format;
import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, java.sql.Date> {
	public String marshal(java.sql.Date date) {
		
		Format formatter = new SimpleDateFormat("M/d/yyyy");
		return formatter.format(date);
	}

	public java.sql.Date unmarshal(String val) {
		return java.sql.Date.valueOf(val);
	}
}
