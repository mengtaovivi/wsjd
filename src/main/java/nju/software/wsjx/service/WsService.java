package nju.software.wsjx.service;


import java.util.List;



import nju.software.wsjx.service.model.FzModel;


public interface WsService {
	
	
	
	
	/**
	 * ������ƪ�ĵ������ƶ�
	 * 
	 */
     public double getXsd(String word1, String word2);
     
     /**
      * ʹ������ֵ���������ƶ�
      * @param word1
      * @param word2
      * @return
      */
     public double calculateXsd(String word1, String word2);

     /**
      * �����㷨 
      * @param words ���������
      * @param filename �����ļ���
      * @return
      */
     public List<FzModel> fz(List<String> words, List<String> filename);
     
}

