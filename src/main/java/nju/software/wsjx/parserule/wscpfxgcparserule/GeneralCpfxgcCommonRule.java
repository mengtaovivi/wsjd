package nju.software.wsjx.parserule.wscpfxgcparserule;

import java.util.HashMap;
/**
 * ���з������̹��õķ���
 * @author wangzh
 */
public class GeneralCpfxgcCommonRule {

	/**
	 * ɾ�������е���������
	 * @param fltw
	 * @return
	 */
	protected String delUrl(String fltw) {
		//ɾ�������в��ɼ��ַ�\u200b
		String str = "";
		for (int i = 0; i < fltw.length(); i++) {
			int ch = (int) fltw.charAt(i);
			if (ch != '\u200b')
				str += fltw.charAt(i);
		}
		//ɾ�������д��ڵ���������
		while(str.contains("(http") || str.contains("(javascript")){
			int zkh = str.indexOf("(http");
			if(zkh == -1)
				zkh = str.indexOf("(javascript");
			int khNum = 1;
			int ykh = zkh;
			while(khNum != 0){
				ykh++;
				if(str.charAt(ykh) == '(')
					khNum++;
				if(str.charAt(ykh) == ')')
					khNum--;
			}
			str = str.substring(0, zkh) + str.substring(ykh + 1);
		}
		return str;
	}
}
