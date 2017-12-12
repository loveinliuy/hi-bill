package loveinliuy.bill.repository;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * description:
 *
 * @author zhangshibo  [2017/12/12].
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class )
public abstract class BaseSpringBootTest {

    /**
     * 子类使用的logger
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());


}
