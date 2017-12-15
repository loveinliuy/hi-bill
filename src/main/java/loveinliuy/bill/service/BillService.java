package loveinliuy.bill.service;

import loveinliuy.bill.model.Bill;

/**
 * description:
 *
 * @author zhangshibo  [2017/12/14].
 */
public interface BillService {

    /**
     * 保存一个账单
     *
     * @param bill 账单
     * @return 保存的账单
     */
    Bill save(Bill bill);

    /**
     * 根据账单ID获取账单详情
     *
     * @param id 账单ID
     * @return 账单详情
     */
    Bill get(String id);
}
