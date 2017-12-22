package loveinliuy.bill.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * description:
 *
 * @author zhangshibo  [2017/12/21].
 */
@Configuration
@EnableMongoRepositories(basePackages = {"loveinliuy.bill.repository"})
public class SpringDataMongodbConfig {
}
