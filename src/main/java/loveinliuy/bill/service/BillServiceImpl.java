package loveinliuy.bill.service;

import loveinliuy.bill.model.Bill;
import loveinliuy.bill.model.User;
import loveinliuy.bill.repository.BillRepository;
import loveinliuy.bill.util.IdentityUtil;
import loveinliuy.bill.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * description:
 *
 * @author zhangshibo  [2017/12/14].
 */
@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository repository;


    @Override
    public List<Bill> getUserRecentBills(User user) {
        return repository.findTop5ByUserId(user.getId(), new Sort(Sort.Direction.DESC, Bill.PROP_NAME_ADD_DATE));
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
