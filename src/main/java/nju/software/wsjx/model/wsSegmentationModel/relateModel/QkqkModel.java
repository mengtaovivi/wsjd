package nju.software.wsjx.model.wsSegmentationModel.relateModel;

import java.util.List;

/**
 * Created by zhx on 2016/12/15.
 */
public class QkqkModel {
    private String qklb;//ǰ�����
    private String cfTime;//����ʱ��
    private List<String> cfReason;//����ԭ��
    private String cfdw;//������λ
    private String cfxs;//������ʽ
    private List<String> cfxq;//��������
    private String xmsfTime;//�����ͷ�����

    public String getQklb() {
        return qklb;
    }

    public void setQklb(String qklb) {
        this.qklb = qklb;
    }

    public String getCfTime() {
        return cfTime;
    }

    public void setCfTime(String cfTime) {
        this.cfTime = cfTime;
    }

    public List<String> getCfReason() {
        return cfReason;
    }

    public void setCfReason(List<String> cfReason) {
        this.cfReason = cfReason;
    }

    public String getCfdw() {
        return cfdw;
    }

    public void setCfdw(String cfdw) {
        this.cfdw = cfdw;
    }

    public String getCfxs() {
        return cfxs;
    }

    public void setCfxs(String cfxs) {
        this.cfxs = cfxs;
    }

    public List<String> getCfxq() {
        return cfxq;
    }

    public void setCfxq(List<String> cfxq) {
        this.cfxq = cfxq;
    }

    public String getXmsfTime() {
        return xmsfTime;
    }

    public void setXmsfTime(String xmsfTime) {
        this.xmsfTime = xmsfTime;
    }
}
