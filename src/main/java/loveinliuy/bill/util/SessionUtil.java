package loveinliuy.bill.util;

import loveinliuy.bill.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * description:
 *
 * @author zhangshibo  [2017/12/18].
 */
public class SessionUtil {

    public static Optional<User> getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        if (auth == null) {
            return Optional.empty();
        }
        Object user = auth.getPrincipal();
        if (user instanceof User) {
            return Optional.of((User) user);
        } else {
            return Optional.empty();
        }
    }

}
