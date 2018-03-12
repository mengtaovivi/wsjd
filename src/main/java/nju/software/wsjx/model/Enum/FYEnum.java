package nju.software.wsjx.model.Enum;



/**
 * ��Ժ��Enum
 * @author byron
 *
 */
public enum FYEnum {
	
	TJGY("����и߼�����Ժ","�и߼���Ժ","120000 200","130.1.1.111"),
	TJYZY("����е�һ�м�����Ժ","��һ�м���Ժ","120100 210","130.2.0.110"),//,������
	TJEZY("����еڶ��м�����Ժ","�ڶ��м���Ժ","120200 220","130.3.100.36"),
	TJHS("����·�Ժ","���·�Ժ","960200 230","130.4.1.196"),
	TJHP("����к�ƽ������Ժ","��ƽ����Ժ","120101 211","130.6.0.200"),
	TJNK("������Ͽ�������Ժ","�Ͽ�����Ժ","120104 212","130.5.0.14"),
	TJHX("����к���������Ժ","��������Ժ","120203 222","130.10.0.167"),
	TJHD("����кӶ�������Ժ","�Ӷ�����Ժ","120202 221","130.9.40.13"),
	TJHB("����кӱ�������Ժ","�ӱ�����Ժ","120105 213","130.7.0.7"),
	TJHQ("����к���������Ժ","��������Ժ","120106 214","130.8.0.73"),//������
	TJBH("��������Ժ","����������Ժ","120242 22A","130.25.1.13"),
	TJTG("���������������Ժ","����������","120204 223","130.15.0.21"),
	TJHG("����к���������Ժ","����������","120205 224","130.16.0.18"),
	TJDG("����д��������Ժ","���������","120206 225","130.17.0.12"),
	TJKFQ("����о��ü�������������Ժ","������������","120241 229","130.23.0.15"),
	TJDL("����ж���������Ժ","��������Ժ","120207 226","130.13.0.13"),
	TJJN("����н���������Ժ","��������Ժ","120208 227","130.14.0.22"),
	TJXQ("���������������Ժ","��������Ժ","120107 215","130.11.1.5"),
	TJBC("����б���������Ժ","��������Ժ","120108 216","130.12.0.2"),
	TJWQ("���������������Ժ","��������Ժ","120222 217","130.19.0.12"),
	TJBD("����б���������Ժ","��������Ժ","120224 219","130.21.0.5"),
	TJJH("����о���������Ժ","�����ط�Ժ","120223 218","130.20.1.8"),
	TJNH("����������Ժ","�����ط�Ժ","120221 228","130.18.0.11"),
	JX("����м�������Ժ","���ط�Ժ","120225 21A","130.22.0.8"),
	TJTL("�������·���䷨Ժ","��·��Ժ","920103 132","130.26.0.7"),
	HJ("���ȫ�з�Ժ�ϼ�","�ϼ�","1200");

	String name;
	
	String jc;
	
	String fydm;

	String fydz;
	
	private FYEnum(String name, String jc, String fydm) {
		this.name = name;
		this.jc = jc;
		this.fydm = fydm;
	}

	
	private FYEnum(String name, String jc, String fydm, String fydz) {
		this.name = name;
		this.jc = jc;
		this.fydm = fydm;
		this.fydz = fydz;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJc() {
		return jc;
	}

	public void setJc(String jc) {
		this.jc = jc;
	}

	public String getFydm() {
		return fydm;
	}

	public void setFydm(String fydm) {
		this.fydm = fydm;
	}
	
	public String getFydz() {
		return fydz;
	}

	public void setFydz(String fydz) {
		this.fydz = fydz;
	}

	
	
	public static String getFYDMByName(String name) {
		for(FYEnum fy:FYEnum.values()){
			if(fy.getName().equals(name))
				return fy.getFydm();
		}
		return null;
	}
	
	public static String getNameByFYDM(String fydm) {
		for(FYEnum fy:FYEnum.values()){
			if(fy.getFydm().equals(fydm))
				return fy.getName();
		}
		return null;
	}
}
