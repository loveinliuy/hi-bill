package loveinliuy.bill.util;

import java.util.UUID;

/**
 * description:
 *
 * @author zhangshibo  [2017/12/12].
 */
public class IdentityUtil {

    /**
     * 生成默认的32位不带连字符的uuid
     *
     * @return uuid
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
