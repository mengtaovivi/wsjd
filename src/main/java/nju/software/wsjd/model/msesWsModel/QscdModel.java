package nju.software.wsjd.model.msesWsModel;

/**
 * ǰ��ö��Ĵ��� ���¶���
 * Created by zhuding on 2018/3/27.
 */
public class QscdModel {

    private String fymc;//��Ժ����

    private String ah;//����

    private String pjfs;//�о���ʽ

    public String getFymc() {
        return fymc;
    }

    public void setFymc(String fymc) {
        this.fymc = fymc;
    }

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public String getPjfs() {
        return pjfs;
    }

    public void setPjfs(String pjfs) {
        this.pjfs = pjfs;
    }

    @Override
    public String toString() {
        return "QscdModel{" +
                "fymc='" + fymc + '\'' +
                ", ah='" + ah + '\'' +
                ", pjfs='" + pjfs + '\'' +
                '}';
    }
}
