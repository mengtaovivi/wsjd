package nju.software.controller;

import nju.software.service.DocManagerService;
import nju.software.service.ErrorCheckService;
import nju.software.service.LawManagerService;
import nju.software.util.LSPSingleton;
import nju.software.util.MultipartFileUtil;
import nju.software.vo.*;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Created by away on 2018/3/26.
 */
@Controller
public class DocController {



    @RequestMapping("/")
    String home(){
        MultipartFileUtil.empty();
        return "redirect:/index";
    }

    @RequestMapping("/index")
    String index(){
        MultipartFileUtil.empty();
        return "index";
    }

    @RequestMapping("/input")
    String docInput(){
        MultipartFileUtil.empty();
        return "input";
    }

    @RequestMapping("/submitdoc")
    String submitdoc(@RequestParam("file") MultipartFile[] files, Model model) {
        MultipartFileUtil.empty();
        model.addAttribute("docList", files);
        MultipartFileUtil.toFiles(files);
        return "list";
    }

    @RequestMapping("/result")
    String result(@RequestParam("index") int index, Model model) throws UnsupportedEncodingException {
        List<File> fileList = MultipartFileUtil.getFileList();
        File file = fileList.get(index);

        DocType docType = docManagerService.getDocType(file);
        WsModel doc = docManagerService.getContent(file);

        // �յ�law, ������Ҫȡ����Ŀ
        List<LawItemVO> lawItemVOList = docManagerService.getLaw(doc.getWscpfxgcModel().getFtModellist());
        // ���������ݵ� law
        lawItemVOList = lawManagerService.getLaw(lawItemVOList);

        //�����Ƽ�
        String content = doc.getWsajjbqSegment();
        if (content == null || content.equals("")) {
            content = doc.getWsqw();
        }
        List<LawItemVO> recommendlawItemVOList = lawManagerService.lawRecommend(content, LSPSingleton.getInstance());
        recommendlawItemVOList = lawManagerService.deduplication(lawItemVOList, recommendlawItemVOList);

        recommendlawItemVOList = lawManagerService.getLaw(recommendlawItemVOList);


        String name = file.getName();
        System.out.println("docType = " + docType);
        System.out.println("name = " + name);
        CheckInfoVO checkInfoVO = errorCheckService.checkError(new DocInfoVO("xml\\"+ name.substring(0,name.indexOf("."))+".xml", docType.getFileName()));

        // ƴд���
        Map<String, List<SectionTypoCheckVO>> typoMap = errorCheckService.checkTypo(doc);


        model.addAttribute("docName", getFileNameWithoutSuffix(file));
        model.addAttribute("docType", docType.getFileName());
        model.addAttribute("doc", doc);
        model.addAttribute("lawList", lawItemVOList);
        model.addAttribute("recommendLawList",recommendlawItemVOList);
        model.addAttribute("error", checkInfoVO);
        model.addAttribute("typoMap", typoMap);
        model.addAttribute("typoNum", getTypoNum(typoMap));
        model.addAttribute("ajjbqkPart", errorCheckService.getAjjbqkPart());
        model.addAttribute("cmssd", getCmssd(doc));
        model.addAttribute("zjd", getZjd(doc));
        return "result";
    }


    private String getFileNameWithoutSuffix(File file) {
        String name = file.getName();
        int index = name.indexOf(".");
        return name.substring(0, index);
    }

    private int getTypoNum(Map<String, List<SectionTypoCheckVO>> typoMap) {
        int count = 0;
        for (List<SectionTypoCheckVO> sectionTypoCheckVOS : typoMap.values()) {
            count += sectionTypoCheckVOS.size();
        }
        return count;
    }

    private String getCmssd(WsModel wsModel) {
        List<String> cmssd = wsModel.getWsajjbqkModel().getCmssd();
        StringBuilder cmss = new StringBuilder();
        if (cmssd != null && !cmssd.isEmpty()) {
            for (String s : cmssd) {
                cmss.append(s);
            }
            return cmss.toString();
        }
        return null;
    }

    private String getZjd(WsModel wsModel) {
        StringBuilder zj = new StringBuilder();
        List<String> zjd = wsModel.getWsajjbqkModel().getZjd();
        if (zjd  != null && !zjd.isEmpty()) {
            for (String s : zjd) {
                zj.append(s);
            }
            return zj.toString();
        }
        return null;
    }

    @Autowired
    DocManagerService docManagerService;

    @Autowired
    LawManagerService lawManagerService;

    @Autowired
    ErrorCheckService errorCheckService;
}
