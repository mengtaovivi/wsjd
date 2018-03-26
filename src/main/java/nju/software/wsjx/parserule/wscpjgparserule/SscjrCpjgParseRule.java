package nju.software.wsjx.parserule.wscpjgparserule;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WscpjgModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;

import java.util.List;

/**
 * Created by zhuding on 2018/3/26.
 */
public class SscjrCpjgParseRule extends GeneralCpjgCommonRule implements CpjgParseRule {

    /**
     * @param wsAnalyse
     * @param wssscyrModellist
     * @return
     * @throws ParseException
     */
    @Override
    public WscpjgModel jxWscpjgModel(WsAnalyse wsAnalyse, List<WssscyrModel> wssscyrModellist) throws ParseException {
        MsysCpjgParseRule msysCpjgParseRule = new MsysCpjgParseRule();
        WscpjgModel wscpjgModel = msysCpjgParseRule.jxWscpjgModel(wsAnalyse, wssscyrModellist);
        List<String> cpjg = wsAnalyse.getCpjg();
        for (String s : cpjg) {
            if(s.contains("���") && s.contains("�μ�����") && s.contains("�˳�����")){
                wscpjgModel.setTdr(s.substring(s.indexOf("׼")+2, s.indexOf("���")));
                wscpjgModel.setBtdr(s.substring(s.indexOf("���")+2, s.indexOf("��Ϊ")));
                String s1 = s.substring(s.indexOf("����") + 2, s.indexOf("�μ�����"));
                if(s1.contains("ԭ��")){
                    wscpjgModel.setSsdw("ԭ��");
                }else if(s1.contains("����")){
                    wscpjgModel.setSsdw("����");
                }
                wscpjgModel.setTcssr(s.substring(s.indexOf("�μ�����")+5, s.indexOf("�˳�����")));
                break;
            }
        }
        return wscpjgModel;
    }
}
