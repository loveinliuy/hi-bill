package loveinliuy.bill.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * description:
 * 用户账单
 *
 * @author zhangshibo  [2017/12/12].
 */
@Data
public class Bill implements Serializable{

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
     * 类目ID
     */
    private String code;

    /**
     * 描述
     */
    private String description;

    /**
     * 花费数目
     */
    private Double cost;

}
