package nju.software.service.impl;

import nju.software.repository.TemplateRepository;
import nju.software.service.ErrorCheckService;
import nju.software.util.JsonParserUtil;
import nju.software.util.Synonym;
import nju.software.util.XmlParserUtil;
import nju.software.vo.CheckInfoItemVO;
import nju.software.vo.CheckInfoVO;
import nju.software.vo.DocInfoVO;
import nju.software.vo.ErrorType;
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

    @Autowired
    private TemplateRepository templateRepository;

    @Override
    public CheckInfoVO checkError(DocInfoVO docInfoVO) {
        CheckInfoVO checkInfoVO = new CheckInfoVO();
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


    private List<CheckInfoItemVO> checkWs(){
        List<CheckInfoItemVO> checkInfoItemVOS = new ArrayList<>();
        final String ahRequirements = this.jsonParserUtil.getAhRequirements();
        String ah = this.xmlParserUtil.getAh();
        if(ah == null){
            checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.JGQS, "ȱ�ٰ���"));
        }
        //Todo ���Ŵ���ȶ�
        return checkInfoItemVOS;
    }

    private List<CheckInfoItemVO> checkSscyr(){
        List<CheckInfoItemVO> checkInfoItemVOS = new ArrayList<>();
        final List<String> dsrRequirements = this.jsonParserUtil.getDsrRequirements();
        String dsrRequirementsArray = Arrays.toString(dsrRequirements.toArray());
        //���������ϵ�λ�ȶ�
        List<String> dsrList = this.xmlParserUtil.getDsr();
//        checkInfoItemVOS.addAll(checkYS("���ϵ�����",dsrRequirements, dsr));
        System.out.println("dsrRequirementsArray = " + dsrRequirementsArray);
        for (String s : dsrList) {
            System.out.println("s = " + s);
            if (!Synonym.isContains(dsrRequirements, s)) {
                String errMsg = "���ϲ����˵�λ������" + dsrRequirementsArray;
                checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.YSCW, errMsg));
            }
        }
        return checkInfoItemVOS;
    }

    private List<CheckInfoItemVO> checkSSJL(){
        List<CheckInfoItemVO> checkInfoItemVOS = new ArrayList<>();
        final List<String> ssjlRequirements = this.jsonParserUtil.getSsjlRequirements();
        Map<String, String> ssjl = this.xmlParserUtil.getSsjl();
        checkInfoItemVOS.addAll(checkYS("���ϼ�¼", ssjlRequirements, ssjl.keySet()));
        //Todo ����У��
        return checkInfoItemVOS;
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
        List<CheckInfoItemVO> checkInfoItemVOS = new ArrayList<>();
        final List<String> pjjgRequirements = this.jsonParserUtil.getPjjgRequirements();
        final Map<String, String> pjjg = this.xmlParserUtil.getPjjg();
        checkInfoItemVOS.addAll(checkYS("�о����", pjjgRequirements, pjjg.keySet()));
        //Todo ����У��
        return checkInfoItemVOS;
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
