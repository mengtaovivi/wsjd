package nju.software.wsjd.model.ysptWsModel.ssjl;

/**
 * ��¼���������뵱���ˣ����練��״�ݽ������뵱���˵�
 * Created by zhuding on 2018/3/23.
 */
public class SsrqydsrModel {

    private String sf;//

    private String name;//

    private String date;//

    public String getSf() {
        return sf;
    }

    public void setSf(String sf) {
        this.sf = sf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SsrqydsrModel{" +
                "sf='" + sf + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
