package nju.software.wsjd.model.ysptWsModel.ssjl;

/**
 * ���� ��һ��ʹ��
 * Created by zhuding on 2018/3/23.
 */
public class FsModel {
    private String fsrq;//��������

    private String yg;//ԭ��

    private String bg;//����

    public String getFsrq() {
        return fsrq;
    }

    public void setFsrq(String fsrq) {
        this.fsrq = fsrq;
    }

    public String getYg() {
        return yg;
    }

    public void setYg(String yg) {
        this.yg = yg;
    }

    public String getBg() {
        return bg;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }

    @Override
    public String toString() {
        return "FsModel{" +
                "fsrq='" + fsrq + '\'' +
                ", yg='" + yg + '\'' +
                ", bg='" + bg + '\'' +
                '}';
    }
}
