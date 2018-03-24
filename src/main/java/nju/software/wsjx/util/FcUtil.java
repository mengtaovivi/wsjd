package nju.software.wsjx.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;
/**
 * �ִʹ��ߣ���ȡĳ���ַ��������зִ�
 * @author lr12
 *
 */
public class FcUtil {
	public static List<String> getTokens(String keyword) 
		{
			// TODO Auto-generated method stub
			List<String> tokens = new ArrayList<String>();
			if (StringUtil.isBlank(keyword)) {
				return tokens;
			}
			String text = keyword;
			// �����ִʶ���
			// �����ִ�����
			try {

				Analyzer anal = Constant.Analyzer;
				StringReader reader = new StringReader(text);
				// �ִ�
				TokenStream ts = anal.tokenStream("", reader);
				ts.reset();
				CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);
				while (ts.incrementToken()) {
					tokens.add(term.toString());
				}
				reader.close();
				ts.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				tokens.clear();
				tokens.add(keyword);
			}
			return tokens;
	}
	public static List<String> getWholeToken(String keyword){
		List<String> tokens = new ArrayList<String>();
		if (StringUtil.isBlank(keyword)) {
			return tokens;
		}
		String text = keyword;
		// �����ִʶ���

		// �����ִ�����

		
		try {

			IKAnalyzer ikAnalyzer=new IKAnalyzer();
			ikAnalyzer.setUseSmart(true);
			
			StringReader reader = new StringReader(text);
			// �ִ�
			TokenStream ts = ikAnalyzer.tokenStream("", reader);
			ts.reset();
			CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);

			while (ts.incrementToken()) {
				
				tokens.add(term.toString());
				//System.out.print(term.toString() + "|");
			}
			reader.close();
			ts.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			tokens.clear();
			tokens.add(keyword);
		}

		return tokens;
	}
	public static void main(String[] args){
		ArrayList<String> contentlist = new ArrayList<String>();
		String rexCh="[һ�����������߰˾�ʮ]";
		String rexNu="\\d{1}";
		Pattern patternCh=Pattern.compile(rexCh);
		Pattern patternNu=Pattern.compile(rexNu);
		String content="��3829451��ͼ���̱꣨����";
		if(content.length()>=4)
			content=content.substring(0,4);
		Matcher matcherCh=patternCh.matcher(content);
		Matcher matcherNu=patternNu.matcher(content);
		int num=0;
		while(matcherNu.find()){
			num++;
		}
System.out.println(num);
	
		String str="������ԭ������˵Ĺ�ͬί�д������չ���";
		String[] st=str.split("��");
		System.out.println(st.length);
		for(int i=0;i<st.length;i++)
		System.out.println(st[i]);
		System.out.println(getTokens("ԭ���չ����Ա����Ѿ�����֧������Ϊ��"));
//		String str="ԭ����Ө�����߱��棩��Ů��1971��10��18�ճ��������壬���Ź������б������иɲ���"
//				+ "ס����к�������ţ�ǵ����Ͻ�ɽ·����������20-2-401��������ݺ���130705197110180964��";
//		String str1="ԭ����Ө�����߱��棩��Ů��1971��10��18�ճ�����"+str;
//		String str2="ԭ����Ө�����߱���";
//		String[] jhsplit=str1.split("��");
//		for(int i=0;i<jhsplit.length;i++){
//			String content=jhsplit[i];
//			String []dhsplit=content.split("��");
//			for(int j=0;j<dhsplit.length;j++){
//				if(dhsplit[j].length()>0){
//					contentlist.add(dhsplit[j]);
//				}
//			}
//		}
//		String str="ԭ������б������󡶵��ߵ硷���������޹�˾";
//		String str1=null;
//		if(str.indexOf("���")!=-1){
//			ArrayList<String> zjxx=(ArrayList<String>) FcUtil.getWholeToken(str);
//			  Pattern pattern = Pattern.compile("\\d"); 
//			  for(int k=0;k<zjxx.size();k++){
//				  int count=0;
//				   Matcher match = pattern.matcher(zjxx.get(k));
//				   while(match.find()) count++;   
//				   if(count>=0) {
//					   str1=zjxx.get(k);
//
//				   }
//
//			  }
//		}

//System.out.println(str.substring(str.indexOf("��"),str.indexOf("��")));
        }
	
}
