package nju.software.wsjx.service.model.msysFactorModel;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import java.util.List;

public class WyModel {

    /**
     * �ο������ȡ��model�����������Ҫ��ȡ��Ҫ������
     */

    /**
     * ������Ŀ
     */
    private String ah;                  //һ ����
    private List<JSONObject> bg;              //�� ����
//    private String csny;                //�� ��������
//    private String mz;                  //�� ����
//    private String zz;                  //�� סַ
    private String qfqssj;              //�� Ƿ����ʼʱ��
    private String wyfwf;               //�� ��ҵ�����
    private String jdssf;               //�� ������ʩ��
    private String rzsj;                //ʮ��סʱ��
    private String fwmj;                //ʮһ �������
    private String sqtjsj;              //ʮ�� ��ǰ����ʱ��
    private String ssf;                 //ʮ�� ���Ϸ�

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public List<JSONObject> getBg() {
        return bg;
    }

    public void setBg(List<JSONObject> bg) {
        this.bg = bg;
    }

//    public String getXb() {
//        return xb;
//    }
//
//    public void setXb(String xb) {
//        this.xb = xb;
//    }
//
//    public String getCsny() {
//        return csny;
//    }
//
//    public void setCsny(String csny) {
//        this.csny = csny;
//    }
//
//    public String getMz() {
//        return mz;
//    }
//
//    public void setMz(String mz) {
//        this.mz = mz;
//    }
//
//    public String getZz() {
//        return zz;
//    }
//
//    public void setZz(String mz) {
//        this.zz = zz;
//    }

    public String getQfqssj() {
        return qfqssj;
    }

    public void setQfqssj(String qfqjsj) {
        this.qfqssj = qfqjsj;
    }

    public String getWyfwf() {
        return wyfwf;
    }

    public void setWyfwf(String wyfwf) {
        this.wyfwf = wyfwf;
    }

    public String getJdssf() {
        return jdssf;
    }

    public void setJdssf(String jdssf) {
        this.jdssf = jdssf;
    }

    public String getrzsj() {
        return rzsj;
    }

    public void setRzsj(String rzsj) {
        this.rzsj = rzsj;
    }

    public String getFwmj() {
        return fwmj;
    }

    public void setFwmj(String fwmj) {
        this.fwmj = fwmj;
    }

    public String getSqtjsj() {
        return sqtjsj;
    }

    public void setSqtjsj(String sqtjsj) {
        this.sqtjsj = sqtjsj;
    }

    public String getSsf() {
        return ssf;
    }

    public void setSsf(String ssf) {
        this.ssf = ssf;
    }

}
