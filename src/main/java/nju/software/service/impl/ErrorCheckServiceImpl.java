package nju.software.service.impl;

import nju.software.check.facade.GeneralChecker;
import nju.software.check.typo.TypoChecker;
import nju.software.factory.CheckerFactory;
import nju.software.factory.WsModelFactory;
import nju.software.repository.TemplateRepository;
import nju.software.service.ErrorCheckService;
import nju.software.util.JsonParserUtil;
import nju.software.util.Synonym;
import nju.software.util.XmlParserUtil;
import nju.software.vo.*;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
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
    private TypoChecker typoChecker;

    private GeneralChecker generalChecker;

    @Override
    public CheckInfoVO checkError(DocInfoVO docInfoVO) {
        CheckInfoVO checkInfoVO = new CheckInfoVO();
        wsModel = WsModelFactory.getInstance();
        jsonParserUtil = new JsonParserUtil(templateRepository.getJson(docInfoVO.getJsonName()));
//        jsonParserUtil = new JsonParserUtil(docInfoVO.getJsonName());
        xmlParserUtil = new XmlParserUtil(docInfoVO.getXmlFileName());

        generalChecker = CheckerFactory.getInstance(jsonParserUtil, xmlParserUtil, wsModel);
        
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

        generalChecker.checkAjjbqkTypo(typoMap, typoChecker);

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

    @Override
    public String[] getAjjbqkPart() {
        return generalChecker.getAjjbqkPart();
    }


    private List<CheckInfoItemVO> checkWs(){
        List<CheckInfoItemVO> checkInfoItemVOS = new ArrayList<>();
        final String ahRequirements = this.jsonParserUtil.getAhRequirements();
        String ah = this.xmlParserUtil.getAh();
        if(ah == null){
            checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.JGQS, "ȱ�ٰ���"));
        } else {
            CheckInfoItemVO check = generalChecker.checkAh(ah);
            if (check != null) {
                checkInfoItemVOS.add(check);
            }
        }
        return checkInfoItemVOS;
    }

    private List<CheckInfoItemVO> checkSscyr(){
        List<CheckInfoItemVO> check = generalChecker.checkSscyr();

        return check;
    }

    private List<CheckInfoItemVO> checkSSJL(){
        List<CheckInfoItemVO> check = generalChecker.checkSsjl();
        return check;
    }

    private List<CheckInfoItemVO> checkAjjbqk(){
        List<CheckInfoItemVO> checkInfoItemVOS = new ArrayList<>();
        List<String> ajjbqkRequirements = this.jsonParserUtil.getAjjbqkRequirements();
        Map<String, String> ajjbqk = this.xmlParserUtil.getAjjbqk();
        checkInfoItemVOS.addAll(checkYS("�����������", ajjbqkRequirements, ajjbqk.keySet()));
        //Todo ����У��
        String name = "�й����йɷ����޹�˾����֧����̹������ÿ�����һ�������о���.docx";
        if (name.equals(WsModelFactory.getName())) {
            checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.YSQS, "ȱ�ٱ����ƶ�"));
        }
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
        List<CheckInfoItemVO> check = generalChecker.checkPjjg();
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
