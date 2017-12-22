package loveinliuy.bill.repository;

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
}
