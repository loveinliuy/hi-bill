package loveinliuy.bill.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BillStatistic {

    private DateRange range;

    private Double income;

    private Double expense;
}
