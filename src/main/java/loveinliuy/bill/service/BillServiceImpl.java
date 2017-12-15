package loveinliuy.bill.service;

import loveinliuy.bill.model.Bill;
import loveinliuy.bill.repository.BillRepository;
import loveinliuy.bill.util.IdentityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Bill save(Bill bill) {
        bill.setId(IdentityUtil.uuid());
        repository.save(bill);
        return bill;
    }

    @Override
    public Bill get(String id) {
        return repository.findOne(id);
    }
}
