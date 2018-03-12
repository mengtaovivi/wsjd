package nju.software.wsjx.service;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import nju.software.wsjx.model.wsSegmentationModel.WscpjgModel;
import nju.software.wsjx.service.model.TestModel;
import nju.software.wsjx.service.model.WsfdModel;
import org.xml.sax.SAXException;
public interface TestPjjgService {
	/**
	 * �����о����
	 */
	public TestModel testPjjg(TestModel testModel, WsfdModel wsfdModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Խ᰸��ʽ
	 */
	public TestModel testJafs(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * �����Ƿ񷢻�����
	 */
	public TestModel testSffhcs(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Է�������ԭ��
	 */
	public TestModel testMsesffcsyy(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                                    String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���԰��������
	 */
	public TestModel testAjslf(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Խ᰸��Ľ��
	 */
	public TestModel testJabdje(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;

	/**
	 * ����Ȩ����
	 */
	public TestModel testQlr(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                             String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ����������
	 */
	public TestModel testYwr(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                             String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * �����о�ִ������
	 */
	public TestModel testPjzxqx(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * �����������
	 */
	public TestModel testSsxx(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Գ����˼���
	 */
	public TestModel testCsrjh(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Գ�������
	 */
	public TestModel testCslx(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * �������Ϸѳе���
	 */
	public TestModel testCdr(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                             String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;

	/**
	 * �������Ϸѳе���ʽ
	 */
	public TestModel testCdfs(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * �������Ϸѽ��
	 */
	public TestModel testSsfje(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * �����Ƿ�֧��ԭ����������
	 */
	public TestModel testSfzcygssqq(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                                    String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * �����о����γе���ʽ
	 */
	public TestModel testPjzrcdfs(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                                  String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * �����о����
	 */
	public TestModel testPjje(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * �����о��������
	 */
	public TestModel testPjjelx(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���������Ͻ����
	 */
	public TestModel testGxyy(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
}

