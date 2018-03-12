/**
 * created by 2010-7-2
 */
package nju.software.wsjx.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * ���ڹ�����
 * 
 * @author zym
 * 
 */
/**
 * @author wangy
 * 
 */
public class DateUtil {
	public final static String shortFormat = "yyyyMMdd";
	public final static String longFormat = "yyyyMMddHHmmss";
	public final static String webFormat = "yyyy-MM-dd";
	public final static String timeFormat = "HHmmss";
	public final static String monthFormat = "yyyyMM";
	public final static String chineseDtFormat = "yyyy��MM��dd��";
	public final static String chineseFullFormat = "yyyy��MM��dd�� HHʱmm��";
	public final static String chineseLongFormat = "yyyy��MM��dd�� HH:mm:ss";
	public final static String chineseshortFormat = "yyyy��MM��";
	/**
	 * ���Ժʹ�����ڸ�ʽYYYY-MM-DD
	 */
	public final static String newFormat = "yyyy-MM-dd";
	public final static String zbFormat = "yyyy/MM/dd";
	public final static String tsfxFormat = "yyyy.MM.dd";
	public final static String noSecondFormat = "yyyy-MM-dd HH:mm";

	public final static String datetimeFormat = "yyyy-MM-dd HH:mm:ss";

	public final static String sqlFormat = "yyyy/MM/dd HH:mm:ss";

	public final static long ONE_DAY_MILL_SECONDS = 86400000;

	/**
	 * ���һ�����ڵ�ȥ���ͬһʱ��
	 * 
	 * @param date
	 *            ����
	 * @return
	 */
	public static Date getLastYear(Date date) {
		if (date == null) {
			return null;
		}

		int year = getYear(date);
		int month = getMonth(date);
		int day = getDayOfMonth(date);

		StringBuffer sb = new StringBuffer();
		sb.append(year - 1).append("-").append(month).append("-").append(day);
		return parse(sb.toString(), webFormat);
	}

	public static String getLastYearByDate(Date date) {
		if (date == null) {
			return null;
		}

		int year = getYear(date);
		int month = getMonth(date);
		int day = getDayOfMonth(date);

		StringBuffer sb = new StringBuffer();
		sb.append(year - 1).append("-").append(month).append("-").append(day);
		return sb.toString();
	}

	// -----------------------------------------------------------------------
	/**
	 * Adds to a date returning a new object. The original date object is
	 * unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param calendarField
	 *            the calendar field to add to
	 * @param amount
	 *            the amount to add, may be negative
	 * @return the new date object with the amount added
	 * @throws IllegalArgumentException
	 *             if the date is null
	 */
	public static Date add(Date date, int calendarField, int amount) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(calendarField, amount);
		return c.getTime();
	}

	public static Date addYears(Date date, int amount) {
		return add(date, Calendar.YEAR, amount);
	}

	public static Date addMonths(Date date, int amount) {
		return add(date, Calendar.MONTH, amount);
	}

	public static Date addWeeks(Date date, int amount) {
		return add(date, Calendar.WEEK_OF_YEAR, amount);
	}

	public static Date addDays(Date date, int amount) {
		return add(date, Calendar.DAY_OF_MONTH, amount);
	}

	public static Date addHours(Date date, int amount) {
		return add(date, Calendar.HOUR_OF_DAY, amount);
	}

	/**
	 * ��׼�����
	 * 
	 * @param date
	 *            ���ڶ���
	 * @param format
	 *            ���������ʽ
	 * @return ����ָ����ʽ������ַ���
	 */
	public static String format(Date date, String format) {
		if (date == null) {
			return null;
		}

		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * ���һ��ʱ��������
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		if (date == null) {
			return -1;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * ���һ��ʱ����·���
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		if (date == null) {
			return -1;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH) + 1;
	}

	/**
	 * ���һ��ʱ����һ�����е�����
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfMonth(Date date) {
		if (date == null) {
			return -1;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * ��׼�����
	 * 
	 * @param s_date
	 * @param format
	 * @return
	 */
	public static Date parse(String s_date, String format) {
		if (s_date == null)
			return null;
		try {
			return new SimpleDateFormat(format).parse(s_date);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ȡ���������ڼ������������1-����2��
	 * 
	 * @param one
	 *            ����1
	 * @param two
	 *            ����2
	 * 
	 * @return �������
	 */
	public static long getDiffSeconds(Date one, Date two) {
		Calendar sysDate = new GregorianCalendar();

		sysDate.setTime(one);

		Calendar failDate = new GregorianCalendar();

		failDate.setTime(two);
		return (sysDate.getTimeInMillis() - failDate.getTimeInMillis()) / 1000;
	}

	/**
	 * ȡ���������ڼ��������������1-����2��
	 * 
	 * @param one
	 * @param two
	 * @return
	 */
	public static long getDiffMinutes(Date one, Date two) {
		Calendar sysDate = new GregorianCalendar();

		sysDate.setTime(one);

		Calendar failDate = new GregorianCalendar();

		failDate.setTime(two);
		return (sysDate.getTimeInMillis() - failDate.getTimeInMillis())
				/ (60 * 1000);
	}

	/**
	 * ȡ���������ڵļ������
	 * 
	 * @param one
	 * @param two
	 * 
	 * @return �������
	 */
	public static long getDiffDays(Date one, Date two) {
		Calendar sysDate = new GregorianCalendar();

		sysDate.setTime(one);

		Calendar failDate = new GregorianCalendar();

		failDate.setTime(two);
		return (sysDate.getTimeInMillis() - failDate.getTimeInMillis())
				/ (24 * 60 * 60 * 1000);
	}

	/**
	 * �ж��Ƿ�����ȷ�����ڸ�ʽ
	 * 
	 * @param strDate
	 *            �����ַ���
	 * @param format
	 *            ���ڸ�ʽ
	 * @return
	 */
	public static boolean isValidDateFormat(String strDate, String format) {
		// ��鳤��
		if (strDate.length() != format.length()) {
			return false;
		}

		// ����ʽ�Ƿ���ȷ
		DateFormat df = new SimpleDateFormat(format);
		df.setLenient(false);
		try {
			df.parse(strDate);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	/**
	 * ��õ���Ŀ�ʼʱ��
	 * 
	 * @return
	 */
	public static Date today() {
		return parse(format(new Date(), webFormat), webFormat);
	}

	/**
	 * �Ƚ��������ڵ��Ⱥ��ϵ
	 * 
	 * @param one
	 *            ����1
	 * @param two
	 *            ����2
	 * @return 0 �� ��ʾ��� 1 �� one����two -1 �� two����one
	 */
	public static int compareDate(Date one, Date two) {
		// �Ϸ����ж�
		if (one == null && two != null)
			return -1;
		else if (one != null && two == null)
			return 1;
		else if (one == null && two == null)
			return 0;
		else {
			if (one.getTime() > two.getTime())
				return 1;
			if (one.getTime() == two.getTime())
				return 0;
			return -1;
		}
	}

	/**
	 * @param date
	 * @return
	 */
	public static String formatToFullChinese(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String sdate = sdf.format(date);
		String newsdate[] = new String[8];
		// ȫ��ת��Ϊ����
		for (int i = 0; i < sdate.length(); i++) {
			int k = Integer.parseInt(Character.toString(sdate.charAt(i)));
			switch (k) {
			case 0:
				newsdate[i] = "��";
				break;
			case 1:
				newsdate[i] = "һ";
				break;
			case 2:
				newsdate[i] = "��";
				break;
			case 3:
				newsdate[i] = "��";
				break;
			case 4:
				newsdate[i] = "��";
				break;
			case 5:
				newsdate[i] = "��";
				break;
			case 6:
				newsdate[i] = "��";
				break;
			case 7:
				newsdate[i] = "��";
				break;
			case 8:
				newsdate[i] = "��";
				break;
			case 9:
				newsdate[i] = "��";
				break;
			}
		}
		// ����������
		List<String> s1 = new ArrayList<String>();
		for (int i = 0; i < 8; i++) {
			if (i < 4) {
				s1.add(newsdate[i]);
			} else if (i == 4) {
				s1.add("��");
				s1.add(newsdate[i]);
			} else if (i == 5) {
				s1.add(newsdate[i]);
			} else if (i == 6) {
				s1.add("��");
				s1.add(newsdate[i]);
			} else if (i == 7) {
				s1.add(newsdate[i]);
				s1.add("��");
			}

		}

		String newstr = "";
		for (String s : s1) {
			newstr += s;
		}
		/*
		 * ��ȡ�·ݡ�����
		 */
		int i = newstr.indexOf("��");
		int j = newstr.indexOf("��");
		String month = newstr.substring(i + 1, j);
		String day = newstr.substring(j + 1, newstr.length() - 1);
		/*
		 * �����·�
		 */
		String str1 = month.substring(0, 1);
		String str2 = month.substring(1);
		String newmonth = "";
		if ("��".equals(str1)) {
			newmonth = str2;
		} else if ("һ".equals(str1) && "��".equals(str2)) {
			newmonth = "ʮ";
		} else if ("һ".equals(str1) && !"��".equals(str2)) {
			newmonth = "ʮ" + str2;
		}

		/*
		 * ��������
		 */
		String st1 = day.substring(0, 1);
		String st2 = day.substring(1);
		String newday = "";
		if ("��".equals(st1)) {
			newday = st2;
		} else if ("һ".equals(st1) && "��".equals(st2)) {
			newday = "ʮ";
		} else if ("һ".equals(st1) && !"��".equals(st2)) {
			newday = "ʮ" + st2;
		} else if ("��".equals(st1) && "��".equals(st2)) {
			newday = st1 + "ʮ";
		} else if ("��".equals(st1) && !"��".equals(st2)) {
			newday = st1 + "ʮ" + st2;
		} else if ("��".equals(st1) && "��".equals(st2)) {
			newday = st1 + "ʮ";
		} else if ("��".equals(st1) && !"��".equals(st2)) {
			newday = st1 + "ʮ" + st2;
		}
		String newstring = newstr.substring(0, i) + "��" + newmonth + "��"
				+ newday + "��";
		return newstring;
	}

	// ��������תΪͨ������
	// ����һ���ַ���
	public static String convertToCNDate(String cndate) {
		int yearPos = cndate.indexOf("��");
		int monthPos = cndate.indexOf("��");
		String cnYear = cndate.substring(0, yearPos);
		String cnMonth = cndate.substring(yearPos + 1, monthPos);
		String cnDay = cndate.substring(monthPos + 1, cndate.length() - 1);
		String year = convertCNToNum(cnYear);
		String month = convertCNDateNum(cnMonth);
		String day = convertCNDateNum(cnDay);
		StringBuilder stringbuilder = new StringBuilder();
		if (year != null)
			stringbuilder.append(year + "��");
		if (month != null)
			stringbuilder.append(month + "��");
		if (day != null)
			stringbuilder.append(day + "��");
		return year + "��" + month + "��" + day + "��";

	}

	public static void main(String[] args) {
		String a = "2015��ʮ���¶�ʮһ��";
		System.out.println(convertToCNDate(a));
	}

	// ����һ��Date
	public static Date convertToCNDate(String cndate, Boolean isDate) {
		int yearPos = cndate.indexOf("��");
		int monthPos = cndate.indexOf("��");
		String cnYear = cndate.substring(0, yearPos);
		String cnMonth = cndate.substring(yearPos + 1, monthPos);
		String cnDay = cndate.substring(monthPos + 1, cndate.length() - 1);
		String year = convertCNToNum(cnYear);
		String month = convertCNDateNum(cnMonth);
		String day = convertCNDateNum(cnDay);
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, Integer.parseInt(year));
		c.set(Calendar.MONTH, Integer.parseInt(month) - 1);
		c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
		return c.getTime();

	}

	private static String convertCNDateNum(String cnNum) {
		if (cnNum.length() == 1) {
			if (cnNum.equals("ʮ"))
				return "10";
			else
				return convertCNToNum(cnNum);
		} else if (cnNum.length() == 2) {
			if (cnNum.startsWith("ʮ")) {
				return "1" + convertCNToNum(cnNum.substring(1, 2));
			}
			if (cnNum.endsWith("ʮ")) {
				return convertCNToNum(cnNum.substring(0, 1)) + "0";
			} else {
				return convertCNToNum(cnNum);
			}
		} else if (cnNum.length() == 3) {
			return convertCNToNum(cnNum.substring(0, 1) + cnNum.substring(2, 3));
		}
		return null;

	}

	private static String convertCNToNum(String cnNumStr) {
		String allCNNum = "o�𩖣�O��O��-һ�����������߰˾�";
		String allNum = "000000001123456789";
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < cnNumStr.length(); i++) {
			char c = cnNumStr.charAt(i);
			int index = allCNNum.indexOf(c);
			if (index != -1) {
				buf.append(allNum.charAt(index));
			} else {
				buf.append(c);
			}
		}
		return buf.toString();

	}

	/**
	 * �õ�Ĭ����ʼʱ��
	 * 
	 * @return ��һ��12��21��
	 */
	public static Date getDefaultYearBeginTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR) - 1, 11, 21);
		return calendar.getTime();
	}

	public static Date getDefaultBeginTime() {
		return DateUtil.addDays(new Date(), -7);
	}

	@SuppressWarnings("deprecation")
	public static Date getBeginTime() {
		Date begin = new Date();
		if (DateUtil.getMonth(begin) == 12
				&& DateUtil.getDayOfMonth(begin) >= 21)
			begin = DateUtil.parse(1900 + begin.getYear() + "-12-21",
					DateUtil.webFormat);
		else
			begin = DateUtil.parse((1900 + begin.getYear() - 1) + "-12-21",
					DateUtil.webFormat);
		return begin;
	}

	/**
	 * ������һ��
	 * 
	 * @param date
	 *            ���죬��ʽxxxx/xx/xx
	 * @return
	 */
	public static String nextDay(String date) {
		// ������������δ�ض��ǺϷ��ģ����Բ����쳣
		try {
			int index1 = date.indexOf("/");
			int index2 = date.lastIndexOf("/");
			int year = Integer.parseInt(date.substring(0, index1));
			int month = Integer.parseInt(date.substring(index1 + 1, index2));
			int day = Integer.parseInt(date.substring(index2 + 1));
			int febDay;
			if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
				febDay = 29;
			else
				febDay = 28;
			switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
				if (day < 31)
					return year + "/" + month + "/" + (day + 1);
				else
					return year + "/" + (month + 1) + "/" + "1";

			case 12:
				if (day < 31)
					return year + "/" + month + "/" + (day + 1);
				else
					return (year + 1) + "/" + "1" + "/" + "1";
			case 2:
				if (day < febDay)
					return year + "/" + month + "/" + (day + 1);
				else
					return year + "/" + 3 + "/" + "1";
			case 4:
			case 6:
			case 9:
			case 11:
				if (day < 30)
					return year + "/" + month + "/" + (day + 1);
				else
					return (year + 1) + "/" + "1" + "/" + "1";
			}
			return "";

		} catch (Exception e) {
			return "";
		}
	}

	public static Date parseToDate(String dateStr) {
		int index1 = dateStr.indexOf("/");
		int index2 = dateStr.lastIndexOf("/");
		int year = Integer.parseInt(dateStr.substring(0, index1));
		int month = Integer.parseInt(dateStr.substring(index1 + 1, index2));
		int day = Integer.parseInt(dateStr.substring(index2 + 1));
		Date date = new Date(year, month, day);
		return date;
	}
}
