package gov.va.vba.framework.common;

import java.sql.Date;

public class DateUtil {

	private static final Double MILISECONDS_PER_DAY = 24.0 * 60.0 * 60.0 * 1000.0;

	public static Integer getDaysBetweenDates(Date startDate, Date endDate) {
		Double diff = new Double(endDate.getTime()) - new Double(startDate.getTime());
		diff += MILISECONDS_PER_DAY;
		Integer days = new Double(Math.round(diff / MILISECONDS_PER_DAY)).intValue();
		return Math.abs(days);
	}
	
	public static Integer getWeeksBetweenDates(Date startDate, Date endDate) {
		Integer daysBetweenDates = getDaysBetweenDates(startDate, endDate);
		return new Double(Math.ceil(daysBetweenDates / 7.0)).intValue();
	}
	
	public static Boolean doDatesOverlap(Date firstStart, Date firstEnd, Date secondStart, Date secondEnd) {
		
		if (firstStart == null || firstEnd == null || secondStart == null
				|| secondEnd == null) {
			return false;
		}
		
		if (secondStart.after(firstStart) && secondStart.before(firstEnd)) {
			return true;
		}
		if (secondEnd.after(firstStart) && secondEnd.before(firstEnd)) {
			return true;
		}
		if(secondStart.before(firstStart) && secondEnd.after(firstEnd)) {
			return true;
		}
		return false;
	}
}
