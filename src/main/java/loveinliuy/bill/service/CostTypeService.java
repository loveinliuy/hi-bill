package loveinliuy.bill.service;

import loveinliuy.bill.model.Bill;
import loveinliuy.bill.model.CostType;
import loveinliuy.bill.model.User;

import java.util.List;

/**
 * description:
 * 消费类型service
 *
 * @author zhangshibo  [2018/1/4].
 */
public interface CostTypeService {

    /*
     * 消费类型不支持删除，只支持增加和查看
     */

    /**
     * 保存type
     *
     * @param type type
     * @return 保存结果
     */
    CostType save(CostType type);

    /**
     * 检查是否有重复
     *
     * @param type 账单类型
     * @param name 消费类型名称
     * @return 重复返回true；不重复返回false
     */
    boolean duplicate(Bill.Type type, String name);


    /**
     * 获取账单类型对应的消费类型
     *
     * @param user  当前用户
     * @param types type
     * @return 消费类型列表
     */
    List<CostType> getCostTypesByBillType(User user, Bill.Type... types);

    /**
     * 删除
     *
     * @param id id
     * @return 是否删除成功
     */
    boolean delete(String id);


}
