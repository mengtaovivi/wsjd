package nju.software.wsjx.service;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import nju.software.wsjx.model.wsSegmentationModel.WswsModel;
import nju.software.wsjx.service.model.TestModel;

import org.xml.sax.SAXException;

public interface TestWsService {
	/**
	 * ��������������λ
	 */
	public TestModel testWszzdw(TestModel testModel, WswsModel wswsmodel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Ծ��취Ժ
	 */
	public TestModel testJbfy(TestModel testModel, WswsModel wswsmodel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���԰���
	 */
	public TestModel testAh(TestModel testModel, WswsModel wswsmodel, String inputpath,
                            String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ������������
	 */
	public TestModel testWsmc(TestModel testModel, WswsModel wswsmodel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * �����������
	 */
	public TestModel testLand(TestModel testModel, WswsModel wswsmodel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Է�Ժ����
	 */
	public TestModel testFyjb(TestModel testModel, WswsModel wswsmodel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ��������������ʡ��
	 */
	public TestModel testXzqhProv(TestModel testModel, WswsModel wswsmodel, String inputpath,
                                  String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���������������У�
	 */
	public TestModel testXzqhCity(TestModel testModel, WswsModel wswsmodel, String inputpath,
                                  String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���԰�������
	 */
	public TestModel testAjxz(TestModel testModel, WswsModel wswsmodel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ������������
	 */
	public TestModel testWszl(TestModel testModel, WswsModel wswsmodel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * �������г���
	 */
	public TestModel testSpcx(TestModel testModel, WswsModel wswsmodel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���԰�������
	 */
	public TestModel testAjlx(TestModel testModel, WswsModel wswsmodel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
}
