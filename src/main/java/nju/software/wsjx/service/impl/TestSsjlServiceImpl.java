package nju.software.wsjx.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;
import nju.software.wsjx.service.TestSsjlService;
import nju.software.wsjx.service.model.TestModel;
import nju.software.wsjx.util.FileUtil;
import nju.software.wsjx.util.XMLReader;

import org.xml.sax.SAXException;

public class TestSsjlServiceImpl implements TestSsjlService {

	@Override
	public TestModel testAy(TestModel testModel, WsssjlModel wsssjlModel,
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
		if (wsssjlModel.getAy() != null) {
			String ay = wsssjlModel.getAy();
			if (ts.judgeNode_1(ay, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else if (ts.judgeNode_2(content, ay)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else if (ts.judgeNode_2(ay, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("����" + ay + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ayspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testWzay(TestModel testModel, WsssjlModel wsssjlModel,
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
		if (wsssjlModel.getWzay() != null) {
			String wzay = wsssjlModel.getWzay();
			if (ts.judgeNode_1(wzay, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else if (ts.judgeNode_2(content, wzay)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("��������" + wzay + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"wzayspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testAydm(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "��") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		} else if (wsssjlModel.getAydm() != null) {
			String aydm = wsssjlModel.getAydm();
			if (ts.judgeNode_1(content, aydm)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("���ɴ��� :" + aydm + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testAjly(TestModel testModel, WsssjlModel wsssjlModel,
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
		if (wsssjlModel.getAjly() != null) {
			String ajly = wsssjlModel.getAjly();
			if (ts.judgeNode_2(content, ajly)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("������Դ :" + ajly + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajlyspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testAjsj(TestModel testModel, WsssjlModel wsssjlModel,
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
		if (wsssjlModel.getAjsj() != null) {
			String ajsj = wsssjlModel.getAjsj();
			if (ts.judgeNode_1(ajsj, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("�����漰" + ajsj + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajsjspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testKtsl(TestModel testModel, WsssjlModel wsssjlModel,
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
		if (wsssjlModel.getKtsl() != null) {
			String ktsl = wsssjlModel.getKtsl();
			if (ts.judgeNode_1(ktsl, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("��ͥ����" + ktsl + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ktslspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testKtrq(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		ArrayList<String> contentList = xr.getXMLNodelist(inputpath + "\\"
				+ filename, node[1]);
		// String content = xr.getXMLNode(inputpath+"\\"+filename,node[1]);
		if (contentList.get(0) == "��") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (wsssjlModel.getKtrq() != null) {
			ArrayList<String> ktrq = wsssjlModel.getKtrq();
			if (contentList.containsAll(ktrq) || ktrq.containsAll(contentList)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("��ͥ����" + ktrq + "   ");
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ktrqspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testQsah(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		ArrayList<String> contentList = xr.getXMLNodelist(inputpath + "\\"
				+ filename, node[1]);
		if (contentList.get(0) == "��") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (wsssjlModel.getQsah() != null) {
			ArrayList<String> qsah = wsssjlModel.getQsah();
			for (int i = 0; i < qsah.size(); i++) {
				for (int j = 0; j < contentList.size(); j++) {
					if (ts.judgeNode_1(qsah.get(i), contentList.get(j))) {
						testModel.setCoNum(testModel.getCoNum() + 1);
					}
					// System.out.println("ǰ�󰸺ţ�"+qsah.get(i)+"   "+contentList.get(j));
				}
			}
			// FileUtil.fileCopy(inputpath, filename, specialpath+"qsahspecial",
			// filename);
		}
		return testModel;
	}

	@Override
	public TestModel testQsfy(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		ArrayList<String> contentList = xr.getXMLNodelist(inputpath + "\\"
				+ filename, node[1]);
		if (contentList.get(0) == "��") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		else if (wsssjlModel.getQsfy() != null) {
			String qsfy = wsssjlModel.getQsfy();
			if (contentList.contains(qsfy)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println(filename + "ǰ��Ժ��" + qsfy + "   " + contentList.get(0));
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"qsfyspecial", filename);
			}
		}else{
//			System.out.println(filename + "ǰ��Ժ��" + "   " + contentList.get(0));
		}
		return testModel;
	}

	@Override
	public TestModel testKtslxx(TestModel testModel, WsssjlModel wsssjlModel,
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
		if (wsssjlModel.getKtslxx() != null) {
			String ktslxx = wsssjlModel.getKtslxx();
			if (ts.judgeNode_1(ktslxx, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("��ͥ������Ϣ" + ktslxx + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ktslxxspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testBgkslyy(TestModel testModel, WsssjlModel wsssjlModel,
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
		if (wsssjlModel.getBgkslyy() != null) {
			String bgkslyy = wsssjlModel.getBgkslyy();
			if (ts.judgeNode_1(bgkslyy, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("����������ԭ��" + bgkslyy + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"bgkslyyspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testLarq(TestModel testModel, WsssjlModel wsssjlModel,
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
		if (wsssjlModel.getLarq() != null) {
			String larq = wsssjlModel.getLarq();
			if (ts.judgeNode_1(larq, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("��������" + larq + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"larqspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testYsajsycx(TestModel testModel, WsssjlModel wsssjlModel,
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
		if (wsssjlModel.getYsajsycx() != null) {
			String ysajsycx = wsssjlModel.getYsajsycx();
			if (ts.judgeNode_1(ysajsycx, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("һ�󰸼����ó���" + ysajsycx + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ysajsycxspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testJyzpt(TestModel testModel, WsssjlModel wsssjlModel,
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
		if (wsssjlModel.getJyzpt() != null) {
			String jyzpt = wsssjlModel.getJyzpt();
			if (ts.judgeNode_1(jyzpt, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("����ת��ͨ" + jyzpt + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"jyzptspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testYsajly(TestModel testModel, WsssjlModel wsssjlModel,
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
		if (wsssjlModel.getYsajly() != null) {
			String ysajly = wsssjlModel.getYsajly();
			if (ts.judgeNode_2(content, ysajly)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("һ�󰸼���Դ" + ysajly + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ysajlyspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testSlrq(TestModel testModel, WsssjlModel wsssjlModel,
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
		if (wsssjlModel.getSlrq() != null) {
			String slrq = wsssjlModel.getSlrq();
			if (ts.judgeNode_1(slrq, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("��������" + slrq + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"slrqspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testSpzz(TestModel testModel, WsssjlModel wsssjlModel,
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
		if (wsssjlModel.getSpzz() != null) {
			String spzz = wsssjlModel.getSpzz();
			if (ts.judgeNode_1(spzz, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("������֯" + filename + spzz + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"spzzspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testDrsp(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		// if(!content.contains("��")&&!content.contains("��")){
		// System.out.println(filename +"   "+ content);
		// }
		if (content == "��") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (wsssjlModel.getDrsp() != null) {
			String drsp = wsssjlModel.getDrsp();
			if (ts.judgeNode_1(drsp, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				// System.out.println("��������"+drsp+"   "+content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"drspspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testSqcsrq(TestModel testModel, WsssjlModel wsssjlModel,
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
		if (wsssjlModel.getSqcsrq() != null) {
			String sqcsrq = wsssjlModel.getSqcsrq();
			if (ts.judgeNode_1(sqcsrq, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				// System.out.println("���볷������"+sqcsrq+"   "+content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"sqcsrqspecial", filename);
			}
		} else {
			System.out.println("���볷������" + content);
		}
		return testModel;
	}

	@Override
	public TestModel testQxrxx(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath + "\\"
				+ filename, node[1]);
		if (contentlist.get(0) == "��") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (!wsssjlModel.getQxrxx().isEmpty()) {
			int index = 0;
			HashMap<String, String> map = wsssjlModel.getQxrxx();
			;
			for (Entry<String, String> entry : map.entrySet()) {
				if (contentlist.contains(entry.getKey())) {
					index++;
				} else {
					System.out.println(filename + entry.getKey());
				}
			}
			if (index == map.size()) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("ȱϯ����Ϣ");
			}
		}
		return testModel;
	}

	@Override
	public TestModel testCtrxx(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath + "\\"
				+ filename, node[1]);
		if (contentlist.get(0) == "��") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (!wsssjlModel.getCtrxx().isEmpty()) {
			int index = 0;
			HashMap<String, String> map = wsssjlModel.getCtrxx();
			for (Entry<String, String> entry : map.entrySet()) {
				if (contentlist.contains(entry.getKey())) {
					index++;
					testModel.setCoNum(testModel.getCoNum() + 1);
					break;
					// System.out.println(entry.getKey());
				} else {
					// System.out.println(filename+entry.getKey()+"����---------------------����");
				}
				if (index < map.size()) {
					// testModel.setCoNum(testModel.getCoNum() + 1);
				} else {
					System.out.println("��ͥ����Ϣ" + map);
					System.out.println("��ͥ����Ϣ" + contentlist);
				}
				break;
			}
		}
		return testModel;
	}

	@Override
	public TestModel testBgzyldct(TestModel testModel, WsssjlModel wsssjlModel,
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
		if (wsssjlModel.getBgzyldct() != null) {
			String bgzyldct = wsssjlModel.getBgzyldct();
			if (ts.judgeNode_1(bgzyldct, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("������Ҫ�쵼��ͥ" + filename + bgzyldct + "   "
						+ content);
//				 FileUtil.fileCopy(inputpath,
//				 filename,specialpath+"bgzyldctqspecial", filename);
			}
		}
		if(content.contains("��")){
			FileUtil.fileCopy(inputpath,
					 filename,specialpath+"bgzyldctqspecial", filename);
		}
		return testModel;
	}

	@Override
	public TestModel testXzxwzl(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if ((content == "��") || (content.contains("����"))) {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (wsssjlModel.getXzxwzl() != null) {
			String xzxwzl = wsssjlModel.getXzxwzl();
			if (ts.judgeNode_1(xzxwzl, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("������Ϊ����" + filename + xzxwzl + "   "
						+ content);
				// FileUtil.fileCopy(inputpath,
				// filename,specialpath+"xzxwzlspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testXzqqxwzl(TestModel testModel, WsssjlModel wsssjlModel,
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
		if (wsssjlModel.getXzqqxwzl() != null) {
			String xzqqxwzl = wsssjlModel.getXzqqxwzl();
			if (ts.judgeNode_1(xzqqxwzl, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("������Ȩ��Ϊ����" + filename + xzqqxwzl + "   "
						+ content);
				// FileUtil.fileCopy(inputpath,
				// filename,specialpath+"xzxwzlspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testQsrq(TestModel testModel, WsssjlModel wsssjlModel,
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
		if (wsssjlModel.getQsrq() != null) {
			String qsrq = wsssjlModel.getQsrq();
			if (ts.judgeNode_1(content, qsrq)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("��������" + qsrq + "   ");
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ktrqspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testSsxz(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "��") {
			System.out.println(filename + "----" + content);
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		} else if (wsssjlModel.getSsxz() != null) {
			String ssxz = wsssjlModel.getSsxz();
			if (ts.judgeNode_1(content, ssxz)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out
						.println(filename + "�������� :" + ssxz + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testWzzm(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath + "\\"
				+ filename, node[1]);
		if (contentlist.get(0) == "��") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		// else if (wsssjlModel.getWsssjlZkxx().get(0).getWsssjlZkzm() != null)
		// {
		// // HashMap<String,String> zkzm = wsssjlModel.getZkzm();
		// boolean bl = true;
		// for(int i=0;i<contentlist.size();i++){
		// // if(!zkzm.containsKey(contentlist.get(i))){
		// // bl = false;
		// // }
		// }
		// if (bl) {
		// testModel.setCoNum(testModel.getCoNum() + 1);
		// }
		else {
			System.out.println(filename + "��������:" + "   " + contentlist);
			// FileUtil.fileCopy(inputpath, filename,
			// specialpath+"wzzmspecial", filename);
		}
		// }
		return testModel;
	}

	@Override
	public TestModel testZmdm(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath + "\\"
				+ filename, node[1]);
		if (contentlist.get(0) == "��") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		return testModel;
	}

	@Override
	public TestModel testGsjg(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "��") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		} else if (wsssjlModel.getGsjg() != null) {
			String gsjg = wsssjlModel.getGsjg();
			if (ts.judgeNode_2(gsjg, content) || ts.judgeNode_2(content, gsjg)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out
						.println(filename + "���߻��� :" + gsjg + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testGsah(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "��") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		} else if (wsssjlModel.getGsah() != null) {
			String gsah = wsssjlModel.getGsah();
			if (ts.judgeNode_1(content, gsah)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out
						.println(filename + "���߰��� :" + gsah + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testMsbfjxsl(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "��") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		} else if (wsssjlModel.getMsbfjxsl() != null) {
			String msbfjxsl = wsssjlModel.getMsbfjxsl();
			if (ts.judgeNode_1(content, msbfjxsl)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println(filename + "�������²��ּ������� :" + msbfjxsl + "   "
						+ content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testJcyjyyqsl(TestModel testModel,
			WsssjlModel wsssjlModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "��") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		} else if (wsssjlModel.getJcyjyyqsl() != null) {
			String jcyjyyqsl = wsssjlModel.getJcyjyyqsl();
			if (ts.judgeNode_1(content, jcyjyyqsl)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println(filename + "���Ժ������������:" + jcyjyyqsl + "   "
						+ content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testQsland(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "��") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		} else if (wsssjlModel.getQsland() != null) {
			String qsland = wsssjlModel.getQsland();
			if (ts.judgeNode_1(content, qsland)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println(filename + "ǰ���������:" + qsland + "   "
						+ content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testQsfyjc(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath + "\\"
				+ filename, node[1]);
		if (contentlist.get(0) == "��") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		} else if (wsssjlModel.getQsfyjc() != null) {
			String qsfyjc = wsssjlModel.getQsfyjc();
			if (contentlist.contains(qsfyjc)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println(filename + "ǰ��Ժ���:" + qsfyjc + "   "+contentlist.get(0));
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testQswszl(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "��") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		} else if (wsssjlModel.getQswszl() != null) {
			String qswszl = wsssjlModel.getQswszl();
			if (ts.judgeNode_1(content, qswszl)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println(filename + "ǰ����������:" + qswszl + "   "
						+ content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testQssj(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "��") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		} else if (wsssjlModel.getQssj() != null) {
			String qssj = wsssjlModel.getQssj();
			if (ts.judgeNode_1(content, qssj)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println(filename + "ǰ����:" + qssj + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testQsajyl(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		ArrayList<String> contentList = xr.getXMLNodelist(inputpath + "\\"
				+ filename, node[1]);
		if (contentList.get(0) == "��") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		} else if (wsssjlModel.getQsajyl() != null) {
			String ajly = wsssjlModel.getQsajyl();
			if (contentList.contains(ajly)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println(filename + "ǰ�󰸼�����:" + ajly + "   "
						+ contentList.get(0));
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}

		}
		return testModel;
	}

	@Override
	public TestModel testXzesqsah(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		ArrayList<String> contentList = xr.getXMLNodelist(inputpath + "\\"
				+ filename, node[1]);
		if (contentList.get(0) == "��") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		} else if (wsssjlModel.getXzesqsah() != null) {
			String xzesqsah = wsssjlModel.getXzesqsah();
			if (contentList.contains(xzesqsah)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println(filename + "��������ǰ�󰸺�:" + xzesqsah);
				// "   " + contentList.get(j));
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testSshksfw(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "��") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		} else if (wsssjlModel.getSshksfw() != null) {
			String sshksfw = wsssjlModel.getSshksfw();
			if (ts.judgeNode_1(content, sshksfw)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println(filename + "���߻��߷�Χ:" + sshksfw + "   "
						+ content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testQsjafs(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		ArrayList<String> contentList = xr.getXMLNodelist(inputpath + "\\"
				+ filename, node[1]);
		if (contentList.get(0) == "��") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		} else if (wsssjlModel.getQsjafs() != null) {
			String qsjafs = wsssjlModel.getQsjafs();
			if (contentList.contains(qsjafs)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println(filename + "ǰ��᰸��ʽ:" + qsjafs + "   "
						+ contentList.get(0));
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testQspj(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		ArrayList<String> contentList = xr.getXMLNodelist(inputpath + "\\"
				+ filename, node[1]);
		if (contentList.get(0) == "��") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		} else if (wsssjlModel.getQspj() != null) {
			String qspj = wsssjlModel.getQspj();
			for (int i = 0; i < contentList.size(); i++) {
				if (ts.judgeNode_2(contentList.get(i), qspj)
						|| ts.judgeNode_2(qspj, contentList.get(i))) {
					testModel.setCoNum(testModel.getCoNum() + 1);
					break;
				}
			}
//			 else {
//				System.out.println(filename + "ǰ���о�:" + qspj + "   " + contentList.get(0));
//				// FileUtil.fileCopy(inputpath, filename,
//				// specialpath+"ajdmspecial", filename);
//			}
		}
		return testModel;
	}

	@Override
	public TestModel testXsesqsah(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		ArrayList<String> contentList = xr.getXMLNodelist(inputpath + "\\"
				+ filename, node[1]);
		if (contentList.get(0) == "��") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (wsssjlModel.getXsesqsah() != null) {
			String xsesqsah = wsssjlModel.getXsesqsah();
			if (contentList.contains(xsesqsah)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println(filename + "���¶���ǰ�󰸺�:" + xsesqsah);
				// "   " + contentList.get(j));
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testQscpsj(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "��") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		} else if (wsssjlModel.getQscpsj() != null) {
			String qscpsj = wsssjlModel.getQscpsj();
			if (ts.judgeNode_1(content, qscpsj)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			}else {
				System.out.println(filename + "ǰ�����ʱ��:" + qscpsj + "   "
						+ content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testQsgsjg(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "��") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		} else if (wsssjlModel.getQsgsjg() != null) {
			String ygsjg = wsssjlModel.getQsgsjg();
			if (ts.judgeNode_1(content, ygsjg)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			}else {
				System.out.println(filename + "ǰ���߻���:" + ygsjg + "   "
						+ content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testBzfymc(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		ArrayList<String> contentList = xr.getXMLNodelist(inputpath + "\\"
				+ filename, node[1]);
		if (contentList.get(0) == "��") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (wsssjlModel.getBzfymc() != null) {
			String bzfymc = wsssjlModel.getBzfymc();
			if (contentList.contains(bzfymc)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println(filename + "��׼��Ժ����:" + bzfymc);
				// "   " + contentList.get(j));
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}
		}
		return testModel;
	}
}
