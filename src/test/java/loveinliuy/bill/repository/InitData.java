package loveinliuy.bill.repository;

import loveinliuy.bill.model.Code;
import loveinliuy.bill.model.User;
import loveinliuy.bill.util.IdentityUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * description:
 *
 * @author zhangshibo  [2017/12/12].
 */
//@Ignore
public class InitData extends BaseSpringBootTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CodeRepository codeRepository;

    @Test
    public void userData() {
        userRepository.deleteAll();

        User user = User.builder()
                .id(IdentityUtil.uuid())
                .username("test")
                .password(User.DEFAULT_ENCRYPTED_PASSWORD)
                .realName("测试1")
                .build();

        userRepository.save(user);

    }

    @Test
    public void codeData(){
        codeRepository.deleteAll();

        Code code = Code.builder()
                .id("-1")
                .codeName("不好分")
                .typeName(Code.COST_TYPE)
                .priority(Integer.MAX_VALUE)
                .build();

        codeRepository.save(code);
    }
}