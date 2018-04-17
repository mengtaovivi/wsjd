package nju.software.preProcess;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcFtModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zhuding on 2018/4/15.
 */
public class WsFtParse {

    private WsAnalyse wsAnalyse;

    public WsFtParse(WsAnalyse wsAnalyse) {
        this.wsAnalyse = wsAnalyse;
    }

    public ArrayList<WscpfxgcFtModel> getFtModelList(){
        ArrayList<String> contentlist = WsAnalyse.getWholeContent(wsAnalyse.getCpfxgc()
                .get(wsAnalyse.getCpfxgc().size() - 1));
        int index = 0;
        //��ȡ���з������̶�
        for (int i = 0; i < contentlist.size(); i++) {
            if (contentlist.get(i).contains("����")) {
                index = i;
                break;
            } else if (contentlist.get(i).contains("����")
                    || contentlist.get(i).contains("����")
                    || contentlist.get(i).contains("����")) {
                index = i;
            }
        }
        // ������������
        String ftString = "";// ����
        for (int j = index; j < contentlist.size(); j++) {
            ftString += contentlist.get(j);
        }
        String[] ftfz = ftString.split("��");
        //ɾ���������һ������Ĺ涨���ַ�
        ftfz[ftfz.length-1] = delGd(ftfz[ftfz.length-1]);
        ArrayList<WscpfxgcFtModel> ftModellist = new ArrayList<WscpfxgcFtModel>();
        for (int j = 0; j < ftfz.length; j++) {
            String content = ftfz[j];
            if (content.contains("��")) {
                WscpfxgcFtModel ftModel = new WscpfxgcFtModel();
                String flftmc = content.substring(0, content.indexOf("��"));
                ftModel.setFlftmc(flftmc);
                //��ȡ��Ŀ��Ŀ
                ftModel.setFtMap(getTmkm(content));
                ftModellist.add(ftModel);
            }
        }
        return ftModellist;
    }


    /**
     * ����content��ȡ��Ŀ��Ŀ��������Ŀ��Ŀ��map
     * @param content
     * @return HashMap<String, String>
     */
    private HashMap<String, String> getTmkm(String content){
        content = content.substring(content.indexOf("��") + 1);
        String tmString[] = content.split("��");
        //����*��*�����ֶ�
        if(content.lastIndexOf("��")==content.length()-1){
            String[] copy=new String[tmString.length+1];
            for(int i=0;i<tmString.length;i++){
                copy[i]=tmString[i];
            }
            copy[copy.length-1]=tmString[tmString.length-1].lastIndexOf("��")==-1?"":tmString[tmString.length-1].substring(tmString[tmString.length-1].lastIndexOf("��"));
            tmString=copy;
        }
        HashMap<String, String> ftMap = new HashMap<String, String>();// ����map<��Ŀ����Ŀ>
        if((tmString.length == 1)){
            if(!tmString[0].contains("��")){
                if(!tmString[0].contains("��") && !tmString[0].contains("��")){
                    //����ʮ��֮�涨
                    if(tmString[0].contains("��")){
                        ftMap.put(tmString[0].substring(tmString[0].indexOf("��") + 1,tmString[0].length()), "û�п�Ŀ");
                    }
                    //��ʮ����֮�涨
                    else{
                        ftMap.put(tmString[0], "û�п�Ŀ");
                    }
                }
                else{
                    //����ʮ��������֮�涨
                    if(tmString[0].contains("��")){
                        if(tmString[0].indexOf("��") != tmString[0].lastIndexOf("��")){
                            int d1 = tmString[0].indexOf("��");
                            String qtmString = tmString[0].substring(tmString[0].indexOf("��") + 1);
                            int d2 = qtmString.indexOf("��")+tmString[0].indexOf(qtmString);
                            String tm = tmString[0].substring(d1 + 1,d2);
                            String km = tmString[0].substring(d2,tmString[0].length());
                            ftMap.put(tm, km);
                        }
                    }
                }
            }
            else {
                //����ʮ�š�����ʮ����֮�涨
                if(tmString[0].indexOf("��") < tmString[0].indexOf("��")){
                    String temp = tmString[0].substring(0,tmString[0].indexOf("��"));

                    if(!temp.contains("��") && !temp.contains("��")){
                        ftMap.put(tmString[0].substring(
                                tmString[0].indexOf("��") + 1,
                                tmString[0].indexOf("��")), "û�п�Ŀ");
                    }
                    String temp2 = tmString[0].substring(tmString[0].indexOf("��") + 1);
                    if(!temp2.contains("��") && !temp2.contains("��") && temp2.contains("��")){
                        ftMap.put(temp2.substring(temp2.indexOf("��") + 1), "û�п�Ŀ");
                    }
                }
            }
        }

        for (int i = 0; i < tmString.length - 1; i++) {
            if (!tmString[i + 1].contains("��")
                    && !tmString[i + 1].contains("��")
                    && tmString[i].contains("��")) {
                ftMap.put(tmString[i].substring(
                        tmString[i].lastIndexOf("��") + 1,
                        tmString[i].length()), "û�п�Ŀ");
                //����ʮ����������ʮ֮�涨
                if((i == tmString.length - 2) && tmString[i + 1].contains("��")){

                    ftMap.put(tmString[i+1].substring(
                            tmString[i+1].lastIndexOf("��") + 1,
                            tmString[i+1].length()), "û�п�Ŀ");
                }
            } else if (tmString[i].contains("��")) {
                String tm = tmString[i].substring(
                        tmString[i].lastIndexOf("��") + 1,
                        tmString[i].length());

                String km = "";
                if (i < tmString.length - 1
                        && tmString[i + 1].contains("��")) {
                    km = tmString[i + 1].substring(0,
                            tmString[i + 1].lastIndexOf("��") + 1);
                    ftMap.put(tm, km);
                }
                if (i < tmString.length - 1
                        && tmString[i + 1].contains("��")) {
                    km = tmString[i + 1].substring(0,
                            tmString[i + 1].lastIndexOf("��") + 1);
                    if(ftMap.get(tm) != null){
                        if(!ftMap.get(tm).contains(km))
                            ftMap.put(tm, km);
                    }
                    else {
                        ftMap.put(tm, km);
                    }
                }

            }
        }
        return ftMap;
    }
    /**
     * ɾ�����涨�����ַ���
     * @param zh
     * @return
     */
    private String delGd(String zh){
        String[] gdList = {"�Ĺ涨","֮�涨","�涨"};
        for(String gd:gdList){
            if (zh.contains("��")) {
                String shString = zh.substring(zh.indexOf("��"));
                if(shString.contains(gd)){
                    return zh.substring(0,zh.indexOf(shString) + shString.lastIndexOf(gd));
                }
            }
        }
        return zh;
    }

}
