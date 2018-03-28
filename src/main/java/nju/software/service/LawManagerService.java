package nju.software.service;

import nju.software.vo.LawItemVO;

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
}
