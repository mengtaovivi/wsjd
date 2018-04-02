package nju.software.wsjx.parserule.wsajjbqkparserule;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WsajjbqkModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuding on 2018/3/31.
 */
public class EsAjjbqkParseRule extends GeneralAjjbqkCommonRule implements AjjbqkParseRule {

    @Override
    public WsajjbqkModel jxWsajjbqkModel(WsAnalyse wsAnalyse, List<WssscyrModel> wssscyrModellist) throws ParseException {
        List<String> ajjbqk = wsAnalyse.getAjjbqk();
        List<String> tempAjjbqk = new ArrayList<>();
        for (String s : ajjbqk) {
            for (WssscyrModel wssscyrModel : wssscyrModellist) {
                if(s.contains(wssscyrModel.getSscyr()))
                    s = s.replaceAll(wssscyrModel.getSscyr(), wssscyrModel.getSssf());
            }
            tempAjjbqk.add(s);
        }

        WsajjbqkModel wsajjbqkModel = new WsajjbqkModel();

        if(ajjbqk.size()==0){
            return wsajjbqkModel;
        }

        List<List<String>> keySentence = new ArrayList<>();
        for (String s : tempAjjbqk) {
            String[] strings = s.split("\\.|��");
            List<String> list = new ArrayList<>();
            for (String string : strings) {

                if(string.contains("��")){
                    string = string.substring(0, string.indexOf("��"));
                }
                if(string.contains(":")){
                    string = string.substring(0, string.indexOf(":"));
                }
                boolean isContainsFY = string.contains("��Ժ")||string.contains("��Ժ");
                boolean isContainsRD = string.contains("��Ϊ")||string.contains("����")||string.contains("�϶�")||string.contains("����")||string.contains("�о�");
                boolean isContainsSSCYR = string.contains("������")||string.contains("ԭ��")||string.contains("����");
                boolean isContainsSS = string.contains("���")||string.contains("�߳�")||string.contains("��������")||string.contains("����");
                boolean isContainsFL = string.contains("��");
                if(((isContainsFY && isContainsRD) || (isContainsSSCYR && isContainsSS)) && !isContainsFL ){
                    list.add(string);
                }
            }
            keySentence.add(list);
        }



        for (int i = 0; i < keySentence.size(); i++) {
            List<String> strings = keySentence.get(i);
            System.out.println(strings);

        }



        return wsajjbqkModel;
    }
}
