package loveinliuy.bill.repository;

import loveinliuy.bill.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * description:
 *
 * @author zhangshibo  [2017/12/21].
 */
@Repository
public class BillRepositoryImpl implements BillRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public AggregationResults<Map> userTotalBillBetweenDate(String userId, Date start, Date end) {
        AggregationOperation user = new MatchOperation(Criteria.where(Bill.PROP_NAME_USER_ID).is(userId));
        AggregationOperation range = new MatchOperation(Criteria.where(Bill.PROP_NAME_DATE).gte(start).lt(end));
        AggregationOperation group = new GroupOperation(Fields.fields(Bill.PROP_NAME_TYPE)).sum(Bill.PROP_NAME_COST)
                .as(SUM_GROUP_ALIAS);
        TypedAggregation<Bill> aggregation = Aggregation.newAggregation(Bill.class, user, range, group);

        return mongoTemplate.aggregate(aggregation, Map.class);
    }
}
