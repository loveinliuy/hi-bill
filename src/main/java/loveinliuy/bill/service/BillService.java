package loveinliuy.bill.service;

import loveinliuy.bill.model.Bill;
import loveinliuy.bill.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * description:
 *
 * @author zhangshibo  [2017/12/14].
 */
public interface BillService {

    /**
     * 获取一个用户的近期账单
     *
     * @param user 用户
     * @return 这个用户的近期账单
     */
    List<Bill> getUserRecentBills(User user);

    /**
     * 获取一个用户在一个日期区间内的账单
     *
     * @param user  user
     * @param page  page
     * @param range date range
     * @return 账单结果
     */
    Page<Bill> getBillsBetweenDateRange(User user, Date[] range, int page);

    /**
     * 检查指定的描述是否在当天有重复
     *
     * @param date        指定日期
     * @param description 指定描述
     * @return 重复返回true，反之返回false
     */
    boolean isWriteThatDay(Date date, String description);

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
