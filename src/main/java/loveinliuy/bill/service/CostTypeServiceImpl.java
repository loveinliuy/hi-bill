package loveinliuy.bill.service;

import loveinliuy.bill.model.Bill;
import loveinliuy.bill.model.CostType;
import loveinliuy.bill.model.User;
import loveinliuy.bill.repository.CostTypeRepository;
import loveinliuy.bill.util.IdentityUtil;
import loveinliuy.bill.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description:
 *
 * @author zhangshibo  [2018/1/4].
 */
@Service
public class CostTypeServiceImpl implements CostTypeService {

    @Autowired
    private CostTypeRepository repository;

    @Override
    public CostType save(CostType type) {
        type.setId(IdentityUtil.uuid());
        type.setUserId(SessionUtil.getCurrentUser().orElseThrow(IllegalStateException::new).getId());
        return repository.save(type);
    }

    @Override
    public boolean duplicate(Bill.Type type, String name) {
        User user = SessionUtil.getCurrentUser().orElseThrow(IllegalStateException::new);
        return repository.findCostTypeByUserIdAndTypeAndName(user.getId(), type, name) != null;
    }

    @Override
    public List<CostType> getCostTypesByBillType(User user, Bill.Type... types) {
        return repository.findCostTypesByUserIdAndTypeInOrderByPriorityAsc(user.getId(), types);
    }
}
