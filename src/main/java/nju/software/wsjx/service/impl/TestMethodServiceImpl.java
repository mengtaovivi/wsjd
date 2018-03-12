package nju.software.wsjx.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;





import nju.software.wsjx.service.TestMethodService;
import nju.software.wsjx.util.FcUtil;
import nju.software.wsjx.util.FileUtil;
import nju.software.wsjx.util.XMLReader;

public class TestMethodServiceImpl implements TestMethodService {

	@Override
	public boolean judgeNode_1(String content1,String content2) {
		if(content1.equals(content2)){
			return true;
		}
		return false;
	}
	@Override
	public boolean judgeNode_2(String content1,String content2) {
		if(content1.contains(content2)){
			return true;
		}
		return false;
	}
	@Override
	public boolean judgeNode_3(String content1, String content2) {
		if(content1==null||content2==null)
			return false;
		if(getSimilarDegree(content1,content2)>0.3){
			return true;
		}
		return false;
	}
    public static double getSimilarDegree(String str1, String str2)  
    {  
       //���������ռ�ģ�ͣ�ʹ��mapʵ�֣�����Ϊ���ֵΪ����Ϊ2�����飬����Ŷ�Ӧ�������ַ����еĳ��ִ���  
        Map<String, int[]> vectorSpace = new HashMap<String, int[]>();  
        int[] itemCountArray = null;//Ϊ�˱���Ƶ�������ֲ����������Խ�itemCountArray�����ڴ�  
        if(str1.contains("��"))
        str1=str1.replaceAll("��", "");
        if(str1.contains(" "))
        str1=str1.replaceAll(" ", "");
        if(str2.contains("��"))
        str2=str2.replaceAll("��", "");
        if(str2.contains(" "))
        str2=str2.replaceAll(" ", "");
          List<String> temp1=FcUtil.getWholeToken(str1);
          String tmp1="";
          for(int i=0;i<temp1.size();i++){
       	   tmp1+=temp1.get(i)+",";
          }
        //�Կո�Ϊ�ָ������ֽ��ַ���  
        String strArray[] = tmp1.split(",");  
        for(int i=0; i<strArray.length; ++i)  
        {  
            if(vectorSpace.containsKey(strArray[i]))  
                ++(vectorSpace.get(strArray[i])[0]);  
            else  
            {  
                itemCountArray = new int[2];  
                itemCountArray[0] = 1;  
                itemCountArray[1] = 0;  
                vectorSpace.put(strArray[i], itemCountArray);  
            }  
        }  
        List<String> temp2=FcUtil.getWholeToken(str2);
        String tmp2="";
        for(int i=0;i<temp2.size();i++){
     	   tmp2+=temp2.get(i)+",";
        }
        strArray = tmp2.split(",");  
        for(int i=0; i<strArray.length; ++i)  
        {  
            if(vectorSpace.containsKey(strArray[i]))  
                ++(vectorSpace.get(strArray[i])[1]);  
            else  
            {  
                itemCountArray = new int[2];  
                itemCountArray[0] = 0;  
                itemCountArray[1] = 1;  
                vectorSpace.put(strArray[i], itemCountArray);  
            }  
        }  
        double result=Ou(vectorSpace);
 return result;
    }  
    public static double Cos(Map<String, int[]> vectorSpace){
        //�������ƶ�  
        double vector1Modulo = 0.00;//����1��ģ  
        double vector2Modulo = 0.00;//����2��ģ  
        double vectorProduct = 0.00; //������  
        Iterator iter = vectorSpace.entrySet().iterator();  
        int[] itemCountArray = null;
        while(iter.hasNext())  
        {  
            Map.Entry entry = (Map.Entry)iter.next();  
            itemCountArray = (int[])entry.getValue();  
              
            vector1Modulo += itemCountArray[0]*itemCountArray[0];  
            vector2Modulo += itemCountArray[1]*itemCountArray[1];  
              
            vectorProduct += itemCountArray[0]*itemCountArray[1];  
        }  
          
        vector1Modulo = Math.sqrt(vector1Modulo);  
        vector2Modulo = Math.sqrt(vector2Modulo);  
          
        //�������ƶ�  
       return (vectorProduct/(vector1Modulo*vector2Modulo));  
   	 
    }
    public static double Ou(Map<String, int[]> vectorSpace){
        //�������ƶ�  
        double vector1Modulo = 0.00;//����1��ģ  
        double vector2Modulo = 0.00;//����2��ģ  
        double vectorProduct = 0.00; //������  
        Iterator iter = vectorSpace.entrySet().iterator();  
        int[] itemCountArray = null;
        while(iter.hasNext())  
        {  
            Map.Entry entry = (Map.Entry)iter.next();  
            itemCountArray = (int[])entry.getValue();  
              
            vector1Modulo += (itemCountArray[0]-itemCountArray[1])*(itemCountArray[0]-itemCountArray[1]);  

        }  
        //�������ƶ�  
       return (1/(1+Math.sqrt(vector1Modulo)));  
   	 
    }
	@Override
	public boolean judgeNode_4(ArrayList<String> contentList1,
			ArrayList<String> contentList2) {
		if(contentList1.containsAll(contentList2) && contentList2.containsAll(contentList1)){
			return true;
		}else{
			return false;
		}
	}
}
