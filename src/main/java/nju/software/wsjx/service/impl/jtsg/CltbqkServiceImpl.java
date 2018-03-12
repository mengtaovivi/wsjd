package nju.software.wsjx.service.impl.jtsg;

import java.util.List;

import nju.software.wsjx.service.jtsg.CltbqkService;

public class CltbqkServiceImpl implements CltbqkService {

	@Override
	public boolean isToubao(List<String> cmsslist) {
		boolean b = true;
		for (String cmss : cmsslist) {
			if (cmss.contains("δͶ��")) {
				b = false;
				break;
			}
		}
		return b;
	}

	@Override
	public String touBaoXZ(List<String> cmsslist) {
		String touBaoXZ = null;
		boolean hasJqx = false;
		boolean hasSybx = false;
		for (String cmss : cmsslist) {
			if (!(cmss.contains("δ") || cmss.contains("û��"))) {
				if (cmss.contains("��ǿ��") || cmss.contains("��ͨ�¹�����ǿ�Ʊ���")) {
					hasJqx = true;
					break;
				}
			}
		}
		for (String cmss : cmsslist) {
			if (!(cmss.contains("δ") || cmss.contains("û��"))) {
				if (cmss.contains("��ҵ����")||cmss.contains("��ҵ������")||cmss.contains("��������ҵ������")) {
					hasSybx = true;
					break;
				}
			}
		}
		if (hasJqx) {
			if (hasSybx) {
				touBaoXZ = "��ͨ�¹�����ǿ�Ʊ���,������������ҵ����";
			} else {
				touBaoXZ = "��ͨ�¹�����ǿ�Ʊ���";
			}
		} else if (hasSybx) {
			touBaoXZ = "������������ҵ����";
		} 
		return touBaoXZ;
	}

	@Override
	public boolean isValid(List<String> cmsslist) {
		boolean b = true;
		for (String cmss : cmsslist) {
			if (cmss.contains("����")
					&& (cmss.contains("�ѹ���") || cmss.contains("�ѹ���") || cmss.contains("���ڱ�����") )) {
				b = false;
			}
		}
		return b;
	}

	@Override
	public boolean isPay(List<String> cmsslist) {
		boolean b = true;
		for (String cmss : cmsslist) {
			if (cmss.contains("�����⸶")) {
				return b;
			}
		}
		return b;
	}

	@Override
	public boolean identifyIsTrue(List<String> cmsslist) {
		boolean b = true;
		for (String cmss : cmsslist) {
			if(cmss.contains("����")&&(cmss.contains("������")||cmss.contains("��Ч"))){
				b = false;
			}
		}
		return b;
	}

	@Override
	public String getShangQing(List<String> cmsslist) {
		String str1 = "���";
		String shangQing = null;
		for(String cmss : cmsslist){
			if(cmss.contains(str1)){
				String str = cmss.substring(cmss.indexOf(str1));
				shangQing = str.substring(0, str.indexOf("��")+1);
			}
		}
		return shangQing;
	}

	@Override
	public String getRealPay(List<String> cmsslist) {
		String realPay = null;
		for(String cmss : cmsslist){
			if(cmss.contains("����")||cmss.contains("�ܼ�")){
				if(cmss.contains("����")){
					String str1 = cmss.substring(cmss.indexOf("����")+2);
					str1 = str1.substring(0, str1.indexOf("Ԫ")+1);
					if(str1.indexOf("��")==0){
						str1 = str1.substring(1);
					}
					if(str1.indexOf("�����")==0){
						str1 = str1.substring(3);
					}
					realPay = str1;
					break;
				}
				else if(cmss.contains("�ܼ�")){
					String str = cmss.substring(cmss.indexOf("�ܼ�")+2);
					str = str.substring(0, str.indexOf("Ԫ")+1);
					if(str.indexOf("��")==0){
						str = str.substring(1);
					}
					if(str.indexOf("�����")==0){
						str = str.substring(3);
					}
					realPay = str;
					break;
				}
			}
		}
		return realPay;
	}

	@Override
	public String getIdentifyContent(List<String> cmsslist) {
		String str1 = "���������";
		String str2 = "�������Ϊ��";
		String str3 = "�������Ϊ��";
		String identifyContent = null;
		for(String cmss : cmsslist){
			if(cmss.contains(str1)||cmss.contains(str2)||cmss.contains(str3)){
				if(cmss.contains(str1)){
					String str = cmss.substring(cmss.indexOf(str1)+5);
					identifyContent = str.substring(0, str.indexOf("��")+1);
				}
				if(cmss.contains(str2)){
					String str = cmss.substring(cmss.indexOf(str2)+6);
					identifyContent = str.substring(0, str.indexOf("��")+1);
				}
				if(cmss.contains(str3)){
					String str = cmss.substring(cmss.indexOf(str3)+6);
					identifyContent = str.substring(0, str.indexOf("��")+1);
				}
			}
		}
		return identifyContent;
	}
}
