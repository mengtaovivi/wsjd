package nju.software.wsjx.util;

import java.util.HashMap;

public class NumberTool {
	 //����λ  
    public static String[] chnNumChar = {"��","һ","��","��","��","��","��","��","��","��"};  
    public static char[] chnNumChinese = {'��','һ','��','��','��','��','��','��','��','��'};  
    //��Ȩλ  
    public static String[] chnUnitSection = {"","��","��","����"};    
    //Ȩλ  
    public static String[] chnUnitChar = {"","ʮ","��","ǧ"};  
    public static HashMap intList = new HashMap();  
    static{  
        for(int i=0;i<chnNumChar.length;i++){  
            intList.put(chnNumChinese[i], i);  
        }  
          
        intList.put('ʮ',10);  
        intList.put('��',100);  
        intList.put('ǧ', 1000);  
    }  
}
