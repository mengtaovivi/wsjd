package nju.software.wsjx.parserule.msysFactorParseRule;
import net.sf.json.JSON;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;
import org.apache.poi.ss.formula.functions.Match;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.service.model.msysFactorModel.WyModel;
import nju.software.wsjx.util.StringUtil;
import nju.software.wsjx.service.impl.jtsg.CmssdpreServiceImpl;


public class WyParseRule {
    public WyModel getWygModel(WsAnalyse wsAnalyse, WsModel wsModel){
        WyModel wyModel=new WyModel();
        wyModel = getCommonElement(wsModel, wyModel);
        wyModel = getFwf(wsModel , wyModel);
        return wyModel;
    }

    //������
    public WyModel getCommonElement(WsModel wsModel ,WyModel wyModel){
        //����
        String ah = wsModel.getWswsModel().getAh();
        wyModel.setAh(ah);

        //����
        List<String> wssscyrSegement = wsModel.getWssscyrSegment();
        List<JSONObject> bg = new ArrayList<JSONObject>();
        for (String it : wssscyrSegement){
            JSONObject bgr = new JSONObject();
            if(it.startsWith("����")){
                //�ֽⱻ���������
                bgr.put("all",it);
                bgr = getBgElement(it , bgr);
                bg.add(bgr);
            }
        }
        wyModel.setBg(bg);

        //���Ϸ�
        String ssf = getSsf(wsModel.getWscpjgModel().getAjslf() , wsModel);
        wyModel.setSsf(ssf);


        return wyModel;
    }

    //�����
    public WyModel getFwf(WsModel wsModel , WyModel wyModel){
        //��Ƿ
        String wsajjbq = wsModel.getWsajjbqSegment();
        if(wsajjbq.equals("")){
            wsajjbq = wsModel.getWsssjlSegment();
        }
        if(wsajjbq.equals("")){
            wsajjbq = wsModel.getWscpjgSegment();
        }
        String qfqssj = "";
        String wyfwf = "";
        String strFwf = "";
        if(!wsajjbq.equals("")){
            Pattern pattern = Pattern.compile("([0-9]{4})��([0-9]{1,2})��(.{0,6})��(.{0,6})([0-9]{1,2})��(.{0,10})(�����|��ҵ��)(.{0,10})Ԫ");
            Matcher m = pattern.matcher(wsajjbq);
            while(m.find()) {
                strFwf = m.group();
            }
            if(!strFwf.equals("")) {
                qfqssj = strFwf.substring(0, strFwf.indexOf("��"));
                wyfwf = strFwf.substring(strFwf.lastIndexOf("��") + 1, strFwf.lastIndexOf("Ԫ") + 1);
            }
        }
        wyModel.setQfqssj(qfqssj);
        wyModel.setWyfwf(wyfwf);
        return wyModel;
    }

    //�ֽⱻ���������
    public JSONObject getBgElement(String it , JSONObject bgr){
        String itString = it.replace("��",",");
        itString = itString.replace("��",",");
        String[] itArray = itString.split(",");
        String bgXm;
        for(String str : itArray){
            //����
            if(str.startsWith("����")){
                if(str.substring(2,3).equals(":")){
                    bgXm = str.substring(3);
                }else{
                    bgXm = str.substring(2);
                }
                bgr.put("bg",bgXm);
            }
            //�Ա�
            if(str.equals("��") | str.equals("Ů")){
                bgr.put("xb" , str);
            }
            //��������
            if(str.endsWith("����")){
                bgr.put("cssj",str.substring(0,str.indexOf("����")));
            }
            //����
            if(str.endsWith("��")){
                bgr.put("mz",str.substring(0,str.indexOf("��")));
            }
            //סַ
            if(str.startsWith("��ס")){
                if(str.startsWith("��סַ")) {
                    bgr.put("zz", str.substring(3));
                }else{
                    bgr.put("zz", str.substring(2));
                }
            }
            if(str.startsWith("ס")){
                if(str.startsWith("סַ��") || str.startsWith("ס����")){
                    bgr.put("zz",str.substring(3));
                }
                else if(str.startsWith("סַ")||str.startsWith("ס��")){
                    bgr.put("zz",str.substring(2));
                }else{
                    bgr.put("zz",str.substring(1));
                }
            }
        }
        return bgr;
    }

    //��ȡ���Ϸ�
    public String getSsf(String wsSsf ,WsModel wsModel){
        String ssf = "";
        if(wsSsf != null){
            int lastIndex = wsSsf.indexOf("Ԫ")+1;
            int firsIndex = wsSsf.indexOf("��")+1;
            if(firsIndex != -1 && lastIndex != -1) {
                ssf = wsSsf.substring(firsIndex, lastIndex);
            }

        }else{
            String wscpjg = wsModel.getWscpjgSegment();
            if(wscpjg.contains("�����")){
                ssf = wscpjg.substring( wscpjg.indexOf("��")+1,wscpjg.indexOf("Ԫ")+1);
            }
        }

        return ssf;
    }
}
