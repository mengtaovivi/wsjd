package nju.software.wsjx.parserule.wsssjlparserule;

import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;

import java.util.List;

/**
 * Created by zhuding
 */
public class MsgxSsjlParseRule extends GeneralSsjlCommonRule implements SsjlParseRule{

    @Override
    public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist, String wsssjl) throws ParseException {
        MsysSsjlParseRule msysSsjlParseRule = new MsysSsjlParseRule();
        WsssjlModel wsssjlModel = msysSsjlParseRule.jxWsssjlModel(wssscyrModellist, wsssjl);
        String[] contentArray = wsssjl.split("��|,|\\.|��|��|;|��");
        //ԭ��ͱ���
        String ay = wsssjlModel.getAy();
        for (String s : contentArray) {
            if (s.contains("ԭ��") && s.contains("����")){
                int index = s.indexOf("��");
                wsssjlModel.setYg(s.substring(s.indexOf("ԭ��") + 2, index));
                wsssjlModel.setBg(s.substring(s.indexOf("����") + 2, s.indexOf(ay)));
                break;
            }
        }
        //������Ժ
        for (String s : contentArray) {
            if (s.contains("��") && s.contains("����")){
                wsssjlModel.setLafy(s.substring(0,s.indexOf("��")));
                break;
            }
        }
        //�����˺����߲ö�
        for (String s : contentArray) {
            if (s.contains("������")){
                wsssjlModel.setSsr(s.substring(s.indexOf("������") + 3,s.indexOf("��")));
                break;
            }
        }
        for (String s : contentArray) {
            if (s.contains("���²ö�") && s.contains("����Ժ") && s.contains("����")){
                wsssjlModel.setSscdfymc(s.substring(s.indexOf("����") + 2, s.indexOf("����Ժ") + 4));
                wsssjlModel.setSscdah(s.substring(s.indexOf("����Ժ") + 4,s.indexOf("���²ö�")));
                break;
            }
        }
        return wsssjlModel;
    }
}
