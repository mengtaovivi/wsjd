package nju.software.wsjd.classify;

import nju.software.wsjd.model.ysptWsModel.ajjbqk.FsqqModel;
import nju.software.wsjd.model.ysptWsModel.ajjbqk.SsqqModel;
import nju.software.wsjd.model.ysptWsModel.ajjbqk.ZjdsrModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhuding on 2018/3/20.
 */
public class test {

    private FsqqModel fsqqModel;//�������� ��һ����ͨ����
    private SsqqModel ssqqModel;//�������� ��һ����ͨ����
    private ZjdsrModel zjdsrModel;//׷�ӵ����˶� ��һ����ͨ����

    @Override
    public String toString() {
        return "test{" +
                "fsqqModel=" + fsqqModel +
                ", ssqqModel=" + ssqqModel +
                ", zjdsrModel=" + zjdsrModel +
                '}';
    }

    public static void main(String[] args) {
        String wsssjl = "һ����������ʡ�ػ�������Ժ��2017����2403���2405�������о��������������ؼ���ʡ�ػ�������Ժ���������˹�����Ԥ���Ķ��󰸼������100Ԫ�����˻���";
//        String re3 = "��׼��(.*)��������|������(.*)Ԥ��";
        String ahreg = "[��\\(����]\\d{4}[��\\)����].*��";
        String re1= "����(.*����Ժ)("+ahreg+")";
        Pattern p3 = Pattern.compile(re1);
        Matcher m3 = p3.matcher(wsssjl);
        if (m3.find()) {
            System.out.println(m3.group(2));
        }
    }

}
