package loveinliuy.bill.model;

import lombok.Data;
import loveinliuy.bill.util.DateUtil;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * description:
 * 用户账单
 *
 * @author zhangshibo  [2017/12/12].
 */
@Data
public class Bill implements Serializable {

    /**
     * 用户ID
     */
    public static final String PROP_NAME_USER_ID = "userId";

    /**
     * 属性名称date
     */
    public static final String PROP_NAME_DATE = "date";

    /**
     * 字段名称：花费
     */
    public static final String PROP_NAME_COST = "cost";

    public static final String PROP_NAME_COST_TYPE_ID = "costTypeId";

    public static final String PROP_NAME_COST_TYPE = "costType";

    /**
     * 类型
     */
    public static final String PROP_NAME_TYPE = "type";
    /**
     * 添加时间
     */
    public static final String PROP_NAME_ADD_DATE = "addDate";

    /**
     * 账单ID
     */
    @Id
    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 类型
     */
    private Type type;

    /**
     * 消费类型，存消费类型id
     */
    private String costTypeId;

    /**
     * 消费类型，只存消费类型名称
     */
    private String costType;

    /**
     * 描述
     */
    private String description;

    /**
     * 花费数目
     */
    private Double cost;


    @DateTimeFormat(pattern = DateUtil.DATE_FORMAT)
    private Date date;

    private Date addDate;

    public enum Type {
        /**
         * 收入
         */
        Income("收入"),
        /**
         * 支出
         */
        Expense("支出");

        String description;

        Type(String desc) {
            description = desc;
        }

        @Override
        public String toString() {
            return description;
        }
    }
}
