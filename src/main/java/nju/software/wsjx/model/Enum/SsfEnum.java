package nju.software.wsjx.model.Enum;

public enum SsfEnum {
	SLF("�����"),
	SSFY("���Ϸ���"),
	SSF("���Ϸ�"),
	CCBQF("�Ʋ���ȫ�����"),
	BQF("��ȫ��"),
	GGF("�����"),
	JDF("������"),
	YDF("�ʵݷ�"),
	YDSDF("�ʼ��ʹ��"),
	QTFY("��������"),
	QTF("����"),
	JBSQ("������ȡ"),
	JBJN("���뽻��");
	private String ssfName;//���Ϸ�����

	private SsfEnum() {
	}

	private SsfEnum(String ssfName) {
		this.ssfName = ssfName;
	}

	public String getSsfName() {
		return ssfName;
	}

	public void setSsfName(String ssfName) {
		this.ssfName = ssfName;
	}
	

}
