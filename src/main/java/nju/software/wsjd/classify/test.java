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
        String wsssjl = "�������ɹ��������߼�����Ժ��2012������ֵ�38�������о�";
        String reg = "[��\\(����]\\d{4}[��\\)����].+?[^��]�ֵ�?\\d+-?\\d+��";
        String re = "����(.*����Ժ)(" + reg + ")";
        Pattern p1 = Pattern.compile(re);
        Matcher m1 = p1.matcher(wsssjl);
        while (m1.find()) {
            System.out.println(m1.group(1));
            System.out.println(m1.group(2));
        }

    }

}
