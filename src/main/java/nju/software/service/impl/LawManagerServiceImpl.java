package nju.software.service.impl;

import nju.software.preProcess.DataProcess;
import nju.software.preProcess.LabeledSentenceProcess;
import nju.software.repository.LawRepository;
import nju.software.service.LawManagerService;
import nju.software.vo.LawItemVO;
import nju.software.wsjd.model.lawModel.KuanModel;
import nju.software.wsjd.model.lawModel.LawModel;
import nju.software.wsjd.model.lawModel.TiaoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by away on 2018/3/28.
 */
@Service
public class LawManagerServiceImpl implements LawManagerService {

    @Autowired
    private LawRepository lawRepository;

    private DataProcess dataProcess = new DataProcess();

    @Override
    @Transactional
    public List<LawItemVO> getLaw(List<LawItemVO> lawItemVOS) {
        List<LawItemVO> results = new ArrayList<>();
//        System.out.println("lawItemVOS size = " + lawItemVOS.size());
//        for (LawItemVO lawItemVO : lawItemVOS) {
//            System.out.println(lawItemVO);
//        }
        for (LawItemVO vo : lawItemVOS){
            LawModel lawModel = lawRepository.findByLawname(vo.getName());
            System.out.println("lawModel = " + lawModel);
            if (lawModel == null) {
                continue;
            }
            Map<String,String> lawmap = vo.getLawMap();
            Set<String> keys = lawmap.keySet();
            HashMap<String,TiaoModel> tiaoModels = lawModel.getContent();
            for (String key:keys){
//                System.out.println("key = " + key);
                StringBuilder content = new StringBuilder();
                KuanModel kuanModel;
                if (key.contains("��")){
                    int tmp = key.indexOf("��")+1;
                    String tiao = key.substring(0,tmp);
                    String kuan;
                    if (key.contains("��")) {
                        kuan = key.substring(tmp, key.indexOf("��")+1);
                    } else {
                        kuan = key.substring(tmp);
                    }
                    kuanModel = tiaoModels.get(tiao).getKuan().get(kuan);
                } else {
                    kuanModel = tiaoModels.get(key).getKuan().get("��һ��");
                }
                content.append(kuanModel.getContent());
                List<String> xiang = kuanModel.getXiang();
                if (xiang!=null){
                    for (String str :xiang){
                        content.append(str);
                    }
                }
                System.out.println("content: " + content);
                lawmap.put(key,content.toString());
            }
            vo.setLawMap(lawmap);
            results.add(vo);
        }
        return results;
    }

    public List<LawItemVO> lawRecommend(String content, LabeledSentenceProcess labeledSentenceProcess) throws UnsupportedEncodingException {
        List<String> laws = dataProcess.getRecommend(content,labeledSentenceProcess);
        List<LawItemVO> lawItemVOS = new ArrayList<>();
        for (String tmp : laws){
            String lawname = tmp.split(" ")[0];
            String tiao  = tmp.split(" ")[1];
            int flag = -1;
            for (int i=0;i<lawItemVOS.size();++i){
                LawItemVO lawItemVO = lawItemVOS.get(i);
                if (lawItemVO.getName().equals(lawname)){
                    flag = i;
                }
            }
            if (flag != -1){
                LawItemVO lawItemVO = lawItemVOS.get(flag);
                Map<String,String> map = lawItemVO.getLawMap();
                map.put(tiao,"");
                lawItemVO.setLawMap(map);
                lawItemVOS.remove(flag);
                lawItemVOS.add(lawItemVO);
            }else {
                LawItemVO lawItemVO = new LawItemVO();
                Map<String,String> map = new HashMap<>();
                map.put(tiao,"");
                lawItemVO.setName(lawname);
                lawItemVO.setLawMap(map);
                lawItemVOS.add(lawItemVO);
            }
        }

        return lawItemVOS;
    }

    @Override
    public List<LawItemVO> deduplication(List<LawItemVO> lawItemVOList, List<LawItemVO> recommendLawItemVOList) {
        List<LawItemVO> result = new ArrayList<>();
        for (LawItemVO recommendLawItemVO : recommendLawItemVOList) {
            String lawName = recommendLawItemVO.getName();
            Map<String, String> recommendLawMap = recommendLawItemVO.getLawMap();
            Map<String, String> lawMap = containsLawName(lawItemVOList, lawName);
            if (lawMap == null) {
                // ���벻�����ķ���
                result.add(recommendLawItemVO);
            } else {
                Map<String, String> resultMap = new HashMap<>();
                for (Map.Entry<String, String> lawMapEntry : recommendLawMap.entrySet()) {
                    // ���벻�����ķ���
                    if (!containsLaw(lawMapEntry.getKey(), lawMap)) {
                        resultMap.put(lawMapEntry.getKey(), lawMapEntry.getValue());
                    }
                }
                if (resultMap.keySet().size() != 0) {
                    result.add(new LawItemVO(lawName, resultMap));
                }
            }
        }
        return result;
    }

    private Map<String, String> containsLawName(List<LawItemVO> lawItemVOList, String name) {
        for (LawItemVO lawItemVO : lawItemVOList) {
            if (name.equals(lawItemVO.getName())) {
                return lawItemVO.getLawMap();
            }
        }
        return null;
    }

    private boolean containsLaw(String name, Map<String, String> lawMap) {
        for (String s : lawMap.keySet()) {
            if (s.equals(name)) {
                return true;
            }
        }
        return false;
    }
}
