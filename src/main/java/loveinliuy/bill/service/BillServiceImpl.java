package loveinliuy.bill.service;

import loveinliuy.bill.model.Bill;
import loveinliuy.bill.model.BillStatistic;
import loveinliuy.bill.model.CostType;
import loveinliuy.bill.model.DateRange;
import loveinliuy.bill.model.User;
import loveinliuy.bill.repository.BillRepository;
import loveinliuy.bill.repository.BillRepositoryCustom;
import loveinliuy.bill.util.IdentityUtil;
import loveinliuy.bill.util.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * description:
 *
 * @author zhangshibo  [2017/12/14].
 */
@Service
public class BillServiceImpl implements BillService {

    private static final int PAGE_SIZE = 10;

    @Autowired
    private BillRepository repository;

    @Override
    public List<Bill> getUserRecentBills(User user) {
        return repository.findTop5ByUserId(user.getId(), new Sort(Sort.Direction.DESC, Bill.PROP_NAME_ADD_DATE));
    }


    @Override
    public Map<String, BillStatistic> getUserBillStatisticBetweenDateRange(User user, DateRange range) {
        AggregationResults<Map> agg = repository.userTotalBillBetweenDate(user.getId(), range.startDate(), range.endDate());
        Map<String, BillStatistic> result = new HashMap<>(2);
        for (Map map : agg.getMappedResults()) {
            String type = (String) map.get(Fields.UNDERSCORE_ID);
            Double total = (Double) map.get(BillRepositoryCustom.SUM_GROUP_ALIAS);
            result.put(type, BillStatistic.builder().type(Bill.Type.valueOf(type)).total(total).build());
        }
        if (!result.containsKey(Bill.Type.Income.name())) {
            result.put(Bill.Type.Income.name(), BillStatistic.builder().type(Bill.Type.Income).total(NumberUtils.DOUBLE_ZERO).build());
        }
        if (!result.containsKey(Bill.Type.Expense.name())) {
            result.put(Bill.Type.Expense.name(), BillStatistic.builder().type(Bill.Type.Expense).total(NumberUtils.DOUBLE_ZERO).build());
        }
        return result;
    }

    @Override
    public List<BillStatistic> getUserBillStatisticBetweenDateRangeGroupByCostType(User user, DateRange range) {
        AggregationResults<BillStatistic> agg = repository.userBillBetweenDateRangeGroupByCostType(user.getId(), range.startDate(), range.endDate());
        return agg.getMappedResults();
    }

    @Override
    public Page<Bill> getBillsBetweenDateRange(User user, DateRange range, int page) {
        Sort sort = new Sort(Sort.Direction.DESC, Bill.PROP_NAME_ADD_DATE);
        PageRequest pageRequest = new PageRequest(page, PAGE_SIZE, sort);
        return repository.findAllByUserIdAndDateBetween(user.getId(), range.startDate(), range.endDate(), pageRequest);
    }

    @Override
    public boolean isWriteThatDay(Date date, String description, String id) {
        Bill bill = repository.findByDateAndDescription(date, description);
        return bill != null && !bill.getId().equals(id);
    }


    @Override
    public Bill save(Bill bill) {
        if(StringUtils.isEmpty(bill.getId())) {
            bill.setId(IdentityUtil.uuid());
        }
        bill.setUserId(SessionUtil.getCurrentUser().orElseThrow(IllegalStateException::new).getId());
        bill.setAddDate(new Date());
        repository.save(bill);
        return bill;
    }

    @Override
    public List<Bill> findByCostType(CostType costType) {
        return findByCostTypeId(costType.getId());
    }

    @Override
    public List<Bill> findByCostTypeId(String costTypeId) {
        return repository.findBillsByCostTypeId(costTypeId);
    }

    @Override
    public Bill get(String id) {
        return repository.findOne(id);
    }

    @Override
    public boolean delete(User user, String id) {
        repository.deleteBillByUserIdAndId(user.getId(), id);
        return true;
    }
}
