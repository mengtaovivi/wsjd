package nju.software.wsjx.parserule.wsssjlparserule;

import nju.software.wsjd.model.ysptWsModel.ssjl.FsModel;
import nju.software.wsjd.model.ysptWsModel.ssjl.SsrqydsrModel;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;

import java.util.List;

/**
 * Created by zhuding on 2018/3/23.
 */
public class YsptSsjlParseRule extends GeneralSsjlCommonRule implements SsjlParseRule{

    @Override
    public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist, String wsssjl) throws ParseException {
        SsjlParseRule msysSsjlParseRule = new MsysSsjlParseRule();
        WsssjlModel wsssjlModel = msysSsjlParseRule.jxWsssjlModel(wssscyrModellist, wsssjl);
        String[] contentArray = wsssjl.split("��|,|\\.|��|��|;|��");
        //ԭ��ͱ���
        String ay = wsssjlModel.getAy();
        for (String s : contentArray) {
            if (s.contains("ԭ��") && s.contains("����") && s.contains("һ��")){
                int index = s.indexOf("��");
                if (index == -1) {
                    index = s.indexOf("��");
                }
                wsssjlModel.setYg(s.substring(s.indexOf("ԭ��") + 2, index));
                wsssjlModel.setBg(s.substring(s.indexOf("����") + 2, s.indexOf(ay)));
                break;
            }
        }

        contentArray = wsssjl.split("\\.|��");
        for (String s : contentArray) {
            SsrqydsrModel ssrqydsrModel = new SsrqydsrModel();
            String[] detail = s.split("��|,|��|;|��");
            if(s.contains("����״")){
                ssrqydsrModel.setSf("������");
                for (String s1 : detail) {
                    if(ssrqydsrModel.getDate() ==null && s1.contains("��") && s1.contains("��")){
                        ssrqydsrModel.setDate(s1);
                    }else if(ssrqydsrModel.getName() ==null&&s1.contains("����״")){
                        ssrqydsrModel.setName(s1.substring(s1.indexOf("�յ�")+2, s1.indexOf("�ķ���״")));
                    }
                }
                wsssjlModel.setFsz(ssrqydsrModel);
            }else if(s.contains("����״")){
                ssrqydsrModel.setSf("������");
                for (String s1 : detail) {
                    if(ssrqydsrModel.getDate() ==null && s1.contains("��") && s1.contains("��")){
                        ssrqydsrModel.setDate(s1);
                    }else if(ssrqydsrModel.getName() ==null&&s1.contains("����״")){
                        ssrqydsrModel.setName(s1.substring(s1.indexOf("�յ�")+2, s1.indexOf("������״")));
                    }
                }
                wsssjlModel.setQsz(ssrqydsrModel);
            }else if(s.contains("�������")){
                FsModel fsModel = new FsModel();
                for (String s1 : detail) {
                    if(fsModel.getFsrq() == null && s1.contains("��") && s1.contains("��")){
                        fsModel.setFsrq(s1);
                    }else if(fsModel.getBg() == null && fsModel.getBg() == null && s.contains("ԭ��") && s.contains("����")){
                        fsModel.setBg(s.substring(s.indexOf("����") + 2, s.indexOf("��")));
                        fsModel.setYg(s.substring(s.indexOf("ԭ��") + 2, s.indexOf("�������")));
                    }
                }
                wsssjlModel.setFs(fsModel);
            }else if(s.contains("�ܲ���ͥ")||s.contains("��;��ͥ")){
                ssrqydsrModel.setSf("������");
                for (String s1 : detail) {
                    if (ssrqydsrModel.getDate() == null && s1.contains("��") && s1.contains("��")) {
                        ssrqydsrModel.setDate(s1);
                    }else if(ssrqydsrModel.getName() ==null&&(s1.contains("������")||s1.contains("����Ʊ����"))){
                        ssrqydsrModel.setName(s1.substring(0, s1.indexOf("����Ʊ����")));
                    }
                }
                wsssjlModel.setJjct(ssrqydsrModel);
            }else if(s.contains("��������")||s.contains("����")){
                ssrqydsrModel.setSf("������");
                for (String s1 : detail) {
                    if (ssrqydsrModel.getDate() == null && s1.contains("��") && s1.contains("��")&&!s1.contains(ay)) {
                        ssrqydsrModel.setDate(s1.substring(s1.indexOf("��")-4, s1.indexOf("��")+1));
                        s1 = s1.substring(0, s1.indexOf("��"));
                    }
                    if(s1.contains("��")&&s1.contains("Ϊ��")){
                        s1 = s1.substring(0, s1.indexOf("��"));

                    }
                    if(ssrqydsrModel.getName() ==null&&s1.contains("ԭ��")&&!s1.contains(ay)){
                        ssrqydsrModel.setName(s1.substring(s1.indexOf("ԭ��") + 2));

                        if(ssrqydsrModel.getName().equals("")){
                            ssrqydsrModel.setName("*δд��");
                        }
                    }


                }
                wsssjlModel.setCs(ssrqydsrModel);
            }
        }
        return wsssjlModel;
    }
}
