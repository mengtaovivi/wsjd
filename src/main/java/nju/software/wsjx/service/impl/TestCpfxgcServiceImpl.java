package nju.software.wsjx.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import nju.software.wsjx.model.wsSegmentationModel.WscpfxgcModel;
import nju.software.wsjx.service.TestCpfxgcService;
import nju.software.wsjx.service.model.TestModel;
import nju.software.wsjx.util.FileUtil;
import nju.software.wsjx.util.XMLReader;

import org.xml.sax.SAXException;

public class TestCpfxgcServiceImpl implements TestCpfxgcService {

	@Override
	public TestModel testFlft(TestModel testModel, WscpfxgcModel wscpfxgcModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {

		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath + "\\"
				+ filename, node[1]);
		if (contentlist.get(0) == "��") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		int index = 0;
		int temp = 0;
		if (wscpfxgcModel != null) {
			String flft = "";
			// if(wscpfxgcModel.getFlftmc()!=null){
			// flft = wscpfxgcModel.getFlftmc();
			// }
			String tm = "";
			String km = "";
			boolean b = false;
			// if (wscpfxgcModel.getFtMap() != null) {
			// temp++;
			// Map<String, String> ftMap = wscpfxgcModel.getFtMap();
			// for (Map.Entry<String, String> entry : ftMap.entrySet()) {
			// if (entry.getKey() != null) {
			// tm = entry.getKey();
			// }
			// if (entry.getValue() != "û�п�Ŀ") {
			// km = entry.getValue();
			// }
			// }
			// }
			// System.out.println(flft);
			// System.out.println(tm);
			// System.out.println(km);
			for (int j = 0; j < contentlist.size(); j++) {
				// System.out.println(contentlist.get(j));
				if (ts.judgeNode_2(contentlist.get(j), flft)
						&& ts.judgeNode_2(contentlist.get(j), tm)
						&& ts.judgeNode_2(contentlist.get(j), km)) {
					index++;
					b = true;
					break;
				} else {
					b = true;
				}
				if (b == false) {
					System.out.println("���ɷ���" + flft + "δ�ҵ�");
					FileUtil.fileCopy(inputpath, filename, specialpath
							+ "flftspecial", filename);
				}
			}
			if (index == temp) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testSfjgxzfy(TestModel testModel,
			WscpfxgcModel wscpfxgcModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "��") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (wscpfxgcModel.getSfjgxzfy() != null) {
			String sfjgxzfy = wscpfxgcModel.getSfjgxzfy();
			if (ts.judgeNode_1(sfjgxzfy, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("�Ƿ񾭹���������" + sfjgxzfy + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"larqspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testXzxwwfbj(TestModel testModel,
			WscpfxgcModel wscpfxgcModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (wscpfxgcModel.getXzxwwfbj() != null) {
			String xzxwwfbj = wscpfxgcModel.getXzxwwfbj();
			if (ts.judgeNode_1(xzxwwfbj, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("������ΪΥ������" + xzxwwfbj + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"larqspecial", filename);
			}

		}
		return testModel;
	}

	@Override
	public TestModel testXzpc(TestModel testModel, WscpfxgcModel wscpfxgcModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "��") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (wscpfxgcModel.getXzpc() != null) {
			String xzpc = wscpfxgcModel.getXzpc();
			if (ts.judgeNode_1(xzpc, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("�����⳥" + xzpc + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"larqspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testGtfz(TestModel testModel, WscpfxgcModel wscpfxgcModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "��") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (wscpfxgcModel.getGtfz()!= null) {
			String gtfz = wscpfxgcModel.getGtfz();
			if (ts.judgeNode_1(gtfz, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("��ͬ����" + gtfz + " --------------------  " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"Gtfzspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testBgrtyrzcx(TestModel testModel,
			WscpfxgcModel wscpfxgcModel, String inputpath,
			String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "��") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (wscpfxgcModel.getBgrtyrzcx() != null) {
			String bgrtyrzcx = wscpfxgcModel.getBgrtyrzcx();
			if (ts.judgeNode_1(bgrtyrzcx, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("������ͬ���������" + bgrtyrzcx + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"larqspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testKtqsqchss(TestModel testModel,
			WscpfxgcModel wscpfxgcModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "��") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (wscpfxgcModel.getKtqsqchss() != null) {
			String ktqsqchss = wscpfxgcModel.getKtqsqchss();
			if (ts.judgeNode_1(ktqsqchss, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("��ͥǰ���볷������" +"  " + ktqsqchss + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"larqspecial", filename);
			}
		}
		return testModel;
	}

}
