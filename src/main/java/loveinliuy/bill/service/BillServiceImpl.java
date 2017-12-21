package loveinliuy.bill.service;

import loveinliuy.bill.model.Bill;
import loveinliuy.bill.model.BillStatistic;
import loveinliuy.bill.model.DateRange;
import loveinliuy.bill.model.User;
import loveinliuy.bill.repository.BillRepository;
import loveinliuy.bill.repository.BillRepositoryImpl;
import loveinliuy.bill.util.IdentityUtil;
import loveinliuy.bill.util.SessionUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public BillStatistic getUserBillStatisticBetweenDateRange(User user, DateRange range) {
        AggregationResults<Map> agg = repository.userTotalBillBetweenDate(user.getId(), range.startDate(), range.endDate());
        List<Map> res = agg.getMappedResults();
        BillStatistic statistic = BillStatistic.builder().range(range).income(Double.NaN).expense(Double.NaN).build();
        if (res == null || res.isEmpty()) {
            return statistic;
        }
        for (Map m : res) {
            Object key = m.get(Fields.UNDERSCORE_ID);
            Double money = Double.parseDouble(String.valueOf(m.get(BillRepository.SUM_GROUP_ALIAS)));
            if (Bill.Type.Income.name().equals(key)) {
                statistic.setIncome(money);
            } else if (Bill.Type.Expense.name().equals(key)) {
                statistic.setExpense(money);
            } else {
                throw new IllegalStateException("error key: " + key);
            }
        }
        return statistic;
    }

    @Override
    public Page<Bill> getBillsBetweenDateRange(User user, DateRange range, int page) {
        Sort sort = new Sort(Sort.Direction.DESC, Bill.PROP_NAME_ADD_DATE);
        PageRequest pageRequest = new PageRequest(page, PAGE_SIZE, sort);
        return repository.findAllByUserIdAndDateBetween(user.getId(), range.startDate(), range.endDate(), pageRequest);
    }

    @Override
    public boolean isWriteThatDay(Date date, String description) {
        Bill bill = repository.findByDateAndDescription(date, description);
        return Objects.nonNull(bill);
    }

    @Override
    public Bill save(Bill bill) {
        bill.setId(IdentityUtil.uuid());
        bill.setUserId(SessionUtil.getCurrentUser().orElseThrow(IllegalStateException::new).getId());
        bill.setAddDate(new Date());
        repository.save(bill);
        return bill;
    }

    @Override
    public Bill get(String id) {
        return repository.findOne(id);
    }
}
