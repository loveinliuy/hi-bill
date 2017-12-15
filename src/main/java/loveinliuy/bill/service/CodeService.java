package loveinliuy.bill.service;

import loveinliuy.bill.model.Code;

import java.util.List;

/**
 * description:
 *
 * @author zhangshibo  [2017/12/15].
 */
public interface CodeService {

    /**
     * 获取所有消费类型列表
     *
     * @return 消费类型列表
     */
    List<Code> getAllCostType();
}
