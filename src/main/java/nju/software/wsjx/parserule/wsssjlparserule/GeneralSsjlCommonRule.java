package nju.software.wsjx.parserule.wsssjlparserule;

public class GeneralSsjlCommonRule {
	public String getDate(String str) {
		String date = null;
		// ���ȴ���"xxxx��x��x��"�ĳ����������������
		if (str.length() > 8 && str.contains("��") && str.contains("��")
				&& str.contains("��")) {
			date = str;
			if (date.indexOf("��") >= 4
					&& date.indexOf("��") > date.indexOf("��")
					&& (date.indexOf("��") == date.indexOf("��") + 2
							|| date.indexOf("��") == date.indexOf("��") + 3 || date
							.indexOf("��") == date.indexOf("��") + 4)) {
				String year = date.substring(date.indexOf("��") - 4,
						date.indexOf("��"));
				String month = date.substring(date.indexOf("��") + 1,
						date.indexOf("��"));
				String day = date.substring(date.indexOf("��") + 1,
						date.indexOf("��"));
				date = year + "��" + month + "��" + day + "��";
			} else {
				date = null;
			}
		}
		return date;
	}
}
