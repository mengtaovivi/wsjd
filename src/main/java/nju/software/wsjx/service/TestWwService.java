package nju.software.wsjx.service;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import nju.software.wsjx.model.wsSegmentationModel.WswwModel;
import nju.software.wsjx.service.model.TestModel;
import org.xml.sax.SAXException;
public interface TestWwService {
	/**
	 * ����������Ա����
	 */
	public TestModel testSpryxm(TestModel testModel, WswwModel wswwModel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ����������Ա��ɫ
	 */
	public TestModel testSpryjs(TestModel testModel, WswwModel wswwModel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Բ���ʱ��
	 */
	public TestModel testCpsj(TestModel testModel, WswwModel wswwModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Խ᰸���
	 */
	public TestModel testJand(TestModel testModel, WswwModel wswwModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Խ᰸������
	 */
	public TestModel testJanyr(TestModel testModel, WswwModel wswwModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Խ᰸����
	 */
	public TestModel testJany(TestModel testModel, WswwModel wswwModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ���Խ᰸�·�
	 */
	public TestModel testJay(TestModel testModel, WswwModel wswwModel, String inputpath,
                             String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
}
