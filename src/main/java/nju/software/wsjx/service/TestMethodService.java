package nju.software.wsjx.service;

import java.util.ArrayList;

/**
 * ������Է���Service
 * @author super
 *
 */
public interface TestMethodService {
	/**
	 * ���Խڵ㷽��1
	 * ���ڵ���ȫ��ͬ����true
	 * @param 
	 * @return
	 */
	public boolean judgeNode_1(String content1, String content2) ;
	/**
	 * ���Խڵ㷽��2
	 * ����content1����content2����true
	 * @param 
	 * @return
	 */
	public boolean judgeNode_2(String content1, String content2) ;
	/**
	 * ���Խڵ㷽��3
	 * ����content1��content2���Ʒ���true
	 * @param 
	 * @return
	 */
	public boolean judgeNode_3(String content1, String content2) ;
	/**
	 * ���Խڵ㷽��4
	 * contentList1��contentList2��ͬ����true
	 * @param contentList1
	 * @param contentList2
	 * @return
	 */
	public boolean judgeNode_4(ArrayList<String> contentList1, ArrayList<String> contentList2);
}
