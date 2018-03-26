package nju.software.wsjx.parserule.wsssjlparserule;

import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;

import java.util.List;

/**
 * Created by zhuding on 2018/3/25.
 */
public class SscjrSsjlParseRule extends GeneralSsjlCommonRule implements SsjlParseRule{

    @Override
    public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist, String wsssjl) throws ParseException {
        SsjlParseRule msysSsjlParseRule = new MsysSsjlParseRule();
        WsssjlModel wsssjlModel = msysSsjlParseRule.jxWsssjlModel(wssscyrModellist, wsssjl);
        String[] contentArray = wsssjl.split("��|,|\\.|��|��|;|��");
        //ԭ��ͱ���
        String ay = wsssjlModel.getAy();
        for (String s : contentArray) {
            if (s.contains("ԭ��") && s.contains("����") && s.contains("һ��")){
                wsssjlModel.setYg(s.substring(s.indexOf("ԭ��") + 2, s.indexOf("��")));
                wsssjlModel.setBg(s.substring(s.indexOf("����") + 2, s.indexOf(ay)));
                break;
            } else if (s.contains("ԭ��") && s.contains("����")){
                wsssjlModel.setYg(s.substring(s.indexOf("ԭ��") + 2, s.indexOf("��")));
                wsssjlModel.setBg(s.substring(s.indexOf("����") + 2));
                break;
            }
        }

        contentArray = wsssjl.split("\\.|��");
        for (String s : contentArray) {
            if(s.contains("���������")){
                String[] strings = s.split("��|,|;|��");
                for (String string : strings) {
                    if(string.contains("��") && string.contains("��") && string.contains("��")){
                        wsssjlModel.setBgsqrq(string.substring(string.indexOf("��")-4, string.indexOf("��")+1));
                    }else if(string.contains("���������") && s.contains("����")){
                        wsssjlModel.setBgsqr(string.substring(0, string.indexOf("��")));
                    }
                }
                break;
            }
        }
        return wsssjlModel;
    }
}
