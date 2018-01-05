package loveinliuy.bill.model;

import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;
import org.joda.time.DurationFieldType;

import java.util.Date;


@Data
@Builder
public class DateRange {

    /**
     * 获取给定时间所在月的起始和终止时间。左闭右开使用。
     * 当月1日0点整到次月1日零点整。
     *
     * @param dateTime 给定时间
     * @return 对应时间区间对象
     */
    public static DateRange fullMonth(DateTime dateTime) {
        return DateRange.builder()
                .start(dateTime.withDayOfMonth(1).withTimeAtStartOfDay())
                .end(dateTime.withFieldAdded(DurationFieldType.months(), 1).withDayOfMonth(1).withTimeAtStartOfDay())
                .build();
    }

    private DateTime start;

    private DateTime end;

    public Date startDate() {
        return start.minusMillis(1).toDate();
    }

    public Date endDate() {
        return end.toDate();
    }
}
