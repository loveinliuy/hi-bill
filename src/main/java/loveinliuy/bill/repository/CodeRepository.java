package loveinliuy.bill.repository;

import loveinliuy.bill.model.Code;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * description:
 *
 * @author zhangshibo  [2017/12/14].
 */
public interface CodeRepository extends MongoRepository<Code, String> {

    /**
     * 根据类型查找
     *
     * @param typeName 代码类型
     * @return 单值代码列表
     */
    List<Code> findByTypeName(String typeName);

}
