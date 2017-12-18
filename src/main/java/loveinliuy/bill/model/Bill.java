package loveinliuy.bill.model;

import lombok.Data;
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
    private String type;

    /**
     * 描述
     */
    private String description;

    /**
     * 花费数目
     */
    private Double cost;


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;

    private Date addDate;
}
