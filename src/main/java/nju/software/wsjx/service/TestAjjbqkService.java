package nju.software.wsjx.service;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import nju.software.wsjx.model.wsSegmentationModel.WsajjbqkModel;
import nju.software.wsjx.service.model.TestModel;



import org.xml.sax.SAXException;

public interface TestAjjbqkService {
	/**
	 * ����ǰ�����
	 */
	public TestModel testQsdl(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ա������
	 */
	public TestModel testBsdl(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ����ǰ���о���
	 */
	public TestModel testQspjd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ����ǰ��ԭ���߳ƶ�
	 */
	public TestModel testQsygscd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                                 String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ����ǰ�󱻸��ƶ�
	 */
	public TestModel testQsbgbcd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                                 String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ����ǰ�����߳ƶ�
	 */
	public TestModel testQsfsscd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                                 String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ����ǰ��֤�ݶ�
	 */
	public TestModel testQszjd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;

	/**
	 * �����������߳ƶ�
	 */
	public TestModel testSsrscd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ա������˱�ƶ�
	 */
	public TestModel testBssrbcd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                                 String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ա�������������
	 */
	public TestModel testBsdsryjd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                                  String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;

	/**
	 * ���Ա��������
	 */
	public TestModel testBssld(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ա���֤�ݶ�
	 */
	public TestModel testBszjd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	
	/**
	 * ����ǰ�������
	 */
	public TestModel testQssld(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ����ԭ���߳ƶ�
	 */
	public TestModel testYgscd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ա����ƶ�
	 */
	public TestModel testBgbcd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Բ�����ʵ��
	 */
	public TestModel testCmssd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ����֤�ݶ�
	 */
	public TestModel testZjd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                             String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ե����������
	 */
	public TestModel testDsryjd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Է����߳ƶ�
	 */
	public TestModel testFsscd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Է��߱�ƶ�
	 */
	public TestModel testFsbcd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * �����������������
	 */
	public TestModel testXzsszyd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                                 String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ����ָ�ض���
	 */
	public TestModel testZkdl(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ա绤����
	 */
	public TestModel testBhdl(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ը����������������
	 */
	public TestModel testFdmsssqqd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                                   String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * �������±��������
	 */
	public TestModel testXsbssld(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                                 String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ����ָ����ʵ
	 */
	public TestModel testZkss(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ����ָ��֤��
	 */
	public TestModel testZkzj(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ����ָ�����
	 */
	public TestModel testZkyj(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ա����˱��
	 */
	public TestModel testBgrbc(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ա绤�˱绤
	 */
	public TestModel testBhrbh(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ��������ǰ�������
	 */
	public TestModel testXsqssld(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                                 String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * �����������߱绤���
	 */
	public TestModel testSsssbhyj(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                                  String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Թ��߻��س�ͥ���
	 */
	public TestModel testGsjgctyj(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                                  String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;




}
