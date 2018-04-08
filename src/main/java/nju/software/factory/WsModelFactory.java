package nju.software.factory;

import nju.software.classify.BaseClassifier;
import nju.software.classify.ParseMap;
import nju.software.vo.DocType;
import nju.software.wsjx.business.PreWsAnalyse;
import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.model.wsSegmentationModel.WswsModel;
import nju.software.wsjx.parse.ParseSegment;
import nju.software.wsjx.util.ListToString;

/**
 * Created by away on 2018/3/28.
 */
// 同一份文书只存在一篇
public class WsModelFactory {

    private static volatile String name;
    private static volatile WsModel wsModel;
    private static final String CLASSIFY_PREFIX = "nju.software.classify.";
    private static final String PARSE_PREFIX = "nju.software.wsjx.parse.";
    private static final String XML_PATH = "xml";
    private WsModelFactory() {}

    public static WsModel getInstance(String content, String filename) {
        // 文书改变或第一次初始化文书时生成
        if (!filename.equals(name) || wsModel == null) {
            synchronized (WsModel.class) {
                if (!filename.equals(name) || wsModel == null) {
                    wsModel = jxWs(content,filename);
                    name = filename;
                }
            }
        }
        return wsModel;
    }

    private static WsModel jxWs(String content,String filename){
        PreWsAnalyse preWsAnalyse=new PreWsAnalyse(filename,  content);
        WswsModel wswsModel=preWsAnalyse.handleWsws();
        WsAnalyse wsAnalyse = new WsAnalyse(filename, content);
        final String ah = wswsModel.getAh();
        String classifierName = null;
        for (String s : ParseMap.classifierNameKeys()) {
            if(ah.contains(s)){
                classifierName = ParseMap.getInstance().getClassifierName(s);
            }
        }
        if(classifierName != null){
            String string = CLASSIFY_PREFIX + classifierName;
            try {
                System.out.println(string);
                Class<?> classifier = Class.forName(string);
                BaseClassifier baseClassifier = (BaseClassifier) classifier.newInstance();

                String parseRuleName = PARSE_PREFIX+baseClassifier.getParseRuleName();
                Class<?> parseDocumentClass = Class.forName(parseRuleName);
                ParseSegment parseCaseinfo = (ParseSegment) parseDocumentClass.newInstance();
                parseCaseinfo.registerWsAnalyse(wsAnalyse);
                wsModel = parseCaseinfo.transformToWsModel();
                fillWsModelSegment(wsModel, wsAnalyse);
                wsModel.setDocType(baseClassifier.getType(wsModel));
                wsModel.transformToXml(XML_PATH,filename.substring(0, filename.indexOf(".")));
                return wsModel;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    private static  WsModel  fillWsModelSegment(WsModel wsModel,WsAnalyse wsAnalyse) {
        wsModel.setWswsSegment(ListToString.List2String(wsAnalyse.getWs()));
        wsModel.setWssscyrSegment(wsAnalyse.getSscyr());
        wsModel.setWsssjlSegment(wsAnalyse.getSsjl());
        wsModel.setWsajjbqSegment(ListToString.List2String(wsAnalyse.getAjjbqk()));
        wsModel.setWscpfxgcSegment(ListToString.List2String(wsAnalyse.getCpfxgc()));
        wsModel.setWscpjgSegment(ListToString.List2String(wsAnalyse.getCpjg()));
        wsModel.setWswwSegment(ListToString.List2String(wsAnalyse.getWw()));
        wsModel.setWsqw(wsAnalyse.getWsnr());
        wsModel.setWsfl(ListToString.List2String(wsAnalyse.getFl()));
        return wsModel;
    }
}
