package nju.software.wsjx.util;

import java.io.*;
/**
 * ��������Ĺ���
 * ��һ���ļ��н�����ȡ�����Ƶ���һ���ļ���
 */
public class WsfzTool {
	public static void wsCopy(String path1,String file1,String path2,String file2) throws IOException{
        FileInputStream fis = new FileInputStream(path1+"\\"+file1);
        FileOutputStream fos = new FileOutputStream(path2+"\\"+file2);
        int len = 0;
        byte[] buf = new byte[1024];
        while ((len = fis.read(buf)) != -1) {
            fos.write(buf, 0, len);
        }
        fis.close();
        fos.close();
	}
	}
	
