package nju.software.wsjd.model.ysptWsModel.ajjbqk;

/**
 * ׷�ӵ����˶� ��һ����ͨ����
 * Created by zhuding on 2018/3/20.
 */
public class ZjdsrModel {

    private String sqsj;//����ʱ��

    private String sqr;//������

    private String dsr;//������

    private String sshly;//��ʵ������

    public String getSqsj() {
        return sqsj;
    }

    public void setSqsj(String sqsj) {
        this.sqsj = sqsj;
    }

    public String getSqr() {
        return sqr;
    }

    public void setSqr(String sqr) {
        this.sqr = sqr;
    }

    public String getDsr() {
        return dsr;
    }

    public void setDsr(String dsr) {
        this.dsr = dsr;
    }

    public String getSshly() {
        return sshly;
    }

    public void setSshly(String sshly) {
        this.sshly = sshly;
    }

    @Override
    public String toString() {
        return "ZjdsrModel{" +
                "sqsj='" + sqsj + '\'' +
                ", sqr='" + sqr + '\'' +
                ", dsr='" + dsr + '\'' +
                ", sshly='" + sshly + '\'' +
                '}';
    }
}
