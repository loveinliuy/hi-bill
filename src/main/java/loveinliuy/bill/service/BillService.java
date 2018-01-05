package loveinliuy.bill.service;

import loveinliuy.bill.model.Bill;
import loveinliuy.bill.model.BillStatistic;
import loveinliuy.bill.model.CostType;
import loveinliuy.bill.model.DateRange;
import loveinliuy.bill.model.User;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
    Map<String, BillStatistic> getUserBillStatisticBetweenDateRange(User user, DateRange range);

    /**
     * 获取一个用户在一个日期区间内的账单统计，根据消费类型分组
     *
     * @param user  用户
     * @param range 日期区间
     * @return 账单统计
     */
    List<BillStatistic> getUserBillStatisticBetweenDateRangeGroupByCostType(User user, DateRange range);

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
     * 查找指定账单类型的账单
     *
     * @param costType 账单类型
     * @return 账单列表
     */
    List<Bill> findByCostType(CostType costType);

    /**
     * 查找指定账单类型的账单
     *
     * @param costTypeId 账单类型Id
     * @return 账单列表
     */
    List<Bill> findByCostTypeId(String costTypeId);

    /**
     * 删除指定用户指定id的账单信息
     *
     * @param user 用户
     * @param id   id
     * @return 删除情况
     */
    boolean delete(User user, String id);
}
