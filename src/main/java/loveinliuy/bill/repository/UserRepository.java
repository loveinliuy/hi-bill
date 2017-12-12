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
     * @param password 编码后的密码
     * @return 对应用户
     */
    User findUserByUsernameAndPassword(String username, String password);
}
