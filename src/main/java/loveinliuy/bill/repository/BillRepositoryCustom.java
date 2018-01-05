package loveinliuy.bill.repository;

import loveinliuy.bill.model.BillStatistic;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;

import java.util.Date;
import java.util.Map;

/**
 * description:
 *
 * @author zhangshibo  [2017/12/21].
 */
public interface BillRepositoryCustom {

    String SUM_GROUP_ALIAS = "total";


    /**
     * 根据用户聚合信息
     *
     * @param userId 用户id
     * @param start  开始
     * @param end    结束
     * @return 聚合结果
     */
    AggregationResults<Map> userTotalBillBetweenDate(String userId, Date start, Date end);

    /**
     * 获取用户在一个时间区间范围内的账单，并按照消费类型分组
     *
     * @param userId 用户
     * @param start  时间区间开始
     * @param end    时间区间结束
     * @return 账单结果
     */
    AggregationResults<BillStatistic> userBillBetweenDateRangeGroupByCostType(String userId, Date start, Date end);
}
