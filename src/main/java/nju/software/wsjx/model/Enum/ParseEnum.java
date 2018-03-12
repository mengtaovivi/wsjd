package nju.software.wsjx.model.Enum;

import nju.software.wsjx.util.StringUtil;

public enum ParseEnum {
	MSYS("����һ��","Msys","ParseMsysSegment","MsysCaseInfo","",""),
	MSGX("���¹�Ͻ","Msgx","ParseMsgxSegment","MsysCaseInfo","",""),
	MSES("���¶���","Mses","ParseMsesSegment","MsesCaseInfo","",""),
	MSZS("��������","Mszs","ParseMszsSegment","MszsCaseInfo","",""),
	MSZSSC("�����������","Mszssc","ParseMszsSegment","MszsscCaseInfo","","");
//	XZYS("����һ��","XZYS","ParseXzysSegment","XzysCaseInfo","",""),
//	XZES("��������","XZES","ParseXzesSegment","XzesCaseInfo","",""),
//	XZZS("��������","XZZS","ParseXzzsSegment","XzzsCaseInfo","",""),
//	XZZSSC("�����������","XZZSSC","ParseXzzsSegment","XzzsscCaseInfo","",""),
//	XSYS("����һ��","XSYS","ParseXsysSegment","XsysCaseInfo","",""),
//	XSES("���¶���","XSES","ParseXsesSegment","XsesCaseInfo","",""),
//	XSZS("��������","XSZS","ParseXszsSegment","XszsCaseInfo","",""),
//	XSZSSC("�����������","Xszssc","ParseXszsSegment","XszsscCaseInfo","","");
	private String parse;//��������
	private String handlerName;//����
	private String parseDocumentName;//ParseXzesSegment
	private String caseinfoName;//XzesCaseInfo
	private String tsblSegment;
	private String tsblModel;
	private ParseEnum() {
	}			
	
	private ParseEnum(String parse, String handlerName,
			String parseDocumentName, String caseinfoName, String tsblSegment,
			String tsblModel) {
		this.parse = parse;
		this.handlerName = handlerName;
		this.parseDocumentName = parseDocumentName;
		this.caseinfoName = caseinfoName;
		this.tsblSegment = tsblSegment;
		this.tsblModel = tsblModel;
	}

	public String getParse() {
		return parse;
	}
	public void setParse(String parse) {
		this.parse = parse;
	}
	public String getHandlerName() {
		return handlerName;
	}
	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}
	
	
	public String getParseDocumentName() {
		return parseDocumentName;
	}

	public void setParseDocumentName(String parseDocumentName) {
		this.parseDocumentName = parseDocumentName;
	}

	public String getCaseinfoName() {
		return caseinfoName;
	}

	public void setCaseinfoName(String caseinfoName) {
		this.caseinfoName = caseinfoName;
	}

	public String getTsblSegment() {
		return tsblSegment;
	}
	public void setTsblSegment(String tsblSegment) {
		this.tsblSegment = tsblSegment;
	}
	public String getTsblModel() {
		return tsblModel;
	}
	public void setTsblModel(String tsblModel) {
		this.tsblModel = tsblModel;
	}
	public static String getHandlerByParse(String Parse){
		for(ParseEnum parseEnum:ParseEnum.values()){
			if(StringUtil.equals(parseEnum.getParse(), Parse)){
				return parseEnum.getHandlerName();
			}
		}
		return null;
	}
	public static ParseEnum getParseEnumByParse(String Parse){
		for(ParseEnum parseEnum:ParseEnum.values()){
			if(StringUtil.equals(parseEnum.getParse(), Parse)){
				return parseEnum;
			}
		}
		return null;
	}
}
