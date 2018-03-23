package nju.software.wsjx.parserule.wsajjbqkparserule;

import nju.software.wsjd.model.ysptWsModel.ajjbqk.FsqqModel;
import nju.software.wsjd.model.ysptWsModel.ajjbqk.SsqqModel;
import nju.software.wsjd.model.ysptWsModel.ajjbqk.ZjdsrModel;
import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WsajjbqkModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;

import java.util.List;

/**
 * Created by zhuding on 2018/3/20.
 */
public class YsptAjjbqkParseRule extends GeneralAjjbqkCommonRule implements AjjbqkParseRule {

    @Override
    public WsajjbqkModel jxWsajjbqkModel(WsAnalyse wsAnalyse, List<WssscyrModel> wssscyrModellist) throws ParseException {
        MsysAjjbqkParseRule msysAjjbqkParseRule = new MsysAjjbqkParseRule();
        WsajjbqkModel wsajjbqkModel = msysAjjbqkParseRule.jxWsajjbqkModel(wsAnalyse, wssscyrModellist);
        List<String> ajjbqk = wsAnalyse.getAjjbqk();
        if(ajjbqk==null||ajjbqk.size()==0)
            return wsajjbqkModel;
        for (String s : ajjbqk) {
            if(s.contains("��������")){
                FsqqModel fsqqModel = new FsqqModel();
                fsqqModel.setFsr(s.substring(s.indexOf("������")+3, s.indexOf("��Ժ")));
                if(s.contains("��������") && s.contains("��ʵ������")){
                    fsqqModel.setFsqq(s.substring(s.indexOf("��������")+5, s.indexOf("��ʵ������")));
                    fsqqModel.setSshly(s.substring(s.indexOf("��ʵ�����ɣ�")+6));
                }
                wsajjbqkModel.setFsqqModel(fsqqModel);

            }else if(s.contains("��������")&& s.contains("��ʵ������")){
                SsqqModel ssqqModel = new SsqqModel();
                ssqqModel.setSsqq(s.substring(s.indexOf("��������")+5, s.indexOf("��ʵ������")));
                ssqqModel.setSshly(s.substring(s.indexOf("��ʵ�����ɣ�")+6));
                String[] strings = s.split("��");
                for (String string : strings) {
                    if(string.contains("��Ժ�����������")){
                        if(s.startsWith("������")){
                            ssqqModel.setQsr(s.substring(s.indexOf("������")+3, s.indexOf("��Ժ")));
                        }else{
                            ssqqModel.setQsr(s.substring(0, s.indexOf("��Ժ")));
                        }
                        break;
                    }
                }
                wsajjbqkModel.setSsqqModel(ssqqModel);
            }else if(s.contains("׷��") && s.contains("��ͬ")){
                ZjdsrModel zjdsrModel = new ZjdsrModel();
                String[] strings = s.split("��");
                int zjxh = 0;
                for (int i = 0; i < strings.length ;i++) {
                    String string = strings[i];
                    if(zjdsrModel.getSqsj()==null&&string.contains("��")&&string.contains("��")){
                        zjdsrModel.setSqsj(string);
                    }else if(zjdsrModel.getDsr()==null&&string.contains("����׷��")&&(string.contains("��ͬԭ��")||string.contains("��ͬ����"))){
                        zjdsrModel.setSqr(string.substring(0, string.indexOf("��Ժ����")));
                        zjdsrModel.setDsr(string.substring(string.indexOf("׷��")+2, string.indexOf("Ϊ")));
                        zjxh = i;
                    }
                }
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = zjxh; i < strings.length; i++) {
                    stringBuilder.append(strings[i]);
                    stringBuilder.append("��");
                }
                zjdsrModel.setSshly(stringBuilder.toString());
                wsajjbqkModel.setZjdsrModel(zjdsrModel);
            }
        }

        return wsajjbqkModel;
    }
}
