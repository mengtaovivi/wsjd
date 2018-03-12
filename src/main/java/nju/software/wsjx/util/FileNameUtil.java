package nju.software.wsjx.util;

/**
 * Created by Hufk on 2017/07/26.
 */
public class FileNameUtil {
    /**
     * ����ļ���չ��
     * @param fileName ����չ�����ļ�ȫ��
     * @return �ļ���չ��
     */
    public static String getExt(String fileName){
        String ext = null;
        int index = fileName.lastIndexOf(".");
        ext = fileName.substring(index+1);
        return ext;
    }

    /**
     * ��ò�����չ�����ļ���
     * @param fileName ����չ�����ļ�ȫ��
     * @return ������չ�����ļ���
     */
    public static String getFileName(String fileName){
        String filename = null;
        int index = fileName.lastIndexOf(".");
        filename = fileName.substring(0,index);
        return filename;
    }

    /**
     * ��װ�ļ���ȫ��
     * @param fileName �ļ���
     * @param AH ����
     * @return ����׺���ļ�ȫ��
     */
    public static String getFullName(String fileName,String AH,int type){
        return AH + "-" + type + "." + getExt(fileName);
    }

    /**
     * ��װ�ļ�ȫ��
     * @param fileName
     * @param ah
     * @param ysah
     * @return
     */
    public static String getFullName(String fileName, String ah, String ysah){
        return ah + "-" + ysah + "." + getExt(fileName);
    }

    //public static String parse
}
