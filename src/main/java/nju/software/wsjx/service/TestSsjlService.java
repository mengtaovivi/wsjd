package nju.software.wsjx.service;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;
import nju.software.wsjx.service.model.TestModel;

import org.xml.sax.SAXException;

public interface TestSsjlService {
	/**
	 * 
	 */
	public TestModel testAy(TestModel testModel, WsssjlModel wsssjlModel,
                            String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 
	 */
	public TestModel testWzay(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 
	 */
	public TestModel testAydm(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 
	 */
	public TestModel testAjly(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 
	 */
	public TestModel testAjsj(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 
	 */
	public TestModel testKtsl(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 
	 */
	public TestModel testKtrq(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 
	 */
	public TestModel testQsah(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 
	 */
	public TestModel testQsfy(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 
	 */
	public TestModel testKtslxx(TestModel testModel, WsssjlModel wsssjlModel,
                                String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 
	 */
	public TestModel testBgkslyy(TestModel testModel, WsssjlModel wsssjlModel,
                                 String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 
	 */
	public TestModel testLarq(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 
	 */
	public TestModel testYsajsycx(TestModel testModel, WsssjlModel wsssjlModel,
                                  String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * ���Լ���ת��ͨ
	 */
	public TestModel testJyzpt(TestModel testModel, WsssjlModel wsssjlModel,
                               String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * ����һ�󰸼���Դ
	 */
	public TestModel testYsajly(TestModel testModel, WsssjlModel wsssjlModel,
                                String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * ������������
	 */
	public TestModel testSlrq(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * ����������֯
	 */
	public TestModel testSpzz(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * ���Զ�������
	 */
	public TestModel testDrsp(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * �������볷������
	 */
	public TestModel testSqcsrq(TestModel testModel, WsssjlModel wsssjlModel,
                                String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * ����ȱϯ����Ϣ
	 */
	public TestModel testQxrxx(TestModel testModel, WsssjlModel wsssjlModel,
                               String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * ���Գ�ͥ����Ϣ
	 */
	public TestModel testCtrxx(TestModel testModel, WsssjlModel wsssjlModel,
                               String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * ���Ա�����Ҫ�쵼��ͥ
	 */
	public TestModel testBgzyldct(TestModel testModel, WsssjlModel wsssjlModel,
                                  String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * ����������Ϊ����
	 */
	public TestModel testXzxwzl(TestModel testModel, WsssjlModel wsssjlModel,
                                String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * ����������Ȩ��Ϊ����
	 */
	public TestModel testXzqqxwzl(TestModel testModel, WsssjlModel wsssjlModel,
                                  String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * ������������
	 */
	public TestModel testQsrq(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * ������������
	 */
	public TestModel testSsxz(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * ������������
	 */
	public TestModel testWzzm(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * ������������
	 */
	public TestModel testZmdm(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * ���Թ��߻���
	 */
	public TestModel testGsjg(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * ���Թ��߰���
	 */
	public TestModel testGsah(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * ���Ը������²��ּ�������
	 */
	public TestModel testMsbfjxsl(TestModel testModel, WsssjlModel wsssjlModel,
                                  String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * ���Լ��Ժ������������
	 */
	public TestModel testJcyjyyqsl(TestModel testModel,
                                   WsssjlModel wsssjlModel, String inputpath, String filename,
                                   String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException;

	public TestModel testQsland(TestModel testModel, WsssjlModel wsssjlModel,
                                String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	public TestModel testQsfyjc(TestModel testModel, WsssjlModel wsssjlModel,
                                String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	public TestModel testQswszl(TestModel testModel, WsssjlModel wsssjlModel,
                                String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	public TestModel testQssj(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	public TestModel testQsajyl(TestModel testModel, WsssjlModel wsssjlModel,
                                String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 
	 */
	public TestModel testXzesqsah(TestModel testModel, WsssjlModel wsssjlModel,
                                  String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	public TestModel testSshksfw(TestModel testModel, WsssjlModel wsssjlModel,
                                 String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	public TestModel testQsjafs(TestModel testModel, WsssjlModel wsssjlModel,
                                String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	public TestModel testQspj(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	public TestModel testXsesqsah(TestModel testModel, WsssjlModel wsssjlModel,
                                  String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	public TestModel testQscpsj(TestModel testModel, WsssjlModel wsssjlModel,
                                String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	public TestModel testQsgsjg(TestModel testModel, WsssjlModel wsssjlModel,
                                String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	public TestModel testBzfymc(TestModel testModel, WsssjlModel wsssjlModel,
                                String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

}
