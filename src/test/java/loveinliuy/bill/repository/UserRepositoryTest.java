package loveinliuy.bill.repository;

import loveinliuy.bill.model.User;
import loveinliuy.bill.util.IdentityUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * description:
 *
 * @author zhangshibo  [2017/12/12].
 */
public class UserRepositoryTest extends BaseSpringBootTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void findUserByUsernameAndPassword() {
        repository.deleteAll();

        User user = User.builder()
                .id(IdentityUtil.uuid())
                .username("test1")
                .password(User.DEFAULT_ENCRYPTED_PASSWORD)
                .realName("测试1")
                .build();
        repository.save(user);


    }
}