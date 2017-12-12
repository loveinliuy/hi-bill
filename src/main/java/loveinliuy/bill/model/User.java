package loveinliuy.bill.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import loveinliuy.bill.util.Md5Util;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * description:
 * 用户
 *
 * @author zhangshibo  [2017/12/12].
 */
@Data
@Builder
public class User implements Serializable{

    /**
     * 默认密码
     */
    private static final String DEFAULT_PASSWORD = "123";

    /**
     * 默认加密的密码
     */
    public static final String DEFAULT_ENCRYPTED_PASSWORD = Md5Util.encrypt(DEFAULT_PASSWORD);

    /**
     * 用户ID
     */
    @Id
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
