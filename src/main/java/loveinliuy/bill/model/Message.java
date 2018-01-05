package loveinliuy.bill.model;

import lombok.Builder;
import lombok.Data;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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

    private String url;

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
        try {
            return "redirect:/message?" +
                    "type=" + type.toString().toLowerCase() +
                    "&" +
                    "message=" + URLEncoder.encode(message, "utf-8") +
                    "&" +
                    "url=" + url;
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }
}
