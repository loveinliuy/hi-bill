package loveinliuy.bill.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * description:
 * 消费类型的统一抽象
 *
 * @author zhangshibo  [2018/1/4].
 */
@Data
public class CostType implements Serializable {

    /**
     * id
     */
    @Id
    private String id;

    /**
     * 账单类型
     */
    private Bill.Type type;

    /**
     * 名称
     */
    private String name;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 排列顺序
     */
    private Integer priority;
}
