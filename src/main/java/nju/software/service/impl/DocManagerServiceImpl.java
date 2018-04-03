package nju.software.service.impl;

import nju.software.classify.BaseClassifier;
import nju.software.classify.ParseMap;
import nju.software.factory.WsModelFactory;
import nju.software.service.DocManagerService;
import nju.software.util.WordHelper;
import nju.software.vo.DocType;
import nju.software.vo.LawItemVO;
import nju.software.wsjx.business.PreWsAnalyse;
import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.model.wsSegmentationModel.WswsModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcFtModel;
import nju.software.wsjx.parse.ParseSegment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by away on 2018/3/26.
 */
@Service
public class DocManagerServiceImpl implements DocManagerService {

    private static final String XML_PATH = "xml";

    @Override
    @Transactional
    public WsModel getContent(File file) {

        InputStream is= null;
        try {
            is = new FileInputStream(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String read = WordHelper.read(is);
        WsModel wsModel = WsModelFactory.getInstance(read, file.getName());
        // ��û�е�������Ϊ��
        if (wsModel.getWsfl().equals("")) {
            wsModel.setWsfl("��");
        }
        if (wsModel.getWsajjbqSegment().equals("")) {
            wsModel.setWsajjbqSegment("��");
        }
        return wsModel;
    }

    @Override
    @Transactional
    public List<LawItemVO> getLaw(List<WscpfxgcFtModel> lawList) {
        List<LawItemVO> lawItemVOList = new ArrayList<>();
        for (WscpfxgcFtModel law : lawList) {
            String name = law.getFlftmc();
            Map<String, String> lawMap = law.getFtMap();
            Map<String, String> lawMapEmpty= new HashMap<>();
//            System.out.println(lawMap.entrySet());

            for (String s : lawMap.keySet()) {
                String section = lawMap.get(s);
                s = "��" + s + "��";
                if (!section.equals("û�п�Ŀ")) {
                    int idx = section.indexOf("��");
                    section = section.substring(0, idx+1);
                    s += section;
                }

                System.out.println("request: " + s);
                lawMapEmpty.put(s, "");
            }
            LawItemVO lawItemVO = new LawItemVO(name, lawMapEmpty);
            lawItemVOList.add(lawItemVO);
        }
        return lawItemVOList;
    }

    @Override
    @Transactional
    public DocType getDocType(File file) {
        InputStream is= null;
        try {
            is = new FileInputStream(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String content = WordHelper.read(is);
        String filename = file.getName();
        WsModel wsModel = WsModelFactory.getInstance(content, filename);
//        System.out.println(wsModel.getWswsModel().getAh());
        //Ԥ��������
        PreWsAnalyse preWsAnalyse=new PreWsAnalyse(filename,  content);
        //��ȡ����Ľ�������
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
            String string = "nju.software.classify." + classifierName;
            try {
                System.out.println(string);
                Class<?> classifier = Class.forName(string);
                BaseClassifier baseClassifier = (BaseClassifier) classifier.newInstance();
                String parseRuleName = "nju.software.wsjx.parse."+baseClassifier.getParseRuleName();
                Class<?> parseDocumentClass = Class.forName(parseRuleName);
                ParseSegment parseCaseinfo = (ParseSegment) parseDocumentClass.newInstance();
                parseCaseinfo.registerWsAnalyse(wsAnalyse);
                WsModelFactory.setWsModel(parseCaseinfo.transformToWsModel());
                WsModelFactory.getInstance(content, filename).transformToXml(XML_PATH, filename.substring(0,filename.indexOf(".")));
                return baseClassifier.getType(wsModel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
