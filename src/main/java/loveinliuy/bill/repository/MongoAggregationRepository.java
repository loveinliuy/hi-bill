package loveinliuy.bill.repository;

import java.util.Date;

/**
 * description:
 *
 * @author zhangshibo  [2017/12/21].
 */
public interface MongoAggregationRepository {


    /**
     * 根据用户聚合信息
     *
     * @param userId 用户id
     * @param start  开始
     * @param end    结束
     * @return 聚合结果
     */
    Object userTotalBillBetweenDate(String userId, Date start, Date end);
}
