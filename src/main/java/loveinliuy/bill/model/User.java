package loveinliuy.bill.model;

import lombok.Getter;
import lombok.Setter;

/**
 * description:
 * 用户
 *
 * @author zhangshibo  [2017/12/12].
 */
@Getter
@Setter
public class User {

    /**
     * 用户ID
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户昵称
     */
    private String realName;

    /**
     * 密码
     */
    private String password;
}
