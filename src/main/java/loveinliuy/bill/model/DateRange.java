package loveinliuy.bill.model;

import com.google.common.base.Preconditions;
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
     * 每月15号统计
     * @param dateTime 给定时间
     * @return 对应时间区间对象
     */
    public static DateRange fullMonth(DateTime dateTime) {
        return fullMonth(dateTime, 15);
    }

    /**
     * 获取给定时间所在月的开始和终止时间，根据传入date，比如date为10，那么获取
     * 这个月10号到下个月10号的时间区间。
     *
     * @param dateTime 给定时间
     * @param date 传入日期
     * @return 时间区间
     */
    public static DateRange fullMonth(DateTime dateTime, int date) {
        DateTime.Property p = dateTime.dayOfMonth();
        Preconditions.checkArgument(p.getMinimumValue() <= date && p.getMaximumValue() >= date, "日期不在指定区间");
        return DateRange.builder()
                .start(dateTime.withDayOfMonth(date).withTimeAtStartOfDay())
                .end(dateTime.withFieldAdded(DurationFieldType.months(), 1).withDayOfMonth(date).withTimeAtStartOfDay())
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
