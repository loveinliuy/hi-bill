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

    private DateRange range;

    private Double income;

    private Double expense;
}
