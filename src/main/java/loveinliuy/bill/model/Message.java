package loveinliuy.bill.model;

import lombok.Builder;
import lombok.Data;

/**
 * description:
 *
 * @author zhangshibo  [2017/12/14].
 */
@Data
@Builder
public class Message {

    private Type type;

    private String message;


    public enum Type {
        /**
         * 成功
         */
        OK,
        /**
         * 失败
         */
        ERROR
    }
}
