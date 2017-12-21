package loveinliuy.bill.repository;

import loveinliuy.bill.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;

/**
 * description:
 *
 * @author zhangshibo  [2017/12/21].
 */
public class MongoAggregationRepositoryImpl implements MongoAggregationRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Object userTotalBillBetweenDate(String userId, Date start, Date end) {
        AggregationOperation user = new MatchOperation(Criteria.where(Bill.PROP_NAME_USER_ID).is(userId));
        AggregationOperation range = new MatchOperation(Criteria.where(Bill.PROP_NAME_DATE).gte(start).lt(end));
        TypedAggregation<Bill> aggregation = Aggregation.newAggregation(Bill.class, user, range);
        Object o = mongoTemplate.aggregate(aggregation, HashMap.class);
        return o;
    }
}
