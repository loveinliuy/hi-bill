package loveinliuy.bill.model;

import lombok.Builder;
import lombok.Data;

/**
 * 账单统计结果
 *
 * @author zhangshibo
 */
@Data
@Builder
public class BillStatistic {

    /**
     * 账单类型
     */
    private Bill.Type type;

    /**
     * 消费类型Id
     */
    private String costTypeId;

    /**
     * 消费类型
     */
    private String costType;

    /**
     * 合计
     */
    private Double total;

}
