package loveinliuy.bill.model;

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
public class Code implements Serializable{

    /**
     * 类型ID
     */
    @Id
    private String id;

    /**
     * 代码类型名称
     */
    private String codeName;

    /**
     * 代码名称
     */
    private String typeName;

    /**
     * 排列顺序
     */
    private Integer priority;


}
