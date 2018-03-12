package nju.software.wsjx.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nju.software.wsjx.model.Enum.HeadEnum;
import nju.software.wsjx.model.Enum.WslbEnum;

/**
 * ���๤����
 * 
 * @author lr12
 *
 */
public class SegmentUtil {

	/**
	 * Ԥ�������ջ����з֣���һЩ�ո��滻
	 * @param words
	 * @return
	 */
	public static List<String> toParagraph(String words) {
		List<String> paragraphs = new ArrayList<String>();
		if (words != null) {
			words = words.replaceAll("[\\r]", "");
			words = words.replaceAll("[\\t]", "");
			// ȥ��ȫ�ǰ�ǿո�
			words = words.replaceAll("��", "");
			words = words.replaceAll(" ", "");
			String[] qfsj = words.split("\n");
			for (int i = 0; i < qfsj.length; i++) {
				if (!qfsj[i].equals("")) {
					paragraphs.add(qfsj[i]);
				}

			}

		}

		return paragraphs;
	}

	/**
	 * ��ȡ����������磨ͥ���¼����������ȣ�
	 * @param paragraphs
	 * @return
	 */
	public static WslbEnum getWslb(List<String> paragraphs){
		if(paragraphs==null||paragraphs.size()<2)
			return WslbEnum.QT;
		String wslbWords=getWslbparagraphs(paragraphs);
		Pattern tsblpPattern=Pattern.compile("��¼");
		Matcher matcher= tsblpPattern.matcher(wslbWords);
		if(matcher.find()){
			return WslbEnum.TSBL;
		}
		if(wslbWords.contains("�о�"))
			return WslbEnum.PJS;
		if(wslbWords.contains("�ö�"))
			return WslbEnum.CDS;
		if(wslbWords.contains("����"))
			return WslbEnum.TJS;
		return WslbEnum.QT;
	}
	/**
	 * ��ȡ��������
	 * @param paragraphs
	 * @return
	 */
	public static String getWslbparagraphs(List<String> paragraphs){
		if(paragraphs==null||paragraphs.size()<2)
			return null;
		int index=-1;
		for(int i=0;i<paragraphs.size();i++){
			if(paragraphs.get(i).contains("��Ժ")){
				index=i;
				break;
			}
		}
		if(index==-1||index>=paragraphs.size()-1)
			return null;
		//��ȡ�ڶ��λ�
		return paragraphs.get(index+1);
		
	}
}
