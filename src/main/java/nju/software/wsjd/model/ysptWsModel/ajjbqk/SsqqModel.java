package nju.software.wsjd.model.ysptWsModel.ajjbqk;

/**
 * �������� ��һ����ͨ����
 * Created by zhuding on 2018/3/20.
 */
public class SsqqModel {

    private String qsr;//������

    private String ssqq;//��������

    private String sshly;//��ʵ������

    public String getQsr() {
        return qsr;
    }

    public void setQsr(String qsr) {
        this.qsr = qsr;
    }

    public String getSsqq() {
        return ssqq;
    }

    public void setSsqq(String ssqq) {
        this.ssqq = ssqq;
    }

    public String getSshly() {
        return sshly;
    }

    public void setSshly(String sshly) {
        this.sshly = sshly;
    }

    @Override
    public String toString() {
        return "SsqqModel{" +
                "qsr='" + qsr + '\'' +
                ", ssqq='" + ssqq + '\'' +
                ", sshly='" + sshly + '\'' +
                '}';
    }
}
