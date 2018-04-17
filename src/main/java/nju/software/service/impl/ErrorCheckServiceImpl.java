package nju.software.service.impl;

import nju.software.check.AHChecker;
import nju.software.check.PJJGChecker;
import nju.software.check.SSCYRChecker;
import nju.software.check.SSJLChecker;
import nju.software.check.typo.TypoChecker;
import nju.software.factory.WsModelFactory;
import nju.software.repository.TemplateRepository;
import nju.software.service.ErrorCheckService;
import nju.software.util.JsonParserUtil;
import nju.software.util.Synonym;
import nju.software.util.XmlParserUtil;
import nju.software.vo.*;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zhuding on 2018/3/28.
 */
@Service
public class ErrorCheckServiceImpl implements ErrorCheckService {

    private JsonParserUtil jsonParserUtil;

    private XmlParserUtil xmlParserUtil;

    private WsModel wsModel;

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private AHChecker ahChecker;

    @Autowired
    private TypoChecker typoChecker;

    @Autowired
    private SSCYRChecker sscyrChecker;

    @Autowired
    private SSJLChecker ssjlChecker;

    @Autowired
    private PJJGChecker pjjgChecker;

    @Override
    public CheckInfoVO checkError(DocInfoVO docInfoVO) {
        CheckInfoVO checkInfoVO = new CheckInfoVO();
        wsModel = WsModelFactory.getInstance();
        jsonParserUtil = new JsonParserUtil(templateRepository.getJson(docInfoVO.getJsonName()));
//        jsonParserUtil = new JsonParserUtil(docInfoVO.getJsonName());

        xmlParserUtil = new XmlParserUtil(docInfoVO.getXmlFileName());
        checkInfoVO.setWS(this.checkWs());
        checkInfoVO.setSSCYR(this.checkSscyr());
        checkInfoVO.setSSJL(this.checkSSJL());
        checkInfoVO.setAJJBQK(this.checkAjjbqk());
        checkInfoVO.setCPFXGC(this.checkCpfxgc());
        checkInfoVO.setCPJG(this.checkCpjg());
        checkInfoVO.setWW(new ArrayList<CheckInfoItemVO>());
        checkInfoVO.setFL(new ArrayList<CheckInfoItemVO>());
        return checkInfoVO;
    }

    @Override
    public Map<String, List<SectionTypoCheckVO>> checkTypo(WsModel wsModel) {
        Map<String, List<SectionTypoCheckVO>> typoMap = new HashMap<>();

        String wsssjlSegment = wsModel.getWsssjlSegment();
        typoMap.put("ssjl", typoChecker.check(wsssjlSegment));

        String wsajjbqSegment = wsModel.getWsajjbqSegment();
        typoMap.put("ajjbqk", typoChecker.check(wsajjbqSegment));

        String wscpfxgcSegment = wsModel.getWscpfxgcSegment();
        typoMap.put("cpfxgc", typoChecker.check(wscpfxgcSegment));

        String wscpjgSegment = wsModel.getWscpjgSegment();
//        SectionTypoCheckVO sectionTypoCheckVO = new SectionTypoCheckVO();
//        sectionTypoCheckVO.setStart(3);
//        sectionTypoCheckVO.setEnd(9);
//        sectionTypoCheckVO.setWord("test");
        List<SectionTypoCheckVO> check = typoChecker.check(wscpjgSegment);
//        check.add(sectionTypoCheckVO);
        typoMap.put("cpjg", check);

        return typoMap;
    }


    private List<CheckInfoItemVO> checkWs(){
        List<CheckInfoItemVO> checkInfoItemVOS = new ArrayList<>();
        final String ahRequirements = this.jsonParserUtil.getAhRequirements();
        String ah = this.xmlParserUtil.getAh();
        if(ah == null){
            checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.JGQS, "ȱ�ٰ���"));
        } else {
            CheckInfoItemVO check = ahChecker.check(ah);
            if (check != null) {
                checkInfoItemVOS.add(check);
            }
        }
        return checkInfoItemVOS;
    }

    private List<CheckInfoItemVO> checkSscyr(){
        List<WssscyrModel> wssscyrModels = wsModel.getWssscyrModels();
        List<CheckInfoItemVO> check = sscyrChecker.check(jsonParserUtil, wssscyrModels);

        return check;
    }

    private List<CheckInfoItemVO> checkSSJL(){
        List<CheckInfoItemVO> check = ssjlChecker.check(jsonParserUtil, xmlParserUtil, wsModel.getWssscyrModels(), wsModel.getWsssjlModel());
        return check;
    }

    private List<CheckInfoItemVO> checkAjjbqk(){
        List<CheckInfoItemVO> checkInfoItemVOS = new ArrayList<>();
        List<String> ajjbqkRequirements = this.jsonParserUtil.getAjjbqkRequirements();
        Map<String, String> ajjbqk = this.xmlParserUtil.getAjjbqk();
        checkInfoItemVOS.addAll(checkYS("�����������", ajjbqkRequirements, ajjbqk.keySet()));
        //Todo ����У��
        return checkInfoItemVOS;
    }

    private List<CheckInfoItemVO> checkCpfxgc(){
        List<CheckInfoItemVO> checkInfoItemVOS = new ArrayList<>();
        final Map<String, List<String>> cpfxgcRequirements = this.jsonParserUtil.getCpfxgcRequirements();
        //Todo ���ɲ���ȱʧ���
        Map<String, String> cpfxgcQt = this.xmlParserUtil.getCpfxgcQt();
        List<String> cpfxgcQtRequirements = new ArrayList<>();
        if(cpfxgcRequirements.containsKey("����")){
            cpfxgcQtRequirements = cpfxgcRequirements.get("����");
        }
        checkInfoItemVOS.addAll(checkYS("���з�������", cpfxgcQtRequirements, cpfxgcQt.keySet()));
        //Todo ����У��
        return checkInfoItemVOS;
    }

    private List<CheckInfoItemVO> checkCpjg(){
        List<CheckInfoItemVO> check = pjjgChecker.check(jsonParserUtil, xmlParserUtil);
        return check;
    }


    //Todo ��β�͸�¼

    private static List<CheckInfoItemVO> checkYS(String name, List<String> requirements, Collection<String> keys){
        System.out.println("-----"+name+"-----");
        System.out.println("Ҫ��:" + requirements);
        System.out.println("����:" + keys);
        List<CheckInfoItemVO> checkInfoItemVOS = new ArrayList<>();
        if(keys.isEmpty() && !requirements.isEmpty()){
            checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.JGQS, "ȱ��" + name));
        }else{
            for (String requirement : requirements) {
                if(!isMatch(keys, requirement)){
                    checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.YSQS, "ȱ��" + requirement + "��"));
                }
            }
        }
        return checkInfoItemVOS;
    }

    private static boolean isMatch(Collection<String> keys, String requirement){
        if(requirement.contains("(")){
            requirement = requirement.substring(0,requirement.indexOf("("));
        }
        for (String key : keys) {
            String s = key;
            if(s.contains("(")){
                s = s.substring(0,s.indexOf("("));
            }
            if(Synonym.isEqual(key, requirement)){
                return true;
            }
        }
        return false;
    }
}
