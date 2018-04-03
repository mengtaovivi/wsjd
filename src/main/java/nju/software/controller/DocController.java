package nju.software.controller;

import nju.software.service.DocManagerService;
import nju.software.service.ErrorCheckService;
import nju.software.service.LawManagerService;
import nju.software.util.MultipartFileUtil;
import nju.software.vo.CheckInfoVO;
import nju.software.vo.DocInfoVO;
import nju.software.vo.DocType;
import nju.software.vo.LawItemVO;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

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
    String result(@RequestParam("index") int index, Model model) {
        List<File> fileList = MultipartFileUtil.getFileList();
        File file = fileList.get(index);

        DocType docType = docManagerService.getDocType(file);
        WsModel doc = docManagerService.getContent(file);

        // �յ�law, ������Ҫȡ����Ŀ
        List<LawItemVO> lawItemVOList = docManagerService.getLaw(doc.getWscpfxgcModel().getFtModellist());
        // ���������ݵ� law
        lawItemVOList = lawManagerService.getLaw(lawItemVOList);

        String name = file.getName();
        System.out.println("docType = " + docType);
        System.out.println("name = " + name);
        CheckInfoVO checkInfoVO = errorCheckService.checkError(new DocInfoVO("xml\\"+ name.substring(0,name.indexOf("."))+".xml", docType.getFileName()));

        model.addAttribute("docName", getFileNameWithoutSuffix(file));
        model.addAttribute("doc", doc);
        model.addAttribute("lawList", lawItemVOList);
        model.addAttribute("error", checkInfoVO);
        return "result";
    }


    private String getFileNameWithoutSuffix(File file) {
        String name = file.getName();
        int index = name.indexOf(".");
        return name.substring(0, index);
    }

    @Autowired
    DocManagerService docManagerService;

    @Autowired
    LawManagerService lawManagerService;

    @Autowired
    ErrorCheckService errorCheckService;
}
