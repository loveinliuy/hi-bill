package loveinliuy.bill.repository;

import loveinliuy.bill.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * description:
 *
 * @author zhangshibo  [2017/12/12].
 */
public interface UserRepository extends MongoRepository<User, String> {


    /**
     * 根据用户名和编码后的面获取用户
     *
     * @param username 用户名
     * @return 对应用户
     */
    User findUserByUsername(String username);


}
