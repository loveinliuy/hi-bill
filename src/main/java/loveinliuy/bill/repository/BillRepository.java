package loveinliuy.bill.repository;

import loveinliuy.bill.model.Bill;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * description:
 *
 * @author zhangshibo  [2017/12/14].
 */
public interface BillRepository extends MongoRepository<Bill, String> {

    /**
     * 根据用户ID获得最新内容
     *
     * @param userId 用户ID
     * @param sort   排序方式
     * @return 最新账单
     */
    List<Bill> findTop5ByUserId(String userId, Sort sort);
}
