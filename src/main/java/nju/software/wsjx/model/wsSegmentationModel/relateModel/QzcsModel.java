package nju.software.wsjx.model.wsSegmentationModel.relateModel;

import java.util.List;

/**
 * Created by zhx on 2016/12/13.
 */
public class QzcsModel {
    private String qzcsCategory;//ǿ�ƴ�ʩ����
    private String qzcsTime;//ǿ�ƴ�ʩִ��ʱ��
    private String qzcsDw;//ǿ�ƴ�ʩ��λ
    private List<String> qscsReason;//ǿ�ƴ�ʩԭ��
    private String isDB;//�Ƿ����
    private String DBTime;//��������

    public String getDBTime() {
        return DBTime;
    }

    public void setDBTime(String DBTime) {
        this.DBTime = DBTime;
    }

    public String getIsDB() {
        return isDB;
    }

    public void setIsDB(String isDB) {
        this.isDB = isDB;
    }

    public String getQzcsCategory() {
        return qzcsCategory;
    }

    public void setQzcsCategory(String qzcsCategory) {
        this.qzcsCategory = qzcsCategory;
    }

    public String getQzcsTime() {
        return qzcsTime;
    }

    public void setQzcsTime(String qzcsTime) {
        this.qzcsTime = qzcsTime;
    }

    public String getQzcsDw() {
        return qzcsDw;
    }

    public void setQzcsDw(String qzcsDw) {
        this.qzcsDw = qzcsDw;
    }

    public List<String> getQscsReason() {
        return qscsReason;
    }

    public void setQscsReason(List<String> qscsReason) {
        this.qscsReason = qscsReason;
    }
}
