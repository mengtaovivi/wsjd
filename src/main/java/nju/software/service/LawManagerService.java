package nju.software.service;

import nju.software.preProcess.LabeledSentenceProcess;
import nju.software.vo.LawItemVO;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by away on 2018/3/28.
 */
public interface LawManagerService {

    /**
     *
     * @param lawItemVOS ����Ϊһϵ�з����Լ����еķ���, LawItemVO �е� lawList ������ map ���,
     *                   key ΪҪ�õķ���, value Ϊ ""
     * @return �� value ��ɷ�������
     */
    List<LawItemVO> getLaw(List<LawItemVO> lawItemVOS);

    /**
     *
     * @param content ����Ϊ����İ����������
     * @param labeledSentenceProcess ����Ϊ�Ѿ����غõ�ģ��
     * @return ���ΪԤ���һ�鷨��
     */
    List<LawItemVO> lawRecommend(String content, LabeledSentenceProcess labeledSentenceProcess) throws UnsupportedEncodingException;

    List<LawItemVO> deduplication(List<LawItemVO> lawItemVOList, List<LawItemVO> recommendlawItemVOList);
}
