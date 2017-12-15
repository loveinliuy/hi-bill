package loveinliuy.bill.model;

import lombok.Builder;
import lombok.Data;
import loveinliuy.bill.util.Md5Util;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

/**
 * description:
 * 用户
 *
 * @author zhangshibo  [2017/12/12].
 */
@Data
@Builder
public class User implements UserDetails, Serializable {

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
