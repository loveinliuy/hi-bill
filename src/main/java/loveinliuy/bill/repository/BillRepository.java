package loveinliuy.bill.repository;

import loveinliuy.bill.model.Bill;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * description:
 *
 * @author zhangshibo  [2017/12/14].
 */
public interface BillRepository extends MongoRepository<Bill, String>{
}
