package nju.software.wsjx.service.model.xs;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nju.software.wsjx.util.MoneyUtil;
import nju.software.wsjx.util.NumberConverter;
import nju.software.wsjx.util.StringUtil;

/**
 * �����о���� ������
 * @author ����
 *
 */
public class FjxModel {
	private String lb;//���������
	private String se;//����
	private String bz;//����
	private String qx;//����
	
	public FjxModel() {
		super();
	}
	
	public FjxModel(XsFjxEnum fjxlb,String content){
		this.lb = fjxlb.getFjx();
		if(fjxlb==XsFjxEnum.BDZZQL){
			this.qx = content.substring(content.indexOf(fjxlb.getFjx()));
		}else if(fjxlb==XsFjxEnum.MSGRBFCC || fjxlb==XsFjxEnum.FJ){
//			this.se = getJeStr(content);
			if(fjxlb==XsFjxEnum.MSGRBFCC){
				this.se = content.substring(content.indexOf("�Ʋ�")+2);
			}
			if(fjxlb==XsFjxEnum.FJ){
				this.se = content.substring(content.indexOf("����")+2);
			}
			if(StringUtil.contains(content, "�����")){
				this.bz = "�����";
				this.se = content.substring(content.indexOf("�����")+3);
			}
			if(StringUtil.contains(this.se, "Ԫ")){
				this.bz = "�����";
				this.se = this.se.substring(0,this.se.indexOf("Ԫ")+1);
				if(!StringUtil.isNum(this.se.substring(0,this.se.length()-1))){
					this.se = NumberConverter.convertFromChinese(this.se)+"Ԫ";
				}
			}
//			����ת���ɰ���������
		}
	}
	
	public static String getJeStr(String content){
		String reg = "[һ�����������߰˾�ʮ][^x00-xff]*?Ԫ";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(content);
		if(m.find()){
			String je = m.group();
			if(je.endsWith("Ԫ")){
				je=je.substring(0, je.length()-1);
//				��������
				if(!StringUtil.isNum(je)){
					int jevalue = NumberConverter.convertFromChinese(je);
					je = jevalue+"";
				}
				je=je+"Ԫ";
			}
			
			return je;
		}
		return null;
	}
	public String getLb() {
		return lb;
	}
	public void setLb(String lb) {
		this.lb = lb;
	}
	public String getSe() {
		return se;
	}
	public void setSe(String se) {
		this.se = se;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getQx() {
		return qx;
	}
	public void setQx(String qx) {
		this.qx = qx;
	}
	

}
