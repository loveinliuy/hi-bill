package loveinliuy.bill.service;

import loveinliuy.bill.model.Bill;
import loveinliuy.bill.model.BillStatistic;
import loveinliuy.bill.model.CostType;
import loveinliuy.bill.model.DateRange;
import loveinliuy.bill.model.User;
import org.springframework.data.domain.Page;

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
     * 获取一个用户在一个日期区间内的账单统计
     *
     * @param user  用户
     * @param range 日期区间
     * @return 账单统计结果
     */
    BillStatistic getUserBillStatisticBetweenDateRange(User user, DateRange range);

    /**
     * 获取一个用户在一个日期区间内的账单
     *
     * @param user  user
     * @param page  page
     * @param range date range
     * @return 账单结果
     */
    Page<Bill> getBillsBetweenDateRange(User user, DateRange range, int page);

    /**
     * 检查指定的描述是否在当天有重复
     *
     * @param date        指定日期
     * @param description 指定描述
     * @return 重复返回true，反之返回false
     */
    boolean isWriteThatDay(Date date, String description);

    /**
     * 获取账单类型对应的消费类型
     *
     * @param user 当前用户
     * @param types type
     * @return 消费类型列表
     */
    List<CostType> getCostTypesByBillType(User user, Bill.Type... types);

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

    /**
     * 删除指定用户指定id的账单信息
     *
     * @param user 用户
     * @param id   id
     * @return 删除情况
     */
    boolean delete(User user, String id);
}
