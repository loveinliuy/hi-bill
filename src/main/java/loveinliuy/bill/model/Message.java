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
        SUCCESS,
        /**
         * 消息
         */
        INFO,
        /**
         * 警告
         */
        WARN,
        /**
         * 失败，危险操作
         */
        DANGER
    }

    public String toParamString() {
        return "redirect:/message?" +
                "type=" + type.toString().toLowerCase() +
                "&" +
                "message=" + message;
    }
}
