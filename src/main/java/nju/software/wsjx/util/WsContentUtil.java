package nju.software.wsjx.util;

/**
 * �������ݹ�����
 * Created by Hufk on 2017/09/02.
 */
public class WsContentUtil {
    /**
     * ��ȡ.doc��.rtf�ļ�����
     * @param fileFullPath �ļ��ľ���·��
     * @return �ļ�����
     */
    public static String getContent(String fileFullPath) {
        if (getSuffix(fileFullPath).equals(".txt")){
            return "";
        }
        String content = "";
        try {
            content = POIUtil.getContent(FileUtil.getContent(fileFullPath), getSuffix(fileFullPath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //һЩ�������ݵ����齫������
        String tempContent = content.replaceAll("\\s","");
        if (tempContent.length()>10){
            String segment = tempContent.substring(0,10);
            if (segment.contains("ѯ�ʱ�¼") || segment.contains("��ͥ�����¼") || segment.contains("������") || segment.contains("��ͥ��¼") || segment.contains("˵��") || segment.contains("���˵��")){
                return "";
            }
        }
        return StringUtil.convertEntireSpace(content);
    }

    /**
     * ��ȡ�ļ����ĺ�׺
     * @param fileFullPath �ļ��ľ���·��
     * @return ���ش���ĺ�׺��
     */
    public static String getSuffix(String fileFullPath) {
        String suffix = "";
        int index = fileFullPath.lastIndexOf(".");
        suffix = fileFullPath.substring(index);
        return suffix;
    }
}
