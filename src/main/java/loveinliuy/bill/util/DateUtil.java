package loveinliuy.bill.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;

import java.util.Date;

public class DateUtil {

    /**
     * The Constant DATE_FORMAT.
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * The Constant SIMPLE_DATE_FORMAT.
     */
    public static final String SIMPLE_DATE_FORMAT = "yyyyMMdd";

    /**
     * The Constant DATE_TIME_FORMAT.
     */
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * The Constant SIMPLE_DATE_TIME_FORMAT.
     */
    public static final String SIMPLE_DATE_TIME_FORMAT = "yyyyMMddHHmmss";

    /**
     * The Constant DATE_TIME_MILLS_FORMAT.
     */
    public static final String DATE_TIME_MILLS_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * The Constant SIMPLE_DATE_TIME_MILLS_FORMAT.
     */
    public static final String SIMPLE_DATE_TIME_MILLS_FORMAT = "yyyyMMddHHmmssSSS";

    /**
     * The Constant SUPPORT_PARSERS.
     */
    private static final DateTimeParser[] SUPPORT_PARSERS = new DateTimeParser[]{
            DateTimeFormat.forPattern(DATE_FORMAT).getParser(),
            DateTimeFormat.forPattern(SIMPLE_DATE_FORMAT).getParser(),
            DateTimeFormat.forPattern(DATE_TIME_FORMAT).getParser(),
            DateTimeFormat.forPattern(SIMPLE_DATE_TIME_FORMAT).getParser(),
            DateTimeFormat.forPattern(DATE_TIME_MILLS_FORMAT).getParser(),
            DateTimeFormat.forPattern(SIMPLE_DATE_TIME_MILLS_FORMAT)
                    .getParser()};

    /**
     * The Constant DEFAULT_FORMATTER.
     */
    private static final DateTimeFormatter DEFAULT_FORMATTER = new DateTimeFormatterBuilder()
            .append(null, SUPPORT_PARSERS).toFormatter();

    /**
     * To date string.
     *
     * @param date the date
     * @return the string
     */
    public static String toDateString(Date date) {
        return new DateTime(date).toString(DATE_FORMAT);
    }


    /**
     * 可匹配多重日期字符串.
     *
     * @param dateString 日期字符串
     * @return DateTime对象
     */
    public static DateTime fromString(String dateString) {
        return DEFAULT_FORMATTER.parseDateTime(dateString);
    }

}
