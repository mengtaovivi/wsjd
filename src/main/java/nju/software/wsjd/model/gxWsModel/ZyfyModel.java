package nju.software.wsjd.model.gxWsModel;

/**
 * Created by zhuding
 * ���鷨Ժ ��Ͻ��
 */
public class ZyfyModel {

    private String fymc;//��Ժ����

    private String larq;//��������

    private String ah;//����

    private String ay;//����

    private String aydh;//���ɴ���

    public String getFymc() {
        return fymc;
    }

    public void setFymc(String fymc) {
        this.fymc = fymc;
    }

    public String getLarq() {
        return larq;
    }

    public void setLarq(String larq) {
        this.larq = larq;
    }

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public String getAy() {
        return ay;
    }

    public void setAy(String ay) {
        this.ay = ay;
    }

    public String getAydh() {
        return aydh;
    }

    public void setAydh(String aydh) {
        this.aydh = aydh;
    }

    @Override
    public String toString() {
        return "ZyfyModel{" +
                "fymc='" + fymc + '\'' +
                ", larq='" + larq + '\'' +
                ", ah='" + ah + '\'' +
                ", ay='" + ay + '\'' +
                ", aydh='" + aydh + '\'' +
                '}';
    }
}
