package loveinliuy.bill.util;

import loveinliuy.bill.model.Message;
import org.springframework.ui.Model;

/**
 * description:
 *
 * @author zhangshibo  [2017/12/14].
 */
public class FrontUtil {

    /**
     * 在前台的消息key
     */
    private static String KEY_OF_MESSAGE_FRONT = "message";


    /**
     * 向前台输出成功消息内容
     *
     * @param model   数据模型
     * @param message 消息内容
     */
    public static void success(Model model, String message) {
        message(model, Message.Type.SUCCESS, message);
    }


    /**
     * 向前台输出失败消息
     *
     * @param model   数据模型
     * @param message 消息内容
     */
    public static void fail(Model model, String message) {
        message(model, Message.Type.DANGER, message);
    }

    /**
     * 向前台输出指定类型的消息
     *
     * @param model   数据模型
     * @param type    类型
     * @param message 消息内容
     */
    private static void message(Model model, Message.Type type, String message) {
        model.addAttribute(KEY_OF_MESSAGE_FRONT, Message.builder().type(type).message(message).build());
    }

}
