package loveinliuy.bill.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * description:
 * 代码值
 *
 * @author zhangshibo  [2017/12/12].
 */
@Data
@Builder
public class Code implements Serializable{


    /**
     * 消费类型存储的代码类型名称
     */
    public static final String COST_TYPE = "消费类型";

    /**
     * 类型ID
     */
    @Id
    private String id;

    /**
     * 代码名称
     */
    private String typeName;

    /**
     * 代码类型名称
     */
    private String codeName;

    /**
     * 排列顺序
     */
    private Integer priority;


}
